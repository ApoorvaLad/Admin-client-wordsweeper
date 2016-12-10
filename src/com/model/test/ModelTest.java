package com.model.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;


import org.junit.Test;

import com.model.Game;
import com.model.Model;
/**
 * Test Case for Model
 * @author Rishitha
 *
 */


public class ModelTest {
	
	/**Test to add the game details*/
	@Test
	public void testSetGameDetails() {
	    Model m = new Model();
	    Game g = new Game();
	    m.addGame(g);
	    ArrayList<Game>games = new ArrayList<>();
	    games.add(g);
	    assertEquals(games,m.getGames());
	}
	/**Test to verify the game details*/
	@Test
	public void testGetGameDetails() {
	    Model m = new Model();
	    Game g = new Game();
	    m.addGame(g);
	    ArrayList<Game>games = new ArrayList<>();
	    games.add(g);
	    ArrayList<Game>result = new ArrayList<>();
	    result=m.getGames();
	    assertEquals(games,result);
	}


}
