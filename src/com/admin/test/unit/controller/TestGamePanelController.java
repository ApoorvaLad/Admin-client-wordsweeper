package com.admin.test.unit.controller;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.admin.controller.GameController;
import com.admin.test.server.MockServerAccess;
import com.admin.xml.Message;
import com.model.Game;
import com.model.Model;
import com.view.Application;

/**
 * This tests the Game Panel Controller
 * 
 * @author Apoorva
 *
 */
public class TestGamePanelController {
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
		String id = null;
		if (game.getGameID() != null) {
			id = game.getGameID();
		}

		GameController controller = new GameController(client);
		controller.process(id);

	}

}