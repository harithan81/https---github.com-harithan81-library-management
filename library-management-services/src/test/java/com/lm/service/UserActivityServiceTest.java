package com.lm.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.lm.domain.gen.BookStatuses;
import com.lm.domain.gen.UserActivity;


public class UserActivityServiceTest extends BaseTest{
	@Autowired
	private UserActivityService userActivityService;

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
	@Test
	@DatabaseSetup(value={"StaticTypes.xml","Book.xml","User.xml","UserActivity.xml"})
	@ExpectedDatabase(value="ReturnBookTest.xml",assertionMode=DatabaseAssertionMode.NON_STRICT)
	public void returnBookTest(){
		UserActivity userActivity=userActivityService.returnBook(1);
		BookStatuses bookStatuses=userActivity.getBookStatuses();
		System.out.println("Book Status is:"+bookStatuses.getName());
		System.out.println("verion is:"+userActivity.getVersion());
	}

}
