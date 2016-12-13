package com.admin.scorpio.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.admin.scorpio.model.Model;
import com.admin.scorpio.view.Application;
import com.admin.scorpio.view.GamePanel;
import com.admin.xml.Message;

/**
 * 
 * This class handles the showGameStateRequest
 * 
 * @author Apoorva
 *
 */
public class ShowGameStateResponseController extends ControllerChain {

	Application app;
	Model model;
	HashMap<String, Color> playerDetails = new HashMap<>();

	public ShowGameStateResponseController(Application app, Model model) {
		this.app = app;
		this.model = model;
	}

	/**
	 * This methd handles the showGameRequestState and displays the board on the
	 * GamePanel
	 */
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
		String gameId = listResponse.getAttributes().getNamedItem("gameId").getNodeValue();
		int rowCount = columncount;

		gamePanel.getBoardPanel().addComponents(columncount, rowCount);

		JPanel[][] tiles = new JPanel[columncount][rowCount];
		// Initialize all buttons
		for (int col = 0; col < columncount; col++) {
			for (int row = 0; row < rowCount; row++) {
				tiles[col][row] = new JPanel();
				tiles[col][row].setBackground(Color.WHITE);
				// tiles[col][row].setBorder(BorderFactory.createLineBorder(Color.black));
				tiles[col][row].setPreferredSize(new Dimension(50, 20));
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

			String playerName = n.getAttributes().getNamedItem("name").getNodeValue();
		

			String color = "#" + createColorForPlayer(playerName);
			Color newColor = new Color(Integer.valueOf(color.substring(1, 3), 16),
					Integer.valueOf(color.substring(3, 5), 16), Integer.valueOf(color.substring(5, 7), 16));
			createPlayerBoard(n, tiles,newColor);


			JLabel e = new JLabel("<html> <font color=" + color + ">  Player: "
					+ n.getAttributes().getNamedItem("name").getNodeValue() + "      Score: "
					+ n.getAttributes().getNamedItem("score").getNodeValue());

			gamePanel.getBoardPanel().getModel().addElement(e.getText());

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

	// Create separate color for each player
	private String createColorForPlayer(String playerName) {
	
		StringBuffer sb = new StringBuffer();
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(playerName.getBytes());
			byte[] digest = md.digest();

			for (byte b1 : digest) {
				sb.append(String.format("%02x", b1 & 0xff));
			}
			System.out.println(sb);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sb.substring(0, 15);
	}

	private Color averageColors(Color c1, Color c2) {
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

	private Color createPlayerBoard(Node n, JPanel[][] tiles, Color color) {
		// Node n = list.item(0);
		int listCounter = 0;
		Color avg = null;
		Color current = null;
		String position = n.getAttributes().getNamedItem("position").getNodeValue();
		List<String> positions = Arrays.asList(position.split("\\s*,\\s*"));
		String content = n.getAttributes().getNamedItem("board").getNodeValue();
		int pos_y = Integer.parseInt(positions.get(0)) - 1;
		int pos_x = Integer.parseInt(positions.get(1)) - 1;
		List<String> items = Arrays.asList(content.split("\\s*,\\s*"));
		for (int col = pos_x; col <= (pos_x + 3); col++) {
			for (int row = pos_y; row <= (pos_y + 3); row++) {
				// tiles[col][row].setBackground(color);
				current = tiles[col][row].getBackground();
				avg = averageColors(current, color);
				tiles[col][row].setBackground(avg);
			}
		}
		return avg;

	}

}
