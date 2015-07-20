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
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.lm.domain.gen.UserActivity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:test-spring-resources.xml", "classpath:spring-services.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@Transactional
public class UserActivityServiceTest {
	@Autowired
	UserActivityService userActivityService;

	@Test
	@DatabaseSetup(value = { "StaticTypes.xml", "Book.xml", "User.xml", "UserActivity.xml" })
	public void findOne() {
		UserActivity userActivity = userActivityService.findOne(1);
		Assert.assertTrue(userActivity.getUserActivityId() == 1);

	}

	@Test
	@DatabaseSetup(value = { "StaticTypes.xml", "Book.xml", "User.xml", "UserActivity.xml" })
	@ExpectedDatabase(value = "RenewBookTest.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void renewBookTest() {

		userActivityService.renewBook(1);
	}

}
