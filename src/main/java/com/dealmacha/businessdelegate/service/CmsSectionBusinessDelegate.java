/**
 *
 */
package com.dealmacha.businessdelegate.service;

import com.dealmacha.businessdelegate.domain.IKeyBuilder;
import com.dealmacha.domain.CmsSection;
import com.dealmacha.model.CmsSectionModel;
import com.dealmacha.service.ICmsSectionService;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

/**
 * @author Jay
 *
 */
@Service

public class CmsSectionBusinessDelegate implements IBusinessDelegate<CmsSectionModel, CmsSectionContext, IKeyBuilder<String>, String> {

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.businessdelegate.service.IBusinessDelegate#create(com.dealmacha.businessdelegate.domain.IModel)
     */
    @Autowired
    private ICmsSectionService cmsSectionService;
    @Autowired
    private ConversionService conversionService;

    @Override
    public CmsSectionModel create(final CmsSectionModel model) {
        // TODO Auto-generated method stub
        CmsSection cmsSection = new CmsSection();
        cmsSection.setSectionName(model.getSectionName());
        cmsSection.setSectionTitle(model.getSectionTitle());
        cmsSection = cmsSectionService.create(cmsSection);
        if (cmsSection.getId() != null) {
            model.setId(cmsSection.getId());
        }
        return model;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.businessdelegate.service.IBusinessDelegate#delete(com.dealmacha.businessdelegate.domain.IKeyBuilder,
     *      com.dealmacha.businessdelegate.service.IBusinessDelegateContext)
     */
    @Override
    public void delete(final IKeyBuilder<String> keyBuilder, final CmsSectionContext context) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.businessdelegate.service.IBusinessDelegate#edit(com.dealmacha.businessdelegate.domain.IKeyBuilder,
     *      com.dealmacha.businessdelegate.domain.IModel)
     */
    @Override
    public CmsSectionModel edit(final IKeyBuilder<String> keyBuilder, final CmsSectionModel model) {
        // TODO Auto-generated method stub
        if (model.getSectionName() != null) {
            CmsSection cmsSection = cmsSectionService.getSectionByName(model.getSectionName());
            cmsSection.setSectionTitle(model.getSectionTitle());

            cmsSection = cmsSectionService.updateCmsSection(cmsSection);
            if (cmsSection.getId() != null) {
                model.setId(cmsSection.getId());
            }
        }
        return model;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.businessdelegate.service.IBusinessDelegate#getByKey(com.dealmacha.businessdelegate.domain.IKeyBuilder,
     *      com.dealmacha.businessdelegate.service.IBusinessDelegateContext)
     */
    @Override
    public CmsSectionModel getByKey(final IKeyBuilder<String> keyBuilder, final CmsSectionContext context) {

        CmsSection cmsSection = new CmsSection();

        // TODO Auto-generated method stub
        if (context.getSectionName() != null) {
            cmsSection = cmsSectionService.getSectionByName(context.getSectionName());
        }

        CmsSectionModel model = conversionService.convert(cmsSection, CmsSectionModel.class);
        return model;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.businessdelegate.service.IBusinessDelegate#getCollection(com.dealmacha.businessdelegate.service.IBusinessDelegateContext)
     */
    @Override
    public Collection<CmsSectionModel> getCollection(final CmsSectionContext context) {
        // TODO Auto-generated method stub
        return null;
    }

}
