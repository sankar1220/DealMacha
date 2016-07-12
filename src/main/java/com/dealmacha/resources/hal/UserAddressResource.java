package com.dealmacha.resources.hal;

import org.springframework.hateoas.core.Relation;

import com.dealmacha.domain.Address;
import com.dealmacha.domain.Users;

@Relation(value="userAddress",collectionRelation="userAddress")
public class UserAddressResource extends ResourceWithEmdedded {
	private String userAddressId;
	  private Users usersId;
	    private Address addressId;
		public String getUserAddressId() {
			return userAddressId;
		}
		public void setUserAddressId(String userAddressId) {
			this.userAddressId = userAddressId;
		}
		public Users getUsersId() {
			return usersId;
		}
		public void setUsersId(Users usersId) {
			this.usersId = usersId;
		}
		public Address getAddressId() {
			return addressId;
		}
		public void setAddressId(Address addressId) {
			this.addressId = addressId;
		}
	    

}
