package com.danielcorroto.conspiracy_letters.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.danielcorroto.conspiracy_letters.model.json.JsonGame;
import com.danielcorroto.conspiracy_letters.model.json.JsonGameInformation;
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
	 * @return Lista de mis partidas en juego
	 */
	@RequestMapping("/games")
	public List<JsonGame> gamesList() {
		User user = getUser();
		LOGGER.debug("Getting game list");

		List<JsonGame> games = gameService.getGamesByUsername(user.getUsername());
		return games;
	}

	/**
	 * Obtiene información de la partida para el jugador actual
	 * 
	 * @param gameId
	 *            Identificador de la partida
	 * @return Información sobre la partida
	 */
	@RequestMapping("/game/{gameId}")
	public JsonGameInformation gameInfo(@PathVariable Long gameId) {
		User user = getUser();
		LOGGER.debug("Getting game information: " + gameId);

		JsonGameInformation game = gameService.getGameInformation(gameId, user.getUsername());
		return game;
	}

	/**
	 * Invita a un jugador a jugar
	 * 
	 * @param invitation
	 *            Información de la invitación
	 * @return Si la invitación ha sido correcta
	 */
	@RequestMapping(value = "/invitation/add", method = RequestMethod.POST)
	public boolean addInvitation(@ModelAttribute JsonGameInvitation invitation) {
		User user = getUser();
		LOGGER.debug("Adding invitation from " + user.getUsername() + ": " + invitation);

		boolean isInvited = gameService.addInvitation(user.getUsername(), invitation);
		return isInvited;
	}

	/**
	 * Partidas a las que se ha invitado
	 * 
	 * @return Lista de partidas a las que he invitado
	 */
	@RequestMapping("/invitation/list")
	public List<JsonGameInvitation> invitationList() {
		User user = getUser();
		LOGGER.debug("Getting invitation list");

		List<JsonGameInvitation> invitation = gameService.getInvitationByUsername(user.getUsername());
		return invitation;
	}

	/**
	 * Partidas a las que se ha sido invitado
	 * 
	 * @return Lista de partidas a las que he invitado
	 */
	@RequestMapping("/invited/list")
	public List<JsonGameInvitation> invitedList() {
		User user = getUser();
		LOGGER.debug("Getting invited list");

		List<JsonGameInvitation> invited = gameService.getInvitedByUsername(user.getUsername());
		return invited;
	}

	/**
	 * Acepta una invitación
	 * 
	 * @param invitationId
	 *            Identificador de la invitación
	 * @return Identificador de invitación o null si no es correcto
	 */
	@RequestMapping("/invited/accept/{invitationId}")
	public JsonGame invitedAccept(@PathVariable Long invitationId) {
		User user = getUser();
		LOGGER.debug("Accepting invitation: " + invitationId);

		JsonGame game = gameService.invitedAccept(user.getUsername(), invitationId);
		return game;
	}

	/**
	 * Rechaza una invitación
	 * 
	 * @param invitationId
	 *            Identificador de la invitación
	 * @return Identificador de invitación o null si no es correcto
	 */
	@RequestMapping("/invited/reject/{invitationId}")
	public Long invitedReject(@PathVariable Long invitationId) {
		User user = getUser();
		LOGGER.debug("Rejecting invitation: " + invitationId);

		boolean ok = gameService.invitedReject(user.getUsername(), invitationId);
		return (ok) ? invitationId : null;
	}

	/**
	 * Cancelar una invitación
	 * 
	 * @param invitationId
	 *            Identificador de la invitación
	 * @return Identificador de invitación o null si no es correcto
	 */
	@RequestMapping("/invitation/cancel/{invitationId}")
	public Long invitationCancel(@PathVariable Long invitationId) {
		User user = getUser();
		LOGGER.debug("Canceling invitation: " + invitationId);

		boolean ok = gameService.invitationCancel(user.getUsername(), invitationId);
		return (ok) ? invitationId : null;
	}

}
