/**
 *
 */
package com.dealmacha.businessdelegate.service;

import com.dealmacha.businessdelegate.domain.IKeyBuilder;
import com.dealmacha.domain.Address;
import com.dealmacha.model.AddressModel;
import com.dealmacha.service.IAddressService;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

/**
 * @author lenovo
 *
 */
@Service
public class AddressBusinessDelegate implements IBusinessDelegate<AddressModel, AddressContext, IKeyBuilder<String>, String> {

    Logger LOGGER = Logger.getLogger(AddressBusinessDelegate.class);
    //private IEntityService entityService;
    //private IEntityBranchService entityBranchService;
    @Autowired
    private ConversionService conversionService;
    @Autowired
    private IAddressService addressService;

    @Override
    public AddressModel create(AddressModel model) {

        Address address = new Address();
        address.setCity(model.getCity());
        address.setCountry(model.getCountry());
        address.setDistrict(model.getDistrict());
        address.setLine1(model.getLine1());
        address.setState(model.getState());
        address.setType(model.getType());
        address.setDistrict(model.getDistrict());
        address.setPincode(model.getPincode());
        address.setArea(model.getArea());
        address.setId(model.getId());
        address = addressService.create(address);
        model = conversionService.convert(address, AddressModel.class);

        return model;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.chitfund.businessdelegate.service.IBusinessDelegate#delete(com.chitfund.businessdelegate.domain.IKeyBuilder,
     *      com.chitfund.businessdelegate.service.IBusinessDelegateContext)
     */
    @Override
    public void delete(final IKeyBuilder<String> keyBuilder, final AddressContext context) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     *
     * @see com.chitfund.businessdelegate.service.IBusinessDelegate#edit(com.chitfund.businessdelegate.domain.IKeyBuilder,
     *      com.chitfund.businessdelegate.domain.IModel)
     */
    @Override
    public AddressModel edit(final IKeyBuilder<String> keyBuilder, final AddressModel model) {
        Address address = addressService.getAddress(keyBuilder.build().toString());

        address.setCity(model.getCity());
        address.setCountry(model.getCountry());
        address.setDistrict(model.getDistrict());
        address.setLine1(model.getLine1());
        address.setState(model.getState());
        address.setArea(model.getArea());
        address.setDistrict(model.getDistrict());
        address.setPincode(model.getPincode());
        address = addressService.updateAddress(address);
        model.setId(address.getId());
        return model;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.chitfund.businessdelegate.service.IBusinessDelegate#getByKey(com.chitfund.businessdelegate.domain.IKeyBuilder,
     *      com.chitfund.businessdelegate.service.IBusinessDelegateContext)
     */
    @Override
    public AddressModel getByKey(final IKeyBuilder<String> keyBuilder, final AddressContext context) {

        Address address = addressService.getAddress(keyBuilder.build().toString());
        AddressModel addressModel = conversionService.convert(address, AddressModel.class);

        return addressModel;
    }

    @Override
    public Collection<AddressModel> getCollection(final AddressContext context) {
        // TODO Auto-generated method stub
        return null;
    }

    /*@Autowired
    public void setEntityService(final IEntityService entityService) {
        this.entityService = entityService;
    }*/

    @Autowired
    public void setConversionService(final ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Autowired
    public void setIAddressService(final IAddressService addressService) {
        this.addressService = addressService;
    }

    /*@Autowired
    public void setIEntityBranchService(final IEntityBranchService entityBranchService) {
        this.entityBranchService = entityBranchService;
    }*/
}
