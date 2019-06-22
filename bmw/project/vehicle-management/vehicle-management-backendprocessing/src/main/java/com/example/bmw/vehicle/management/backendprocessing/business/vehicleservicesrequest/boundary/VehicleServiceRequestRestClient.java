package com.example.bmw.vehicle.management.backendprocessing.business.vehicleservicesrequest.boundary;

import java.net.URI;
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
import com.example.bmw.vehicle.management.backendprocessing.business.vehiclenotification.types.VehicleNotificationRequestTO;
import com.example.bmw.vehicle.management.backendprocessing.business.vehiclenotification.types.VehicleNotificationResponseTO;
import com.example.bmw.vehicle.management.backendprocessing.business.vehicleservicesrequest.control.VehicleServiceJsonFormatter;


public class VehicleServiceRequestRestClient implements VehicleServicesRequestESI {
    private static final Logger LOG = Logger.getLogger( VehicleServiceRequestRestClient.class.getName() );
    private static final String NOTIFY_VEHICLE_SUB_URL = "/v2/notify-vehicle";

    @Inject
    private ApplicationConfiguration applicationConfiguration;
    @Inject
    private VehicleServiceJsonFormatter vehicleServiceJsonFormatter;

    private static final String ERROR_MESSAGE_SUFFIX = "occurred while requesting vehicle information. "
            + "'%s' with response code '%s'";

    @Override
    public VehicleNotificationResponseTO sendNotificationRequest(VehicleNotificationRequestTO vehicleNotificationRequestTO) {
        final URI uri = buildUri();
        final Response response;
        try {
            response = ClientBuilder.newClient()
                    .target(uri)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(vehicleNotificationRequestTO, MediaType.APPLICATION_JSON_TYPE));

        } catch (final RuntimeException e) {
            final String message = String.format(
                    "Failed to request '%s'",
                    uri.toASCIIString());
            throw new TechnicalException(message, e);
        }

        evaluateResponse(response, uri);

        String json = response.readEntity(String.class);

        LOG.info(String.format("Received JSON: %s", json));

        return vehicleServiceJsonFormatter.fromJson(json);
    }

    private URI buildUri() {
        final String baseUrl = applicationConfiguration.getString(ConfigurationKey.VEHICLE_SERVICES_BASE_URL);

        // <base url>/<resource url>/<resource id>
        return UriBuilder.fromPath(baseUrl)
                .path(NOTIFY_VEHICLE_SUB_URL)
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
