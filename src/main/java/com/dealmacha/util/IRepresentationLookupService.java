package com.dealmacha.util;

import org.springframework.core.convert.TypeDescriptor;

/**
 * Service to look up the representation details from the registry
 *
 * @author rbuddepu
 */
public interface IRepresentationLookupService {

    /**
     * Method to get the Representation type
     *
     * @param typeDescriptor
     * @param matchClass
     * @return
     */
    TypeDescriptor getRepresentation(TypeDescriptor typeDescriptor, Class<?> matchClass);
}
