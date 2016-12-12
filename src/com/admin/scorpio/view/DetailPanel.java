package com.admin.scorpio.view;
import java.awt.Rectangle;

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
