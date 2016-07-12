package com.dealmacha.util;

import java.util.Collection;

import org.apache.commons.lang3.Validate;
import org.springframework.core.ResolvableType;
import org.springframework.core.convert.TypeDescriptor;

/**
 * Class to describe the type of the given collection
 *
 * @author sankar
 */
public class CollectionTypeDescriptor extends TypeDescriptor {

    /**
     * Constructor for CollectionTypeDescriptor
     *
     * @param resolvableType
     */
    public CollectionTypeDescriptor(final ResolvableType resolvableType) {
        super(resolvableType, null, null);
    }

    /**
     * @param typeDescriptor
     */
    public static TypeDescriptor forCollection(final Collection collection) {
        final TypeDescriptor collectionDescriptor = TypeDescriptor.forObject(collection);

        if (!collection.isEmpty() && collectionDescriptor.getResolvableType().hasUnresolvableGenerics()) {
            TypeDescriptor elementType = collectionDescriptor.elementTypeDescriptor(collection.iterator().next());
            ResolvableType rType = ResolvableType.forClassWithGenerics(collection.getClass(), elementType.getType());
            return new CollectionTypeDescriptor(rType);
        }

        return collectionDescriptor;
    }

    /**
     *
     * @param collection
     * @param collectionType
     * @return
     */
    public static TypeDescriptor forCollection(final Collection collection, final Class<?> collectionType) {
        final TypeDescriptor collectionDescriptor = TypeDescriptor.forObject(collection);
        return forType(collectionDescriptor, collectionType);
    }

    /**
     *
     * @param collectionDescriptor
     * @param collectionType
     * @return
     */
    public static TypeDescriptor forType(final TypeDescriptor collectionDescriptor, final Class<?> collectionType) {
        Validate.notNull(collectionDescriptor, "collectionDescriptor must not be null.");
        Validate.notNull(collectionType, "colectionType must not be null.");

        if (collectionDescriptor.isCollection()) {
            ResolvableType rType = ResolvableType.forClassWithGenerics(collectionDescriptor.getObjectType(), collectionType);
            return new CollectionTypeDescriptor(rType);
        }

        throw new IllegalStateException("collectionDescriptor must describe a Collection!");
    }
}
