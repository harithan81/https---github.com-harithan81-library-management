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

public class BookControllerTest extends BaseWebTest {

	@Test
	@DatabaseSetup(value = { "StaticTypes.xml", "Book.xml" })
	public void findOne() throws Exception {
		MvcResult results = mockmvc.perform(get("/book/1")).andExpect(status().isOk()).andReturn();
		Assert.assertTrue(results.getResponse().getContentAsString().contains("The Alchemist"));
	}

	@Test
	@DatabaseSetup(value = { "StaticTypes.xml", "Book.xml" })
	public void findAll() throws Exception {
		MvcResult results = mockmvc
				.perform(
						get("/book").param("bookId", "1").param("bookName", "The Alchemist").param("authorName", "Paulo Coelho")
								.param("isbn", "ISBN 0-06-250217-4")).andExpect(status().isOk()).andReturn();
		System.out.println(">>>" + results.getResponse().getContentAsString());
		Assert.assertTrue(results.getResponse().getContentAsString().contains("The Alchemist"));
	}

	@Test
	@DatabaseSetup(value = { "StaticTypes.xml", "Book.xml" })
	@ExpectedDatabase(value = "CreateBookTest.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void createBook() throws Exception {
		ResultActions resultActions = mockmvc
				.perform(post("/book")
						.content(
								"{\"version\":1, \"bookName\": \"Sherlock Holmes\",  \"authorName\": \"joel Silver\", \"isbn\": \"ISBN 0-06-250217-5\",\"bookCount\":3,  \"bookTypes\": [],  \"userActivities\": []}\"")
						.contentType(MediaType.APPLICATION_JSON));
		resultActions.andExpect(status().is2xxSuccessful());
		Assert.assertTrue(resultActions.andReturn().getResponse().getContentAsString().contains("Sherlock Holmes"));

	}

	@Test
	@DatabaseSetup(value = { "StaticTypes.xml", "Book.xml", "User.xml" })
	@ExpectedDatabase(value = "BorrowBookTest.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void borrowBook() throws Exception {
		ResultActions resultActions = mockmvc.perform(post("/book/2/borrow"));
		resultActions.andExpect(status().is2xxSuccessful());
		Assert.assertTrue(resultActions.andReturn().getResponse().getContentAsString().contains("Chandamama"));
	}

}
