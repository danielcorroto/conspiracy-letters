package com.danielcorroto.conspiracy_letters.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Información del set de una partida
 * 
 * @author Daniel Corroto
 *
 */
@Entity
@Table(name = "gameset")
public class GameSet implements Serializable {
	private static final long serialVersionUID = 5543901443096452813L;

	/**
	 * Identificador del set de la partida
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	/**
	 * Cartas del mazo
	 */
	@Column
	private String deck;

	/**
	 * Jugador que gana el set
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "turn")
	private Player turn;
	/**
	 * Jugador que gana el set
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "winner")
	private Player winner;

	/**
	 * Partida relacionada con el set
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_game")
	private Game game;

	/**
	 * Jugadores que participan en el set
	 */
	@OneToMany(mappedBy = "gameset")
	private List<PlayerGameSet> playerGamesets;

	public GameSet() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeck() {
		return this.deck;
	}

	public void setDeck(String deck) {
		this.deck = deck;
	}

	public Game getGame() {
		return this.game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public List<PlayerGameSet> getPlayerGamesets() {
		return playerGamesets;
	}

	public void setPlayerGamesets(List<PlayerGameSet> playerGamesets) {
		this.playerGamesets = playerGamesets;
	}

	public Player getTurn() {
		return turn;
	}

	public void setTurn(Player turn) {
		this.turn = turn;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

	@Override
	public String toString() {
		return "GameSet [id=" + id + ", deck=" + deck + ", turn=" + turn + ", game=" + game + "]";
	}

}
