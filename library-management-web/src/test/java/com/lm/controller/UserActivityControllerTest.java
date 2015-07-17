package com.lm.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
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
public class UserActivityControllerTest {

	private MockMvc mockmvc;
	@Autowired
	WebApplicationContext wac;

	@Before
	public void setUp() {
		mockmvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	private Logger log = LoggerFactory
			.getLogger(UserActivityControllerTest.class);

	@Test
	@DatabaseSetup("UserActivity.xml")
	public void findOne() throws Exception {
		ResultActions resultActions = mockmvc.perform(get("/userActivity/1"));
		resultActions.andExpect(status().is2xxSuccessful());
		log.info(">>>Book Found:{}", resultActions.andReturn().getResponse()
				.getContentAsString());
	}

	@Test
	@Ignore
	@DatabaseSetup("UserActivity.xml")
	public void renewBook() throws Exception {
		ResultActions resultActions = mockmvc.perform(post("/userActivity/1"));
		resultActions.andExpect(status().is2xxSuccessful());
		log.info("Book Renewed:{}", resultActions.andReturn().getResponse()
				.getContentAsString());
	}

}
