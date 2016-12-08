package com.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DetailPanel extends JPanel {

	
	public DetailPanel() {
		initialise();
	}

	private void initialise(){
		setLayout(null);
		this.setBounds(new Rectangle(0, 0, 100, 100));
		//jPanel.setBorder();
		JLabel jLabel = new JLabel("PlayerName");
		JLabel name = new JLabel("dasd");
		add(jLabel);
		add(name);
		//add(jPanel);
	}

	

}
