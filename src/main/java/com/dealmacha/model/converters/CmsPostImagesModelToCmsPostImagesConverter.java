package com.dealmacha.model.converters;

import com.dealmacha.domain.CmsPostsImages;
import com.dealmacha.model.CmsPostImagesModel;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Class to convert address model domain to address
 *
 * @author Naveen
 *
 */
@Component("cmsPostImagesModelToCmsPostImagesConverter")
public class CmsPostImagesModelToCmsPostImagesConverter implements Converter<CmsPostImagesModel, CmsPostsImages> {

    private ObjectFactory<CmsPostsImages> cmsPostImagesFactory;

    @Override
    public CmsPostsImages convert(final CmsPostImagesModel source) {
        CmsPostsImages cmsPostImages = cmsPostImagesFactory.getObject();
        BeanUtils.copyProperties(source, cmsPostImages);

        return cmsPostImages;
    }

    @Autowired
    public void setCmsPostImagesFactory(final ObjectFactory<CmsPostsImages> cmsPostImagesFactory) {
        this.cmsPostImagesFactory = cmsPostImagesFactory;
    }

}
