package com.lm.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.github.springtestdbunit.annotation.DatabaseSetup;

public class UserActivityControllerTest extends BaseWebTest {

	@Test
	@DatabaseSetup(value = { "StaticTypes.xml", "Book.xml", "User.xml", "UserActivity.xml" })
	public void findOne() throws Exception {
		MvcResult result = mockmvc.perform(get("/userActivity/1")).andExpect(status().isOk()).andReturn();
		Assert.assertTrue(result.getResponse().getContentAsString().contains("harithan"));
	}

	@Test
	@Ignore
	@DatabaseSetup("UserActivity.xml")
	public void renewBook() throws Exception {
		ResultActions resultActions = mockmvc.perform(post("/userActivity/1"));
		resultActions.andExpect(status().is2xxSuccessful());
		log.info("Book Renewed:{}", resultActions.andReturn().getResponse().getContentAsString());
	}

}
