package com.lm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lm.domain.gen.Book;
import com.lm.domain.gen.QBook;
import com.lm.service.BookService;
import com.mysema.query.BooleanBuilder;

@Controller
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;
	private Logger log = LoggerFactory.getLogger(BookController.class);

	@RequestMapping(method = RequestMethod.GET, value = "{bookId}")
	@ResponseBody
	public Book findOne(@PathVariable int bookId) {
		Book book = bookService.findOne(bookId);
		log.info("Book Name:{}", book.getBookName());
		Book b = new Book();
		b.setAuthorName(book.getAuthorName());
		b.setBookName(book.getBookName());
		b.setIsbn(book.getIsbn());
		b.setBookId(book.getBookId());
		return b;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Page<Book> findAll(@RequestParam(value = "bookIdasd", required = false) Integer bookId,
			@RequestParam(value = "bookName", required = false) String bookName,
			@RequestParam(value = "authorName", required = false) String authorName,
			@RequestParam(value = "isbn", required = false) String isbn) {
		log.info(">>>bookId:{},bookName:{},authorName:{},isbn:{}.", bookId, bookName, authorName, isbn);
		QBook qBook = QBook.book;
		BooleanBuilder builder = new BooleanBuilder();
		if (bookId != null) {
			builder.and(qBook.bookId.eq(bookId));
		}
		if (bookName != null) {
			builder.and(qBook.bookName.eq(bookName));
		}
		if (authorName != null) {
			builder.and(qBook.authorName.eq(authorName));
		}
		if (isbn != null) {
			builder.and(qBook.isbn.eq(isbn));
		}

		Page<Book> books = bookService.findAll(builder, new PageRequest(0, 50));
		return books;

	}
}