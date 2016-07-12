/**
 *
 */
package com.dealmacha.model;

import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Jay
 *
 */

@Component("cmsPostsModel")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CmsPostsModel extends AbstractModel {

    private String postTitle;
    private String postName;
    private String postsExpiryTime;
    private String postsProductTitle;
    private String postsProductPrice;
    private String postsProductAfterPrice;
    private String orderOfPlace;
    private String cmsBlockId;
    private String url;
    private String postsImageWidth;
    private String postsImageHeight;
    private String postsImageUrl;
    private String postsImageId;
    private String cmsBlockName;

    private String postsImageAlt;
    private List<CmsPostImagesModel> cmsPostImageModels;

    public String getCmsBlockId() {
        return cmsBlockId;
    }

    /**
     * @return the cmsBlockName
     */
    public String getCmsBlockName() {
        return cmsBlockName;
    }

    /**
     * @return the cmsPostImageModel
     */
    public List<CmsPostImagesModel> getCmsPostImageModel() {
        return cmsPostImageModels;
    }

    public String getOrderOfPlace() {
        return orderOfPlace;
    }

    public String getPostName() {
        return postName;
    }

    public String getPostsExpiryTime() {
        return postsExpiryTime;
    }

    /**
     * @return the postsImageAlt
     */
    public String getPostsImageAlt() {
        return postsImageAlt;
    }

    public String getPostsImageHeight() {
        return postsImageHeight;
    }

    /**
     * @return the postsImageId
     */
    public String getPostsImageId() {
        return postsImageId;
    }

    /**
     * @return the postsImageUrl
     */
    public String getPostsImageUrl() {
        return postsImageUrl;
    }

    public String getPostsImageWidth() {
        return postsImageWidth;
    }

    public String getPostsProductAfterPrice() {
        return postsProductAfterPrice;
    }

    public String getPostsProductPrice() {
        return postsProductPrice;
    }

    public String getPostsProductTitle() {
        return postsProductTitle;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public String getUrl() {
        return url;
    }

    public void setCmsBlockId(final String cmsBlockId) {
        this.cmsBlockId = cmsBlockId;
    }

    /**
     * @param cmsBlockName
     *            the cmsBlockName to set
     */
    public void setCmsBlockName(final String cmsBlockName) {
        this.cmsBlockName = cmsBlockName;
    }

    /**
     * @param cmsPostImageModel
     *            the cmsPostImageModel to set
     */
    public void setCmsPostImageModel(final List<CmsPostImagesModel> cmsPostImageModels) {
        this.cmsPostImageModels = cmsPostImageModels;
    }

    public void setOrderOfPlace(final String orderOfPlace) {
        this.orderOfPlace = orderOfPlace;
    }

    public void setPostName(final String postName) {
        this.postName = postName;
    }

    public void setPostsExpiryTime(final String postsExpiryTime) {
        this.postsExpiryTime = postsExpiryTime;
    }

    /**
     * @param postsImageAlt
     *            the postsImageAlt to set
     */
    public void setPostsImageAlt(final String postsImageAlt) {
        this.postsImageAlt = postsImageAlt;
    }

    public void setPostsImageHeight(final String postsImageHeight) {
        this.postsImageHeight = postsImageHeight;
    }

    /**
     * @param postsImageId
     *            the postsImageId to set
     */
    public void setPostsImageId(final String postsImageId) {
        this.postsImageId = postsImageId;
    }

    /**
     * @param postsImageUrl
     *            the postsImageUrl to set
     */
    public void setPostsImageUrl(final String postsImageUrl) {
        this.postsImageUrl = postsImageUrl;
    }

    public void setPostsImageWidth(final String postsImageWidth) {
        this.postsImageWidth = postsImageWidth;
    }

    public void setPostsProductAfterPrice(final String postsProductAfterPrice) {
        this.postsProductAfterPrice = postsProductAfterPrice;
    }

    public void setPostsProductPrice(final String postsProductPrice) {
        this.postsProductPrice = postsProductPrice;
    }

    public void setPostsProductTitle(final String postsProductTitle) {
        this.postsProductTitle = postsProductTitle;
    }

    public void setPostTitle(final String postTitle) {
        this.postTitle = postTitle;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

}
