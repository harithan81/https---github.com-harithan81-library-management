package com.lm.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lm.domain.gen.UserActivity;
import com.lm.replicator.UserActivityReplicator;
import com.lm.service.UserActivityService;

@Controller
@RequestMapping("/userActivity")
@Transactional
public class UserActivityController {
	@Autowired
	private UserActivityService userActivityService;
	@Autowired
	private UserActivityReplicator userActivityReplicator;

	@RequestMapping(method = RequestMethod.GET, value = "{userActivityId}")
	@ResponseBody
	public UserActivity findOne(@PathVariable int userActivityId) {
		UserActivity userActivity = userActivityService.findOne(userActivityId);
		return userActivityReplicator.replicate(userActivity);
	}

	@RequestMapping(method = RequestMethod.POST, value = "{userActivityId}")
	@ResponseBody
	public UserActivity renewBook(@PathVariable int userActivityId) {
		return userActivityService.renewBook(userActivityId);
	}
}
