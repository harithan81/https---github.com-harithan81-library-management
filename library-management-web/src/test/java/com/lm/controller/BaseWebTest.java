package com.lm.controller;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.springtestdbunit.DbUnitTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:test-spring-resources.xml",
		"classpath:spring-services.xml",
		"file:src/main/resources/webapps/WEB-INF/dispatcher-servlet.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@org.springframework.transaction.annotation.Transactional
@WebAppConfiguration
public abstract class BaseWebTest {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	protected WebApplicationContext wac;

	protected MockMvc mockmvc;

	protected Logger log = LoggerFactory.getLogger(BookControllerTest.class);

	@AfterTransaction
	public void clearDatabase() throws Exception {
		jdbcTemplate
				.execute("TRUNCATE SCHEMA PUBLIC RESTART IDENTITY AND COMMIT NO CHECK");

	}

	@Before
	public void setUp() {
		mockmvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
}
