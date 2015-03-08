package com.danielcorroto.conspiracy_letters.model.json;

/**
 * Información de una invitación a una partida de la Vista
 * 
 * @author Daniel Corroto
 *
 */
public class JsonGameInvitation {
	/**
	 * Nombre de la partida
	 */
	private String name;
	/**
	 * Jugador invitado a la partida
	 */
	private String guest;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGuest() {
		return guest;
	}

	public void setGuest(String guest) {
		this.guest = guest;
	}

	@Override
	public String toString() {
		return "JsonGameInvitation [name=" + name + ", guest=" + guest + "]";
	}

}
