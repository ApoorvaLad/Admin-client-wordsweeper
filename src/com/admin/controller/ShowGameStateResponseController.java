package com.admin.controller;

import java.awt.Button;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.admin.xml.Message;
import com.model.Game;
import com.model.Model;
import com.view.Application;
import com.view.GamePanel;

/**
 * 
 * 
 * @author Apoorva
 *
 */
public class ShowGameStateResponseController extends ControllerChain {

	Application app;
	Model model;

	public ShowGameStateResponseController(Application app, Model model) {
		this.app = app;
		this.model = model;
	}

	@Override
	public boolean process(Message response) {

		String type = response.contents.getFirstChild().getLocalName();
		if (!type.equals("boardResponse")) {
			return next.process(response);
		}
		/*
		 * Node listResponse = response.contents.getFirstChild(); Game game =
		 * new Game(); ArrayList<Player> players = null; NodeList list =
		 * listResponse.getChildNodes(); for (int i = 0; i < list.getLength();
		 * i++) { Player player=new Player(); Node n = list.item(i); String name
		 * = n.getAttributes().getNamedItem("name").getNodeValue();
		 * player.setName(name); String score =
		 * n.getAttributes().getNamedItem("score").getNodeValue();
		 * player.setScore(score); players.add(player); game.setPlayer(players);
		 * 
		 * app.getAdminPanel().getGamePanel().getModel().addElement("Name: " +
		 * player.getname());
		 * app.getAdminPanel().getGamePanel().getModel().addElement("Score: " +
		 * player.getScore()); }
		 */

		GamePanel gamePanel = app.getAdminPanel().getGamePanel();
		/*
		 * gamePanel.getBoardPanel().removeAll();
		 * gamePanel.getBoardPanel().updateUI();
		 */
		Node listResponse = response.contents.getFirstChild();
		NodeList list = listResponse.getChildNodes();
		int columncount = Integer.parseInt(listResponse.getAttributes().getNamedItem("size").getNodeValue());
		int rowCount = Integer.parseInt(listResponse.getAttributes().getNamedItem("size").getNodeValue());
		listResponse.getNodeValue();

		int listIndex = 0;

		gamePanel.getBoardPanel().addComponents(rowCount, columncount);
		// app.getAdminPanel().getGamePanel().getBoardPanel().addComponents(rowCount,
		// columncount);

		JButton[][] buttons = new JButton[rowCount][columncount];
		Button button = new Button("");
		/*
		 * for (int i = 0; i < list.getLength(); i++) { listIndex = 0;
		 */
		for (int row = 0; row < rowCount; row++) {
			for (int col = 0; col < columncount; col++) {
				buttons[row][col] = new JButton("");

				gamePanel.getBoardPanel().getPanel().add(buttons[row][col]);
			}

		}
		Game game = new Game();
		for (int i = 0; i < list.getLength(); i++) {
			Node n = list.item(i);
			JLabel e = new JLabel("Player: " + n.getAttributes().getNamedItem("name").getNodeValue() + "      Score: "
					+ n.getAttributes().getNamedItem("score").getNodeValue());

			// app.getAdminPanel().getGamePanel().getBoardPanel().getDetails().add(e);
			// app.getAdminPanel().getGamePanel().
			
			gamePanel.getBoardPanel().getModel().addElement(e.getText());
			/*
			 * app.getAdminPanel().getGamePanel().getBoardPanel().getNameLabel()
			 * .setText(n.getAttributes().getNamedItem("name").getNodeValue());
			 */
			createPlayerBoard(n, buttons);

		}

		/*
		 * for (int pos_row = pos_y; pos_row < 4; pos_row++) { for (int pos_col
		 * = pos_x; pos_col < 4; pos_col++) {
		 * button.setLabel(items.get(listIndex++)); } }
		 */
		// Button button = new Button(Integer.toString());
		/*
		 * app.getAdminPanel().getGamePanel().getBoardPanel().
		 * getPanel().add(button);
		 * app.getAdminPanel().getGamePanel().setBounds(new Rectangle(100, 50,
		 * 650, 700)); app.getAdminPanel().getGamePanel().setVisible(true);
		 */

		// */
		// String gameID =
		// n.getAttributes().getNamedItem("gameId").getNodeValue();

		// }
		// boardPanel.setBounds(new Rectangle(100, 50, 650, 700));
		// app.getAdminPanel().getGamePanel().setBounds(new Rectangle(180, 50,
		// 500, 300));
		// app.getAdminPanel().
		gamePanel.setVisible(true);

		return true;
	}

	private void createPlayerBoard(Node n, JButton[][] buttons) {
		// Node n = list.item(0);
		int listCounter = 0;
		String position = n.getAttributes().getNamedItem("position").getNodeValue();
		List<String> positions = Arrays.asList(position.split("\\s*,\\s*"));
		String content = n.getAttributes().getNamedItem("board").getNodeValue();
		int pos_x = Integer.parseInt(positions.get(0));
		int pos_y = Integer.parseInt(positions.get(1));
		List<String> items = Arrays.asList(content.split("\\s*,\\s*"));
		for (int row = pos_x; row <= (pos_x + 3); row++) {
			for (int col = pos_y; col <= (pos_y + 3); col++) {
				buttons[row][col].setText(items.get(listCounter++));
			}
		}

	}

}
