package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LivingTest
{
	private int[] population;
	private Jungle jOld, jNew;
	@Before
	public void setUp() throws FileNotFoundException
	{
		population = new int[Living.NUM_LIFE_FORMS];
		jOld = new Jungle("public3-10x10.txt");
		jNew = new Jungle(jOld.getWidth());
	}
	@After
	public void cleanUp()
	{
		population = new int[Living.NUM_LIFE_FORMS];
	}
	@Test
	public void testCencus11()
	{
		CircleOfLife.updateJungle(jOld, jNew);
		System.out.println(jOld.toString());
		//System.out.println(jNew.toString());
		
		jOld.grid[1][1].census(population);
		int pop11[] = {1, 1, 4, 1, 2};
		assertTrue(Arrays.equals(population, pop11));
	}
	@Test
	public void testCencus22()
	{
		jOld.grid[2][2].census(population);
		int pop22[] = {2, 2, 3, 1, 1};
		assertTrue(Arrays.equals(population, pop22));
	}
	@Test
	public void testCencus33()
	{
		jOld.grid[3][3].census(population);
		int pop33[] = {5, 1, 1, 0, 2};
		assertTrue(Arrays.equals(population, pop33));
	}
}
