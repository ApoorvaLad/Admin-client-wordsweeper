package com.model;

import java.util.HashMap;

//import com.model.Player;

/**
 * Object model for Game
 * @author Apoorva
 *
 */
public class Game {

	private static final HashMap<String, String> gameDetails = new HashMap<>();
	private String gameID;
	private int noOfPlayers;
	private int score;
	private String playerName;
	//private ArrayList<Player> players;

	public void setGameDetails(String gameName, String gameId) {
		gameDetails.put(gameName, gameId);
	}

	public HashMap<String, String> getGameDetails() {
		return gameDetails;
	}

	public String getGameID() {
		return gameID;
	}

	public void setGameID(String gameID) {
		this.gameID = gameID;
	}

	public int getNoOfPlayers() {
		return noOfPlayers;
	}

	public void setNoOfPlayers(int noOfPlayers) {
		this.noOfPlayers = noOfPlayers;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
