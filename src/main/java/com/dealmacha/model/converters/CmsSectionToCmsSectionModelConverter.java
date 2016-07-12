package com.dealmacha.model.converters;

import com.dealmacha.domain.CmsSection;
import com.dealmacha.model.CmsBlockModel;
import com.dealmacha.model.CmsSectionModel;
import com.dealmacha.util.CollectionTypeDescriptor;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Class to convert CmsSection domain to CmsSection model
 *
 * @author Naveen
 *
 */
@Component("cmsSectionToCmsSectionModelConverter")
public class CmsSectionToCmsSectionModelConverter implements Converter<CmsSection, CmsSectionModel> {

    private ObjectFactory<CmsSectionModel> cmsSectionModelFactory;
    @Autowired
    private ConversionService conversionService;

    @Override
    public CmsSectionModel convert(final CmsSection source) {
        CmsSectionModel cmsSectionModel = cmsSectionModelFactory.getObject();
        BeanUtils.copyProperties(source, cmsSectionModel);
        if (CollectionUtils.isNotEmpty(source.getCmsBlocks())) {
            List<CmsBlockModel> converted = (List<CmsBlockModel>) conversionService.convert(source.getCmsBlocks(),
                    TypeDescriptor.forObject(source.getCmsBlocks()),
                    CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), CmsBlockModel.class));
            cmsSectionModel.getCmsBlockModels().addAll(converted);
        }
        return cmsSectionModel;
    }

    @Autowired
    public void setCmsSectionFactory(final ObjectFactory<CmsSectionModel> cmsSectionModelFactory) {
        this.cmsSectionModelFactory = cmsSectionModelFactory;
    }

}
