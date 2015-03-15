package com.danielcorroto.conspiracy_letters.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.danielcorroto.conspiracy_letters.dao.GameSetDao;
import com.danielcorroto.conspiracy_letters.model.Game;
import com.danielcorroto.conspiracy_letters.model.GameSet;

/**
 * Player DAO
 * 
 * @author Daniel Corroto
 *
 */
@Repository
public class GameSetDaoImpl extends BasicDaoImpl<GameSet> implements GameSetDao {
	private static final Logger LOGGER = Logger.getLogger(GameSetDaoImpl.class);

	@Override
	public GameSet findLastByGame(Game game) {
		LOGGER.trace("Find last gameset by game " + game.getId());
		
		GameSet set = (GameSet) getSession().createCriteria(GameSet.class).add(Restrictions.eq("game", game)).addOrder(Order.desc("id")).uniqueResult();
		return set;
	}

}
