package com.admin.scorpio.controller;

import static org.junit.Assert.assertTrue;

import com.admin.xml.Message;
import com.admin.test.util.XMLUtil;
import com.admin.scorpio.view.Application;

/**
 * Controller to request a list of active games
 * @author Apoorva
 *
 */
public class GameListController implements IAdminController {

	Application application;

	private XMLUtil xml = new XMLUtil();

	public GameListController(Application application) {
		this.application = application;
	}

	/**
	 * Make the request to the server and wait
	 */
	public void process() {
		String xmlString = Message.requestHeader() + "<listGamesRequest/></request>";
		Message m = new Message(xmlString);

		application.getServerAccess().sendRequest(m);
		
	}

	@Override
	public boolean process(Message request) {
		// TODO Auto-generated method stub
		return false;
	}
}
