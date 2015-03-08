package com.danielcorroto.conspiracy_letters.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Información sobre las invitaciones a jugar
 * 
 * @author Daniel Corroto
 *
 */
@Entity
@Table(name = "game_invitation")
public class GameInvitation implements Serializable {
	private static final long serialVersionUID = 7410466075048389303L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date creation;

	@Column(nullable = false)
	private String name;

	/**
	 * Jugador anfitrión de la partida
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "host_player")
	private Player player1;

	/**
	 * Jugador invitado 1 (no opcional)
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "guest_player")
	private Player player2;

	public GameInvitation() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreation() {
		return this.creation;
	}

	public void setCreation(Date creation) {
		this.creation = creation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Player getPlayer1() {
		return this.player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return this.player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	@Override
	public String toString() {
		return "GameInvitation [id=" + id + ", creation=" + creation + ", name=" + name + ", player1=" + player1 + ", player2=" + player2
				+ "]";
	}

}
