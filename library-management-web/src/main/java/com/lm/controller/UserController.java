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
import com.lm.replicator.UserReplicator;
import com.lm.service.UserService;

@Controller
@RequestMapping("/user")
@Transactional
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserReplicator userReplicator;

	private Logger log = LoggerFactory.getLogger(BookController.class);

	@RequestMapping(method = RequestMethod.GET, value = "{userId}")
	@ResponseBody
	public User findOne(@PathVariable String userId) {

		User user = userService.findOne(userId);
		return userReplicator.replicate(user);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

}
