package com.danielcorroto.conspiracy_letters.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danielcorroto.conspiracy_letters.dao.PlayerDao;
import com.danielcorroto.conspiracy_letters.model.Player;
import com.danielcorroto.conspiracy_letters.service.ConfigurationService;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {
	private static final Logger LOGGER = Logger.getLogger(ConfigurationServiceImpl.class);

	@Autowired
	private PlayerDao playerDao;

	@Override
	@Transactional(readOnly = true)
	public Player getPlayerByName(String name) {
		LOGGER.debug("Getting user data by " + name);
		return playerDao.findBy("name", name);
	}

	@Override
	@Transactional(readOnly = true)
	public long getPlayerSize() {
		LOGGER.debug("Getting user size");
		return playerDao.size();
	}

}
