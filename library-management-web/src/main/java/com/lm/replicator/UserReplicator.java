package com.lm.replicator;

import org.springframework.stereotype.Component;

import com.lm.domain.gen.User;

@Component
public class UserReplicator {
	public User replicate(User user) {
		User user2 = new User();
		user2.setUserId(user.getUserId());
		user2.setFirstName(user.getFirstName());
		user2.setLastName(user.getLastName());
		user2.setGender(user.getGender());
		user2.setBirthDate(user.getBirthDate());
		user2.setVersion(user.getVersion());
		return user2;
	}
}
