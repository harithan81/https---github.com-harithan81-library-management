package com.lm.controller;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lm.domain.gen.User;
import com.lm.service.UserService;

@Controller
@RequestMapping("/user")
@Transactional
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private Logger log = LoggerFactory.getLogger(BookController.class);

	@RequestMapping(method = RequestMethod.GET, value = "{userId}")
	@ResponseBody
	public User findOne(@PathVariable String userId) {
		User user = userService.findOne(userId);
		log.info("user firstName:{}", user.getFirstName());

		User user2 = new User();
		user2.setUserId(user.getUserId());
		user2.setVersion(user.getVersion());
		user2.setFirstName(user.getFirstName());
		user2.setLastName(user.getLastName());
		user2.setGender(user.getGender());
		user2.setBirthDate(user.getBirthDate());

		return user2;

	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public User createUser(@RequestBody User user) {
		log.info("enter into post method:");

		User user2 = userService.createUser(user);
		return user2;

	}

}
