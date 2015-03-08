package com.danielcorroto.conspiracy_letters.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.danielcorroto.conspiracy_letters.model.Player;
import com.danielcorroto.conspiracy_letters.model.json.JsonGame;
import com.danielcorroto.conspiracy_letters.model.json.JsonGameInvitation;
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
	@RequestMapping(value = "/invitation/add", method = RequestMethod.POST)
	public boolean addInvitation(@ModelAttribute JsonGameInvitation invitation) {
		User user = getUser();
		LOGGER.debug("Invitation from " + user.getUsername() + ": " + invitation);

		if (user.getUsername().equals(invitation.getGuest()) || invitation.getGuest().isEmpty()) {
			return false;
		}

		Player p = gameService.findByUsername(invitation.getGuest());
		// TODO guardar datos en base de datos
		return p != null;
	}

}
