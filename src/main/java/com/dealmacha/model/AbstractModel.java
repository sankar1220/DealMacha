/**
 *
 */
package com.dealmacha.model;

import com.dealmacha.businessdelegate.domain.IModel;

/**
 * @author Jay
 *
 */
public class AbstractModel implements IModel {

    protected String id;

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.businessdelegate.domain.IModel#getId()
     */
    @Override
    public String getId() {
        // TODO Auto-generated method stub
        return id;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.businessdelegate.domain.IModel#setId(java.lang.String)
     */
    @Override
    public void setId(final String id) {
        // TODO Auto-generated method stub
        this.id = id;

    }

}
