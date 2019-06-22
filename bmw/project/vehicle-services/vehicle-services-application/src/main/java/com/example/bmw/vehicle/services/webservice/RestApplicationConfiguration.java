package com.example.bmw.vehicle.services.webservice;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * I configure the application path of our REST Services and Swagger API documentation.
 *
 * @author Created by Lars Gielsok, MaibornWolff.
 */
@ApplicationPath("/api")
public class RestApplicationConfiguration extends Application {

    /**
     * Constructor
     */
    public RestApplicationConfiguration() {
    }

}
