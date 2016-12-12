package com.admin.test.unit.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.admin.scorpio.controller.GameListResponseController;
import com.admin.scorpio.model.Game;
import com.admin.scorpio.model.Model;
import com.admin.scorpio.view.Application;
import com.admin.test.server.MockServerAccess;
import com.admin.xml.Message;

/**
 * This tests the Game List Response
 * 
 * @author Apoorva
 *
 */
public class TestGameListResponseController {
	Model model = new Model();
	Application client = new Application();
	MockServerAccess mockServer = new MockServerAccess("localhost");

	@Before
	public void set() {
		// FIRST thing to do is register the protocol being used.
		if (!Message.configure("wordsweeper.xsd")) {
			fail("unable to configure protocol");
		}
		client.setVisible(true);
		client.setServerAccess(mockServer);
	}

	/**
	 * this is responsible for testing the GameListResponse - For a single game
	 */
	@Test
	public void setGameListResponseController1() {

		String gameId = "newGame";
		int noOfPlayers = 1;
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><response id=\"someMessageID\" success=\"true\">"
				+ "<listGamesResponse><gameBrief gameId = \"%s\" players=\"%d\"/></listGamesResponse></response>";
		xml = String.format(xml, gameId, noOfPlayers);
		Message m = new Message(xml);
		new GameListResponseController(client, model).process(m);

		Game game = new Game();
		game.setGameID(gameId);
		game.setNoOfPlayers(noOfPlayers);
		model.addGame(game);

		assertEquals(model.getGames().get(1).getGameID(), gameId);
		assertEquals(model.getGames().get(1).getNoOfPlayers(), noOfPlayers);

	}
	
	

	/**
	 * this is responsible for testing the GameListResponse - For a multiple games 
	 */
	@Test
	public void setGameListResponseController2() {

		String gameId1 = "newGame";
		int noOfPlayers1 = 1;
		String gameId2 = "game";
		int noOfPlayers2 = 1;
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><response id=\"someMessageID\" success=\"true\">"
				+ "<listGamesResponse><gameBrief gameId = \"%s\" players=\"%d\"/><gameBrief gameId = \"%s\" players=\"%d\"/></listGamesResponse></response>";
		xml = String.format(xml, gameId1, noOfPlayers1, gameId2, noOfPlayers2);
		Message m = new Message(xml);
		new GameListResponseController(client, model).process(m);

		Game game1= new Game();
		Game game2= new Game();
		game1.setGameID(gameId1);
		game1.setNoOfPlayers(noOfPlayers1);
		game2.setGameID(gameId2);
		game2.setNoOfPlayers(noOfPlayers2);
		model.addGame(game1);
		model.addGame(game2);

		assertEquals(model.getGames().get(2).getGameID(), gameId1);
		assertEquals(model.getGames().get(2).getNoOfPlayers(), noOfPlayers1);

	}

}
