package com.example.bmw.vehicle.services.backendprocessing.business.configuration.boundary;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import com.example.bmw.common.exception.TechnicalException;
import com.example.bmw.vehicle.services.backendprocessing.business.configuration.control.PropertyProvider;
import com.google.common.base.Strings;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;


@Singleton
@Startup
public class ApplicationConfiguration {

    public static final String PROPERTIES_KEY = "properties";
    private LoadingCache<String, Properties> propertyCache;
    @Inject
    private PropertyProvider databasePropertyProvider;

    // ~~~ Property access


    /**
     * By calling this method, the caller ensures, that properties are loaded.
     */
    @PostConstruct
    public void initialize() {
        synchronized (this) {
            if (propertyCache == null) {
                propertyCache = CacheBuilder.newBuilder().maximumSize(1) // maximum 10 records can be cached
                        // .expireAfterAccess(10, TimeUnit.MINUTES) // cache will expire after 10 minutes of access
                        .expireAfterAccess(60, TimeUnit.SECONDS)
                        .build(new CacheLoader<String, Properties>() { // build the cacheloader
                            @Override
                            public Properties load(final String propertyId) {
                                // make the expensive call
                                return databasePropertyProvider.loadProperties();
                            }
                        });
            }
            //initial loading of properties
            fetchProperties();
        }
    }


    /**
     * Indicates if there is a non-empty configuration property for the given key.
     */
    public boolean hasProperty(final ConfigurationKey key) {
        final String value = fetchProperties().getProperty(key.getValue());
        return !Strings.isNullOrEmpty(value);
    }


    /**
     * Returns the configuration value as string or throws an TechnicalException if no property was defined.
     */
    public String getString(final ConfigurationKey key) {
        final String value = fetchProperties().getProperty(key.getValue());
        if (value == null) {
            throw new TechnicalException("Application property with key '" + key
                    + "' does not exists. Did you forget to define the property value? Is zfus.properties loaded?");
        }
        return value;
    }


    /**
     * Returns the configuration value as string.
     * If no property if defined for that key, the default value is returned.
     */
    public String getString(final ConfigurationKey key, final String defaultValue) {
        return fetchProperties().getProperty(key.getValue(), defaultValue);
    }

    // ~~~ Typed access


    /**
     * Returns the configuration value as boolean or throws an TechnicalException if no property was defined.
     */
    public boolean getBoolean(final ConfigurationKey key) {
        return Boolean.valueOf(getString(key, "false"));
    }


    /**
     * Returns the configuration value as {@link Path} or throws an TechnicalException if no property was defined.
     */
    public Path getPath(final ConfigurationKey key) {
        final String pathString = getString(key);
        return Strings.isNullOrEmpty(pathString) ? null : FileSystems.getDefault().getPath(pathString);
    }


    /**
     * Returns the configuration value as int or throws a TechnicalException if no property was defined.
     */
    public int getInt(final ConfigurationKey key) {
        final String intString = getString(key);
        return Integer.valueOf(intString);
    }


    /**
     * returns Properties object from the cache
     */
    public Properties getAllCurrent() {
        return fetchProperties();
    }


    // ~~~ helper


    /**
     * Returns the Properties object from the cache
     */
    private Properties fetchProperties() {
        try {
            final Properties env = System.getProperties();
            final Properties props = propertyCache.get(PROPERTIES_KEY);
            props.putAll(env);
            return props;
        } catch (final ExecutionException e) {
            throw new TechnicalException("Exception when trying to access cached Properties", e);
        }
    }
}
