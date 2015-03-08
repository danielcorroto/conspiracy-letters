package com.danielcorroto.conspiracy_letters.model.json;

/**
 * Información de una invitación a una partida de la Vista
 * 
 * @author Daniel Corroto
 *
 */
public class JsonGameInvitation {
	/**
	 * Identificador de la invitación
	 */
	private Long id;
	/**
	 * Nombre de la partida
	 */
	private String name;
	/**
	 * Jugador invitado a la partida
	 */
	private String guest;

	public JsonGameInvitation() {
		super();
	}

	public JsonGameInvitation(Long id, String name, String guest) {
		super();
		this.id = id;
		this.name = name;
		this.guest = guest;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
		return "JsonGameInvitation [id=" + id + ", name=" + name + ", guest=" + guest + "]";
	}

}
