package com.lm.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

public class UserActivityControllerTest extends BaseWebTest {

	@Test
	@DatabaseSetup(value = { "StaticTypes.xml", "Book.xml", "User.xml", "UserActivity.xml" })
	public void findOne() throws Exception {
		MvcResult result = mockmvc.perform(get("/userActivity/1")).andExpect(status().isOk()).andReturn();
		Assert.assertTrue(result.getResponse().getContentAsString().contains("harithan"));
	}

	@Test
	@DatabaseSetup(value = { "StaticTypes.xml", "Book.xml", "User.xml", "UserActivity.xml" })
	@ExpectedDatabase(value = "RenewBookTest.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void renewBook() throws Exception {
		ResultActions resultActions = mockmvc.perform(post("/userActivity/1/renew"));
		resultActions.andExpect(status().is2xxSuccessful());
		log.info("Book Renewed:{}", resultActions.andReturn().getResponse().getContentAsString());
	}
	@Test
	@DatabaseSetup(value = { "StaticTypes.xml", "Book.xml", "User.xml", "UserActivity.xml" })
	@ExpectedDatabase(value = "ReturnBookTest.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void returnBook() throws Exception {
		ResultActions resultActions = mockmvc.perform(post("/userActivity/1/return"));
		resultActions.andExpect(status().is2xxSuccessful());
		log.info("Book Returnd:{}", resultActions.andReturn().getResponse().getContentAsString());
	}
}
