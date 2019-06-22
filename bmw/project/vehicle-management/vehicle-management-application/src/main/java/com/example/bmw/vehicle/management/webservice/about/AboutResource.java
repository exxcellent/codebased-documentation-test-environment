package com.example.bmw.vehicle.management.webservice.about;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Path("/v1/about")
@RequestScoped
@Api(tags = "about", description = "Provides information about the installed release of Vehicle Information Services")
public class AboutResource {

    /**
     * return buildVersion
     */
    @GET
    @Produces("text/plain")
    @ApiOperation(value = "Returns current version of the application")
    public String about() {
        return "BMW Vehicle Information example app";
    }



}
