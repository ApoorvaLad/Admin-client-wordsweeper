package com.admin.controller;

import java.util.HashMap;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.admin.xml.Message;
import com.model.Game;
import com.model.Model;
import com.view.Application;

/**
 * Controller to handle and display the list of Active games
 * @author Apoorva
 *
 */
public class GameListResponseController extends ControllerChain {

	public Application application;
	public Model model;

	public GameListResponseController(Application a, Model m) {
		super();
		application = a;
		model = m;
	}

	public boolean process(Message response) {
		String type = response.contents.getFirstChild().getLocalName();
		if (!type.equals("listGamesResponse")) {
			return next.process(response);
		}
		Node listResponse = response.contents.getFirstChild();
		Game game = new Game();
		NodeList list = listResponse.getChildNodes();
		HashMap<String, String> gameDetails = game.getGameDetails();
		for (int i = 0; i < list.getLength(); i++) {
			Node n = list.item(i);
			String gameID = n.getAttributes().getNamedItem("gameId").getNodeValue();
			
			game.setGameID(gameID);
			gameDetails.put("Game " + (i + 1),gameID);
			model.addGame(game);
			application.getAdminPanel().getGameListPanel().getModel().addElement("Game " + (i + 1));
		}
		return true;
	}

}
