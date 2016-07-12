package com.dealmacha.model.converters;

import com.dealmacha.domain.CmsBlock;
import com.dealmacha.model.CmsBlockModel;

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
@Component("cmsBlockModelToCmsBlockConverter")
public class CmsBlockModelToCmsBlockConverter implements Converter<CmsBlockModel, CmsBlock> {

    private ObjectFactory<CmsBlock> cmsBlockFactory;

    @Override
    public CmsBlock convert(final CmsBlockModel source) {
        CmsBlock cmsBlock = cmsBlockFactory.getObject();
        BeanUtils.copyProperties(source, cmsBlock);

        return cmsBlock;
    }

    @Autowired
    public void setCmsBlockFactory(final ObjectFactory<CmsBlock> cmsBlockFactory) {
        this.cmsBlockFactory = cmsBlockFactory;
    }

}
