/**
 *
 */
package com.dealmacha.businessdelegate.service;

import com.dealmacha.businessdelegate.domain.IKeyBuilder;
import com.dealmacha.domain.CmsBlock;
import com.dealmacha.domain.CmsSection;
import com.dealmacha.model.CmsBlockModel;
import com.dealmacha.service.ICmsBlockService;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Naveen-PC
 *
 */
@Service
public class CmsBlockBusinessDelegate implements IBusinessDelegate<CmsBlockModel, CmsBlockContext, IKeyBuilder<String>, String> {

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.businessdelegate.service.IBusinessDelegate#create(com.dealmacha.businessdelegate.domain.IModel)
     */
    @Autowired
    private ICmsBlockService cmsBlockService;

    @Override
    public CmsBlockModel create(final CmsBlockModel model) {
        // TODO Auto-generated method stub
        CmsBlock cmsBlock = new CmsBlock();
        cmsBlock.setBlockName(model.getBlockName());
        cmsBlock.setBlockTitle(model.getBlockTitle());
        cmsBlock.setBlockStyle(model.getBlockStyle());
        cmsBlock.setBlockPostsMin(model.getBlockPostsMin());
        cmsBlock.setBlockPostsMax(model.getBlockPostsMax());
        cmsBlock.setPostsImageHeight(Integer.parseInt(model.getPostsImageHeight()));
        cmsBlock.setPostsImageWidth(Integer.parseInt(model.getPostsImageWidth()));
        cmsBlock.setOrderOfPlace(Integer.parseInt(model.getOrderOfPlace()));
        CmsSection cmsSection = new CmsSection();
        cmsSection.setId(model.getCmsSectionId());
        cmsBlock.setCmsSection(cmsSection);
        cmsBlock = cmsBlockService.create(cmsBlock);
        if (cmsBlock.getId() != null) {
            model.setId(cmsBlock.getId());
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
    public void delete(final IKeyBuilder<String> keyBuilder, final CmsBlockContext context) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.businessdelegate.service.IBusinessDelegate#edit(com.dealmacha.businessdelegate.domain.IKeyBuilder,
     *      com.dealmacha.businessdelegate.domain.IModel)
     */
    @Override
    public CmsBlockModel edit(final IKeyBuilder<String> keyBuilder, final CmsBlockModel model) {
        // TODO Auto-generated method stub

        if (model.getBlockName() != null) {
            CmsBlock cmsBlock = cmsBlockService.getCmsBlockByName(model.getBlockName());
            cmsBlock.setBlockTitle(model.getBlockTitle());
            cmsBlock.setOrderOfPlace(Integer.parseInt(model.getOrderOfPlace()));
            cmsBlock = cmsBlockService.updateCmsBlock(cmsBlock);
            if (cmsBlock.getId() != null) {
                model.setId(cmsBlock.getId());
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
    public CmsBlockModel getByKey(final IKeyBuilder<String> keyBuilder, final CmsBlockContext context) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.businessdelegate.service.IBusinessDelegate#getCollection(com.dealmacha.businessdelegate.service.IBusinessDelegateContext)
     */
    @Override
    public Collection<CmsBlockModel> getCollection(final CmsBlockContext context) {
        // TODO Auto-generated method stub
        return null;
    }

}
