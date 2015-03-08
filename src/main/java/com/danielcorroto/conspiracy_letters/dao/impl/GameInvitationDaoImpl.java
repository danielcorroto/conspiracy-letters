package com.danielcorroto.conspiracy_letters.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.danielcorroto.conspiracy_letters.dao.GameInvitationDao;
import com.danielcorroto.conspiracy_letters.model.GameInvitation;
import com.danielcorroto.conspiracy_letters.model.Player;

/**
 * Game Invitation DAO
 * 
 * @author Daniel Corroto
 *
 */
@Repository
public class GameInvitationDaoImpl extends BasicDaoImpl<GameInvitation> implements GameInvitationDao {

	@Override
	public List<GameInvitation> getInvitation(Player p) {
		List<GameInvitation> gi = multipleFindBy("player1", p);
		return gi;
	}

	@Override
	public List<GameInvitation> getInvited(Player p) {
		List<GameInvitation> gi = multipleFindBy("player2", p);
		return gi;
	}

}
