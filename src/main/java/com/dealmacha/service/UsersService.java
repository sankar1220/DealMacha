package com.dealmacha.service;

import com.dealmacha.dao.UsersRepository;
import com.dealmacha.domain.Account;
import com.dealmacha.domain.UserAddress;
import com.dealmacha.domain.Users;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@PropertySource("classpath:mail.properties")
//@ImportResource("classpath:spring-beans.xml")
public class UsersService implements IUsersService {

    private static final Logger LOGGER = Logger.getLogger(UsersService.class);
    @Autowired
    private UsersRepository usersRepository;
    // @Autowired
    //private UserImagesRepository userImagesRepository;
    @Autowired
    private IAddressService addressService;
    /* @Autowired
    private IUserImagesService userImagesService;*/
    @Autowired
    private UserAddressService userAddressService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private JavaMailSender mailSender;
    @Value("${mail.from}")
    private String mailFrom;
    @Value("${mail.username}")
    private String mailUserName;
    @Value("${url}")
    private String url;

    /*  *//**
           * {@inheritDoc}
           *
           * @see com.chitfund.service.IUsersService#addImages(com.chitfund.domain.Users, java.util.List)
           */
    /*
    @Override
    public Users addImages(final Users users, final List<UserImages> userImages) {
     Validate.notNull(users, "user must not be null");
     Set<UserImages> addImages = new HashSet<UserImages>(userImages);

     for (UserImages uImages : userImages) {
         UserImages userImages1 = new UserImages();
         String s = uImages.getImageName();

         if (uImages.getId() != null && uImages.getId() != "") {
             UserImages userImages2 = userImagesRepository.findOne(uImages.getId());
             s = s.replaceAll("\\\\", "/");
             userImages2.setImageName(s);
             userImages2.setImageType(uImages.getImageType());
             userImages2.setImageLocation(uImages.getImageLocation());
             userImages1 = userImagesRepository.save(userImages2);
         }
         else {
             s = s.replaceAll("\\\\", "/");
             userImages1.setImageName(spImages.getImageLocation());
             userImages1.setImageType(uImages.getImageType());
             userImages1.setImageLocation(uImages.getImageLocation());
             userImages1 = userImagesRepository.save(userImages1);
         }

     }
     users.setUserImageses(addImages);
     return users;
    }
     */
    /**
     * {@inheritDoc}
     *
     * @see com.chitfund.service.IUsersService#addRoles(com.chitfund.domain.Users, java.util.List)
     */
    /*  @Override
    public Users addRoles(final Users users, final List<UserRole> userRoles) {
        Validate.notNull(users, "user must not be null");
        Users newUser = new Users();
        Set<UserRole> addRoles = new HashSet<UserRole>(userRoles);
        newUser.setId(users.getId());
        usersRepository.save(newUser);
        return newUser;
    }*/

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.IUsersService#addUsersAddress(com.dealmacha.domain.Users, java.util.Set)
     */
    @Override
    public Users addUsersAddress(final Users users, final Set<UserAddress> userAddress) {
        Validate.notNull(users, "users must not be null");
        Set<UserAddress> addUserAddress = new HashSet<UserAddress>(userAddress);
        for (UserAddress uAddress : userAddress) {
            UserAddress usrAddress = new UserAddress();
            if (uAddress.getId() != null) {
                usrAddress = userAddressService.getUserAddress(uAddress.getId());
                usrAddress.setId(usrAddress.getId());
                usrAddress.setAddress(uAddress.getAddress());
                /*usrAddress.setStatus(uAddress.getStatus());*/
                usrAddress.setUsers(usrAddress.getUsers());
                usrAddress = userAddressService.updateUserAddress(usrAddress);
            }
            else {
                usrAddress.setAddress(uAddress.getAddress());
                /*usrAddress.setStatus(uAddress.getStatus());*/
                usrAddress.setUsers(users);
                /*usrAddress.setCreatedDate(new Date());*/

                usrAddress = userAddressService.create(usrAddress);
                addUserAddress.add(usrAddress);
            }
        }
        users.setUserAddress(addUserAddress);
        return users;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.chitfund.service.IUsersService#addUserImages(com.chitfund.domain.Users, java.util.List)
     */
    /* @Override
     public Users addUserImages(final Users users, final List<UserImages> userImag) {
         Validate.notNull(users, "users must not be null");
         Set<UserImages> addImages = new HashSet<UserImages>(userImag);
         for (UserImages uImages : userImag) {
             UserImages userImages = new UserImages();
             String s = uImages.getImageName();
             s = s.replaceAll("\\\\", "/");
             if (uImages.getId() != null) {
                 userImages = userImagesService.getUserImages(uImages.getId());
                 userImages.setId(userImages.getId());
                 userImages.setImageName(s);
                 userImages.setImageType(uImages.getImageType());
                 userImages.setImageLocation(uImages.getImageLocation());
                 userImages.setUsers(userImages.getUsers());
                 userImages = userImagesService.updateUserImages(userImages);
             }
             else {
                 userImages.setImageName(s);
                 userImages.setImageType(uImages.getImageType());
                 userImages.setImageLocation(uImages.getImageLocation());
                 userImages.setUsers(users);
                 addImages.add(userImages);
                 userImages = userImagesRepository.save(userImages);
             }

         }
         users.setUserImageses(addImages);
         return users;
     }*/

    public Users changesignup(final String emailId, final String newPassword,
            final String current/*String username, String password,String emailId*/) {
        Users ud1 = usersRepository.findByEmailId(emailId);
        if (ud1 != null) {

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (!newPassword.isEmpty()) {
                String hashedPassword1 = passwordEncoder.encode(newPassword);
                ud1.setPassword(hashedPassword1);
                ud1.setAuthenticateStatus("Success");
                ud1 = usersRepository.save(ud1);

                /*  Mail mail = new Mail();
                  mail.setMailFrom(mailFrom);
                  mail.setMailTo("to@gmail.com"emailId);
                  mail.setMailSubject("Password Changed Successfully");
                  sendMail(mail,emailId);*/

            }
            else {
                ud1.setAuthenticateStatus("InCorrect::Please Enter Valid Current Password!");
            }
        }
        else {
            ud1.setAuthenticateStatus(" Please Enter Valid EmailId!");
        }
        return ud1;
    }

    /**
     * @param user
     * @param confirmPassword
     * @param password
     * @return
     */
    private Users changesignup(Users user, final String newPassword, final String currentPassword) {

        if (user != null) {

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            if (passwordEncoder.matches(currentPassword, user.getPassword())) {

                /* if (!newPassword.isEmpty()) {*/
                String hashedPassword1 = passwordEncoder.encode(newPassword);
                user.setPassword(hashedPassword1);
                user.setAuthenticateStatus("Success");
                user = usersRepository.save(user);

                /*  Mail mail = new Mail();
                  mail.setMailFrom(mailFrom);
                  mail.setMailTo("to@gmail.com"emailId);
                  mail.setMailSubject("Password Changed Successfully");
                  sendMail(mail,emailId);*/

            }
            else {
                user.setAuthenticateStatus("InCorrect::Please Enter Valid Current Password!");
            }
        }
        else {
            user.setAuthenticateStatus(" InCorrect::Please Enter Valid EmailId!");
        }
        return user;
    }

    /**
     * @param emailId
     * @return
     */
    public boolean checkEmailExists(final String email) {
        return usersRepository.findByEmailId(email) != null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.chitfund.service.IUsersService#create(com.chitfund.domain.Users)
     */
    public boolean checkPhoneNoExists(final String mobile) {
        return usersRepository.findByMobileNo(mobile) != null;
    }

    @Override
    @Transactional
    public Users create(final Users users) {
    	  Validate.notNull(users, "user must not be null" + users.getEmailId());
          users.setAuthenticateStatus("PENDING");
          users.setEmailStatus("PENDING");
          boolean emailExists = checkEmailExists(users.getEmailId().toLowerCase());
          boolean phoneNoExists = checkPhoneNoExists(users.getMobileNo());
          if (emailExists || phoneNoExists) {
              if (emailExists && phoneNoExists) {
                  users.setEmailStatus("DUPLICATEEM");
                  return users;
              }
              if (emailExists) {
                  users.setEmailStatus("DUPLICATEE");
                  return users;
              }
              if (phoneNoExists) {
                  users.setEmailStatus("DUPLICATEM");
                  return users;
              }
          }
          else {
              signup(users);
              users.setEmailStatus("SUCCESS");
              users.setAuthenticateStatus("SUCCESS");
          }
          return users;
    }


	/**
     * {@inheritDoc}
     *
     * @see com.chitfund.service.IUsersService#deleteUsers(java.lang.String)
     */
    @Override
    public void deleteUsers(final String usersId) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.IUsersService#findByChangePassword(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Users getByChangePassword(final String userId, final String confirmPassword, final String newPassword, final String password) {
    	 Users user = usersRepository.findOne(userId);
         Users user1 = new Users();
         List<Users> users = new ArrayList<Users>();
         if (user.getId() != null) {
             user1.setEmailId(user.getEmailId());
             user1.setPassword(confirmPassword);
             user1.setMobileNo(user.getMobileNo());
             user1.setUserType(user.getUserType());
             user1.setId(user.getId());

             //userRepository.save(user);
             if (newPassword.equals(confirmPassword)) {
                 user1 = changesignup(user, confirmPassword, password);
             }
         }
         else {
             user.setAuthenticateStatus("InCorrect::Please Enter Valid EmailId!");
         }
         users.add(user1);
         return user1;
    }

 

    /**
     * {@inheritDoc}
     *
     * @see com.chitfund.service.IUsersService#findByEmailIdAndForgotPassword(java.lang.String, java.lang.String)
     */
    @Override
    public Users findByEmailIdAndForgotPassword(final String emailId, final String forgotPasswordStatus) {

        Users user = usersRepository.findByEmailId(emailId);
        if (user != null) {
        }
        /*    Mail mail = new Mail();
            mail.setMailFrom(mailFrom);
            mail.setMailTo("to@gmail.com"user.getEmailId());
            mail.setMailSubject("Subject - Reset Your Password");
            mail.setTemplateName("forgot.vm");

            String html = "<p>Hi!</p><a href=\"google.com\">Link text</a>";

            String firmName = null;
            for (FirmBranch fb : user.getFirmBranchs()) {
                firmName = fb.getFirmDetails().getFirmName();
            }
            mail.setFirmName(firmName);
            sendMail(mail, user);
        }*/
        else {
            user.setAuthenticateStatus(" Please Enter Valid EmailId!");
        }
        return user;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.chitfund.service.IUsersService#findByEmailIdAndPassword(java.lang.String, java.lang.String)
     */
    @Override
    public Users findByEmailIdAndPassword(final String emailId, final String password) {
        Validate.notNull(emailId, "emailId must not be null");

        String e = emailId.toLowerCase();
        Users usr = usersRepository.findByEmailIdAndPassword(e);
        if (usr == null || usr.equals("") || usr.equals(null)) {
            usr = new Users();
            usr.setAuthenticateStatus("Please Enter Valid EmailId!");
        }
        else {

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(usr.getPassword());
            //users.setPassword(hashedPassword);
            if (hashedPassword.equals(usr.getPassword())) {
                return usr;
            }
            else {
                usr.setAuthenticateStatus("Please Enter Valid Password!");
                return usr;
            }
        }
        return usr;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.chitfund.service.IUsersService#findByMobileNoAndPassword(java.lang.String, java.lang.String)
     */
    @Override
    public Users findByMobileNoAndPassword(final String mobileNo, final String password) {
        Validate.notNull(mobileNo, "mobileNo must not be null");
        Users user = usersRepository.findByMobileNo(mobileNo);
        if (user == null || user.equals("") || user.equals(null)) {
            user = new Users();
            user.setAuthenticateStatus("Please Enter Valid MobileNo!");
        }
        else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(user.getPassword());
            if (hashedPassword.equals(user.getPassword())) {
                return user;
            }
            else {
                user.setAuthenticateStatus("Please Enter Valid Password!");
                return user;
            }
        }
        return user;
    }

    @Override
    public Users findByUser(final String id) {
        // TODO Auto-generated method stub
        return usersRepository.findByUserId(id);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.IUsersService#getAll()
     */
    @Override
    public List<Users> getAll() {
    	 List<Users> user = new ArrayList<Users>();
        
    	 user = (List<Users>) usersRepository.findAll();
         List<Users> users = new ArrayList<Users>();
         for (Users u : user) {
        	 Users usr = new Users();
        	 usr.setAge(u.getAge());
        	 usr.setId(u.getId());
        	 usr.setStatus(u.getStatus());
        	 usr.setPassword(u.getPassword());
        	 usr.setMobileNo(u.getMobileNo());
        	 usr.setEmailStatus(u.getEmailStatus());
             usr.setEmailId(u.getEmailId());
             usr.setAuthenticateStatus(u.getAuthenticateStatus());
             usr.setAlternateMobileNo(u.getAlternateMobileNo());
             usr.setUserCode(u.getUserCode());
             usr.setUserCount(u.getUserCount());
             usr.setUserName(u.getUserName());
             usr.setUserType(u.getUserType());
             
             users.add(usr);
             Set<Account> accts = new HashSet<Account>();
             if (u.getAccounts() != null) {
                 for (Account ac : u.getAccounts()) {
                	 Account acc = new Account();
                     acc.setCashbackAmount(ac.getCashbackAmount());
                     acc.setId(ac.getId());              
                     Users users1=new Users();
         			users1.setId(ac.getUsers().getId());
         			acc.setUsers(users1);
                     accts.add(acc);
                 }
             }
             usr.setAccounts(accts);
         }
         return users;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.chitfund.service.IUsersService#getAllUsers(java.lang.String)
     */
    @Override
    public List<Users> getAllUsers(final String userRole) {
        List<Users> users = (List<Users>) usersRepository.findAll();
        if (CollectionUtils.isEmpty(users)) {
            return new ArrayList<Users>();
        }
        return users;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.chitfund.service.IUsersService#getByForgotPasswordEmail(java.lang.String, java.lang.String)
     */
    @Override
    public List<Users> getByForgotPasswordEmail(final String emailId, final String forgotPasswordStatus) {
        List<Users> ud = new ArrayList<Users>();

        Users user = usersRepository.findByEmailId(emailId);
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        /* SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailId);
        String replyTo = "contact@electrikals.com";
        String from = "contact@electrikals.com";
        String subject = "Test Mail Sender";
        String SALT = "ZYX123abc";
        String saltedPassword = user.getPassword().concat(SALT);//+"SALT";
        String hashedPassword = SimpleMD5Example(saltedPassword);

        String text = "Hi " + user.getFirstName().concat(user.getLastName()) + "your email Id is " + user.getEmailId()
                + " and  password is" + hashedPassword;
        mailMessage.setReplyTo(replyTo);
        mailMessage.setFrom(from);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        Send s = new Send();
        s.send(emailId, mailMessage.getReplyTo(), mailMessage.getFrom(), mailMessage.getSubject(), mailMessage.getText());*/
        if (user != null) {
            /*  Mail mail = new Mail();
            mail.setMailFrom(mailFrom);
            mail.setMailTo("to@gmail.com"user.getEmailId());
            mail.setMailSubject("Subject - Reset Your Password");
            mail.setTemplateName("forgot.vm");
             */
            String html = "<p>Hi!</p><a href=\"" + url + "/getForgotPassword.html#" + user.getId() + "?em=" + user.getEmailId()
                    + "\">Link text</a>";

            //   sendMail(mail, user);
            user.setAuthenticateStatus("Reset Password Link Success Fully Send to EmailId");
        }
        else {
            user.setAuthenticateStatus("Plese Enter Valid EmailId!");
        }
        ud.add(user);

        return ud;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.chitfund.service.IUsersService#getByUserType(java.lang.String)
     */
    @Override
    public List<Users> getByUserType(final String userType) {
        List<Users> users = usersRepository.getByUserType(userType);

        if (CollectionUtils.isEmpty(users)) {
            return new ArrayList<Users>();
        }
        return users;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.chitfund.service.IUsersService#getByUserTypeOnlyActiveUsers(java.lang.String)
     */
    @Override
    public List<Users> getByUserTypeOnlyActiveUsers(final String userType) {
        String status = "Active";
        List<Users> users = usersRepository.getByActiveUserType(userType, status);
        List<Users> users1 = new ArrayList<Users>();
        for (Users ud : users) {
            Users u = new Users();
            u.setUserName(ud.getUserName());
            u.setEmailId(ud.getEmailId());
            u.setId(ud.getId());
            u.setAuthenticateStatus(ud.getAuthenticateStatus());
            u.setMobileNo(ud.getMobileNo());
            u.setUserType(ud.getUserType());
            users1.add(u);
        }
        if (CollectionUtils.isEmpty(users1)) {
            return new ArrayList<Users>();
        }
        return users1;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.chitfund.service.IUsersService#getByUserTypeOnlyUsers(java.lang.String)
     */
    @Override
    public List<Users> getByUserTypeOnlyUsers(final String userType) {
        List<Users> users = usersRepository.getByUserType(userType);
        List<Users> users1 = new ArrayList<Users>();
        for (Users ud : users) {
            Users u = new Users();
            u.setUserName(ud.getUserName());
            u.setEmailId(ud.getEmailId());
            u.setId(ud.getId());
            u.setAuthenticateStatus(ud.getAuthenticateStatus());
            u.setMobileNo(ud.getMobileNo());
            u.setUserType(ud.getUserType());
            users1.add(u);
        }
        if (CollectionUtils.isEmpty(users1)) {
            return new ArrayList<Users>();
        }
        return users1;
    }

    /*public void sendMail(final Mail mail, final Users ud) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(mail.getMailFrom());
        message.setTo(mail.getMailTo());
        message.setSubject(mail.getMailSubject());

        Template template = velocityEngine.getTemplate("./templates/" + mail.getTemplateName());

        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("firstName", "Yashwant"ud.getFirstName());
        velocityContext.put("lastName", "Chavan"ud.getLastName());*/
    // velocityContext.put("location", /*"Pune"*/);
    /* String firmName = null;
     for (FirmBranch fb : ud.getFirmBranchs()) {
         firmName = fb.getFirmDetails().getFirmName();
     }
     String html = "<p>Hi!</p><a href=\"" + url + "/getForgotPassword.html#" + ud.getId() + "?em=" + ud.getEmailId()
             + "\">Link text</a>";
     velocityContext.put("html", html);*/
    //System.out.println("firm details are "+firmName);
    // velocityContext.put("firmName", firmName());
    /*velocityContext.put("firmName", firmName);
    StringWriter stringWriter = new StringWriter();
    template.merge(velocityContext, stringWriter);
    message.setText(stringWriter.toString());

    MimeMessagePreparator preparator = new MimeMessagePreparator() {
        @Override
        public void prepare(final MimeMessage mimeMessage) throws Exception {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setTo(mail.getMailTo());
            message.setSubject(mail.getMailSubject());
            message.setFrom(mail.getMailFrom());

            Map map = new HashMap();
            map.put("firstName", ud.getFirstName());
            map.put("lastName", ud.getLastName());
            map.put("userId", ud.getId());
            String html = url + "/getForgotPassword.html#" + ud.getId() + "?em=" + ud.getEmailId();
            map.put("html", html);

            String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/forgot.vm", "UTF-8", map);

            message.setText(text, true);
     */
    /*       }
       };
       //Send email using the autowired mailSender
       mailSender.send(preparator);

       //mailSender.send(message);
    }*/

    /**
     * {@inheritDoc}
     *
     * @see com.chitfund.service.IUsersService#getMaxCode()
     */
    @Override
    public Integer getMaxCode() {
        Integer c = usersRepository.getMaxCode();
        return c;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.chitfund.service.IUsersService#getUsers(java.lang.String)
     */
    @Override
    public Users getUsers(final String usersId) {
    	Users users=new Users();
    	users=usersRepository.findOne(usersId);
    	Users usr=new Users();
    	usr.setAge(users.getAge());
    	usr.setAlternateMobileNo(users.getAlternateMobileNo());
    	usr.setAuthenticateStatus(users.getAuthenticateStatus());
    	usr.setCreatedDate(users.getCreatedDate());
    	usr.setEmailId(users.getEmailId());
    	usr.setEmailStatus(users.getEmailStatus());
    	usr.setId(users.getId());
    	usr.setMobileNo(users.getMobileNo());
    	usr.setPassword(users.getPassword());
    	usr.setStatus(users.getStatus());
    	usr.setUserCode(users.getUserCode());
    	usr.setUserCount(users.getUserCount());
    	usr.setUserName(users.getUserName());
    	usr.setUserType(users.getUserType());
    	if(users.getAccounts()!=null){
    		Set<Account> accounts=new HashSet<Account>();
    		for(Account acc:accounts){
    			Account acct=new Account();
    			acct.setCashbackAmount(acc.getCashbackAmount());
    			accounts.add(acct);
    		}
    		usr.setAccounts(accounts);
    	}
        return users;
    }

    public void signup(Users users) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(users.getPassword());
        users.setPassword(hashedPassword);
        users.setEmailId(users.getEmailId());
        users.setMobileNo(users.getMobileNo());
        users = usersRepository.save(users);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.chitfund.service.IUsersService#updateUsers(com.chitfund.domain.Users)
     */
    @Override
    public Users updateUsers(final Users users) {
        // TODO Auto-generated method stub
        return usersRepository.save(users);
    }

	@Override
	public Users addAccount(Users users, Set<Account> accounts) {
		 Validate.notNull(users, "users must not be null");
	        Set<Account> addAccounts = new HashSet<Account>(accounts);
	        for (Account acc : accounts) {
	        	Account acct = new Account();
	            	acct.setCashbackAmount(acc.getCashbackAmount());
	            	acct.setUsers(users);
	            	acct = accountService.create(acct);
	                addAccounts.add(acct);
	            }
	        users.setAccounts(addAccounts);
	        return users;
	}

	@Override
	public List<Users> getActivatedUsers() {
		
		List<Users> users = new ArrayList<Users>();
		users = usersRepository.findByAll();
        List<Users> users1 = new ArrayList<Users>();
        for (Users user1 : users) {
        	Users us = new Users();
        	us.setId(user1.getId());
        	us.setAge(user1.getAge());
        	us.setAlternateMobileNo(user1.getAlternateMobileNo());
        	us.setAuthenticateStatus(user1.getAuthenticateStatus());
        	us.setCreatedDate(user1.getCreatedDate());
        	us.setStatus(user1.getStatus());
            us.setEmailId(user1.getEmailId());
            us.setEmailStatus(user1.getEmailStatus());
            us.setMobileNo(user1.getMobileNo());
            us.setPassword(user1.getPassword());
            us.setUserCode(user1.getUserCode());
            us.setUserCount(user1.getUserCount());
            us.setUserName(user1.getUserName());
            us.setUserType(user1.getUserType());
            users1.add(us);
        }
        return users1;
	}

	

}