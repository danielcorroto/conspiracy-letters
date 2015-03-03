package com.danielcorroto.conspiracy_letters.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Información del jugador
 * 
 * @author Daniel Corroto
 *
 */
@Entity
@Table(name = "player")
public class Player implements Serializable {
	private static final long serialVersionUID = -2851122693385327463L;

	/**
	 * Número identificador del usuario
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	/**
	 * Nombre del usuario (login)
	 */
	@Column(nullable = false, unique = true)
	private String name;

	/**
	 * Contraseña del usuario, cifrada con BCryptPasswordEncoder
	 */
	@Column(nullable = false)
	private String password;

	/**
	 * Si el usuario tiene permisos de administrador
	 */
	@Column(nullable = false)
	private boolean admin;

	/**
	 * Relación jugador/partidas
	 */
	@OneToMany(mappedBy = "player")
	private List<PlayerGame> playerGames;

	/**
	 * Relación jugador/sets
	 */
	@OneToMany(mappedBy = "player")
	private List<PlayerGameset> playerGamesets;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public List<PlayerGame> getPlayerGames() {
		return playerGames;
	}

	public void setPlayerGames(List<PlayerGame> playerGames) {
		this.playerGames = playerGames;
	}

	public List<PlayerGameset> getPlayerGamesets() {
		return playerGamesets;
	}

	public void setPlayerGamesets(List<PlayerGameset> playerGamesets) {
		this.playerGamesets = playerGamesets;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", name=" + name + ", password=" + password + ", admin=" + admin + "]";
	}

}
