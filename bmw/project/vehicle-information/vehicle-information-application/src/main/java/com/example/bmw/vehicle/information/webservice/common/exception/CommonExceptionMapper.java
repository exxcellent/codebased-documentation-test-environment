package com.example.bmw.vehicle.information.webservice.common.exception;

import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import com.example.bmw.common.exception.BusinessException;


public class CommonExceptionMapper implements ExceptionMapper<Exception> {

    private static final Logger LOG = Logger.getLogger(CommonExceptionMapper.class.getName());


    @Override
    public Response toResponse(final Exception ex) {
        LOG.severe(String.format("An internal server error occurred while processing a REST request. %s", ex));

        if (ex instanceof BusinessException) {
            return Response
                    .status(499)
                    .type(MediaType.TEXT_PLAIN)
                    .entity("An internal business error occurred while processing your request.")
                    .build();
        }

        return Response
                .status(Status.INTERNAL_SERVER_ERROR)
                .type(MediaType.TEXT_PLAIN)
                .entity("An internal server error occurred while processing your request.")
                .build();
    }
}
