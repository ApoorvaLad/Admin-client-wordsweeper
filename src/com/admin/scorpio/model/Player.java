package com.admin.scorpio.model;

/**
 * Object model for Player
 * @author Rishitha
 *
 */

public class Player {

	//private static final HashMap<String,String> playerDetails = new HashMap<>();
	private String name;
	private String score;
	//private Coordinate location;

	//public void setGameDetails(String playerName, String score) {
		//playerDetails.put(playerName, score);
	//}

	//public HashMap<String, String> getplayerDetails() {
		//return playerDetails;
//	}

	public String getname() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}


}
