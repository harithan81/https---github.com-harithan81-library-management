package com.lm.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.lm.domain.gen.Book;
import com.lm.service.BookService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:test-spring-resources.xml", "classpath:spring-services.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@WebAppConfiguration
public class BookControllerTest {

	private MockMvc mockmvc;

	@Test
	public void findOne() throws Exception {
		mockmvc.perform(get("/book/1")).andExpect(status().isOk());
	}

	@Test
	public void hello() {
		System.out.println(">>>");
	}

	@Autowired
	private BookService bookService;

	@Test
	@DatabaseSetup("Book.xml")
	public void findOne1() {
		Book book = bookService.findOne(1);
		System.out.println("book is:" + book.getBookId());
		Assert.assertTrue(book.getBookId() == 1);

	}
}
