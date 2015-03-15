package com.danielcorroto.conspiracy_letters.service;

import java.util.List;

import com.danielcorroto.conspiracy_letters.model.Player;
import com.danielcorroto.conspiracy_letters.model.json.JsonGame;
import com.danielcorroto.conspiracy_letters.model.json.JsonGameInformation;
import com.danielcorroto.conspiracy_letters.model.json.JsonGameInvitation;

/**
 * Service de jugadores, partidas e invitaciones
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

	/**
	 * Obtiene la información de una partida para el jugador indicado
	 * 
	 * @param id
	 *            Identificador de la partida
	 * @param username
	 *            Nombre del jugador
	 * @return Información de la partida o null si username no juega la partida
	 *         id
	 */
	public JsonGameInformation getGameInformation(Long id, String username);

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

	/**
	 * Acepta una invitación
	 * 
	 * @param guest
	 *            Jugador que ha sido invitado
	 * @param invitation
	 *            Identificador de la invitación
	 * @return Información de la partida
	 */
	public JsonGame invitedAccept(String guest, Long invitationId);

	/**
	 * Rechaza una invitación
	 * 
	 * @param guest
	 *            Jugador que ha sido invitado
	 * @param invitation
	 *            Identificador de la invitación
	 * @return true si se ha rechazado correctamente (guest correcto en la
	 *         invitación)
	 */
	public boolean invitedReject(String guest, Long invitationId);

	/**
	 * Cancela una invitación
	 * 
	 * @param host
	 *            Jugador que ha invitado
	 * @param invitation
	 *            Identificador de la invitación
	 * @return true se se ha cancelado correctamente (host correcto en la
	 *         invitación)
	 */
	public boolean invitationCancel(String host, Long invitationId);

}
