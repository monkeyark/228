package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

public class DeerTest
{	
	private Jungle j, jOld, jNew;
	private Living l;
	private Animal a;
	private Deer d;
	
	@Before
	public void setUp() throws FileNotFoundException
	{
		j = new Jungle(9);
		l = new Deer(j, 0, 0, 0);
		a = new Deer(j, 1, 1, 1);
		d = new Deer(j, 2, 2, 2);
		jOld = new Jungle("public3-10x10.txt");
		jNew = new Jungle(jOld.getWidth());
	}
	@Test
	public void testWho()
	{
		assertTrue(l.who() == State.DEER);
		assertTrue(a.who() == State.DEER);
		assertTrue(d.who() == State.DEER);
	}
	@Test
	public void testToString()
	{
		assertEquals(l.toString(), "D0");
		assertEquals(a.toString(), "D1");
		assertEquals(d.toString(), "D2");
	}
	@Test
	public void testNext()
	{
		jOld.grid[0][0] = new Deer(jOld, 0, 0, Living.DEER_MAX_AGE);
		CircleOfLife.updateJungle(jOld, jNew);
		System.out.println(jOld.toString());
		System.out.println(jNew.toString());
		
		//a)Empty, if the Deer's current age is 6
		assertEquals(jOld.grid[0][0].toString(), "D6");
		assertEquals(jOld.grid[0][0].next(jNew).toString(), "E ");
		
		//b)Empty, if there is no Grass in the neighborhood
		assertEquals(jOld.grid[8][2].toString(), "D0");
		assertEquals(jOld.grid[8][2].next(jNew).toString(), "E ");
		
		//c)Puma, if in the neighborhood there are more Pumas and Jaguars together than
		//Deers, and furthermore, if there are at least twice as many Pumas as Jaguars
		assertEquals(jOld.grid[1][5].toString(), "D0");
		assertEquals(jOld.grid[1][5].next(jNew).toString(), "P0");
		
		//d)Jaguar, if there are more Pumas and Jaquars together than Deers, and if there
		//are at least as many Jaguars as Pumas
		assertEquals(jOld.grid[7][2].toString(), "D0");
		assertEquals(jOld.grid[7][2].next(jNew).toString(), "J0");
		
		//e)Deer (the Deer will live on)
		assertEquals(jOld.grid[2][7].toString(), "D0");
		assertEquals(jOld.grid[2][7].next(jNew).toString(), "D1");
	}
}