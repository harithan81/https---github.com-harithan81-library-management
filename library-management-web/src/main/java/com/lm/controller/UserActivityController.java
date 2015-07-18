package com.lm.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lm.domain.gen.Book;
import com.lm.domain.gen.BookStatuses;
import com.lm.domain.gen.User;
import com.lm.domain.gen.UserActivity;
import com.lm.service.UserActivityService;

@Controller
@RequestMapping("/userActivity")
@Transactional
public class UserActivityController {
	@Autowired
	private UserActivityService userActivityService;

	@RequestMapping(method = RequestMethod.GET, value = "{userActivityId}")
	@ResponseBody
	public UserActivity findOne(@PathVariable int userActivityId) {

		UserActivity userActivity = userActivityService.findOne(userActivityId);

		Book book = userActivity.getBook();
		Book book2 = new Book();
		book2.setAuthorName(book.getAuthorName());
		book2.setBookId(book.getBookId());
		book2.setBookName(book.getBookName());
		book2.setBookCount(book.getBookCount());
		book2.setVersion(book.getVersion());

		User user = userActivity.getUser();
		User user2 = new User();
		user2.setUserId(user.getUserId());
		user2.setVersion(user.getVersion());
		user2.setFirstName(user.getFirstName());
		user2.setLastName(user.getLastName());
		user2.setGender(user.getGender());
		user2.setBirthDate(user.getBirthDate());

		BookStatuses bookStatuses = userActivity.getBookStatuses();
		BookStatuses bookStatuses2 = new BookStatuses();
		bookStatuses2.setBookStatusId(bookStatuses.getBookStatusId());
		bookStatuses2.setName(bookStatuses.getName());

		UserActivity userActivity2 = new UserActivity();
		userActivity2.setBook(book2);
		userActivity2.setUserActivityId(userActivity.getUserActivityId());
		userActivity2.setVersion(userActivity.getVersion());
		userActivity2.setBookStatuses(bookStatuses2);
		userActivity2.setUser(user2);

		return userActivity2;

	}

	@RequestMapping(method = RequestMethod.POST, value = "{userActivityId}")
	@ResponseBody
	public UserActivity renewBook(@PathVariable int userActivityId) {
		return userActivityService.renewBook(userActivityId);
	}
}
