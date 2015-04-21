package com.lm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lm.domain.gen.Book;

@Service
@Transactional
public class BookService {

	public List<Book> getBooks() {
		Book book = new Book(123);
		List<Book> books = new ArrayList<Book>();
		books.add(book);
		return books;
	}
}
