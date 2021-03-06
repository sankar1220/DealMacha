/**
 *
 */
package com.dealmacha.resources.hal;

import org.springframework.hateoas.core.Relation;

/**
 * @author Jay
 *
 */
@Relation(value = "cmsPosts", collectionRelation = "cmsPosts")
public class CmsPostsResource extends ResourceWithEmdedded {

    private String postTitle;
    private String postName;
    private String postsExpiryTime;
    private String postsProductTitle;
    private String postsProductPrice;
    private String postsProductAfterPrice;
    private String orderOfPlace;
    private String cmsBlockId;
    private String url;
    private String urlTargetLocation;
    private String postsImageWidth;
    private String postsImageHeight;
    private String cmsPostsId;
    private String postsImageUrl;
    private String postsImageId;
    private String cmsBlockName;
    private String description;

    private String postsImageAlt;

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
     * @return the cmsPostsId
     */
    public String getCmsPostsId() {
        return cmsPostsId;
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
     * @param cmsPostsId
     *            the cmsPostsId to set
     */
    public void setCmsPostsId(final String cmsPostsId) {
        this.cmsPostsId = cmsPostsId;
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

	public String getUrlTargetLocation() {
		return urlTargetLocation;
	}

	public void setUrlTargetLocation(String urlTargetLocation) {
		this.urlTargetLocation = urlTargetLocation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
