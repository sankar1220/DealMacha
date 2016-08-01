package com.dealmacha.model.converters;

import com.dealmacha.domain.CmsBlock;
import com.dealmacha.model.CmsBlockModel;
import com.dealmacha.model.CmsPostsModel;
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
 * Class to convert address domain to address model
 *
 * @author Naveen
 *
 */
@Component("cmsBlockToCmsBlockModelConverter")
public class CmsBlockToCmsBlockModelConverter implements Converter<CmsBlock, CmsBlockModel> {

	private ObjectFactory<CmsBlockModel> cmsBlockModelFactory;
	@Autowired
	private ConversionService conversionService;

	@Override
	public CmsBlockModel convert(final CmsBlock source) {
		CmsBlockModel cmsBlockModel = cmsBlockModelFactory.getObject();
		BeanUtils.copyProperties(source, cmsBlockModel);
		if (source.getOrderOfPlace() != null) {
			cmsBlockModel.setOrderOfPlace(source.getOrderOfPlace().toString());
		}
		if (CollectionUtils.isNotEmpty(source.getCmsPostses()))

		{
			List<CmsPostsModel> converted = (List<CmsPostsModel>) conversionService.convert(source.getCmsPostses(),
					TypeDescriptor.forObject(source.getCmsPostses()),
					CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), CmsPostsModel.class));
			cmsBlockModel.getCmsPostsModels().addAll(converted);
		}
		return cmsBlockModel;
	}

	@Autowired
	public void setCmsBlockFactory(final ObjectFactory<CmsBlockModel> cmsBlockModelFactory) {
		this.cmsBlockModelFactory = cmsBlockModelFactory;
	}

}
