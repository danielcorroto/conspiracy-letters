package com.danielcorroto.conspiracy_letters.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.danielcorroto.conspiracy_letters.dao.PlayerDao;
import com.danielcorroto.conspiracy_letters.model.Player;

/**
 * Player DAO
 * 
 * @author Daniel Corroto
 *
 */
@Repository
public class PlayerDaoImpl extends BasicDaoImpl<Player> implements PlayerDao {
	private static final Logger LOGGER = Logger.getLogger(PlayerDaoImpl.class);

	@Override
	public Player findByUsername(String username) {
		LOGGER.trace("Find by username " + username);
		
		return findBy("name", username);
	}

}
