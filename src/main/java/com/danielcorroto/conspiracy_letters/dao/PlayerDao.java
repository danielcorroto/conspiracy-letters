package com.danielcorroto.conspiracy_letters.dao;

import com.danielcorroto.conspiracy_letters.model.Player;

/**
 * DAO del jugador
 * 
 * @author Daniel Corroto
 *
 */
public interface PlayerDao extends BasicDao<Player> {
	/**
	 * Devuelve la información de un jugador a partir de su nombre
	 * 
	 * @param username
	 *            Nombre del jugador
	 * @return Información del jugador
	 */
	public Player findByUsername(String username);
}
