package com.crud_example.core.utils;

import com.crud_example.core.types.IdentificationType;

import java.util.Arrays;

public class CustomerUtils {

    public static void validateIdentificationType(IdentificationType identificationType) {

        boolean isValidIdentificationType = Arrays.asList(IdentificationType.values()).contains(identificationType);

        if (!isValidIdentificationType) {
            throw new IllegalArgumentException("ERROR: Invalid identification type provided. Please try again.");
        }
    }

}