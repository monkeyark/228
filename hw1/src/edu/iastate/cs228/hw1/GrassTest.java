package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

public class GrassTest
{
	private Jungle j, jOld, jNew;
	private Living l;
	private Grass g;
	
	@Before
	public void setUp() throws FileNotFoundException
	{
		j = new Jungle(9);
		l = new Grass(j, 0, 0);
		g = new Grass(j, 2, 2);
		jOld = new Jungle("public3-10x10.txt");
		jNew = new Jungle(jOld.getWidth());
	}
	@Test
	public void testWho()
	{
		assertTrue(l.who() == State.GRASS);
		assertTrue(g.who() == State.GRASS);
	}
	@Test
	public void testToString()
	{
		assertEquals(l.toString(), "G ");
		assertEquals(g.toString(), "G ");
	}
	@Test
	public void testNext()
	{
		CircleOfLife.updateJungle(jOld, jNew);
		System.out.println(jOld.toString());
		System.out.println(jNew.toString());
		
		//a)Empty, if at least three times as many Deers as Grasses in the neighborhood
		assertEquals(jOld.grid[2][4].toString(), "G ");
		assertEquals(jOld.grid[2][4].next(jNew).toString(), "E ");
		
		//b)Deer, if there are at least four Deers in the neighborhood
		assertEquals(jOld.grid[3][6].toString(), "G ");
		assertEquals(jOld.grid[3][6].next(jNew).toString(), "D0");
		
		//e)Grass
		assertEquals(jNew.grid[6][9].toString(), "G ");
		assertEquals(jOld.grid[6][9].next(jNew).toString(), "G ");
	}
}
