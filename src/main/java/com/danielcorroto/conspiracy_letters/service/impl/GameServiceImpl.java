package com.danielcorroto.conspiracy_letters.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danielcorroto.conspiracy_letters.dao.GameDao;
import com.danielcorroto.conspiracy_letters.dao.GameInvitationDao;
import com.danielcorroto.conspiracy_letters.dao.GameSetDao;
import com.danielcorroto.conspiracy_letters.dao.PlayerDao;
import com.danielcorroto.conspiracy_letters.dao.PlayerGameDao;
import com.danielcorroto.conspiracy_letters.dao.PlayerGameSetDao;
import com.danielcorroto.conspiracy_letters.model.Game;
import com.danielcorroto.conspiracy_letters.model.GameInvitation;
import com.danielcorroto.conspiracy_letters.model.GameSet;
import com.danielcorroto.conspiracy_letters.model.Player;
import com.danielcorroto.conspiracy_letters.model.PlayerGame;
import com.danielcorroto.conspiracy_letters.model.PlayerGameSet;
import com.danielcorroto.conspiracy_letters.model.json.JsonGame;
import com.danielcorroto.conspiracy_letters.model.json.JsonGameInvitation;
import com.danielcorroto.conspiracy_letters.service.GameService;

@Service
public class GameServiceImpl implements GameService {
	private static final Logger LOGGER = Logger.getLogger(GameServiceImpl.class);
	
	private static final String CARD_SEPARATOR = " ";

	@Autowired
	private PlayerGameDao playerGameDao;

	@Autowired
	private PlayerGameSetDao playerGameSetDao;

	@Autowired
	private PlayerDao playerDao;

	@Autowired
	private GameDao gameDao;

	@Autowired
	private GameSetDao gameSetDao;

	@Autowired
	private GameInvitationDao gameInvitationDao;

	@Override
	@Transactional(readOnly = true)
	public Player findByUsername(String username) {
		LOGGER.debug("Getting player name " + username);
		Player p = playerDao.findByUsername(username);
		return p;
	}

	@Override
	@Transactional(readOnly = true)
	public List<JsonGame> getGamesByUsername(String username) {
		LOGGER.debug("Getting list of game for " + username);

		List<JsonGame> jsongames = new ArrayList<JsonGame>();
		Player p = playerDao.findByUsername(username);

		if (p != null) {
			List<PlayerGame> games = playerGameDao.findByPlayer(p);
			for (PlayerGame pg : games) {
				jsongames.add(new JsonGame(pg.getGame().getId(), pg.getGame().getName(), pg.getPoints()));
			}
		}

		return jsongames;
	}

	@Override
	@Transactional
	public boolean addInvitation(String host, JsonGameInvitation invitation) {
		LOGGER.debug("Adding invitation from " + host + ": " + invitation);

		Player hostPlayer = playerDao.findByUsername(host);
		Player guestPlayer = playerDao.findByUsername(invitation.getGuest());

		// Precondiciones
		if (hostPlayer == null || guestPlayer == null) {
			return false;
		}

		GameInvitation gi = new GameInvitation();
		gi.setCreation(new Date());
		gi.setName(invitation.getName());
		gi.setPlayer1(hostPlayer);
		gi.setPlayer2(guestPlayer);
		gameInvitationDao.save(gi);

		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public List<JsonGameInvitation> getInvitationByUsername(String username) {
		LOGGER.debug("Getting list of invitation for " + username);

		Player host = playerDao.findByUsername(username);
		List<GameInvitation> gis = gameInvitationDao.getInvitation(host);

		List<JsonGameInvitation> jsongi = new ArrayList<JsonGameInvitation>();
		for (GameInvitation gi : gis) {
			jsongi.add(new JsonGameInvitation(gi.getId(), gi.getName(), gi.getPlayer2().getName()));
		}

		return jsongi;
	}

	@Override
	@Transactional(readOnly = true)
	public List<JsonGameInvitation> getInvitedByUsername(String username) {
		LOGGER.debug("Getting list of invited for " + username);

		Player guest = playerDao.findByUsername(username);
		List<GameInvitation> gis = gameInvitationDao.getInvited(guest);

		List<JsonGameInvitation> jsongi = new ArrayList<JsonGameInvitation>();
		for (GameInvitation gi : gis) {
			jsongi.add(new JsonGameInvitation(gi.getId(), gi.getName(), gi.getPlayer1().getName()));
		}

		return jsongi;
	}

	@Override
	@Transactional
	public JsonGame invitedAccept(String guest, Long invitationId) {
		LOGGER.debug("Accepting invitation by " + guest + ": " + invitationId);

		// Precondición: error en los datos
		if (invitationId == null) {
			return null;
		}

		Player playerGuest = playerDao.findByUsername(guest);
		GameInvitation invitationData = gameInvitationDao.get(invitationId);

		// Precondición el jugador ha sido invitado a la partida indicada
		if (invitationData == null || invitationData.getPlayer2().getId() != playerGuest.getId()) {
			return null;
		}

		Player playerHost = playerDao.get(invitationData.getPlayer1().getId());
		List<Player> players = getPlayerListRandomOrdered(playerHost, playerGuest);

		// Partida
		Game game = new Game();
		game.setName(invitationData.getName());
		game = gameDao.save(game);

		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			PlayerGame pg = createPlayerGame(player, game, i + 1);
			playerGameDao.save(pg);
		}

		// Set
		GameSet gameset = new GameSet();
		gameset.setDeck(createRandomDeck());
		gameset.setGame(game);
		gameset.setTurn(players.get(0));
		gameset = gameSetDao.save(gameset);

		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			PlayerGameSet pgs = createPlayerGameSet(player, gameset, i==0);
			playerGameSetDao.save(pgs);
		}
		gameSetDao.save(gameset);

		// Eliminar invitación
		gameInvitationDao.delete(invitationId);

		return new JsonGame(game.getId(), game.getName(), 0);
	}

	/**
	 * Genera la entidad de relación jugador/partida
	 * 
	 * @param player
	 *            Datos del jugador
	 * @param game
	 *            Datos de la partida
	 * @param order
	 *            Orden del jugador (1..4)
	 * @return Entidad jugador/partida
	 */
	private PlayerGame createPlayerGame(Player player, Game game, int order) {
		PlayerGame pg = new PlayerGame();
		pg.setGame(game);
		pg.setPlayer(player);
		pg.setPlayerOrder(order);
		pg.setPoints(0);

		return pg;
	}

	private String createRandomDeck() {
		List<String> cards = Arrays
				.asList(new String[] { "1", "1", "1", "1", "1", "2", "2", "3", "3", "4", "4", "5", "5", "6", "7", "8", });
		Collections.shuffle(cards);
		return StringUtils.join(cards, CARD_SEPARATOR);
	}

	private PlayerGameSet createPlayerGameSet(Player player, GameSet gameset, boolean isFirstPlayer) {
		List<String> cards = Arrays.asList(gameset.getDeck().split(CARD_SEPARATOR));
		String card = cards.get(0);
		cards = cards.subList(1, cards.size());
		if (isFirstPlayer) {
			card += CARD_SEPARATOR + cards.get(0);
			cards = cards.subList(1, cards.size());
		}
		gameset.setDeck(StringUtils.join(cards, CARD_SEPARATOR));

		PlayerGameSet pgs = new PlayerGameSet();
		pgs.setDeck(card);
		pgs.setGameset(gameset);
		pgs.setPlayer(player);

		return pgs;
	}

	/**
	 * Genera una lista ordenada aleatoriamente de jugadores
	 * 
	 * @param player
	 *            Jugadores
	 * @return
	 */
	private List<Player> getPlayerListRandomOrdered(Player... player) {
		List<Player> list = Arrays.asList(player);
		Collections.shuffle(list);

		return list;
	}

	@Override
	@Transactional
	public boolean invitedReject(String guest, Long invitationId) {
		LOGGER.debug("Rejecting invitation by " + guest + ": " + invitationId);

		// Precondición: error en los datos
		if (invitationId == null) {
			return false;
		}

		Player p = playerDao.findByUsername(guest);
		GameInvitation invitationData = gameInvitationDao.get(invitationId);

		// Precondición el jugador ha sido invitado a la partida indicada
		if (invitationData == null || invitationData.getPlayer2().getId() != p.getId()) {
			return false;
		}

		gameInvitationDao.delete(invitationId);

		return true;
	}

	@Override
	@Transactional
	public boolean invitationCancel(String host, Long invitationId) {
		LOGGER.debug("Canceling invitation by " + host + ": " + invitationId);

		// Precondición: error en los datos
		if (invitationId == null) {
			return false;
		}

		Player p = playerDao.findByUsername(host);
		GameInvitation invitationData = gameInvitationDao.get(invitationId);

		// Precondición el jugador ha sido invitado a la partida indicada
		if (invitationData == null || invitationData.getPlayer1().getId() != p.getId()) {
			return false;
		}

		gameInvitationDao.delete(invitationId);

		return true;
	}

}
