package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

public class JaguarTest
{
	private Jungle j, jOld, jNew;
	private Living l;
	private Animal a;
	private Jaguar ja;
	
	@Before
	public void setUp() throws FileNotFoundException
	{
		j = new Jungle(9);
		l = new Jaguar(j, 0, 0, 0);
		a = new Jaguar(j, 1, 1, 1);
		ja = new Jaguar(j, 2, 2, 2);
		jOld = new Jungle("public3-10x10.txt");
		jNew = new Jungle(jOld.getWidth());
	}
	@Test
	public void testWho()
	{
		assertTrue(l.who() == State.JAGUAR);
		assertTrue(a.who() == State.JAGUAR);
		assertTrue(ja.who() == State.JAGUAR);
	}
	@Test
	public void testToString()
	{
		assertEquals(l.toString(), "J0");
		assertEquals(a.toString(), "J1");
		assertEquals(ja.toString(), "J2");
	}
	@Test
	public void testNext()
	{
		jOld.grid[0][9] = new Jaguar(jOld, 0, 9, Living.JAGUAR_MAX_AGE);
		CircleOfLife.updateJungle(jOld, jNew);
		System.out.println(jOld.toString());
		System.out.println(jNew.toString());
		
		//a)Empty, if the Jaguar is at age 5 in the current cycle
		assertEquals(jOld.grid[0][9].toString(), "J5");
		assertEquals(jOld.grid[0][9].next(jNew).toString(), "E ");
		
		//b)Puma, if there are at least twice as many Pumas as Jaguars in the neighborhood
		assertEquals(jOld.grid[1][3].toString(), "J0");
		assertEquals(jOld.grid[1][3].next(jNew).toString(), "P0");
		
		//c)Empty, if Jaguars and Pumas together outnumber Deers in the neighborhood
		assertEquals(jOld.grid[5][5].toString(), "J0");
		assertEquals(jOld.grid[5][5].next(jNew).toString(), "E ");
		
		//d)Jaguar (the Jaguar will live on)
		assertEquals(jOld.grid[5][1].toString(), "J0");
		assertEquals(jOld.grid[5][1].next(jNew).toString(), "J1");
	}
}
