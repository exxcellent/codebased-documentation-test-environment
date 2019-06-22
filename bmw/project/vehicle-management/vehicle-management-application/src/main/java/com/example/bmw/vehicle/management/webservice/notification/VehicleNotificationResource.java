package com.example.bmw.vehicle.management.webservice.notification;

import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.example.bmw.vehicle.management.backendprocessing.business.vehiclemanagement.boundary.VehicleManagementBCI;
import com.example.bmw.vehicle.management.backendprocessing.business.vehiclenotification.boundary.VehicleNotificationBCI;
import com.example.bmw.vehicle.management.backendprocessing.business.vehiclenotification.types.VehicleNotificationResponseTO;
import com.example.bmw.vehicle.management.backendprocessing.business.vehiclenotification.types.VehicleNotificationTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Path("/v1/trigger-notification")
@RequestScoped
@Api(tags = "vehicle",
        description = "RESTful webservice to send vehicle notifications")
public class VehicleNotificationResource {

    private static final Logger LOG = Logger.getLogger( VehicleNotificationResource.class.getName() );

    @Inject
    private VehicleNotificationBCI vehicleNotificationBCI;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Triggers a vehicle notification")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request was processed successfully"),
            @ApiResponse(code = 499, message = "Business error while processing request"),
            @ApiResponse(code = 500, message = "Technical error while processing request")
    })
    public VehicleNotificationTO triggerNotification(
            @QueryParam("vehicleId")
            @ApiParam(name = "vehicleId" , required = true)
                    int vehicleId,
            @QueryParam("message")
            @ApiParam(name = "message" , required = true)
                    String message) {
        LOG.info(String.format("Received vehicle notification request with Vehicle ID %s", vehicleId));

        VehicleNotificationTO vehicleNotificationTO = vehicleNotificationBCI.triggerNotification(vehicleId, message);

        LOG.info(String.format("Response vehicle notification report: %s", vehicleNotificationTO.toString()));

        return vehicleNotificationTO;
    }
}
