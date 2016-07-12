/**
 *
 */
package com.dealmacha.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Jay
 *
 */
@Component("cmsBlockModel")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CmsBlockModel extends AbstractModel {

    private String cmsSectionId;
    private String blockTitle;
    private String blockName;
    private String blockPostsMin;
    private String blockPostsMax;
    private String orderOfPlace;
    private String blockStyle;
    private String postsImageWidth;
    private String postsImageHeight;
    private List<CmsPostsModel> cmsPostsModels = new ArrayList<CmsPostsModel>(0);

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

    public List<CmsPostsModel> getCmsPostsModels() {
        return cmsPostsModels;
    }

    public String getCmsSectionId() {
        return cmsSectionId;
    }

    public String getOrderOfPlace() {
        return orderOfPlace;
    }

    /**
     * @return the postsImageHeight
     */
    public String getPostsImageHeight() {
        return postsImageHeight;
    }

    /**
     * @return the postsImageWidth
     */
    public String getPostsImageWidth() {
        return postsImageWidth;
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

    public void setCmsPostsModels(final List<CmsPostsModel> cmsPostsModels) {
        this.cmsPostsModels = cmsPostsModels;
    }

    public void setCmsSectionId(final String cmsSectionId) {
        this.cmsSectionId = cmsSectionId;
    }

    public void setOrderOfPlace(final String orderOfPlace) {
        this.orderOfPlace = orderOfPlace;
    }

    /**
     * @param postsImageHeight
     *            the postsImageHeight to set
     */
    public void setPostsImageHeight(final String postsImageHeight) {
        this.postsImageHeight = postsImageHeight;
    }

    /**
     * @param postsImageWidth
     *            the postsImageWidth to set
     */
    public void setPostsImageWidth(final String postsImageWidth) {
        this.postsImageWidth = postsImageWidth;
    }

}
