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
 * Información de la partida
 * 
 * @author Daniel Corroto
 *
 */
@Entity
@Table(name = "game")
public class Game implements Serializable {
	private static final long serialVersionUID = -2392711897406792951L;

	/**
	 * Número identificador de la partida
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * Nombre de la partida
	 */
	@Column(nullable = false)
	private String name;

	/**
	 * Jugador que gana la partida
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "winner")
	private Player player;

	/**
	 * Sets de los que se compone una partida
	 */
	@OneToMany(mappedBy = "game")
	private List<GameSet> gamesets;

	/**
	 * Relación jugadores/partida
	 */
	@OneToMany(mappedBy = "game")
	private List<PlayerGame> playerGames;

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

	public List<GameSet> getGamesets() {
		return gamesets;
	}

	public void setGamesets(List<GameSet> gamesets) {
		this.gamesets = gamesets;
	}

	public List<PlayerGame> getPlayerGames() {
		return playerGames;
	}

	public void setPlayerGames(List<PlayerGame> playerGames) {
		this.playerGames = playerGames;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public String toString() {
		return "Match [id=" + id + ", name=" + name + "]";
	}

}
