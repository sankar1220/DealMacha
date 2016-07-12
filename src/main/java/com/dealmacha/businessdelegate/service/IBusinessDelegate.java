package com.dealmacha.businessdelegate.service;

import com.dealmacha.businessdelegate.domain.IKeyBuilder;
import com.dealmacha.businessdelegate.domain.IModel;

import java.io.Serializable;
import java.util.Collection;

public interface IBusinessDelegate<T extends IModel, U extends IBusinessDelegateContext, V extends IKeyBuilder<W>, W extends Serializable> {
    /**
     * Given a <code>model</code>, create the associated business record(s).
     *
     * @param model
     *            Model to create
     * @return The official model as now recorded in the System-Of-Record
     */
    T create(T model);

    /**
     * Given a <code>keyBuilder</code> instance, load and delete the specified model.
     *
     * @param key
     *            Builder that represents the object to delete
     * @param context
     *            Context to further qualify the delete action
     */
    void delete(V keyBuilder, U context);

    /**
     * Given a <code>model</code>, edit the associated business record(s).
     *
     * @param key
     *            Builder that represents the object to load
     * @param model
     *            Model that contains the changes to apply to the business record
     * @return The official model as now recorded in the System-Of-Record
     */
    T edit(V keyBuilder, T model);

    /**
     * Given a <code>keyBuilder</code> instance, load the specified model.
     *
     * @param key
     *            Builder that represents the object to load
     * @param context
     *            Context to further qualify the get action
     */
    T getByKey(V keyBuilder, U context);

    /**
     * @param productContext
     * @return
     */
    Collection<T> getCollection(U context);

    /**
     * @param keyBuilder
     * @param model
     * @return
     */

}
