package com.danielcorroto.conspiracy_letters.service;

import com.danielcorroto.conspiracy_letters.model.Player;

/**
 * Service de configuración
 * 
 * @author Daniel Corroto
 *
 */
public interface ConfigurationService {

	/**
	 * Obtiene información del usuario a partir de su nombre
	 * 
	 * @param name
	 *            Nombre de usuario
	 * @return Datos del jugador
	 */
	public Player getPlayerByName(String name);

	/**
	 * Obtiene el número total de jugadores en la base de datos
	 * 
	 * @return Número de jugadores en la base de datos
	 */
	public long getPlayerSize();

}
