package com.admin.scorpio.view;

import java.awt.GridLayout;
import java.awt.Scrollbar;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
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

	/**
	 * Creates the Outline of the board panel
	 * @param row
	 * @param col
	 */
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

	/**
	 * Method to add the elements in the board panel
	 * @param row
	 * @param col
	 */
	public void addComponents(int row, int col) {
		GridLayout gridLayout = new GridLayout(row, col);
		baordPanel.setLayout(gridLayout);

	}

	/**
	 * Returns the internal panel
	 * @return
	 */
	public JPanel getPanel() {
		return baordPanel;
	}

	/**
	 * returns the label 
	 * @return
	 */
	public JLabel getNameLabel() {
		return name;
	}

	/**
	 * returns the gamedetails
	 * @return
	 */
	public ArrayList<JLabel> getDetails() {
		return details;
	}

	/**
	 * returns the Game Detail Panel
	 * @return
	 */
	public JPanel getDetailsPanel() {
		return detailsPanel;
	}

	/**
	 * returns the model for the list
	 * @return
	 */
	public DefaultListModel getModel() {
		return model;
	}
}
