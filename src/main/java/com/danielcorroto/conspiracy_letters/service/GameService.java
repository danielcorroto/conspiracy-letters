package com.danielcorroto.conspiracy_letters.service;

import java.util.List;

import com.danielcorroto.conspiracy_letters.model.Player;
import com.danielcorroto.conspiracy_letters.model.json.JsonGame;

/**
 * Service de patidas
 * 
 * @author Daniel Corroto
 *
 */
public interface GameService {

	/**
	 * Obtiene la lista de partidas de un jugador
	 * 
	 * @param username
	 *            Nombre del jugador
	 * @return Lista de partidas de ese jugador
	 */
	public List<JsonGame> getGamesByUsername(String username);

	/**
	 * Obtiene los datos de un jugador a partir de su nombre
	 * 
	 * @param username
	 *            Nombre del jugador
	 * @return Datos del jugador
	 */
	public Player findByUsername(String username);

}
