package com.danielcorroto.conspiracy_letters.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/player/")
public class PlayerController {
	private static final Logger LOGGER = Logger.getLogger(PlayerController.class);

	@RequestMapping(value = "/")
	public String list(Map<String, Object> map) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		LOGGER.debug("Default page " + user.getUsername());
		map.put("name", user.getUsername());

		return "/test";
	}
}
