package com.lm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lm.domain.gen.BookStatuses;
import com.lm.repository.BookStatusesRepository;

@Service
public class BookStatusesService {
	@Autowired
	private BookStatusesRepository bookStatusesRepository;
	public BookStatuses findOne(int bookStatusId){
		return bookStatusesRepository.findOne(bookStatusId);
		
	}

}
