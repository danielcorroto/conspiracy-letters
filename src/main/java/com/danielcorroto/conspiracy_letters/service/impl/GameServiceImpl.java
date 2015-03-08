package com.danielcorroto.conspiracy_letters.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danielcorroto.conspiracy_letters.dao.GameInvitationDao;
import com.danielcorroto.conspiracy_letters.dao.PlayerDao;
import com.danielcorroto.conspiracy_letters.dao.PlayerGameDao;
import com.danielcorroto.conspiracy_letters.model.GameInvitation;
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

}
