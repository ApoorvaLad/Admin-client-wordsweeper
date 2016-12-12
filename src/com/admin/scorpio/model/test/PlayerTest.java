package com.admin.scorpio.model.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.admin.scorpio.model.Player;
/**
 * Test Case for Player
 * @author Rishitha
 *
 */
public class PlayerTest {
	/**Test to verify the name of player*/
	@Test
	public void testSetName() {
		Player p=new Player();
	    p.setName("Abc");
	    assertEquals("Abc",p.getname());
	}
	@Test
	public void testGetName() {
		Player p=new Player();
	    p.setName("Abc");
	    String result = p.getname();
	    assertEquals("Abc",result);
	}
	
	/**Test to verify the score*/
	@Test
	public void testSetScore() {
	    Player p=new Player();
	    p.setScore("300");;
	    assertEquals("300",p.getScore());
	}
	@Test
	public void testGetScore() {
		Player p=new Player();
	    p.setScore("300");
	    String result=p.getScore();
	    assertEquals("300",result);
	}

}
