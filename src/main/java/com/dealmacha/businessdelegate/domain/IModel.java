package com.dealmacha.businessdelegate.domain;

import java.io.Serializable;

/**
 * @author varma
 *
 */
public interface IModel extends Serializable {

    /**
     * @return
     */
    String getId();

    /**
     * @param id
     */
    void setId(String id);

}
