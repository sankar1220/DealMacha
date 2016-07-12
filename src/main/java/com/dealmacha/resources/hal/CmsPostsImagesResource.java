/**
 *
 */
package com.dealmacha.resources.hal;

import org.springframework.hateoas.core.Relation;

/**
 * @author Jay
 *
 */
@Relation(value = "cmsPostImages", collectionRelation = "cmsPostImages")
public class CmsPostsImagesResource extends ResourceWithEmdedded {

    private String cmsPostsId;
    private Integer imageWidth;
    private Integer imageHeight;
    private String image_alt;
    private String imageLocation;
    private String cmsPostImagesId;

    /**
     * @return the cmsPostImagesId
     */
    public String getCmsPostImagesId() {
        return cmsPostImagesId;
    }

    public String getCmsPostsId() {
        return cmsPostsId;
    }

    public String getImage_alt() {
        return image_alt;
    }

    public Integer getImageHeight() {
        return imageHeight;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public Integer getImageWidth() {
        return imageWidth;
    }

    /**
     * @param cmsPostImagesId
     *            the cmsPostImagesId to set
     */
    public void setCmsPostImagesId(final String cmsPostImagesId) {
        this.cmsPostImagesId = cmsPostImagesId;
    }

    public void setCmsPostsId(final String cmsPostsId) {
        this.cmsPostsId = cmsPostsId;
    }

    public void setImage_alt(final String image_alt) {
        this.image_alt = image_alt;
    }

    public void setImageHeight(final Integer imageHeight) {
        this.imageHeight = imageHeight;
    }

    public void setImageLocation(final String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public void setImageWidth(final Integer imageWidth) {
        this.imageWidth = imageWidth;
    }

}
