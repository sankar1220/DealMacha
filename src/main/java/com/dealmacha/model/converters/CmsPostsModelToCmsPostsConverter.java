package com.dealmacha.model.converters;

import com.dealmacha.domain.CmsPosts;
import com.dealmacha.model.CmsPostsModel;

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
@Component("cmsPostsModelToCmsPostsConverter")
public class CmsPostsModelToCmsPostsConverter implements Converter<CmsPostsModel, CmsPosts> {

    private ObjectFactory<CmsPosts> cmsPostsFactory;

    @Override
    public CmsPosts convert(final CmsPostsModel source) {
        CmsPosts cmsPosts = cmsPostsFactory.getObject();
        BeanUtils.copyProperties(source, cmsPosts);

        return cmsPosts;
    }

    @Autowired
    public void setCmsPostsFactory(final ObjectFactory<CmsPosts> cmsPostsFactory) {
        this.cmsPostsFactory = cmsPostsFactory;
    }

}
