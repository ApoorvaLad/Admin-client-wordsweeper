package com.admin.test.unit.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.admin.scorpio.controller.GameController;
import com.admin.test.server.MockServerAccess;
import com.admin.xml.Message;
import com.admin.scorpio.model.Game;
import com.admin.scorpio.model.Model;
import com.admin.scorpio.view.Application;

/**
 * This tests the Game Panel Controller
 * 
 * @author Apoorva
 *
 */
public class TestGameController {
	Model model = new Model();
	Application client = new Application();
	MockServerAccess mockServer = new MockServerAccess("localhost");

	/** this is the setting for the test */
	@Before
	public void set() {
		// FIRST thing to do is register the protocol being used.
		if (!Message.configure("wordsweeper.xsd")) {
			fail("unable to configure protocol");
		}
		client.setVisible(true);
		client.setServerAccess(mockServer);
	}

	@Test
	public void testShowGameResponseController() {
		Game game = new Game();
		String id = "1";
		if (game.getGameID() != null) {
			id = game.getGameID();
		}

		GameController controller = new GameController(client);
		controller.process(id);
		ArrayList<Message> reqs = mockServer.getAndClearMessages();
		Message r = reqs.get(0);
		assertEquals("showGameStateRequest", r.contents.getFirstChild().getLocalName());
		assertTrue(reqs.size() == 1);
		assertEquals(id, r.contents.getFirstChild().getAttributes().getNamedItem("gameId").getNodeValue());

	}

}