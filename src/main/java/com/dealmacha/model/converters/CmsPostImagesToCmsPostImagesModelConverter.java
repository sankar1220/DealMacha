package com.dealmacha.model.converters;

import com.dealmacha.domain.CmsPostsImages;
import com.dealmacha.model.CmsPostImagesModel;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Class to convert address domain to address model
 *
 * @author Naveen
 *
 */
@Component("cmsPostImagesToCmsPostImagesModelConverter")
public class CmsPostImagesToCmsPostImagesModelConverter implements Converter<CmsPostsImages, CmsPostImagesModel> {

    private ObjectFactory<CmsPostImagesModel> cmsPostImagesModelFactory;

    @Override
    public CmsPostImagesModel convert(final CmsPostsImages source) {
        CmsPostImagesModel cmsPostImagesModel = cmsPostImagesModelFactory.getObject();
        BeanUtils.copyProperties(source, cmsPostImagesModel);

        return cmsPostImagesModel;
    }

    @Autowired
    public void setCmsPostImagesFactory(final ObjectFactory<CmsPostImagesModel> cmsPostImagesModelFactory) {
        this.cmsPostImagesModelFactory = cmsPostImagesModelFactory;
    }

}
