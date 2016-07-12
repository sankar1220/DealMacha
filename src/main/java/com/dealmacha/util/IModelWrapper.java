package com.dealmacha.util;

import org.springframework.core.convert.TypeDescriptor;

/**
 * Generic Model Warpper class which can accept Model or Collection of models along with type descriptor
 *
 * @author rbuddepu
 *
 * @param <T>
 */
public interface IModelWrapper<T> {

    T getModel();

    TypeDescriptor getModelType();
}
