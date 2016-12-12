package com.admin.test.unit.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.admin.controller.ConnectResponseController;
import com.admin.test.server.MockServerAccess;
import com.admin.xml.Message;
import com.model.Game;
import com.model.Model;
import com.view.Application;


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

