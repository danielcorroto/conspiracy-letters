package com.danielcorroto.conspiracy_letters.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Relación entre jugador y partida
 * 
 * @author Daniel Corroto
 *
 */
@Entity
@Table(name = "player_game")
public class PlayerGame implements Serializable {
	private static final long serialVersionUID = 3853523767226541205L;

	/**
	 * Orden del jugador dentro de la partida (1..4)
	 */
	@Column(nullable = false)
	private int playerOrder;

	/**
	 * Puntos del jugador en la partida
	 */
	@Column(nullable = false)
	private int points;

	/**
	 * Jugador de la relación
	 */
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_player")
	private Player player;

	/**
	 * Partida de la relación
	 */
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_game")
	private Game game;

	public PlayerGame() {
	}

	public int getPoints() {
		return this.points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Player getPlayer() {
		return this.player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Game getGame() {
		return this.game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public int getPlayerOrder() {
		return playerOrder;
	}

	public void setPlayerOrder(int playerOrder) {
		this.playerOrder = playerOrder;
	}

}