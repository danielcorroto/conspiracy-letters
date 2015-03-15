package com.danielcorroto.conspiracy_letters.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.danielcorroto.conspiracy_letters.dao.PlayerGameDao;
import com.danielcorroto.conspiracy_letters.model.Game;
import com.danielcorroto.conspiracy_letters.model.Player;
import com.danielcorroto.conspiracy_letters.model.PlayerGame;

/**
 * Player Game DAO
 * 
 * @author Daniel Corroto
 *
 */
@Repository
public class PlayerGameDaoImpl extends BasicDaoImpl<PlayerGame> implements PlayerGameDao {
	private static final Logger LOGGER = Logger.getLogger(PlayerGameDaoImpl.class);

	@Override
	public List<PlayerGame> findByPlayer(Player player) {
		LOGGER.trace("Finding by player " + player.getName());

		List<PlayerGame> pg = multipleFindBy("player", player);
		return pg;
	}

	@Override
	public PlayerGame findByPlayerAndGame(Player player, Game game) {
		LOGGER.trace("Finding by player " + player.getName() + " and game " + game.getId());

		PlayerGame found = (PlayerGame) getSession().createCriteria(PlayerGame.class).add(Restrictions.eq("player", player))
				.add(Restrictions.eq("game", game)).uniqueResult();
		return found;
	}
}
