package com.admin.scorpio.view;

import javax.swing.JFrame;

import com.admin.ServerAccess;

import java.util.HashMap;

/**
 * Main Application Class - Starting point
 * 
 * @author Apoorva
 *
 */
public class Application extends JFrame {

	ServerAccess serverAccess;
	AdminPanel adminPanel;

	static Application instance = null;
	public HashMap<Integer, String> gameIndexMapping = new HashMap<Integer, String>();

	/**
	 * Creates the single instance of the Application class
	 * 
	 * @return
	 */
	public static Application getInstance() {
		if (instance == null) {
			instance = new Application();
		}

		return instance;
	}

	public Application() {
		super();
		setTitle("Game Manager");
		setSize(800, 550);

		adminPanel = new AdminPanel();
		add(adminPanel);
	}

	/**
	 * Record the means to communicate with server
	 * 
	 * @param access
	 */
	public void setServerAccess(ServerAccess access) {
		this.serverAccess = access;
	}

	/**
	 * Get the server access object
	 * 
	 * @return
	 */
	public ServerAccess getServerAccess() {
		return serverAccess;
	}

	/**
	 * Get the admin panel object
	 * 
	 * @return
	 */
	public AdminPanel getAdminPanel() {
		return adminPanel;
	}

}
