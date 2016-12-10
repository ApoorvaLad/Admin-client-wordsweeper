package com.model.test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

import com.model.Game;



public class GameTest {
	
	@Test
	public void testSetGameDetails() {
	    Game g = new Game();
	    g.setGameDetails("game1", "1");
	    HashMap<String, String> result = new HashMap<>();
	    result=g.getGameDetails();
	    assertEquals(300,result);
	}
	@Test
	public void testGetGameDetails() {
	    Game g = new Game();
	    g.setGameDetails("game1", "1");
	    HashMap<String, String> result = new HashMap<>();
	    result.put("game1","1");
	    HashMap<String, String> result2 = new HashMap<>();
	    result2=g.getGameDetails();
	    assertEquals(result,result2);
	}
	
	@Test
	public void testSetId() {
	    Game g = new Game();
	    g.setGameID("123");
	    assertEquals("123",g.getGameID());
	}
	/**Test to verify the game ID*/
	@Test
	public void testGetId() {
	    Game g = new Game();
	    g.setGameID("123");
	    String result = g.getGameID();
	    assertEquals("123",result);
	}
	
	@Test
	public void testSetNoOfPlayers() {
	    Game g = new Game();
	    g.setNoOfPlayers(4);
	    assertEquals(4,g.getNoOfPlayers());
	}
	@Test
	public void testGetNoOfPlayers() {
	    Game g = new Game();
	    g.setNoOfPlayers(4);
	    int result = g.getNoOfPlayers();
	    assertEquals(4,result);
	}
	@Test
	public void testSetScore() {
	    Game g = new Game();
	    g.setScore(300);;
	    assertEquals(300,g.getScore());
	}
	@Test
	public void testGetScore() {
	    Game g = new Game();
	    g.setScore(300);
	    int result=g.getScore();
	    assertEquals(300,result);
	}


}
