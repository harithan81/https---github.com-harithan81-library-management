package com.lm.replicator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lm.domain.gen.Book;
import com.lm.domain.gen.BookStatuses;
import com.lm.domain.gen.User;
import com.lm.domain.gen.UserActivity;

@Component
public class UserActivityReplicator {
	@Autowired
	private BookReplicator bookReplicator;
	@Autowired
	private UserReplicator userReplicator;
	@Autowired
	private BookStatusesReplicator bookStatusesReplicator;

	public UserActivity replicate(UserActivity userActivity) {
		UserActivity userActivity2 = new UserActivity();
		Book book = userActivity.getBook();
		User user = userActivity.getUser();
		BookStatuses bookStatuses = userActivity.getBookStatuses();
		userActivity2.setBook(bookReplicator.replicate(book));
		userActivity2.setUser(userReplicator.replicate(user));
		userActivity2.setBookStatuses(bookStatusesReplicator.replicate(bookStatuses));
		userActivity2.setUserActivityId(userActivity.getUserActivityId());
		userActivity2.setVersion(userActivity.getVersion());
		return userActivity2;

	}
}
