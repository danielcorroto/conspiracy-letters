package com.danielcorroto.conspiracy_letters.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Relación entre jugador y set de partida
 * 
 * @author Daniel Corroto
 *
 */
@Entity
@Table(name = "player_gameset")
public class PlayerGameset implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Cartas del jugador
	 */
	private int deck;

	/**
	 * Jugador de la relación
	 */
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_player")
	private Player player;

	/**
	 * Set de la relación
	 */
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_gameset")
	private Gameset gameset;

	public PlayerGameset() {
	}

	public int getDeck() {
		return this.deck;
	}

	public void setDeck(int deck) {
		this.deck = deck;
	}

	public Player getPlayer() {
		return this.player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Gameset getGameset() {
		return this.gameset;
	}

	public void setGameset(Gameset gameset) {
		this.gameset = gameset;
	}
}