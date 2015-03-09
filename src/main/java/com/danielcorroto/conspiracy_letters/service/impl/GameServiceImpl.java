package com.danielcorroto.conspiracy_letters.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danielcorroto.conspiracy_letters.dao.GameDao;
import com.danielcorroto.conspiracy_letters.dao.GameInvitationDao;
import com.danielcorroto.conspiracy_letters.dao.PlayerDao;
import com.danielcorroto.conspiracy_letters.dao.PlayerGameDao;
import com.danielcorroto.conspiracy_letters.model.Game;
import com.danielcorroto.conspiracy_letters.model.GameInvitation;
import com.danielcorroto.conspiracy_letters.model.GameSet;
import com.danielcorroto.conspiracy_letters.model.Player;
import com.danielcorroto.conspiracy_letters.model.PlayerGame;
import com.danielcorroto.conspiracy_letters.model.json.JsonGame;
import com.danielcorroto.conspiracy_letters.model.json.JsonGameInvitation;
import com.danielcorroto.conspiracy_letters.service.GameService;

@Service
public class GameServiceImpl implements GameService {
	private static final Logger LOGGER = Logger.getLogger(GameServiceImpl.class);

	@Autowired
	private PlayerGameDao playerGameDao;

	@Autowired
	private PlayerDao playerDao;

	@Autowired
	private GameDao gameDao;

	@Autowired
	private GameInvitationDao gameInvitationDao;

	@Override
	@Transactional(readOnly = true)
	public Player findByUsername(String username) {
		LOGGER.debug("Getting player name " + username);
		Player p = playerDao.findByUsername(username);
		return p;
	}

	@Override
	@Transactional(readOnly = true)
	public List<JsonGame> getGamesByUsername(String username) {
		LOGGER.debug("Getting list of game for " + username);

		List<JsonGame> jsongames = new ArrayList<JsonGame>();
		Player p = playerDao.findByUsername(username);

		if (p != null) {
			List<PlayerGame> games = playerGameDao.findByPlayer(p);
			for (PlayerGame pg : games) {
				jsongames.add(new JsonGame(pg.getGame().getId(), pg.getGame().getName(), pg.getPoints()));
			}
		}

		return jsongames;
	}

	@Override
	@Transactional
	public boolean addInvitation(String host, JsonGameInvitation invitation) {
		LOGGER.debug("Adding invitation from " + host + ": " + invitation);

		Player hostPlayer = playerDao.findByUsername(host);
		Player guestPlayer = playerDao.findByUsername(invitation.getGuest());

		// Precondiciones
		if (hostPlayer == null || guestPlayer == null) {
			return false;
		}

		GameInvitation gi = new GameInvitation();
		gi.setCreation(new Date());
		gi.setName(invitation.getName());
		gi.setPlayer1(hostPlayer);
		gi.setPlayer2(guestPlayer);
		gameInvitationDao.save(gi);

		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public List<JsonGameInvitation> getInvitationByUsername(String username) {
		LOGGER.debug("Getting list of invitation for " + username);

		Player host = playerDao.findByUsername(username);
		List<GameInvitation> gis = gameInvitationDao.getInvitation(host);

		List<JsonGameInvitation> jsongi = new ArrayList<JsonGameInvitation>();
		for (GameInvitation gi : gis) {
			jsongi.add(new JsonGameInvitation(gi.getId(), gi.getName(), gi.getPlayer2().getName()));
		}

		return jsongi;
	}

	@Override
	@Transactional(readOnly = true)
	public List<JsonGameInvitation> getInvitedByUsername(String username) {
		LOGGER.debug("Getting list of invited for " + username);

		Player guest = playerDao.findByUsername(username);
		List<GameInvitation> gis = gameInvitationDao.getInvited(guest);

		List<JsonGameInvitation> jsongi = new ArrayList<JsonGameInvitation>();
		for (GameInvitation gi : gis) {
			jsongi.add(new JsonGameInvitation(gi.getId(), gi.getName(), gi.getPlayer1().getName()));
		}

		return jsongi;
	}

	@Override
	@Transactional
	public JsonGame invitedAccept(String guest, Long invitationId) {
		LOGGER.debug("Accepting invitation by " + guest + ": " + invitationId);

		// Precondición: error en los datos
		if (invitationId == null) {
			return null;
		}

		Player p = playerDao.findByUsername(guest);
		GameInvitation invitationData = gameInvitationDao.get(invitationId);

		// Precondición el jugador ha sido invitado a la partida indicada
		if (invitationData == null || invitationData.getPlayer2().getId() != p.getId()) {
			return null;
		}

		Game game = new Game();
		game.setName(invitationData.getName());

		PlayerGame pg = new PlayerGame();
		pg.setGame(game);
		pg.setPlayer(invitationData.getPlayer1());
		pg.setPlayerOrder(1);
		pg.setPoints(0);

		List<PlayerGame> listPg = new ArrayList<PlayerGame>();
		listPg.add(pg);
		game.setPlayerGames(listPg);
		List<GameSet> listGs = new ArrayList<GameSet>();
		game.setGamesets(listGs);

		playerGameDao.save(pg);

		// TODO implementar gameset

		return null;
	}

	@Override
	@Transactional
	public boolean invitedReject(String guest, Long invitationId) {
		LOGGER.debug("Rejecting invitation by " + guest + ": " + invitationId);

		// Precondición: error en los datos
		if (invitationId == null) {
			return false;
		}

		Player p = playerDao.findByUsername(guest);
		GameInvitation invitationData = gameInvitationDao.get(invitationId);

		// Precondición el jugador ha sido invitado a la partida indicada
		if (invitationData == null || invitationData.getPlayer2().getId() != p.getId()) {
			return false;
		}

		gameInvitationDao.delete(invitationId);

		return true;
	}

	@Override
	@Transactional
	public boolean invitationCancel(String host, Long invitationId) {
		LOGGER.debug("Canceling invitation by " + host + ": " + invitationId);

		// Precondición: error en los datos
		if (invitationId == null) {
			return false;
		}

		Player p = playerDao.findByUsername(host);
		GameInvitation invitationData = gameInvitationDao.get(invitationId);

		// Precondición el jugador ha sido invitado a la partida indicada
		if (invitationData == null || invitationData.getPlayer1().getId() != p.getId()) {
			return false;
		}

		gameInvitationDao.delete(invitationId);

		return true;
	}

}
