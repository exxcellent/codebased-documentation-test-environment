package com.example.bmw.vehicle.management.backendprocessing.business.vehicleinformationrequest.boundary;

import java.net.URI;
import java.util.Set;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import com.example.bmw.common.exception.BusinessException;
import com.example.bmw.common.exception.TechnicalException;
import com.example.bmw.common.validation.Preconditions;
import com.example.bmw.vehicle.management.backendprocessing.business.configuration.boundary.ApplicationConfiguration;
import com.example.bmw.vehicle.management.backendprocessing.business.configuration.boundary.ConfigurationKey;
import com.example.bmw.vehicle.management.backendprocessing.business.vehicleinformationrequest.control.VehicleInformationJsonFormatter;
import com.example.bmw.vehicle.management.backendprocessing.business.vehicleinformationrequest.types.VehicleInformationCreateTO;
import com.example.bmw.vehicle.management.backendprocessing.business.vehicleinformationrequest.types.VehicleInformationResponseTO;


public class VehicleInformationRequestRestClient implements VehicleInformationRequestESI {
    private static final Logger LOG = Logger.getLogger( VehicleInformationRequestRestClient.class.getName() );

    @Inject
    private ApplicationConfiguration applicationConfiguration;
    @Inject
    private VehicleInformationJsonFormatter vehicleInformationJsonFormatter;

    private static final String ERROR_MESSAGE_SUFFIX = "occurred while requesting vehicle information. "
            + "'%s' with response code '%s'";

    @Override
    public VehicleInformationResponseTO requestVehicleInformation(int vehicleId) {
        final URI uri = buildUri(vehicleId);
        final Response response;
        try {
            response = ClientBuilder.newClient()
                    .target(uri)
                    .request(MediaType.APPLICATION_JSON)
                    .get();

        } catch (final RuntimeException e) {
            final String message = String.format(
                    "Failed to request '%s'",
                    uri.toASCIIString());
            throw new TechnicalException(message, e);
        }

        evaluateResponse(response, uri);

        String json = response.readEntity(String.class);

        LOG.info(String.format("Received JSON: %s", json));

        return vehicleInformationJsonFormatter.fromJson(json);
    }


    @Override
    public VehicleInformationResponseTO createVehicleInformation(String name) {
        final URI uri = buildUri();
        final Response response;
        try {
            VehicleInformationCreateTO vehicleInformationCreateTO = new VehicleInformationCreateTO();
            vehicleInformationCreateTO.setName(name);

            response = ClientBuilder.newClient()
                    .target(uri)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(vehicleInformationCreateTO, MediaType.APPLICATION_JSON));

        } catch (final RuntimeException e) {
            final String message = String.format(
                    "Failed to request '%s'",
                    uri.toASCIIString());
            throw new TechnicalException(message, e);
        }

        evaluateResponse(response, uri);

        String json = response.readEntity(String.class);

        LOG.info(String.format("Received JSON: %s", json));

        return vehicleInformationJsonFormatter.fromJson(json);
    }


    @Override
    public VehicleInformationResponseTO updateVehicleInformation(
            VehicleInformationResponseTO newVehicleInformationResponseTO) {
        final URI uri = buildUri();
        final Response response;
        try {
            response = ClientBuilder.newClient()
                    .target(uri)
                    .request(MediaType.APPLICATION_JSON)
                    .put(Entity.entity(newVehicleInformationResponseTO, MediaType.APPLICATION_JSON));

        } catch (final RuntimeException e) {
            final String message = String.format(
                    "Failed to request '%s'",
                    uri.toASCIIString());
            throw new TechnicalException(message, e);
        }

        evaluateResponse(response, uri);

        String json = response.readEntity(String.class);

        LOG.info(String.format("Received JSON: %s", json));

        return vehicleInformationJsonFormatter.fromJson(json);
    }


    @Override
    public void deleteVehicleInformation(int vehicleId) {
        final URI uri = buildUri(vehicleId);
        final Response response;
        try {
            response = ClientBuilder.newClient()
                    .target(uri)
                    .request(MediaType.APPLICATION_JSON)
                    .delete();

        } catch (final RuntimeException e) {
            final String message = String.format(
                    "Failed to request '%s'",
                    uri.toASCIIString());
            throw new TechnicalException(message, e);
        }

        evaluateResponse(response, uri);
    }


    @Override
    public VehicleInformationResponseTO[] requestAllVehicleInformation() {
        final URI uri = buildUri();
        final Response response;
        try {
            response = ClientBuilder.newClient()
                    .target(uri)
                    .request(MediaType.APPLICATION_JSON)
                    .get();

        } catch (final RuntimeException e) {
            final String message = String.format(
                    "Failed to request '%s'",
                    uri.toASCIIString());
            throw new TechnicalException(message, e);
        }

        evaluateResponse(response, uri);

        String json = response.readEntity(String.class);

        LOG.info(String.format("Received JSON: %s", json));

        return vehicleInformationJsonFormatter.fromJsonArray(json);
    }

    private URI buildUri() {
        final String baseUrl = applicationConfiguration.getString(ConfigurationKey.VEHICLE_INFORMATION_BASE_URL);
        final String resourceUrl = applicationConfiguration.getString(
                ConfigurationKey.VEHICLE_INFORMATION_SUB_URL);

        // <base url>/<resource url>/<resource id>
        return UriBuilder.fromPath(baseUrl)
                .path(resourceUrl)
                .build();
    }


    private URI buildUri(int vehicleId) {
        final String baseUrl = applicationConfiguration.getString(ConfigurationKey.VEHICLE_INFORMATION_BASE_URL);
        final String resourceUrl = applicationConfiguration.getString(
                ConfigurationKey.VEHICLE_INFORMATION_SUB_URL);

        // <base url>/<resource url>/<resource id>
        return UriBuilder.fromPath(baseUrl)
                .path(resourceUrl)
                .path(String.valueOf(vehicleId))
                .build();
    }


    private void evaluateResponse(final Response response, final URI uri) {
        Preconditions.checkNotNull(response, "Vehicle Information response must not be null");

        final int responseCode = response.getStatus();

        if (responseCode == Response.Status.OK.getStatusCode()) {
            // Nothing to be done here.
            return;
        } else if (responseCode / 100 == 5) {
            throw new TechnicalException(
                    String.format("A technical exception " + ERROR_MESSAGE_SUFFIX,
                            uri.toASCIIString(), responseCode));
        } else if (responseCode / 100 == 4) {
            throw new BusinessException(
                    String.format("A business exception " + ERROR_MESSAGE_SUFFIX,
                            uri.toASCIIString(), responseCode));
        } else {
            throw new TechnicalException(String.format("Received an unexpected response code '%s' for request '%s'",
                    response.getStatus(), uri.toASCIIString()));
        }
    }
}
