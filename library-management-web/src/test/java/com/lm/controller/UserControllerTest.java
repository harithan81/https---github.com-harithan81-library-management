package com.lm.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:test-spring-resources.xml",
		"classpath:spring-services.xml",
		"file:src/main/resources/webapps/WEB-INF/dispatcher-servlet.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@WebAppConfiguration
public class UserControllerTest {

	private MockMvc mockmvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setUp() {
		mockmvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	private Logger log = LoggerFactory.getLogger(BookControllerTest.class);

	@Test
	@DatabaseSetup("User.xml")
	public void findOne() throws Exception {
		MvcResult result = mockmvc.perform(get("/user/harithan")).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}

	@Test
	@DatabaseSetup("User.xml")
	public void createUser() throws Exception {
		ResultActions resultActions = mockmvc
				.perform(post("/user")
						.content(
								"{\"userId\": 2,  \"version\": 1,  \"firstName\": \"Subhash\", \"lastName\": \"Boreddy\",  \"gender\": \"M\",  \"birthDate\": \"Dec1985\"}\"")
						.contentType(MediaType.APPLICATION_JSON));
		resultActions.andExpect(status().is2xxSuccessful());
		log.info("User created:{}.", resultActions.andReturn().getResponse()
				.getContentAsString());

	}

}
