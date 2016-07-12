/**
 *
 */
package com.dealmacha.model;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Jay
 *
 */

@Component("cmsPostImagesModel")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CmsPostImagesModel extends AbstractModel {

    private String cmsPostsId;
    private Integer imageWidth;
    private Integer imageHeight;
    private String image_alt;
    private String imageLocation;

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
