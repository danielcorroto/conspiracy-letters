package com.danielcorroto.conspiracy_letters.service;

import java.util.List;

import com.danielcorroto.conspiracy_letters.model.Player;
import com.danielcorroto.conspiracy_letters.model.json.JsonGame;
import com.danielcorroto.conspiracy_letters.model.json.JsonGameInvitation;

/**
 * Service de patidas
 * 
 * @author Daniel Corroto
 *
 */
public interface GameService {
	// PLAYER

	/**
	 * Obtiene los datos de un jugador a partir de su nombre
	 * 
	 * @param username
	 *            Nombre del jugador
	 * @return Datos del jugador
	 */
	public Player findByUsername(String username);

	// GAME

	/**
	 * Obtiene la lista de partidas de un jugador
	 * 
	 * @param username
	 *            Nombre del jugador
	 * @return Lista de partidas de ese jugador
	 */
	public List<JsonGame> getGamesByUsername(String username);

	// INVITATION

	/**
	 * Invitación de un jugador a unirse a una partida
	 * 
	 * @param host
	 *            Jugador que invita
	 * @param invitation
	 *            Información de la partida a unirse
	 * @return Si la invitación era correcta
	 */
	public boolean addInvitation(String host, JsonGameInvitation invitation);

	/**
	 * Lista de partidas a las que se ha invitado
	 * 
	 * @param username
	 *            Nombre del usuario host
	 * @return Lista de invitaciones
	 */
	public List<JsonGameInvitation> getInvitationByUsername(String username);

	/**
	 * Lista de partidas a las que se ha sido invitado
	 * 
	 * @param username
	 *            Nombre del usuario guest;
	 * @return Lista de invitaciones
	 */
	public List<JsonGameInvitation> getInvitedByUsername(String username);

}
