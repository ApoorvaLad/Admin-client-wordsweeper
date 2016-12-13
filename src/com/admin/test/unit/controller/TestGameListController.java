package com.admin.test.unit.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.admin.scorpio.controller.GameListController;
import com.admin.test.server.MockServerAccess;
import com.admin.xml.Message;
import com.admin.scorpio.model.Game;
import com.admin.scorpio.model.Model;
import com.admin.scorpio.view.Application;

/**
 * This class is used to test the Game List Controller
 * @author Apoorva 
 */
public class TestGameListController {

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
	 * This tests the Game List Controller
	 */
	@Test
	public void gameListController() {

		Game game = new Game();
		game.setGameID("newGame");
		GameListController controller = new GameListController(client);
		controller.process();

		ArrayList<Message> reqs = mockServer.getAndClearMessages();
		assertTrue(reqs.size() == 1);
		Message r = reqs.get(0);
		assertEquals("listGamesRequest", r.contents.getFirstChild().getLocalName());
		assertTrue(reqs.size() == 1);

	}

}
