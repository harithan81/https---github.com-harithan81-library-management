package com.lm.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lm.domain.gen.Book;

@Service
@Transactional
public class BookService {

	@PersistenceContext
	private EntityManager entityManager;

	public Book findOne(int bookId) {
		return entityManager.find(Book.class, bookId);
	}
}
