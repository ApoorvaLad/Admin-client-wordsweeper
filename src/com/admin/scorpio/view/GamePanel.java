package com.admin.scorpio.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.admin.scorpio.controller.GameController;

/**
 * 
 * Game Panel - Displays the game board and game details
 * @author Apoorva
 *
 */
public class GamePanel extends JPanel {

	JScrollPane gamesListPanel = null;
	JLabel gameDetails;
	JLabel playerName;
	JScrollPane gameInfo;
	int rows;
	int columns;
	BoardPanel boardPanel;
	JPanel detailsPanel;
	JList jList;
	DefaultListModel model;
	JList list;


	public GamePanel(JList list) {
		this.list = list;
		initialize();
	}

	/**
	 * Creates an outline for the Game Panel
	 */
	void initialize() {
		//setLayout(new GridLayout(0, 1));
		JButton updateButton = new JButton("Update Game");
		add(updateButton);
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list.getSelectedValue();

				GameController gc = new GameController(Application.instance);
				String gameID = Application.getInstance().gameIndexMapping.get(list.getSelectedIndex());
				gc.process(gameID);
			}
		});
		boardPanel = new BoardPanel();
		add(boardPanel);
	
	}

	/**
	 * returns the internal board panel
	 * @return
	 */
	public BoardPanel getBoardPanel() {
		return boardPanel;
	}
	
	/**
	 * Reset the board panel
	 */
	public void resetBoardPanel(){
		this.boardPanel.getModel().removeAllElements();
	}
}
