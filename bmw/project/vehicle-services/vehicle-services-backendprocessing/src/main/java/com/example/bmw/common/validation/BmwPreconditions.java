//======================================================================================================================
// Module: BMW Remote Software Update (RSU) - Zentrales Fahrzeug Update System (ZFUS)
// Copyright (c) 2017 BMW Group. All rights reserved.
//======================================================================================================================
package com.example.bmw.common.validation;

import java.util.Collection;
import com.example.bmw.common.exception.BusinessException;

/**
 * I check preconditions for early reject purpose.
 *
 * @author Andre Lehnert, eXXcellent solutions consulting & software gmbh
 */
public final class BmwPreconditions {

    /**
     * Default constructor
     */
    private BmwPreconditions() {

    }


    /**
     * Ensures that an object reference passed as a parameter to the calling method is not null.
     *
     * @param reference    an object reference
     * @param errorMessage the exception message to use if the check fails
     * @return the non-null reference that was validated
     * @throws BusinessException if {@code reference} is null
     */
    public static <T> T checkNotNull(final T reference, final String errorMessage) throws BusinessException {
        if (reference == null) {
            throw new BusinessException(errorMessage);
        }

        return reference;
    }


    /**
     * Ensures that a String passed as a parameter to the calling method is not null or empty.
     *
     * @param parameter    an object reference
     * @param errorMessage the exception message to use if the check fails
     * @return the non-null String that was validated
     * @throws BusinessException if {@code parameter} is null or empty
     */
    public static String checkNotNullOrEmpty(final String parameter, final String errorMessage)
            throws BusinessException {
        if (parameter == null || parameter.length() == 0) {
            throw new BusinessException(errorMessage);
        }

        return parameter;
    }


    /**
     * Ensures that a byte array passed as a parameter to the calling method is not null or empty.
     *
     * @param parameter    an object reference
     * @param errorMessage the exception message to use if the check fails
     * @return the non-empty byte array that was validated
     * @throws BusinessException if {@code parameter} is null or empty
     */
    public static byte[] checkNotEmpty(final byte[] parameter, final String errorMessage)
            throws BusinessException {
        if (parameter == null || parameter.length == 0) {
            throw new BusinessException(errorMessage);
        }

        return parameter;
    }


    /**
     * Ensures that a Collection reference passed as a parameter to the calling method is not null or empty.
     *
     * @param reference    an object reference
     * @param errorMessage the exception message to use if the check fails
     * @return the non-null reference that was validated
     * @throws BusinessException if {@code reference} is null or empty
     */
    public static Collection<?> checkNotNullOrEmpty(final Collection<?> reference, final String errorMessage)
            throws BusinessException {
        if (reference == null || reference.isEmpty()) {
            throw new BusinessException(errorMessage);
        }

        return reference;
    }


    /**
     * Ensures that a boolean expression passed as a parameter to the calling method is true.
     *
     * @param expression   a boolean expression
     * @param errorMessage the exception message to use if the check fails
     * @throws BusinessException if {@code expression} is false
     */
    public static void checkArgument(final boolean expression, final String errorMessage) throws BusinessException {
        if (!expression) {
            throw new BusinessException(errorMessage);
        }
    }
}
