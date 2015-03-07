package com.danielcorroto.conspiracy_letters.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielcorroto.conspiracy_letters.model.json.JsonGame;
import com.danielcorroto.conspiracy_letters.service.GameService;

@RestController
@RequestMapping("/api")
public class GamesRestController {
	@Autowired
	GameService gameService;

	/**
	 * Devuelve la lista de partidas del jugador logado
	 * 
	 * @return
	 */
	@RequestMapping("/games")
	public List<JsonGame> gamesList() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<JsonGame> games = gameService.getGamesByUsername(user.getUsername());
		return games;
	}
}
