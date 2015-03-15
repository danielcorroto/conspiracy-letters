package com.danielcorroto.conspiracy_letters.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class PlayerGameSet implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Número identificador de la relación jugador/set
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	/**
	 * Cartas del jugador
	 */
	private String hand;

	/**
	 * Cartas jugadas por el jugador
	 */
	private String deck;

	/**
	 * Jugador de la relación
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_player")
	private Player player;

	/**
	 * Set de la relación
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_gameset")
	private GameSet gameset;

	public PlayerGameSet() {
	}

	public String getDeck() {
		return this.deck;
	}

	public void setDeck(String deck) {
		this.deck = deck;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getHand() {
		return hand;
	}

	public void setHand(String hand) {
		this.hand = hand;
	}

	public Player getPlayer() {
		return this.player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public GameSet getGameset() {
		return this.gameset;
	}

	public void setGameset(GameSet gameset) {
		this.gameset = gameset;
	}

	@Override
	public String toString() {
		return "PlayerGameSet [id=" + id + ", deck=" + deck + ", player=" + player + ", gameset=" + gameset + "]";
	}

}