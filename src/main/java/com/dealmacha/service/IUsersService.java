package com.dealmacha.service;

import com.dealmacha.domain.Account;
import com.dealmacha.domain.UserAddress;
import com.dealmacha.domain.Users;

import java.util.List;
import java.util.Set;

public interface IUsersService {

    /**
     * @param users
     * @param userAddress
     * @return
     */
    Users addUsersAddress(Users users, Set<UserAddress> userAddress);

    /**
     * @param branchId
     * @return
     */
    //List<UserDetails> findByBranch(String branchId);

    Users create(Users users);

    /**
     * @param city
     * @return
     */
    //List<UserDetails> findBycity(String city);

    void deleteUsers(String usersId);

    /**
     * @param userId
     * @param confirmPassword
     * @param newPassword
     * @param password
     * @return
     */

    Users getByChangePassword(String userId, String confirmPassword, String newPassword, String password);

    /* Users findByUser(String id);*/

    Users findByEmailIdAndForgotPassword(String emailId, String forgotPasswordStatus);

    Users findByEmailIdAndPassword(String emailId, String password);

    Users findByMobileNoAndPassword(String mobileNo, String password);

    Users findByUser(String id);

    /**
     * @return
     */
    List<Users> getAll();

    /**
     * @param userRole
     * @return
     */
    List<Users> getAllUsers(String userRole);

    List<Users> getByForgotPasswordEmail(String emailId, String forgotPasswordStatus);

    List<Users> getByUserType(String userType);

    List<Users> getByUserTypeOnlyActiveUsers(String userType);

    List<Users> getByUserTypeOnlyUsers(String userType);

    /*    Integer getInactiveCust(String sts);*/

    Integer getMaxCode();

    /* Integer getUserCustActive(String ust);
    
    Integer getUserCustomers();*/

    /**
     * @param string
     * @return
     */
    Users getUsers(String usersId);

    Users updateUsers(Users users);


	List<Users> getActivatedUsers();

	Users getUsersByEmailId(String emailId);

	Users getByResetPassword(String userId, String confirmPassword, String newPassword);

	Users getUserResetPassword(Users user);



}
