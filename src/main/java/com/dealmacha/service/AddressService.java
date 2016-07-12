package com.dealmacha.service;

import com.dealmacha.dao.AddressRepository;
import com.dealmacha.domain.Address;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AddressService implements IAddressService {

    private static final Logger LOGGER = Logger.getLogger(AddressService.class);
    @Autowired
    private AddressRepository addressRepository;

    /**
     * {@inheritDoc}
     *
     * @see com.chitfund.service.IAddressService#create(com.chitfund.domain.Address)
     */
    @Override
    @Transactional
    public Address create(final Address address) {
        return addressRepository.save(address);

    }

    /**
     * {@inheritDoc}
     *
     * @see com.chitfund.service.IAddressService#deleteAddress(java.lang.String)
     */
    @Override
    public void deleteAddress(final String addressId) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     *
     * @see com.chitfund.service.IAddressService#getAddress(java.lang.String)
     */
    @Override
    public Address getAddress(final String addressId) {
        Address address = addressRepository.findOne(addressId);
        return address;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.chitfund.service.IAddressService#getAll()
     */
    @Override
    public List<Address> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.chitfund.service.IAddressService#getCity(java.lang.String)
     */
    /* @Override
     public List<Address> getCity(final String city) {
         List<Address> addressmodel = new ArrayList<Address>();
         String type = "Distributor";

         addressmodel = addressRepository.getCities(type);
         LOGGER.info("address mode ls are" + addressmodel.size());

         return addressmodel;
     }*/

    /**
     * {@inheritDoc}
     *
     * @see com.chitfund.service.IAddressService#getEntity(java.lang.String)
     */
    /*  @Override
      public Address getEntity(final String entityId) {
          return null;
      }*/

    /**
     * {@inheritDoc}
     *
     * @see com.chitfund.service.IAddressService#getState(java.lang.String)
     */
    /* @Override
     public List<Address> getState(final String state) {
         List<Address> addressmodel = new ArrayList<Address>();
         String type = "Distributor";

         addressmodel = addressRepository.getCities(type);
         LOGGER.info("address mode ls are" + addressmodel.size());

         return addressmodel;
     }*/

    @Override
    public List<Address> getCity(final String city) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @param addressRepository
     *            the addressRepository to set
     */
    @Autowired
    public void setAddressRepository(final AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.chitfund.service.IAddressService#updateEntity(com.chitfund.domain.Address)
     */
    @Override
    public Address updateAddress(final Address address) {
        /*
                Address ud = new Address();
                ud.setCity(address.getCity());
                ud.setCountry(address.getCountry());
                ud.setDistrict(address.getDistrict());
                ud.setLine1(address.getLine1());
                ud.setState(address.getState());
                ud.setTown(address.getTown());
                ud.setPincode(address.getPincode());
                ud.setType(address.getType());

                addressRepository.update(address.getLine1(), address.getTown(), address.getCity(), address.getDistrict(), address.getState(),
                        address.getCountry(), address.getPincode(), address.getId());*/

        return addressRepository.save(address);
    }

}
