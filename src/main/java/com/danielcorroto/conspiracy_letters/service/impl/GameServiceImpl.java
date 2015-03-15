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
import com.danielcorroto.conspiracy_letters.model.json.JsonGameInformation;
import com.danielcorroto.conspiracy_letters.model.json.JsonGameInvitation;
import com.danielcorroto.conspiracy_letters.model.json.JsonPlayerInfo;
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
	@Transactional(readOnly = true)
	public JsonGameInformation getGameInformation(Long id, String username) {
		Player player = playerDao.findByUsername(username);
		Game game = gameDao.get(id);

		// Precondición deben existir player y game
		if (player == null || game == null) {
			return null;
		}

		GameSet set = gameSetDao.findLastByGame(game);

		// Precondición debe existir set
		if (set == null) {
			return null;
		}

		PlayerGame pg = playerGameDao.findByPlayerAndGame(player, game);
		List<PlayerGameSet> listpgs = playerGameSetDao.findByGameSet(set);

		// Precondición debe existir jugador juega la partida
		if (pg == null) {
			return null;
		}

		// Generar información de la partida
		JsonGameInformation info = new JsonGameInformation();
		info.setName(game.getName());
		info.setUnplayed(set.getDeck().split(CARD_SEPARATOR).length - 4);
		info.setDiscarded(getDiscardedFromGameSet(set, 3));

		for (PlayerGameSet pgs : listpgs) {
			if (pgs.getPlayer().getId() == player.getId()) {
				JsonPlayerInfo pi = createPlayerInformation(pgs, pg, true);
				info.setMe(pi);
			} else {
				PlayerGame pgInfo = playerGameDao.findByPlayerAndGame(pgs.getPlayer(), game);
				JsonPlayerInfo pi = createPlayerInformation(pgs, pgInfo, false);
				info.setRival(pi);
			}
		}

		return info;
	}

	/**
	 * Crea la información del jugador
	 * 
	 * @param pgs
	 *            Información de la relaión jugador/set
	 * @param pgInfo
	 *            Información de la relación jugador/partida
	 * @param isMe
	 *            Si la información generada es del jugador que la solicita
	 * @return Información del jugador
	 */
	private JsonPlayerInfo createPlayerInformation(PlayerGameSet pgs, PlayerGame pgInfo, boolean isMe) {
		JsonPlayerInfo pi = new JsonPlayerInfo();
		pi.setName(pgs.getPlayer().getName());
		pi.setPoints(pgInfo.getPoints());
		pi.setPlayed(transformStringToList(pgs.getDeck(), true));
		pi.setHand(transformStringToList(pgs.getHand(), isMe));

		// FIXME
		pi.setLog(new ArrayList<String>());

		return pi;
	}

	/**
	 * Transforma una cadena con las cartas en una lista. Si la lista no es
	 * visible, se devuelve una lista con el tamaño indicado rellena de 0
	 * 
	 * @param input
	 *            Cadena p. ej "1 2 2 8 5"
	 * @param visible
	 *            Si la tranformación deja las cartas visibles
	 * @return Lista de enteros con las cartas
	 */
	private List<Integer> transformStringToList(String input, boolean visible) {
		if (input == null) {
			input = "";
		}

		String[] cards = input.split(CARD_SEPARATOR);
		List<Integer> cardList = new ArrayList<Integer>();

		if (!input.isEmpty()) {
			for (String card : cards) {
				cardList.add(visible ? Integer.parseInt(card) : 0);
			}
		}
		
		return cardList;
	}

	/**
	 * Genera una lista de descartes del mazo a partir de la información de un
	 * set
	 * 
	 * @param set
	 *            Información del set
	 * @param size
	 *            Tamaño de la lista
	 * @return Lista de descartes
	 */
	private List<Integer> getDiscardedFromGameSet(GameSet set, int size) {
		List<Integer> res = new ArrayList<Integer>();

		String[] cards = set.getDeck().split(CARD_SEPARATOR);
		for (int i = 0; i < size; i++) {
			res.add(Integer.parseInt(cards[cards.length - 1 - i]));
		}

		return res;
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
			PlayerGameSet pgs = createPlayerGameSet(player, gameset, i == 0);
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

	/**
	 * Genera una baraja barajada
	 * 
	 * @return Cadena con los datos de la baraja
	 */
	private String createRandomDeck() {
		List<String> cards = Arrays
				.asList(new String[] { "1", "1", "1", "1", "1", "2", "2", "3", "3", "4", "4", "5", "5", "6", "7", "8", });
		Collections.shuffle(cards);
		return StringUtils.join(cards, CARD_SEPARATOR);
	}

	/**
	 * Crea una entidad de relación jugador/set
	 * 
	 * @param player
	 *            Información del jugador
	 * @param gameset
	 *            Información del set
	 * @param isFirstPlayer
	 *            Si es el primero en jugar
	 * @return Entidad jugador/set
	 */
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
		pgs.setDeck("");
		pgs.setHand(card);
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
