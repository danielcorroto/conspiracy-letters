package com.danielcorroto.conspiracy_letters.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danielcorroto.conspiracy_letters.dao.PlayerDao;
import com.danielcorroto.conspiracy_letters.dao.PlayerGameDao;
import com.danielcorroto.conspiracy_letters.model.Player;
import com.danielcorroto.conspiracy_letters.model.PlayerGame;
import com.danielcorroto.conspiracy_letters.model.json.JsonGame;
import com.danielcorroto.conspiracy_letters.service.GameService;

@Service
public class GameServiceImpl implements GameService {
	private static final Logger LOGGER = Logger.getLogger(GameServiceImpl.class);

	@Autowired
	private PlayerGameDao playerGameDao;

	@Autowired
	private PlayerDao playerDao;

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

}
