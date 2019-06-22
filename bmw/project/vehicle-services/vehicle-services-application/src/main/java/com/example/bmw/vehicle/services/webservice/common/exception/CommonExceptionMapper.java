package com.example.bmw.vehicle.services.webservice.common.exception;

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

        if (ex instanceof BusinessException) {
            final BusinessException be = (BusinessException) ex;

            LOG.severe(String.format("An internal server error occurred while processing a REST request. %s", ex));

            return Response
                    .status(499)
                    .type(MediaType.TEXT_PLAIN)
                    .entity("An internal error occurred while processing your request.")
                    .build();
        } else {
            LOG.severe(String.format("An internal server error occurred while processing a REST request. %s", ex));

        }

        return Response
                .status(Status.INTERNAL_SERVER_ERROR)
                .type(MediaType.TEXT_PLAIN)
                .entity("An internal server error occurred while processing your request.")
                .build();
    }
}
