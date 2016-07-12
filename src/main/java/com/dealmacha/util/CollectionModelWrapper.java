package com.dealmacha.util;

import com.dealmacha.businessdelegate.domain.IModel;

import java.util.Collection;

import org.springframework.core.convert.TypeDescriptor;

/**
 * Implementation class for IModel wrapper which hold collection of models along with the type descriptor
 *
 * @author rbuddepu
 */
public class CollectionModelWrapper<T extends IModel> implements IModelWrapper<Collection<T>> {
    private TypeDescriptor modelDescriptor;
    private Collection<T> modelObjects;

    /**
     * Constructor for CollectionModelWrapper
     *
     * @param modelClass
     * @param modelObjects
     */
    public CollectionModelWrapper(final Class<T> modelClass, final Collection<T> modelObjects) {
        super();
        this.modelDescriptor = CollectionTypeDescriptor.forCollection(modelObjects, modelClass);
        this.modelObjects = modelObjects;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.cscinfo.entity.util.model.IModelWrapper#getModel()
     */
    @Override
    public Collection<T> getModel() {
        return modelObjects;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.cscinfo.entity.util.model.IModelWrapper#getModelType()
     */
    @Override
    public TypeDescriptor getModelType() {
        return modelDescriptor;
    }
}
