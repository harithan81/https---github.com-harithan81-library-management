package com.lm.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.lm.domain.gen.Book;
import com.lm.domain.gen.QBook;
import com.lm.repository.UserActivityRepository;
import com.mysema.query.BooleanBuilder;

public class BookServiceTest extends BaseTest {

	@Autowired
	private BookService bookService;
	@Autowired
	private UserActivityRepository userActivityService;

	@Test
	@DatabaseSetup(value = { "StaticTypes.xml", "Book.xml" })
	public void findOne() {
		Book book = bookService.findOne(1);
		Assert.assertTrue(book.getBookId() == 1);

	}

	@Test
	@DatabaseSetup(value = { "StaticTypes.xml", "Book.xml" })
	public void getBooks() {
		QBook qBook = QBook.book;
		BooleanBuilder builder = new BooleanBuilder();

		builder.and(qBook.bookName.eq("The Alchemist"));
		builder.and(qBook.bookId.eq(1));
		builder.and(qBook.authorName.eq("Paulo Coelho"));

		Page<Book> books = bookService.findAll(builder, new PageRequest(0, 50));

		for (Book book : books.getContent()) {
			System.out.println("book name is:" + book.getBookName() + "book Id is" + book.getBookId() + "author name is:"
					+ book.getAuthorName() + "book ISBN is :" + book.getIsbn());
			Assert.assertTrue(book.getBookName().equals("The Alchemist"));
			Assert.assertTrue(book.getAuthorName().equals("Paulo Coelho"));
		}
	}

	@Test
	@DatabaseSetup(value = { "StaticTypes.xml", "Book.xml" })
	@ExpectedDatabase(value = "CreateBookTest.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void createBookTest() {
		Book book = new Book();
		book.setBookName("Sherlock Holmes");
		book.setAuthorName("joel Silver");
		book.setIsbn("ISBN 0-06-250217-5");
		book.setBookCount(3);
		book = bookService.createBook(book);
	}

	@Test
	@DatabaseSetup(value = { "StaticTypes.xml", "Book.xml", "User.xml" })
	@ExpectedDatabase(value = "ExpectedBorrowBook.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void borrowBookTest() {
		bookService.borrowBook(2);
	}
}
