package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AnimalTest
{
	private Jungle j;
	private Animal a;
	
	/**
	 * Test Animal age from range 0 to MAX_AGE
	 */
	
	@Before
	public void setUp()
	{
		j = new Jungle(9);
	}
	@Test
	public void testDeerAge()
	{
		for (int i=0; i<=Living.DEER_MAX_AGE; i++)
		{
			a = new Deer(j, 0, 1, i);
			assertEquals(a.myAge(), i);
		}
	}
	@Test
	public void testPumaAge()
	{
		for (int i=0; i<=Living.PUMA_MAX_AGE; i++)
		{
			a = new Puma(j, 0, 1, i);
			assertEquals(a.myAge(), i);
		}
	}
	@Test
	public void testJaguarAge()
	{
		for (int i=0; i<=Living.JAGUAR_MAX_AGE; i++)
		{
			a = new Jaguar(j, 0, 1, i);
			assertEquals(a.myAge(), i);
		}
	}
}
