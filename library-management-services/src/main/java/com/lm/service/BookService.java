package com.lm.service;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lm.domain.gen.Book;
import com.lm.domain.gen.BookStatuses;
import com.lm.domain.gen.UserActivity;
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
	/* (non-Javadoc)
	 * @see com.lm.service.IBookService#placeHoldOnBook(int)
	 */
	public Book placeHoldOnBook(int bookId){
		Book book=bookRepository.findOne(bookId);
		BookStatuses bookStatuses = null;
		Set<UserActivity> userActivities=book.getUserActivities();
		for(UserActivity userActivity:userActivities){
			bookStatuses=userActivity.getBookStatuses();
						
			}
		System.out.println("status Id:"+bookStatuses.getBookStatusId());
		if(bookStatuses.getBookStatusId()==1){
			userActivityService.updateActivity(book);
			
		}
		return book;
		
	}

}
