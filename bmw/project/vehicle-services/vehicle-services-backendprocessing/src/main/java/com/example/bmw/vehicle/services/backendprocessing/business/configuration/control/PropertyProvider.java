package com.example.bmw.vehicle.services.backendprocessing.business.configuration.control;

import java.util.Properties;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import com.example.bmw.vehicle.services.backendprocessing.business.configuration.boundary.ConfigurationKey;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class PropertyProvider {


    /**
     * load properties from db and return as Properties object instance
     */
    public Properties loadProperties() {
        final Properties properties = new Properties();

        for (ConfigurationKey configurationKey : ConfigurationKey.values()) {
            try {
                String value = System.getenv(configurationKey.getValue());
                properties.setProperty(configurationKey.getValue(), value);
            } catch (RuntimeException ignore) {
                // ignore ...
            }
        }

        return properties;
    }

}
