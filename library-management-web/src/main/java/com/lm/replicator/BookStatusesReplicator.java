package com.lm.replicator;

import org.springframework.stereotype.Component;

import com.lm.domain.gen.BookStatuses;

@Component
public class BookStatusesReplicator {
	public BookStatuses replicate(BookStatuses bookStatuses) {
		BookStatuses bookStatuses2 = new BookStatuses();
		bookStatuses2.setBookStatusId(bookStatuses.getBookStatusId());
		bookStatuses2.setName(bookStatuses.getName());
		return bookStatuses2;
	}
}
