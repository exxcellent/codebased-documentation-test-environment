package com.example.bmw.vehicle.services.webservice.vehiclenotification;

import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.example.bmw.vehicle.services.backendprocessing.business.vehiclenotification.boundary.VehicleNotificationBCI;
import com.example.bmw.vehicle.services.backendprocessing.business.vehiclenotification.types.VehicleNotificationResponseTO;
import com.example.bmw.vehicle.services.backendprocessing.business.vehiclenotification.types.VehicleNotificationRequestTO;
import com.example.bmw.vehicle.services.webservice.common.json.JsonMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/v2/notify-vehicle")
@RequestScoped
@Api(tags = "notify-vehicle",
        description = "Sends notifications to vehicles")
public class VehicleNotificationResource {

    private static final Logger LOG = Logger.getLogger( VehicleNotificationResource.class.getName() );

    @Inject
    private VehicleNotificationBCI vehicleNotificationBCI;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Requests the vehicle name from the Vehicle Information Service and simulates a vehicle notification")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request was processed successfully"),
            @ApiResponse(code = 499, message = "Business error while processing request"),
            @ApiResponse(code = 500, message = "Technical error while processing request")
    })
    public String sendVehicleNotification(
            VehicleNotificationRequestTO vehicleNotificationRequestTO) {
        LOG.info(String.format("Received vehicle notification request: %s", vehicleNotificationRequestTO.toString()));

        VehicleNotificationResponseTO vehicleNotificationResponseTO = vehicleNotificationBCI.sendVehicleNotification(vehicleNotificationRequestTO);

        String json = new JsonMapper().asJsonString(vehicleNotificationResponseTO);

        LOG.info(String.format("Send vehicle notification response: %s", json));

        return json;
    }




}
