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
	 * Manos de los jugadores
	 */
	private List<PlayerHand> hands;

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

	public List<PlayerHand> getHands() {
		return hands;
	}

	public void setHands(List<PlayerHand> hands) {
		this.hands = hands;
	}

	private class PlayerHand {
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

	}
}
