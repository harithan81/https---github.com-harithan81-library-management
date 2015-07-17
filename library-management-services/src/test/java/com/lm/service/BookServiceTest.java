package com.lm.service;

import java.util.List;

import javax.transaction.Transactional;

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
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.lm.domain.gen.Book;
import com.lm.domain.gen.QBook;
import com.lm.domain.gen.UserActivity;
import com.lm.repository.UserActivityRepository;
import com.mysema.query.BooleanBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:test-spring-resources.xml",
		"classpath:spring-services.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@Transactional
public class BookServiceTest {

	@Autowired
	private BookService bookService;
	@Autowired
	private UserActivityRepository userActivityService;

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
			System.out.println("book name is:" + book.getBookName()
					+ "book Id is" + book.getBookId() + "author name is:"
					+ book.getAuthorName() + "book ISBN is :" + book.getIsbn());
			Assert.assertTrue(book.getBookName().equals("The Alchemist"));
			Assert.assertTrue(book.getAuthorName().equals("Paulo Coelho"));
		}
	}

	@Test
	@DatabaseSetup("Book.xml")
	//@ExpectedDatabase(value = "ExpectedBook.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void createBookTest() {
		Book book = new Book();
		book.setBookId(2);
		book.setVersion(1);
		book.setBookName("Sherlock Holmes");
		book.setAuthorName("joel Silver");
		book.setIsbn("ISBN 0-06-250217-5");
		book.setBookCount(3);
		book = bookService.createBook(book);
		Assert.assertTrue(book.getBookName().equals("Sherlock Holmes"));
		book = bookService.findOne(2);
		System.out.println("Book on id2" + book.getBookName());
		Assert.assertTrue(book.getBookId() == 2);
		System.out.println("book name is:" + book.getBookName() + "book Id is"
				+ book.getBookId() + "author name is:" + book.getAuthorName()
				+ "book ISBN is :" + book.getIsbn() + "book count is:"
				+ book.getBookCount());

	}

	@Test
	@DatabaseSetup("Book.xml")
	public void borrowBookTest() {
		Book book = bookService.borrowBook(3);
		System.out.println("version:" + book.getVersion());
		System.out.println("Book deatails:" + book.getAuthorName());
		Assert.assertTrue(book.getAuthorName().equals("Kaarnati"));
		Assert.assertTrue(book.getBookCount() == 2);
		List<UserActivity> activity = userActivityService.findAll();
		System.out.println(">>>" + activity);
		System.out.println(">>>" + book);
	}
}
