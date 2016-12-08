package com.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.admin.controller.GameController;
import com.model.Game;


/**
 * List OfGames
 * @author Apoorva
 *
 */
public class GameListPanel extends JPanel {

	JList list;
	DefaultListModel model;

	public GameListPanel() {
		initilise();
	}

	private void initilise() {
		
		setLayout(new BorderLayout());
		model = new DefaultListModel();
		list = new JList(model);
		
		
		JScrollPane jScrollPane = new JScrollPane(list);
		
		// jScrollPane.setBounds(new Rectangle(7, 50, 100, 435));
		JButton showGame = new JButton("Show Game");
		showGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list.getSelectedValue();
				
				Game game = new Game(); 
				new GameController(Application.instance)
						.process(game.getGameDetails().get(list.getSelectedValue().toString()));

			}
		});

		add(jScrollPane, BorderLayout.CENTER);
		add(showGame,BorderLayout.PAGE_END);

	}

	public DefaultListModel getModel() {
		return model;
	}

}
