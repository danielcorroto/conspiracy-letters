package com.danielcorroto.conspiracy_letters.model.json;

import java.util.List;

/**
 * Información completa de la partida que se envía a la Vista
 * 
 * @author Daniel Corroto
 *
 */
public class JsonGameInformation {
	/**
	 * Nombre de la partida
	 */
	private String name;

	/**
	 * Cartas de la baraja que faltan por jugar
	 */
	private int unplayed;

	/**
	 * Cartas descartadas boca arriba
	 */
	private List<Integer> discarded;

	/**
	 * Información propia
	 */
	private JsonPlayerInfo me;

	/**
	 * Información del rival;
	 */
	private JsonPlayerInfo rival;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUnplayed() {
		return unplayed;
	}

	public void setUnplayed(int unplayed) {
		this.unplayed = unplayed;
	}

	public List<Integer> getDiscarded() {
		return discarded;
	}

	public void setDiscarded(List<Integer> discarded) {
		this.discarded = discarded;
	}

	public JsonPlayerInfo getMe() {
		return me;
	}

	public void setMe(JsonPlayerInfo me) {
		this.me = me;
	}

	public JsonPlayerInfo getRival() {
		return rival;
	}

	public void setRival(JsonPlayerInfo rival) {
		this.rival = rival;
	}

	@Override
	public String toString() {
		return "JsonGameInformation [name=" + name + ", unplayed=" + unplayed + ", discarded=" + discarded + ", me=" + me + ", rival="
				+ rival + "]";
	}

}
