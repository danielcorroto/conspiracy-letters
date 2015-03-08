package com.danielcorroto.conspiracy_letters.dao;

import java.util.List;

import com.danielcorroto.conspiracy_letters.model.GameInvitation;
import com.danielcorroto.conspiracy_letters.model.Player;

/**
 * DAO de la invitación
 * 
 * @author Daniel Corroto
 *
 */
public interface GameInvitationDao extends BasicDao<GameInvitation> {
	/**
	 * Lista de invitaciones que ha hecho el jugador
	 * 
	 * @param p
	 *            Jugador que ha invitado
	 * @return Lista de invitaciones
	 */
	public List<GameInvitation> getInvitation(Player p);

	/**
	 * Lista de invitaciones que se le han hecho al jugador
	 * 
	 * @param p
	 *            Jugador invitado
	 * @return Lista de invitaciones
	 */
	public List<GameInvitation> getInvited(Player p);
}
