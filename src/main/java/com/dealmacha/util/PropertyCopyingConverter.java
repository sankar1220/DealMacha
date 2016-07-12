package com.dealmacha.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 * Converter to copy the properties from the source to target using spring bean utils
 *
 * @author rbuddepu
 */
public class PropertyCopyingConverter<S, T> implements Converter<S, T> {
    private ObjectFactory<T> factory;

    /**
     * Constructor for PropertyCopyingConverter
     */
    public PropertyCopyingConverter() {
        super();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    @Override
    public T convert(final S source) {
        T target = getFactory().getObject();
        BeanUtils.copyProperties(source, target);
        return target;
    }

    /**
     * @return the factory
     */
    public ObjectFactory<T> getFactory() {
        return factory;
    }

    /**
     * @param factory
     *            the factory to set
     */
    @Autowired
    public void setFactory(final ObjectFactory<T> factory) {
        this.factory = factory;
    }

}
