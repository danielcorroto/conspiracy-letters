package com.danielcorroto.conspiracy_letters.dao;

import com.danielcorroto.conspiracy_letters.model.Game;
import com.danielcorroto.conspiracy_letters.model.GameSet;

/**
 * DAO del set de la partida
 * 
 * @author Daniel Corroto
 *
 */
public interface GameSetDao extends BasicDao<GameSet> {
	/**
	 * Busca el último gameset a partir de la información de una partida
	 * 
	 * @param game
	 *            Información de la partida
	 * @return Información del set
	 */
	public GameSet findLastByGame(Game game);
}
