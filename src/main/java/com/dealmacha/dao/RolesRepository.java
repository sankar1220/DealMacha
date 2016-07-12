/**
 *
 */
package com.dealmacha.dao;

import com.dealmacha.domain.Roles;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author arthvedi
 *
 */
public interface RolesRepository extends PagingAndSortingRepository<Roles, Serializable> {
    /**
     * @param username
     * @return
     */
    @Query("SELECT r.roleName from Roles r join r.users u where u.emailId=?1")
    List<String> findRoleByUserName(String emailId);

    @Query("select r from Roles r where r.users.id=?1")
    List<Roles> getByUsers(String id);

}
