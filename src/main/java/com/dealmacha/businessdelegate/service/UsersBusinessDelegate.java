package com.dealmacha.businessdelegate.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import com.dealmacha.DBSequences;
import com.dealmacha.businessdelegate.domain.IKeyBuilder;
import com.dealmacha.dao.UsersRepository;
import com.dealmacha.domain.Account;
import com.dealmacha.domain.Address;
import com.dealmacha.domain.Roles;
import com.dealmacha.domain.UserAddress;
import com.dealmacha.domain.Users;
import com.dealmacha.model.AccountModel;
import com.dealmacha.model.UserAddressModel;
import com.dealmacha.model.UsersModel;
import com.dealmacha.service.AccountService;
import com.dealmacha.service.IAddressService;
import com.dealmacha.service.IMailConfigService;
import com.dealmacha.service.IMailService;
import com.dealmacha.service.IRolesService;
import com.dealmacha.service.IUsersService;
import com.dealmacha.service.UserAddressService;

/**
 * @author lenovo
 *
 */
@Service
@PropertySource("classpath:mail.properties")
//@ImportResource("classpath:spring-beans.xml")
public class UsersBusinessDelegate implements IBusinessDelegate<UsersModel, UsersContext, IKeyBuilder<String>, String> {

    Logger LOGGER = Logger.getLogger(UsersBusinessDelegate.class);
    @Autowired
    private IAddressService addressService;
    @Autowired
    private ConversionService conversionService;
    @Autowired
    private IUsersService usersService;
    @Autowired
    private IMailConfigService mailConfigService;
    /*  @Autowired
      private AddressRepository addressRepository;*/
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private IMailService mailService;
    @Autowired
    private UserAddressService userAddressService;
    @Autowired
    private AccountService accountService;
    /*  @Autowired
      private AddressService addressService;*/
    @Autowired
    private IRolesService rolesService;
    @Value("${mail.from}")
    private String mailFrom;
    @Value("${mail.username}")
    private String mailUserName;
    @Value("${url}")
    private String url;

    public boolean checkEmailExists(final String email) {
        return usersRepository.findByEmailId(email) != null;
    }

    public boolean checkMobileExists(final String mobile) {
        return usersRepository.findByMobileNo(mobile) != null;
    }

    @Override
    public UsersModel create(final UsersModel model) {
        Users users = new Users();
        users.setEmailId(model.getEmailId());
        users.setUserName(model.getUserName());
        users.setMobileNo(model.getMobileNo());
        users.setPassword(model.getPassword());
        if(model.getUserType()!=null){
        	  users.setUserType(model.getUserType());
        }else{
        	users.setUserType("CUSTOMER");
        }
        if(model.getStatus()!=null){
        users.setStatus(model.getStatus());
        }else{
        users.setStatus("INACTIVE");
        }
        users.setAlternateMobileNo(model.getAlternateMobileNo());
        users.setAge(model.getAge());

        Integer i = usersService.getMaxCode();
        if (i == null || i == 0) {
            i = 0;
            users.setUserCount(i + 1);
        }
        else {
            users.setUserCount(i + 1);
        }
        Integer co = i + 1;
        String m = DBSequences.USERS.getSequenceName();
        String mc = m.concat(co.toString());
        users.setUserCode(mc);
        users.setCreatedDate(new Date());

        if (model.getPassword() != null && model.getConfirmPassword() != null) {
            if (model.getPassword().equals(model.getConfirmPassword())) {
                model.setEmailStatus("SUCCESS");
                users = usersService.create(users);
                if (users.getId() != null) {
                    Roles r = new Roles();
                    r.setRoleDetails("role created");
                    r.setEnable("true");
                    if (users.getUserType().equals("CUSTOMER")) {
                        r.setRoleName("ROLE_USER");
                    }
                    if(model.getUserType()!=null){
                    if (model.getUserType().equals("ADMINUSER")) {
                        r.setRoleName("ROLE_ADMIN");
                    }
                    }
                    r.setUsers(users);
                    r = rolesService.create(r);
                }
            }
            else {
                model.setEmailStatus("Password and ConfirmPassword Not Match");
            }
        }
     
        if (users.getId() != null) {
            if (model.getUserAddressModel() != null) {
                Set<UserAddress> userAddress = new HashSet<UserAddress>();
                for (UserAddressModel userAddressModel : model.getUserAddressModel()) {
                    UserAddress uadd = new UserAddress();
                    Address address = new Address();
                    address.setLine1(userAddressModel.getLine1());
                    address.setCity(userAddressModel.getCity());
                    address.setDistrict(userAddressModel.getDistrict());
                    address.setState(userAddressModel.getState());
                    address.setCountry(userAddressModel.getCountry());
                    address.setPincode(userAddressModel.getPincode());
                    address.setType(userAddressModel.getType());
                    address.setArea(userAddressModel.getArea());
                    address = addressService.create(address);
                    uadd.setAddress(address);
                    uadd.setUsers(users);
                    /* uadd.setStatus(userAddressModel.getStatus());*/
                    userAddress.add(uadd);
                }
                //  users.setUserAddress(userAddress);
                users = usersService.addUsersAddress(users, userAddress);
            }
        }
     if(users.getId()!=null){
    		Set<Account> accounts = new HashSet<Account>();
    		Account account = new Account();
    		BigDecimal cashBackAmount = new BigDecimal(0.00);
    		account.setCashbackAmount(cashBackAmount);
    		account.setUsers(users);
    		accounts.add(account);
    		
    		users=usersService.addAccount(users,accounts);
    	
    }
        
        if (users.getEmailStatus() != null) {
            if (users.getEmailStatus().equals("DUPLICATEE")) {
                model.setEmailStatus("DUPLICATEE");
                model.setUserEmailIdStatus("You are Already Registered. Please Login !");
            }
            if (users.getEmailStatus().equals("DUPLICATEM")) {
                model.setEmailStatus("DUPLICATEM");
                model.setUserMobileNoStatus("You are Already Registered With MobileNo. Please Login !");
            }
            if (users.getEmailStatus().equals("DUPLICATEEM")) {
                model.setEmailStatus("DUPLICATEEM");
                model.setUserMobileNoStatus("Email-Id And Phone-No Already Exists. Please Login !");
            }
        }
        
        /* if (model.getAddressModel() != null) {
            Set<Address> addresses = new HashSet<Address>();
            for (AddressModel addressModel : model.getAddressModel()) {
                Address address = new Address();
                address.setArea(addressModel.getArea());
                address.setCity(addressModel.getCity());
                address.setCountry(addressModel.getCountry());
                address.setDistrict(addressModel.getDistrict());
                address.setLine1(addressModel.getLine1());
                address.setPincode(addressModel.getPincode());
                address.setState(address.getState());
                address.setType(addressModel.getType());
                addresses.add(address);
            }
            users.setAddresses(addresses);
        }*/
        /* if (users.getId() != null) {
             if (model.getUserImagesModel() != null) {
                 List<UserImages> userImag = new ArrayList<UserImages>();
                 for (UserImagesModel userImagesModel : model.getUserImagesModel()) {
                     UserImages userImages = new UserImages();
                     userImages.setUsers(users);
                     userImages.setImageName(userImagesModel.getImageName());
                     userImages.setImageType(userImagesModel.getImageType());
                     userImages.setImageLocation(userImagesModel.getImageLocation());
                     userImag.add(userImages);
                 }

                 users = usersService.addUserImages(users, userImag);
             }/
         }*/
        /*     if (model.getAddressModels() != null) {
                 Set<Address> addresses = new HashSet<Address>();
                 for (AddressModel addressModel : model.getAddressModels()) {
                     Address address = new Address();
                     address.setLine1(addressModel.getLine1());
                     address.setCity(addressModel.getCity());
                     address.setDistrict(addressModel.getDistrict());
                     address.setState(addressModel.getState());
                     address.setCountry(addressModel.getCountry());
                     address.setPincode(addressModel.getPincode());
                     address.setType(addressModel.getType());
                     address.setArea(addressModel.getArea());
                     addresses.add(address);
                 }
                 users.setAddresses(addresses);

             }*/
   /*   if (users.getId() != null) {
            MailConfig mcfgs = mailConfigService.getUserRegistrationMailConfig();
            if (mcfgs != null) {
                Mail mail = new Mail();
                mail.setMailFrom(mailFrom);
                mail.setMailTo(users.getEmailId());
                mail.setMailSubject("Account Activation Request");
                mailService.sendUserRegistraionMail(mail, users);
            }
        }*/
        /*   model = conversionService.convert(users, UsersModel.class);*/
        model.setId(users.getId());
        return model;

    }

    @Override
    public void delete(final IKeyBuilder<String> keyBuilder, final UsersContext context) {
        // TODO Auto-generated method stub

    }

    @Override
    public UsersModel edit(final IKeyBuilder<String> keyBuilder, final UsersModel usersModel) {
        Users user = usersService.getUsers(keyBuilder.build().toString());
        if (usersModel.getAge() != null) {
            user.setAge(usersModel.getAge());
        }
        else {
            user.setAge(user.getAge());
        }
        if (usersModel.getUserName() != null) {
            user.setUserName(usersModel.getUserName());
        }
        if (usersModel.getUserType() != null) {
            user.setUserType(usersModel.getUserType());
        }
        else {
            user.setUserType(user.getUserType());
        }
        if(usersModel.getEmailId()!=null){
        	user.setEmailId(usersModel.getEmailId());
        }
        else {
        user.setEmailId(user.getEmailId());
        }
        if(usersModel.getMobileNo()!=null){
        	user.setMobileNo(usersModel.getMobileNo());
        }
        else {
        user.setMobileNo(user.getMobileNo());
        }

        if (usersModel.getConfirmPassword() != null && usersModel.getNewPassword() != null) {
            if (usersModel.getPassword().equals(user.getPassword())) {
                if (usersModel.getConfirmPassword().equals(usersModel.getNewPassword())) {
                    user.setPassword(usersModel.getConfirmPassword());
                }
            }
        }
        else {
            user.setPassword(user.getPassword());
        }
        if (usersModel.getStatus() != null) {
            user.setStatus(usersModel.getStatus());
        }
        else {
            user.setStatus(user.getStatus());
        }

        if (usersModel.getAlternateMobileNo() != null) {
            user.setAlternateMobileNo(usersModel.getAlternateMobileNo());
        }
        else {
            user.setAlternateMobileNo(user.getAlternateMobileNo());
        }

        if (checkEmailExists(user.getEmailId())) {
            user.setEmailStatus("Duplicate");
        }
        if (checkMobileExists(user.getMobileNo())) {
            user.setAuthenticateStatus("DuplicateM");
            user.setEmailStatus("DuplicateM");
        }
        user.setAuthenticateStatus("Success");
        user.setEmailStatus("Success");
        user = usersService.updateUsers(user);

        return usersModel;

    }

    @Override
    public UsersModel getByKey(final IKeyBuilder<String> keyBuilder, final UsersContext context) {
        Users users = usersService.getUsers(keyBuilder.build().toString());
        UsersModel userModel = conversionService.convert(users, UsersModel.class);
        return userModel;
    }

    @Override
    public Collection<UsersModel> getCollection(final UsersContext context) {

        List<Users> users = new ArrayList<Users>();
        if (context.getAll() != null) {
            users = usersService.getAll();
        }
        if (context.getUserType() != null) {
            users = usersService.getByUserType(context.getUserType());
            
        }
        if(context.getAll()!=null && context.getActivatedUsers()!=null) {
        	users=usersService.getActivatedUsers();
        }
        if (context.getUserId() != null && context.getConfirmPassword() != null && context.getNewPassword() != null
                && context.getChangePassword() != null && context.getPassword() != null) {
            Users user = usersService.getByChangePassword(context.getUserId(), context.getConfirmPassword(), context.getNewPassword(),
                    context.getPassword());
            users.add(user);
        }
        List<UsersModel> usersModels = (List<UsersModel>) conversionService.convert(users, TypeDescriptor.forObject(users),
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(UsersModel.class)));
        return usersModels;

    }

    @Autowired
    public void setConversionService(final ConversionService conversionService) {
        this.conversionService = conversionService;
    }

}
