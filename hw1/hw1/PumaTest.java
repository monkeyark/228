package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

public class PumaTest
{
	private Jungle j, jOld, jNew;
	private Living l;
	private Animal a;
	private Puma p;
	
	@Before
	public void setUp() throws FileNotFoundException
	{
		j = new Jungle(9);
		l = new Puma(j, 0, 0, 0);
		a = new Puma(j, 1, 1, 1);
		p = new Puma(j, 2, 2, 2);
		jOld = new Jungle("public3-10x10.txt");
		jNew = new Jungle(jOld.getWidth());
	}
	@Test
	public void testWho()
	{
		assertTrue(l.who() == State.PUMA);
		assertTrue(a.who() == State.PUMA);
		assertTrue(p.who() == State.PUMA);
	}
	@Test
	public void testToString()
	{
		assertEquals(l.toString(), "P0");
		assertEquals(a.toString(), "P1");
		assertEquals(p.toString(), "P2");
	}
	@Test
	public void testNext()
	{
		jOld.grid[3][0] = new Puma(jOld, 3, 0, Living.PUMA_MAX_AGE);
		CircleOfLife.updateJungle(jOld, jNew);
		System.out.println(jOld.toString());
		System.out.println(jNew.toString());
		
		//a)Empty, if the Puma is currently at age 4
		assertEquals(jOld.grid[3][0].toString(), "P4");
		assertEquals(jOld.grid[3][0].next(jNew).toString(), "E ");
		
		//b)Jaguar, if there are more Jaguars than Pumas in the neighborhood
		assertEquals(jOld.grid[8][0].toString(), "P0");
		assertEquals(jOld.grid[8][0].next(jNew).toString(), "J0");
		
		//c)Empty, if Jaguars and Pumas together outnumber Deers in the neighborhood
		assertEquals(jOld.grid[7][3].toString(), "P0");
		assertEquals(jOld.grid[7][3].next(jNew).toString(), "E ");
		
		//d)Puma (the Puma will live on)
		assertEquals(jOld.grid[4][3].toString(), "P0");
		assertEquals(jOld.grid[4][3].next(jNew).toString(), "P1");
	}
}
