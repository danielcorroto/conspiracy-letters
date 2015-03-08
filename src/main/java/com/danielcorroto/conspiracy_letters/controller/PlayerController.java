package com.danielcorroto.conspiracy_letters.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/player/")
public class PlayerController extends BaseController {
	private static final Logger LOGGER = Logger.getLogger(PlayerController.class);

	@RequestMapping(value = { "/", "/games" })
	public String listGames(Map<String, Object> map) {
		User user = getUser();

		LOGGER.debug("Games list " + user.getUsername());
		map.put("username", user.getUsername());

		return "/games";
	}

	@RequestMapping("/creategame")
	public String createGame() {
		return "/creategame";
	}
}
