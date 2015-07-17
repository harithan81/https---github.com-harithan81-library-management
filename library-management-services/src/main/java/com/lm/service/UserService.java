package com.lm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lm.domain.gen.User;
import com.lm.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	public User findOne(String userId) {
		return userRepository.findOne(userId);

	}
	public User createUser(User user){
		return userRepository.saveAndFlush(user);
	}
}