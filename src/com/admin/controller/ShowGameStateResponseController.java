package com.admin.controller;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;

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


		GamePanel gamePanel = app.getAdminPanel().getGamePanel();
		gamePanel.resetBoardPanel();


		Node listResponse = response.contents.getFirstChild();
		NodeList list = listResponse.getChildNodes();
		int columncount = Integer.parseInt(listResponse.getAttributes().getNamedItem("size").getNodeValue());
		int rowCount = columncount;

		gamePanel.getBoardPanel().addComponents(columncount, rowCount);

		JPanel[][] tiles = new JPanel[columncount][rowCount];
		// Initialize all buttons
		for (int col = 0 ; col < columncount; col++) {
			for (int row = 0; row < rowCount; row++) {
				tiles[col][row] = new JPanel();
				tiles[col][row].setBackground(Color.WHITE);
				//tiles[col][row].setBorder(BorderFactory.createLineBorder(Color.black));
				tiles[col][row].setPreferredSize( new Dimension(50, 20) );
				gamePanel.getBoardPanel().getPanel().add(tiles[col][row]);
			}
		}



		// Populate all characters on the board
		String content = listResponse.getAttributes().getNamedItem("contents").getNodeValue();
		int pos_x = 0;
		int pos_y = 0;
		int listCounter = 0;
		List<String> items = Arrays.asList(content.split("\\s*,\\s*"));
		for (int col = pos_x; col < columncount; col++) {
			for (int row = pos_y; row < rowCount; row++) {
				tiles[col][row].add(new JLabel(items.get(listCounter++)));
			}
		}

		// Shade cells for each player board
		for (int i = 0; i < list.getLength(); i++) {
			Node n = list.item(i);
			JLabel e = new JLabel("Player: " + n.getAttributes().getNamedItem("name").getNodeValue() + "      Score: "
					+ n.getAttributes().getNamedItem("score").getNodeValue());

			gamePanel.getBoardPanel().getModel().addElement(e.getText());

			createPlayerBoard(n, tiles);

		}

		// Insert Bonus
		String position = listResponse.getAttributes().getNamedItem("bonus").getNodeValue();
		List<String> bonus = Arrays.asList(position.split("\\s*,\\s*"));
		int bonus_y = Integer.parseInt(bonus.get(0)) - 1;
		int bonus_x = Integer.parseInt(bonus.get(1)) - 1;
		tiles[bonus_x][bonus_y].setBackground(Color.YELLOW);

		gamePanel.revalidate();
		gamePanel.repaint();
		gamePanel.setVisible(true);


		return true;
	}

	private Color averageColors(Color c1, Color c2){
		int r1 = c1.getRed();
		int g1 = c1.getGreen();
		int b1 = c1.getBlue();

		int r2 = c2.getRed();
		int g2 = c2.getGreen();
		int b2 = c2.getBlue();

		// Mix the colors by averaging
		int mr = (int) Math.floor((r1 + r2) / 2);
		int mg = (int) Math.floor((g1 + g2) / 2);
		int mb = (int) Math.floor((b1 + b2) / 2);

		return new Color(mr, mg, mb);
	}

	private void createPlayerBoard(Node n, JPanel[][] tiles) {
		// Node n = list.item(0);
		int listCounter = 0;
		String position = n.getAttributes().getNamedItem("position").getNodeValue();
		List<String> positions = Arrays.asList(position.split("\\s*,\\s*"));
		String content = n.getAttributes().getNamedItem("board").getNodeValue();
		int pos_y = Integer.parseInt(positions.get(0)) - 1;
		int pos_x = Integer.parseInt(positions.get(1)) - 1;
		List<String> items = Arrays.asList(content.split("\\s*,\\s*"));
		for (int col = pos_x; col <= (pos_x + 3); col++) {
			for (int row = pos_y; row <= (pos_y + 3); row++) {
				Color current = tiles[col][row].getBackground();
				Color avg = averageColors(current, Color.BLUE);
				tiles[col][row].setBackground(avg);
			}
		}

	}

}
