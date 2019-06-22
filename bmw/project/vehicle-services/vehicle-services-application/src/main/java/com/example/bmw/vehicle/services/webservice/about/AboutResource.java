package com.example.bmw.vehicle.services.webservice.about;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * I report the version and build information for the current software artifact.
 *
 * @author Lars Gielsok, MaibornWolff GmbH
 */
@Path("/v1/about")
@RequestScoped
@Api(tags = "about", description = "Provides information about the installed release of RSA Vehicle Frontend Services")
public class AboutResource {

    /**
     * return buildVersion
     */
    @GET
    @Produces("text/plain")
    @ApiOperation(value = "Returns current version of the application")
    public String about() {
        return "BMW example app";
    }



}
