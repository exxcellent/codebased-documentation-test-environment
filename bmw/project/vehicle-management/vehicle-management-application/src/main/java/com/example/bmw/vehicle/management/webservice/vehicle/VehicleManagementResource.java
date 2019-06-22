package com.example.bmw.vehicle.management.webservice.vehicle;

import java.util.Set;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.example.bmw.vehicle.management.backendprocessing.business.vehiclemanagement.boundary.VehicleManagementBCI;
import com.example.bmw.vehicle.management.backendprocessing.business.vehiclemanagement.types.VehicleInformationTO;
import com.example.bmw.vehicle.management.webservice.vehicle.types.CreateVehicleTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Path("/v1/vehicle")
@RequestScoped
@Api(tags = "vehicle",
        description = "RESTful webservice to manage vehicle data")
public class VehicleManagementResource {

    private static final Logger LOG = Logger.getLogger( VehicleManagementResource.class.getName() );

    @Inject
    private VehicleManagementBCI vehicleManagementBCI;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Provides information for the Vehicle Service Application")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request was processed successfully"),
            @ApiResponse(code = 499, message = "Business error while processing request"),
            @ApiResponse(code = 500, message = "Technical error while processing request")
    })
    public Set<VehicleInformationTO> getAll() {
        LOG.info("Received find all vehicle information request");

        Set<VehicleInformationTO> vehicleTOs = vehicleManagementBCI.getAllVehicleInformation();

        LOG.info(String.format("Response vehicle information items: %s", vehicleTOs.size()));

        return vehicleTOs;
    }


    @GET
    @Path("{vehicleId}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Provides information for the Vehicle Service Application")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request was processed successfully"),
            @ApiResponse(code = 499, message = "Business error while processing request"),
            @ApiResponse(code = 500, message = "Technical error while processing request")
    })
    public VehicleInformationTO getVehicleInformation(
            @PathParam("vehicleId")
            @ApiParam(name = "vehicleId" , required = true)
                    int vehicleId) {
        LOG.info(String.format("Received vehicle information request with Vehicle ID %s", vehicleId));

        VehicleInformationTO vehicleTO = vehicleManagementBCI.getVehicleInformation(vehicleId);

        LOG.info(String.format("Response vehicle information: %s", vehicleTO.toString()));

        return vehicleTO;
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Creates a new vehicle")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request was processed successfully"),
            @ApiResponse(code = 499, message = "Business error while processing request"),
            @ApiResponse(code = 500, message = "Technical error while processing request")
    })
    public VehicleInformationTO createVehicle(CreateVehicleTO createVehicleTO) {
        LOG.info(String.format("Received create vehicle information request with Vehicle name %s", createVehicleTO.getName()));

        VehicleInformationTO vehicleTO = vehicleManagementBCI.createVehicleInformation(createVehicleTO.getName());

        LOG.info(String.format("Response vehicle information: %s", vehicleTO.toString()));

        return vehicleTO;
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Updates a new vehicle")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request was processed successfully"),
            @ApiResponse(code = 499, message = "Business error while processing request"),
            @ApiResponse(code = 500, message = "Technical error while processing request")
    })
    public VehicleInformationTO updateVehicle(VehicleInformationTO newVehicleInformationTO) {
        LOG.info(String.format("Received update vehicle information request: %s", newVehicleInformationTO.toString()));

        VehicleInformationTO vehicleTO = vehicleManagementBCI.updateVehicleInformation(newVehicleInformationTO);

        LOG.info(String.format("Response vehicle information: %s", vehicleTO.toString()));

        return vehicleTO;
    }


    @DELETE
    @Path("{vehicleId}")
    @ApiOperation(value = "Deletes a new vehicle")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request was processed successfully"),
            @ApiResponse(code = 499, message = "Business error while processing request"),
            @ApiResponse(code = 500, message = "Technical error while processing request")
    })
    public Response deleteVehicle(@PathParam("vehicleId")
                                        @ApiParam(name = "vehicleId" , required = true)
                                                int vehicleId) {
        LOG.info(String.format("Received delete vehicle information request for ID: %s", vehicleId));

        vehicleManagementBCI.deleteVehicleInformation(vehicleId);

        LOG.info(String.format("Vehicle information deleted for ID: %s", vehicleId));

        return Response.ok().build();
    }

}
