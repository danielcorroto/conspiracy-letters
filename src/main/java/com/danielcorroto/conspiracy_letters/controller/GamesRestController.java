package com.danielcorroto.conspiracy_letters.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielcorroto.conspiracy_letters.model.Player;
import com.danielcorroto.conspiracy_letters.model.json.JsonGame;
import com.danielcorroto.conspiracy_letters.service.GameService;

@RestController
@RequestMapping("/api")
public class GamesRestController extends BaseController {
	private static final Logger LOGGER = Logger.getLogger(GamesRestController.class);

	@Autowired
	private GameService gameService;

	/**
	 * Devuelve la lista de partidas del jugador logado
	 * 
	 * @return
	 */
	@RequestMapping("/games")
	public List<JsonGame> gamesList() {
		User user = getUser();
		LOGGER.debug("Getting game list");

		List<JsonGame> games = gameService.getGamesByUsername(user.getUsername());
		return games;
	}

	/**
	 * Invita a un jugador a jugar
	 * 
	 * @param guest
	 *            Jugador invitado
	 * @return Si la invitación ha sido correcta
	 */
	@RequestMapping("/invitation/add")
	public boolean addInvitation(@ModelAttribute String guest) {
		User user = getUser();
		LOGGER.debug("Invitation from " + user.getUsername() + " to " + guest);
		
		if (user.getUsername().equals(guest) || guest.isEmpty()) {
			return false;
		}

		Player p = gameService.findByUsername(guest);
		// TODO guardar datos en base de datos
		return p != null;
	}
}
