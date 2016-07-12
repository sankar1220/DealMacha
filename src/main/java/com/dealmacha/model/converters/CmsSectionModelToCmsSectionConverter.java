package com.dealmacha.model.converters;

import com.dealmacha.domain.CmsSection;
import com.dealmacha.model.CmsSectionModel;

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
@Component("cmsSectionModelToCmsSectionConverter")
public class CmsSectionModelToCmsSectionConverter implements Converter<CmsSectionModel, CmsSection> {

    private ObjectFactory<CmsSection> cmsSectionFactory;

    @Override
    public CmsSection convert(final CmsSectionModel source) {
        CmsSection cmsSection = cmsSectionFactory.getObject();
        BeanUtils.copyProperties(source, cmsSection);

        return cmsSection;
    }

    @Autowired
    public void setCmsSectionFactory(final ObjectFactory<CmsSection> cmsSectionFactory) {
        this.cmsSectionFactory = cmsSectionFactory;
    }

}
