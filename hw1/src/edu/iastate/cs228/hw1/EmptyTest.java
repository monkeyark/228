package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

public class EmptyTest
{
	private Jungle j, jOld, jNew;
	private Living l;
	private Empty e;
	
	@Before
	public void setUp() throws FileNotFoundException
	{
		j = new Jungle(9);
		l = new Empty(j, 0, 0);
		e = new Empty(j, 2, 2);
		jOld = new Jungle("public3-10x10.txt");
		jNew = new Jungle(jOld.getWidth());
	}
	@Test
	public void testWho()
	{
		assertTrue(l.who() == State.EMPTY);
		assertTrue(e.who() == State.EMPTY);
	}
	@Test
	public void testToString()
	{
		assertEquals(l.toString(), "E ");
		assertEquals(e.toString(), "E ");
	}
	@Test
	public void testNext()
	{
		jOld.grid[0][0] = new Empty(jOld, 0, 0);
		jOld.grid[4][0] = new Empty(jOld, 4, 0);
		jOld.grid[4][1] = new Empty(jOld, 4, 1);
		jOld.grid[4][2] = new Empty(jOld, 4, 2);
		CircleOfLife.updateJungle(jOld, jNew);
		System.out.println(jOld.toString());
		System.out.println(jNew.toString());
		
		//a)Deer, if more than one neighboring Deer
		assertEquals(jOld.grid[8][8].toString(), "E ");
		assertEquals(jOld.grid[8][8].next(jNew).toString(), "D0");
		
		//b)Puma, if more than one neighboring Puma
		assertEquals(jOld.grid[0][2].toString(), "E ");
		assertEquals(jOld.grid[0][2].next(jNew).toString(), "P0");
		
		//c)Jaguar, if more than one neighboring Jaguar
		assertEquals(jOld.grid[0][8].toString(), "E ");
		assertEquals(jOld.grid[0][8].next(jNew).toString(), "J0");
		
		//d)Grass, if at least one neighboring Grass
		assertEquals(jOld.grid[0][0].toString(), "E ");
		assertEquals(jOld.grid[0][0].next(jNew).toString(), "G ");
		
		//e)Empty
		assertEquals(jOld.grid[4][2].toString(), "E ");
		assertEquals(jOld.grid[4][2].next(jNew).toString(), "E ");
	}
}
