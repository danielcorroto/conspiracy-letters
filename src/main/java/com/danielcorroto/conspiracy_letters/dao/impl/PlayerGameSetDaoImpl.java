package com.danielcorroto.conspiracy_letters.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.danielcorroto.conspiracy_letters.dao.PlayerGameSetDao;
import com.danielcorroto.conspiracy_letters.model.GameSet;
import com.danielcorroto.conspiracy_letters.model.PlayerGameSet;

/**
 * Player GameSet DAO
 * 
 * @author Daniel Corroto
 *
 */
@Repository
public class PlayerGameSetDaoImpl extends BasicDaoImpl<PlayerGameSet> implements PlayerGameSetDao {
	private static final Logger LOGGER = Logger.getLogger(PlayerGameSetDaoImpl.class);

	@Override
	public List<PlayerGameSet> findByGameSet(GameSet gameset) {
		LOGGER.trace("Finding by gameset: " + gameset.getId());

		@SuppressWarnings("unchecked")
		List<PlayerGameSet> list = (List<PlayerGameSet>) getSession().createCriteria(PlayerGameSet.class)
				.add(Restrictions.eq("gameset", gameset)).list();
		return list;
	}
}
