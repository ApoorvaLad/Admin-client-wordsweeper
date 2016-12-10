package com.model.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.model.Game;
import com.model.Player;



public class PlayerTest {
	
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
