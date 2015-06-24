package com.lm.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lm.domain.gen.Book;
import com.lm.repository.BookRepository;
import com.mysema.query.types.Predicate;

@Service
public class BookService {

	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private BookRepository bookRepository;

	public Book findOne(int bookId) {
		return bookRepository.findOne(bookId);
	}

	public Page<Book> findAll(Predicate predicate, Pageable pageable) {
		return bookRepository.findAll(predicate, pageable);

	}

	public Book createBook(Book book) {
		return bookRepository.saveAndFlush(book);
	}

}
