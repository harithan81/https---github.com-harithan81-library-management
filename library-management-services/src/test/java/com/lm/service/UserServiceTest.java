package com.lm.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.lm.domain.gen.User;
import com.lm.repository.AddressRepository;

public class UserServiceTest extends BaseTest{
	@Autowired
	private UserService userService;
	@Autowired
	private AddressRepository addressRepository;

	@Test
	@DatabaseSetup(value = { "User.xml" })
	public void findOne() {
		User user = userService.findOne("harithan");
		Assert.assertTrue(user.getFirstName().equalsIgnoreCase("haritha"));
	}

	@Test
	@DatabaseSetup({ "User.xml" })
	@ExpectedDatabase(value = "CreateUserTest.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void createUser() {
		User user = new User();
		user.setUserId("subhashb");
		user.setVersion(1);
		user.setFirstName("Subhash");
		user.setLastName("Boreddy");
		user.setGender('M');
		user.setEmailId("subhashreddy98@gmail.com");
		user.setBirthDate("Dec1985");
		user = userService.createUser(user);
		
	}
	

}
