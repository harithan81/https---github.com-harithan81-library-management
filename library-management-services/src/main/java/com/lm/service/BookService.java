package com.lm.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lm.domain.gen.Book;
import com.lm.repository.BookRepository;
import com.mysema.query.types.Predicate;

@Service
@Transactional
public class BookService {

	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private UserActivityService userActivityService;
	@Autowired
	private BookRepository bookRepository;
	Logger log = LoggerFactory.getLogger(BookService.class);

	public Book findOne(int bookId) {
		return bookRepository.findOne(bookId);
	}

	public Page<Book> findAll(Predicate predicate, Pageable pageable) {
		return bookRepository.findAll(predicate, pageable);

	}

	public Book createBook(Book book) {
		return bookRepository.saveAndFlush(book);
	}

	public Book borrowBook(int bookId) {

		Book book = bookRepository.findOne(bookId);
		book.setBookCount(book.getBookCount() - 1);
		bookRepository.saveAndFlush(book);
		userActivityService.updateUserActivity(book);
		return book;

	}

}
