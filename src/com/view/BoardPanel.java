package com.view;

import java.awt.GridLayout;
import java.awt.Scrollbar;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Board Panel
 * 
 * @author Apoorva
 *
 */
public class BoardPanel extends JPanel {
	JPanel baordPanel;
	ArrayList<JLabel> details;
	JPanel detailsPanel;
	JLabel name;
	JList list;
	DefaultListModel model;
	Scrollbar scrollbarH = null;
	Scrollbar scrollbarV = null;
	int rowCount = 7;
	int columncount = 7;

	public BoardPanel() {
		initialize(0, 1);

	}

	private void initialize(int row, int col) {
		this.setLayout(new GridLayout(0, 1));

		baordPanel = new JPanel();
		JScrollPane jScrollPane = new JScrollPane(baordPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		add(jScrollPane);
		detailsPanel = new JPanel();
		model = new DefaultListModel();
		list = new JList(model);
		JScrollPane detailsScrollPane = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		details = new ArrayList<>();
		name = new JLabel();
		
		add(detailsScrollPane);
	}

	public void addComponents(int row, int col) {
		GridLayout gridLayout = new GridLayout(row, col);

		/*
		 * Dimension dimension = new Dimension(100, 100);
		 * jPanel.setSize(dimension);
		 */
		baordPanel.setLayout(gridLayout);

	}

	public JPanel getPanel() {
		return baordPanel;
	}

	public JLabel getNameLabel() {
		return name;
	}

	public ArrayList<JLabel> getDetails() {
		return details;
	}

	public JPanel getDetailsPanel() {
		return detailsPanel;
	}

	public DefaultListModel getModel() {
		return model;
	}
}
