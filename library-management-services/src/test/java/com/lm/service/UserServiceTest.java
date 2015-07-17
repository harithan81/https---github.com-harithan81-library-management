package com.lm.service;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.lm.domain.gen.User;
import com.lm.repository.AddressRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:test-spring-resources.xml",
		"classpath:spring-services.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@Transactional
public class UserServiceTest {
	@Autowired
	UserService userService;
	@Autowired
	AddressRepository addressRepository;

	@Test
	@DatabaseSetup("User.xml")
	public void findOne() {
		User user = userService.findOne("harithan");
		Assert.assertTrue(user.getFirstName().equalsIgnoreCase("haritha"));
		System.out.println("User Id is :" + "" + user.getUserId() + " "
				+ "version:" + user.getVersion() + " " + "User firstName :"
				+ user.getFirstName() + " " + "User lastName:"
				+ user.getLastName() + "" + "Gender : " + user.getGender()
				+ " " + "User birthdate:" + user.getBirthDate());
	}

	@Test
	@DatabaseSetup("User.xml")
	public void createUser() {
		//Address address = new Address();
		User user = new User();
		user.setUserId("subhashb");
		user.setVersion(1);
		user.setFirstName("Subhash");
		user.setLastName("Boreddy");
		user.setGender('M');
		user.setBirthDate("Dec1985");

		user = userService.createUser(user);
		Assert.assertTrue(user.getFirstName().equalsIgnoreCase("Subhash"));
		System.out.println("User Id is :" + "" + user.getUserId() + " "
				+ "version:" + user.getVersion() + " " + "User firstName :"
				+ user.getFirstName() + " " + "User lastName:"
				+ user.getLastName() + "" + "Gender : " + user.getGender()
				+ " " + "User birthdate:" + user.getBirthDate());

		//user.setAddresses(addressRepository.;

	}

}
