package com.danielcorroto.conspiracy_letters.dao;

import java.util.List;

import com.danielcorroto.conspiracy_letters.model.Player;
import com.danielcorroto.conspiracy_letters.model.PlayerGame;

/**
 * DAO de la relación jugador/partida
 * 
 * @author Daniel Corroto
 *
 */
public interface PlayerGameDao extends BasicDao<PlayerGame> {
	/**
	 * Devuelve la información de las partidas de un jugador
	 * 
	 * @param player
	 *            Información del jugador
	 * @return Lista de partidas
	 */
	public List<PlayerGame> findByPlayer(Player player);
}
