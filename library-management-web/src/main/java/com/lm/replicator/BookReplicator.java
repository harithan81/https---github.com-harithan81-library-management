package com.lm.replicator;

import org.springframework.stereotype.Component;

import com.lm.domain.gen.Book;

@Component
public class BookReplicator {

	public Book replicate(Book book) {
		Book book1 = new Book();
		book1.setBookName(book.getBookName());
		book1.setAuthorName(book.getAuthorName());
		book1.setIsbn(book.getIsbn());
		book1.setBookId(book.getBookId());
		book1.setBookCount(book.getBookCount());
		return book1;
	}

}
