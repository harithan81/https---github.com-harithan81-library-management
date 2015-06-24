package com.lm.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.lm.domain.gen.Book;
import com.lm.domain.gen.QBook;
import com.mysema.query.BooleanBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:test-spring-resources.xml", "classpath:spring-services.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
public class BookServiceTest {

	@Autowired
	private BookService bookService;

	@Test
	@DatabaseSetup("Book.xml")
	public void findOne() {
		Book book = bookService.findOne(1);
		System.out.println("book is:" + book.getBookId());
		Assert.assertTrue(book.getBookId() == 1);

	}

	@Test
	@DatabaseSetup("Book.xml")
	public void getBooks() {

		QBook qBook = QBook.book;
		BooleanBuilder builder = new BooleanBuilder();

		builder.and(qBook.bookName.eq("The Alchemist"));
		builder.and(qBook.bookId.eq(1));
		builder.and(qBook.authorName.eq("Paulo Coelho"));
		
		

		Page<Book> books = bookService.findAll(builder, new PageRequest(0, 50));

		for (Book book : books.getContent()) {
			System.out.println("book name is:" + book.getBookName() + "book Id is" + book.getBookId() + "author name is:" + book.getAuthorName()
					+ "book ISBN is :" + book.getIsbn());
			Assert.assertTrue(book.getBookName().equals("The Alchemist"));
			Assert.assertTrue(book.getAuthorName().equals("Paulo Coelho"));
		}
	}

	@Test
	@DatabaseSetup("Book.xml")
	public void createBookTest() {
		Book book = new Book();
		book.setBookId(2);
		book.setBookName("Sherlock Holmes");
		book.setAuthorName("joel Silver");
		book.setIsbn("ISBN 0-06-250217-6");

		book = bookService.createBook(book);
		Assert.assertTrue(book.getBookName().equals("Sherlock Holmes"));
		book = bookService.findOne(2);
		System.out.println("Book on id2" + book.getBookName());
		Assert.assertTrue(book.getBookId() == 2);
		System.out.println("book name is:" + book.getBookName() + "book Id is" + book.getBookId() + "author name is:" + book.getAuthorName()
				+ "book ISBN is :" + book.getIsbn());

	}
}
