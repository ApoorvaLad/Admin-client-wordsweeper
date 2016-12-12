package com.admin.test.unit.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.admin.scorpio.controller.ShowGameStateResponseController;
import com.admin.scorpio.model.Game;
import com.admin.scorpio.model.Model;
import com.admin.scorpio.view.Application;
import com.admin.test.server.MockServerAccess;
import com.admin.xml.Message;

public class TestShowGameResponseController {

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
	 * this is to test the ShowGameStateResponseController
	 */

	@Test
	public void testShowGameStateResponseController() {

		Game game = new Game();
		String name1 = "player1", name2 = "player2";
		int col1 = 4, col2 = 2, row1 = 1, row2 = 2;
		String bonus = "4,3";
		String gameId = "some id";
		String managingUser = name2;
		String board1 = "J,X,L,M,N,P,K,E,G,W,O,S,K,R,W,F";
		String contents = "V,Z,O,Qu,Z,W,W,B,F,K,P,G,O,H,T,J,X,L,M,C,C,Z,N,P,K,E,Z,R,J,G,W,O,S,K,U,E,K,R,W,F,E,K,R,P,X,P,U,A,M";
		String size = "7";
		String pos1 = col1 + "," + row1, pos2 = col2 + "," + row2;
		int score1 = 5, score2 = 10;

		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><response id=\"someMessageID\" success=\"true\">"
				+ "<boardResponse bonus=\"%s\" gameId=\"%s\" size=\"%s\" managingUser=\"%s\" contents=\"%s\">"
				+ "<player board=\"%s\" name=\"%s\" position=\"%s\" score=\"%s\"/>" + "</boardResponse></response>";
		xml = String.format(xml, bonus, gameId, size, managingUser, contents, board1, name1, pos1, score1);
		System.out.println(xml);
		Message m = new Message(xml);
		new ShowGameStateResponseController(client, model).process(m);

		game.setGameID(gameId);
		game.setNoOfPlayers(1);
		game.setScore(score1);
		model.addGame(game);
		assertEquals(model.getGames().get(0).getGameID(), gameId);
		assertEquals(model.getGames().get(0).getNoOfPlayers(), 1);
		assertEquals(model.getGames().get(0).getScore(), score1);

	}
}
