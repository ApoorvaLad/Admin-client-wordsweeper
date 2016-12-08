package com.view;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import com.admin.controller.GameListController;

/**
 * The Outer panel
 * 
 * @author Apoorva
 *
 */
public class AdminPanel extends JPanel {

	// GUI widgets for displaying the information.
	GamePanel gamePanel = null;
	GameListPanel gameListPanel = null;
	JButton getGames;
	
	public AdminPanel() {
		initialise();
	}

	void initialise() {

		setLayout(null);
		setSize(633, 510);
		getGames = new JButton("Get Games");
		getGames.setBounds(new Rectangle(0, 0, 150, 30));
		getGames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GameListController(Application.instance).process();

			}
		});

		add(getGames);
		add(getGameListPanel());
		add(getGamePanel());

	}
	
	public GameListPanel getGameListPanel() {
		if (gameListPanel == null) {
			gameListPanel = new GameListPanel();
			gameListPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			gameListPanel.setBounds(new Rectangle(0, 30, 150, 480));
		}
		return gameListPanel;
	}

	public GamePanel getGamePanel() {
		if (gamePanel == null) {
			gamePanel = new GamePanel(gameListPanel.list);
			gamePanel.setVisible(false);
			gamePanel.setBorder(BorderFactory.createLineBorder(Color.black));
			gamePanel.setBounds(new Rectangle(150, 0, 633, 510));
			//gamePanel.setBounds(new Rectangle(50, 0, 650, 700));
		}
		return gamePanel;
	}
	
	

	

}
