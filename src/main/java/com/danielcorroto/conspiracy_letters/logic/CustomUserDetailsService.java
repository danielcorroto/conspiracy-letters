package com.danielcorroto.conspiracy_letters.logic;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.danielcorroto.conspiracy_letters.model.Player;
import com.danielcorroto.conspiracy_letters.service.ConfigurationService;

/**
 * Custom service for login authentication
 * 
 * @author Daniel Corroto
 *
 */
public class CustomUserDetailsService implements UserDetailsService {
	private static final Logger LOGGER = Logger.getLogger(CustomUserDetailsService.class);
	private static final String ROLE_ADMIN = "ROLE_ADMIN";
	private static final String ROLE_USER = "ROLE_USER";

	private static final String DEFAULT_USER = "admin";
	private static final String DEFAULT_PASS = "nimda";

	@Autowired
	private ConfigurationService configService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LOGGER.trace("Loggin as " + username);
		if (configService.getPlayerSize() == 0) {
			return loadByDefault(username);
		} else {
			return loadByDatabase(username);
		}
	}

	/**
	 * Login sobre el usuario por defecto
	 * 
	 * @param username
	 *            Nombre de usuario que se está logando
	 * @return Información del usuario por defecto
	 * @throws UsernameNotFoundException
	 *             Si el usuario no se encuentra
	 */
	private UserDetails loadByDefault(String username) throws UsernameNotFoundException {
		if (DEFAULT_USER.equals(username)) {
			Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
			auths.add(new SimpleGrantedAuthority(ROLE_USER));
			auths.add(new SimpleGrantedAuthority(ROLE_ADMIN));

			BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
			String pass = pe.encode(DEFAULT_PASS);

			UserDetails user = new User(username, pass, auths);
			return user;
		} else {
			throw new UsernameNotFoundException("User " + username + " not found");
		}
	}

	/**
	 * Login contra la base de datos
	 * 
	 * @param username
	 *            Nombre de usuario que se está logando
	 * @return Información del usuario por defecto
	 * @throws UsernameNotFoundException
	 *             Si el usuario no se encuentra
	 */
	private UserDetails loadByDatabase(String username) throws UsernameNotFoundException {
		Player p = configService.getPlayerByName(username);
		if (p != null) {
			Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
			auths.add(new SimpleGrantedAuthority(ROLE_USER));
			if (p.isAdmin()) {
				auths.add(new SimpleGrantedAuthority(ROLE_ADMIN));
			}

			UserDetails user = new User(username, p.getPassword(), auths);
			return user;
		} else {
			throw new UsernameNotFoundException("User " + username + " not found");
		}

	}

}
