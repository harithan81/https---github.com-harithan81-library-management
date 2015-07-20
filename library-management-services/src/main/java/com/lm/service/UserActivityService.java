package com.lm.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lm.domain.gen.Book;
import com.lm.domain.gen.User;
import com.lm.domain.gen.UserActivity;
import com.lm.repository.BookStatusesRepository;
import com.lm.repository.UserActivityRepository;
import com.lm.repository.UserRepository;

@Service
@Transactional
public class UserActivityService {
	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

	@Autowired
	private UserActivityRepository userActivityRepository;
	@Autowired
	private BookStatusesService bookStatusesService;
	@Autowired
	private BookStatusesRepository bookStatusesRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;

	public UserActivity findOne(int userActivityId) {
		return userActivityRepository.findOne(userActivityId);

	}

	private Date checkedOutDate() {

		Calendar calendar = Calendar.getInstance();
		Date checkedOutDate = calendar.getTime();
		return checkedOutDate;

	}

	private Date dueDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.WEEK_OF_YEAR, 2);
		Date dueDate = calendar.getTime();
		return dueDate;
	}

	public UserActivity updateUserActivity(Book book) {
		User user = new User();
		user.setUserId("harithan");
		UserActivity userActivity = new UserActivity();
		Date checkedOutDate = checkedOutDate();
		Date dueDate = dueDate();
		userActivity.setVersion(1);
		userActivity.setBook(book);
		userActivity.setCheckedOutDate(checkedOutDate);
		userActivity.setDueDate(dueDate);
		userActivity.setBookStatuses(bookStatusesService.findOne(1));
		userActivity.setUser(userService.findOne(user.getUserId()));
		return userActivityRepository.saveAndFlush(userActivity);

	}

	public UserActivity renewBook(int userActivityId) {

		UserActivity userActivity = userActivityRepository.findOne(userActivityId);
		userActivity.setDueDate(dueDate());
		userActivity.setBookStatuses(bookStatusesService.findOne(2));
		Short count = userActivity.getRenewalCount();
		userActivity.setRenewalCount(count++);
		return userActivityRepository.saveAndFlush(userActivity);
	}
}
