package com.admin.scorpio.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.admin.xml.Message;
import com.admin.scorpio.model.Game;
import com.admin.scorpio.model.Model;
import com.admin.scorpio.view.Application;

/**
 * Controller to handle and display the list of Active games
 * 
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

	/**
	 * This method handles the response for listing the games on the GameListPanel.
	 */
	public boolean process(Message response) {
		String type = response.contents.getFirstChild().getLocalName();
		if (!type.equals("listGamesResponse")) {
			return next.process(response);
		}
		Node listResponse = response.contents.getFirstChild();
		int prevListLength =0;
		NodeList list = listResponse.getChildNodes();
		
		List<Game> games = new ArrayList<>();
		List<String> availableIds = new ArrayList<>();

		for (Game g : model.getGames()) {
			availableIds.add(g.getGameID());
		}
		// Reset
		Application.getInstance().gameIndexMapping.clear();
		model.clearGames();
		application.getAdminPanel().getGameListPanel().getModel().removeAllElements();
	
			
		for (int i = 0; i < list.getLength(); i++) {
			
			Node n = list.item(i);
			String gameID = n.getAttributes().getNamedItem("gameId").getNodeValue();
			Application.getInstance().gameIndexMapping.put(i, gameID);
			if(list.getLength() > prevListLength) {
				Game game = new Game();
				game.setGameID(gameID);
				model.addGame(game);
				application.getAdminPanel().getGameListPanel().getModel().addElement("Game " + gameID);
			}
			
			/*if (!availableIds.contains(gameID)) {
				
			}*/

		}
		prevListLength = list.getLength();
		return true;
	}

}
