/**
 *
 */
package com.dealmacha.resources.hal;

import org.springframework.hateoas.core.Relation;

/**
 * @author Jay
 *
 */
@Relation(value = "cmsBlock", collectionRelation = "cmsBlock")
public class CmsBlockResource extends ResourceWithEmdedded {

    private String cmsSectionId;
    private String cmsBlockId;
    private String blockTitle;
    private String blockName;
    private String blockPostsMin;
    private String blockPostsMax;
    private String orderOfPlace;
    private String blockStyle;
    private String postsImageWidth;
    private String postsImageHeight;

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

    public String getCmsBlockId() {
        return cmsBlockId;
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

    public void setCmsBlockId(final String cmsBlockId) {
        this.cmsBlockId = cmsBlockId;
    }

    public void setCmsSectionId(final String cmsSectionId) {
        this.cmsSectionId = cmsSectionId;
    }

    public void setOrderOfPlace(final String orderOfPlace) {
        this.orderOfPlace = orderOfPlace;
    }

    /**
     * @return the postsImageWidth
     */
    public String getPostsImageWidth() {
        return postsImageWidth;
    }

    /**
     * @param postsImageWidth the postsImageWidth to set
     */
    public void setPostsImageWidth(String postsImageWidth) {
        this.postsImageWidth = postsImageWidth;
    }

    /**
     * @return the postsImageHeight
     */
    public String getPostsImageHeight() {
        return postsImageHeight;
    }

    /**
     * @param postsImageHeight the postsImageHeight to set
     */
    public void setPostsImageHeight(String postsImageHeight) {
        this.postsImageHeight = postsImageHeight;
    }

}
