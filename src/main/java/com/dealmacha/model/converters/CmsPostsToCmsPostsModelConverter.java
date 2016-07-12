package com.dealmacha.model.converters;

import com.dealmacha.domain.CmsPosts;
import com.dealmacha.domain.CmsPostsImages;
import com.dealmacha.model.CmsPostsModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Class to convert address domain to address model
 *
 * @author Naveen
 *
 */
@Component("cmsPostsToCmsPostsModelConverter")
public class CmsPostsToCmsPostsModelConverter implements Converter<CmsPosts, CmsPostsModel> {

    private ObjectFactory<CmsPostsModel> cmsPostsModelFactory;
    @Autowired
    private ConversionService conversionService;

    @Override
    public CmsPostsModel convert(final CmsPosts source) {
        CmsPostsModel cmsPostsModel = cmsPostsModelFactory.getObject();
        BeanUtils.copyProperties(source, cmsPostsModel);
        if (source.getOrderOfPlace() != null) {
            cmsPostsModel.setOrderOfPlace(source.getOrderOfPlace().toString());
        }
        if (source.getCmsBlock() != null) {
            cmsPostsModel.setCmsBlockId(source.getCmsBlock().getId());
            cmsPostsModel.setCmsBlockName(source.getCmsBlock().getBlockName());
        }
        if (source.getCmsPostsImageses() != null) {
            List<CmsPostsImages> list = new ArrayList<CmsPostsImages>(source.getCmsPostsImageses());
            if (list.size() != 0) {
                cmsPostsModel.setPostsImageId(list.get(0).getId());
                cmsPostsModel.setPostsImageUrl(list.get(0).getImageLocation());
                cmsPostsModel.setPostsImageAlt(list.get(0).getImageAlt());

                if (list.get(0).getImageHeight() != null) {
                    cmsPostsModel.setPostsImageHeight(list.get(0).getImageHeight().toString());
                }
                if (list.get(0).getImageWidth() != null) {
                    cmsPostsModel.setPostsImageWidth(list.get(0).getImageWidth().toString());
                }
            }
            /*cmsPostsModel.setPostsImageId(source.getCmsPostsImageses());*/
        }
        if (source.getPostsExpiryTime() != null) {

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            cmsPostsModel.setPostsExpiryTime(sdf.format(source.getPostsExpiryTime()));
        }
        /*if (CollectionUtils.isNotEmpty(source.getCmsPostsImageses())) {
            List<CmsPostImagesModel> converted = (List<CmsPostImagesModel>) conversionService.convert(source.getCmsPostsImageses(),
                    TypeDescriptor.forObject(source.getCmsPostsImageses()),
                    CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), CmsPostImagesModel.class));
            cmsPostsModel.getCmsPostImageModel().addAll(converted);
        }*/
        return cmsPostsModel;
    }

    @Autowired
    public void setCmsPostsFactory(final ObjectFactory<CmsPostsModel> cmsPostsModelFactory) {
        this.cmsPostsModelFactory = cmsPostsModelFactory;
    }

}
