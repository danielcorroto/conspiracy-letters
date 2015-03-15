package com.danielcorroto.conspiracy_letters.dao;

import java.util.List;

import com.danielcorroto.conspiracy_letters.model.GameSet;
import com.danielcorroto.conspiracy_letters.model.PlayerGameSet;

/**
 * DAO de la relación jugador/set
 * 
 * @author Daniel Corroto
 *
 */
public interface PlayerGameSetDao extends BasicDao<PlayerGameSet> {
	/**
	 * Obtiene todas las relaciones jugador/set
	 * 
	 * @param gameset
	 *            Inforamción del set
	 * @return Lista de relaciones jugador/set
	 */
	public List<PlayerGameSet> findByGameSet(GameSet gameset);
}
