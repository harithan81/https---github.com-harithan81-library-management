package com.lm.service;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Ignore;
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
import com.lm.domain.gen.BookStatuses;
import com.lm.domain.gen.UserActivity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:test-spring-resources.xml",
		"classpath:spring-services.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@Transactional
public class UserActivityServiceTest {
	@Autowired
	UserActivityService userActivityService;

	@Test
	@DatabaseSetup(value = { "StaticTypes.xml", "Book.xml", "User.xml","UserActivity.xml" })
	public void findOne() {
		UserActivity userActivity = userActivityService.findOne(1);
		Assert.assertTrue(userActivity.getUserActivityId() == 1);

	}

	@Test
	@DatabaseSetup("UserActivity.xml")
	@Ignore
	// @ExpectedDatabase("UserActivityExpected.xml")
	public void renewBookTest() {

		UserActivity userActivity = userActivityService.renewBook(1);
		Assert.assertTrue(userActivity.getRenewalCount() == 3);
		BookStatuses bookStatuses = userActivity.getBookStatuses();
		Assert.assertTrue(bookStatuses.getName().equals("Renewed"));
		System.out.println("book status is:" + bookStatuses.getName());
		System.out
				.println("Renewal count is:" + userActivity.getRenewalCount());
	}

}
