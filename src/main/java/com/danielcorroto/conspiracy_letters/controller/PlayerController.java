package com.danielcorroto.conspiracy_letters.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.danielcorroto.conspiracy_letters.logic.InvitationLogic;

@Controller
@RequestMapping("/player/")
public class PlayerController extends BaseController {
	private static final Logger LOGGER = Logger.getLogger(PlayerController.class);

	@Autowired
	private InvitationLogic invitationLogic;

	@RequestMapping(value = { "/", "/games" })
	public String listGames(Map<String, Object> map) {
		User user = getUser();

		LOGGER.debug("Games list " + user.getUsername());
		map.put("username", user.getUsername());

		return "/games";
	}

	@RequestMapping("/creategame")
	public String createGame(Map<String, Object> map) {
		User user = getUser();

		LOGGER.debug("Creating new game");
		map.put("username", user.getUsername());
		map.put("gamename", invitationLogic.generateGameName());

		return "/creategame";
	}

	@RequestMapping("/game/{gameId}")
	public String showGame(@PathVariable Long gameId, Map<String, Object> map) {
		User user = getUser();

		LOGGER.debug("Showing game");
		map.put("username", user.getUsername());

		return "/game";
	}
}
