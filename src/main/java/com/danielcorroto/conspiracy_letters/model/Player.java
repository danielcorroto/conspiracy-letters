package com.danielcorroto.conspiracy_letters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Información del jugador
 * 
 * @author Daniel Corroto
 *
 */
@Entity
@Table(name = "player")
public class Player {
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

	@Override
	public String toString() {
		return "Player [id=" + id + ", name=" + name + ", password=" + password + ", admin=" + admin + "]";
	}

}
