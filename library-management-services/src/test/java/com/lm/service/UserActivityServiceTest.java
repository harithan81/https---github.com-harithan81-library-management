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
import com.lm.domain.gen.Book;
import com.lm.domain.gen.BookStatuses;
import com.lm.domain.gen.User;
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
	@DatabaseSetup("UserActivity.xml")
	public void findOne() {
		UserActivity userActivity = userActivityService.findOne(1);
		Assert.assertTrue(userActivity.getUserActivityId() == 1);
		System.out.println("Id is:" + userActivity);

	}

	@Test
	@DatabaseSetup("UserActivity.xml")
	public void updateUserActivityTest() {
		Book book = new Book();
		book.setBookId(3);
		book.setVersion(1);
		book.setBookName("Chandamama");
		book.setAuthorName("Kaarnati");
		book.setIsbn("ISBN 0-06-250217-6");
		book.setBookCount(3);

		UserActivity userActivity = userActivityService
				.updateUserActivity(book);
		User user = userActivity.getUser();
		Book userBook = userActivity.getBook();
		Assert.assertTrue(userBook.getBookName().equals("Chandamama"));
		System.out.println("book name:" + userBook.getBookName());

		System.out.println("user name:" + user.getFirstName());
		// Assert.assertTrue(userActivity.getUser().equals(user));
		System.out.println("User details" + userActivity.getUserActivityId());

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
