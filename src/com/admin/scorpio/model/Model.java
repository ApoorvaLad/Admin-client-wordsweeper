package com.admin.scorpio.model;

import java.util.ArrayList;

/**
 * Entity for Model
 * 
 * @author Apoorva
 *
 */
public class Model {
	ArrayList<Game>games = new ArrayList<>();
	
	
	public void addGame(Game game) {
		games.add(game);
	}
	public void clearGames() { games.clear();}
	
	public ArrayList<Game> getGames() {
		return games;
	}

	
}
