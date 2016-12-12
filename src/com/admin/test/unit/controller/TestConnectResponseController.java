package com.admin.test.unit.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.admin.scorpio.controller.ConnectResponseController;
import com.admin.test.server.MockServerAccess;
import com.admin.xml.Message;
import com.admin.scorpio.model.Game;
import com.admin.scorpio.model.Model;
import com.admin.scorpio.view.Application;


/**
 * Class to test the ConnectResponseController
 * @author Apoorva
 *
 */
public class TestConnectResponseController {
	Model model = new Model();
	Application client = new Application();
	MockServerAccess mockServer = new MockServerAccess("localhost");

	@Before
	public void set() {
		/**this is the preparation before the test.*/
		// FIRST thing to do is register the protocol being used.
		if (!Message.configure("wordsweeper.xsd")) {
			fail("unable to configure protocol");
		}
		client.setVisible(true);
		client.setServerAccess(mockServer);
	}
	
	/**this is the test for connect response process*/
	@Test
	public void TestConnectResponseProcess(){
		String id= "Game1";
		
		Game game = new Game();
		game.setGameID(id);

		String xml= "<?xml version=\"1.0\" encoding=\"UTF-8\"?><response id=\"someMessageID\" success=\"true\">"
		    + "<connectResponse id=\"%s\"/></response>";
		xml =String.format(xml,id);
		Message m = new Message(xml);
		ConnectResponseController crc=new ConnectResponseController(client,model);
		assertTrue(crc.process(m));
	}

}

