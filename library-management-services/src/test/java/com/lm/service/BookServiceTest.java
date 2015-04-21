package com.lm.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lm.domain.gen.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-context.xml")
public class BookServiceTest {

	@Autowired
	private BookService bookService;

	@Test
	public void getBooks() {
		List<Book> books = bookService.getBooks();
		System.out.println("books are:" + books.get(0).getBookId());
	}
}
