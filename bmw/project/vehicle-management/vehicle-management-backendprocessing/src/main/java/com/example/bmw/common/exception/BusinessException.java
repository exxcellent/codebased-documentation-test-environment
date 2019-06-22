package com.example.bmw.common.exception;

import java.util.logging.Logger;
import javax.ejb.ApplicationException;

/**
 * BusinessException class.
 *
 * @author Michael Bauer, MaibornWolff
 */
@ApplicationException(rollback = true)
public class BusinessException extends BmwException {

    private static final Logger LOG = Logger.getLogger( BusinessException.class.getName() );

    /**
     * Delegate to superclass
     *
     * @param message the detail message (see {@link RuntimeException}).
     */
    public BusinessException(final String message) {
        super(message, null);
        LOG.fine(String.format("BusinessException: %s;", message));
    }
}
