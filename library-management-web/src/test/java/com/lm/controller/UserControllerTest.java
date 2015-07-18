package com.lm.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

public class UserControllerTest extends BaseWebTest {

	@Test
	@DatabaseSetup("User.xml")
	public void findOne() throws Exception {
		MvcResult result = mockmvc.perform(get("/user/harithan")).andExpect(status().isOk()).andReturn();
		Assert.assertTrue(result.getResponse().getContentAsString().contains("harithan"));
	}

	@Test
	@DatabaseSetup("User.xml")
	@ExpectedDatabase(value = "CreateUserTest.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void createUser() throws Exception {
		ResultActions resultActions = mockmvc
				.perform(post("/user")
						.content(
								"{\"userId\": \"subhashb\",  \"version\": 1,  \"firstName\": \"Subhash\", \"lastName\": \"Boreddy\",  \"gender\": \"M\",  \"birthDate\": \"Dec1985\"}\"")
						.contentType(MediaType.APPLICATION_JSON));
		resultActions.andExpect(status().is2xxSuccessful());
		Assert.assertTrue(resultActions.andReturn().getResponse().getContentAsString().contains("Subhash"));

	}

}
