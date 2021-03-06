package com.danielcorroto.conspiracy_letters.model.json;

import java.util.List;

/**
 * Información de un jugador en un set
 * 
 * @author Daniel Corroto
 *
 */
public class JsonPlayerInfo {
	/**
	 * Nombre del jugador
	 */
	private String name;

	/**
	 * Puntuación del jugador
	 */
	private int points;

	/**
	 * Cartas jugadas por el jugador
	 */
	private List<Integer> played;

	/**
	 * Cartas en la mano del jugador
	 */
	private List<Integer> hand;

	/**
	 * Registro de las jugadas del jugador
	 */
	private List<String> log;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public List<Integer> getPlayed() {
		return played;
	}

	public void setPlayed(List<Integer> played) {
		this.played = played;
	}

	public List<String> getLog() {
		return log;
	}

	public void setLog(List<String> log) {
		this.log = log;
	}

	public List<Integer> getHand() {
		return hand;
	}

	public void setHand(List<Integer> hand) {
		this.hand = hand;
	}

	@Override
	public String toString() {
		return "JsonPlayerInfo [name=" + name + ", points=" + points + ", played=" + played + ", hand=" + hand + ", log=" + log + "]";
	}

}
