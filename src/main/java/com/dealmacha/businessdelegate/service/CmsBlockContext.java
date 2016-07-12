/**
 *
 */
package com.dealmacha.businessdelegate.service;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Naveen-PC
 *
 */

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CmsBlockContext implements IBusinessDelegateContext {

    private String cmsSectionId;
    private String blockTitle;
    private String blockName;
    private String blockPostsMin;
    private String blockPostsMax;
    private String orderOfPlace;
    private String blockStyle;

    public String getBlockName() {
        return blockName;
    }

    public String getBlockPostsMax() {
        return blockPostsMax;
    }

    public String getBlockPostsMin() {
        return blockPostsMin;
    }

    public String getBlockStyle() {
        return blockStyle;
    }

    public String getBlockTitle() {
        return blockTitle;
    }

    public String getCmsSectionId() {
        return cmsSectionId;
    }

    public String getOrderOfPlace() {
        return orderOfPlace;
    }

    public void setBlockName(final String blockName) {
        this.blockName = blockName;
    }

    public void setBlockPostsMax(final String blockPostsMax) {
        this.blockPostsMax = blockPostsMax;
    }

    public void setBlockPostsMin(final String blockPostsMin) {
        this.blockPostsMin = blockPostsMin;
    }

    public void setBlockStyle(final String blockStyle) {
        this.blockStyle = blockStyle;
    }

    public void setBlockTitle(final String blockTitle) {
        this.blockTitle = blockTitle;
    }

    public void setCmsSectionId(final String cmsSectionId) {
        this.cmsSectionId = cmsSectionId;
    }

    public void setOrderOfPlace(final String orderOfPlace) {
        this.orderOfPlace = orderOfPlace;
    }

}
