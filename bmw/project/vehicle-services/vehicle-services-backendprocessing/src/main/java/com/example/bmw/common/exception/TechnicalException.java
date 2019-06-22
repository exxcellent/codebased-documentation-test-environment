package com.example.bmw.common.exception;

import java.util.logging.Logger;

public class TechnicalException extends BmwException {

    private static final Logger LOG = Logger.getLogger( BusinessException.class.getName() );

    public TechnicalException(final String message, final Throwable cause) {
        super(message, cause);
        LOG.fine(String.format("TechnicalException: %s; cause %s;", message, ExceptionHelper.toString(cause)));

    }

    public TechnicalException(final String message) {
        super(message);
        LOG.fine(String.format("TechnicalException: %s;", message));
    }
}
