package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

public class JungleTest
{
	private Jungle j1, j2, j3, j4, j5;
	
	@Before
	public void setUp() throws FileNotFoundException
	{
		j1 = new Jungle("public1-3x3.txt");
		j2 = new Jungle("public2-6x6.txt");
		j3 = new Jungle("public3-10x10.txt");
	}
	@Test
	public void testGetWidth()
	{
		assertTrue(j1.getWidth() == 3);
		assertTrue(j2.getWidth() == 6);
		assertTrue(j3.getWidth() == 10);
		
		j4 = new Jungle(1);
		assertTrue(j4.getWidth() == 1);
		j5= new Jungle(0);
		assertTrue(j5.getWidth() == 0);
	}
	@Test
	public void testReadFile() throws FileNotFoundException
	{
		j1 = new Jungle("public1-3x3.txt");
		String s1 = "G  J0 G  " + System.lineSeparator()
				  + "J0 D0 P0 " + System.lineSeparator()
				  + "J0 D0 E  " + System.lineSeparator();
		//System.out.println(j1.toString());
		//System.out.println(s1);
		assertEquals(j1.toString(), s1);
		
		j2 = new Jungle("public2-6x6.txt");
		String s2 = "E  G  D0 D0 J0 P0 " + System.lineSeparator()
				  + "J0 D0 G  J0 P0 G  " + System.lineSeparator()
				  + "J0 G  D0 J0 P0 J0 " + System.lineSeparator()
				  + "J0 P0 J0 E  G  G  " + System.lineSeparator()
				  + "E  P0 E  P0 D0 E  " + System.lineSeparator()
				  + "J0 G  E  P0 J0 G  " + System.lineSeparator();
		//System.out.println(j2.toString());
		//System.out.println(s2);
		assertEquals(j2.toString(), s2);
		
		j3 = new Jungle("public3-10x10.txt");
		String s3 = "D0 P0 E  P0 E  P0 J0 J0 E  J0 " + System.lineSeparator()
				  + "G  G  G  J0 G  D0 P0 E  D0 G  " + System.lineSeparator()
				  + "J0 G  P0 D0 G  D0 G  D0 G  D0 " + System.lineSeparator()
				  + "P0 E  E  D0 D0 D0 G  D0 J0 D0 " + System.lineSeparator()
				  + "D0 D0 D0 P0 D0 J0 J0 P0 G  E  " + System.lineSeparator()
				  + "J0 J0 E  E  P0 J0 P0 E  G  D0 " + System.lineSeparator()
				  + "D0 E  P0 G  D0 E  D0 P0 D0 G  " + System.lineSeparator()
				  + "E  J0 D0 P0 E  G  P0 P0 D0 P0 " + System.lineSeparator()
				  + "P0 J0 D0 J0 P0 E  P0 P0 E  J0 " + System.lineSeparator()
				  + "D0 J0 J0 D0 P0 E  E  D0 G  J0 " + System.lineSeparator();
		//System.out.println(j3.toString());
		//System.out.println(s3);
		assertEquals(j3.toString(), s3);
	}
	@Test
	public void testWriteFile() throws FileNotFoundException
	{
		j1 = new Jungle(3);
		j1.randomInit();
		String s1 = j1.toString();
		System.out.println(j1.toString());
		j1.write("j1.3x3.txt");
		Jungle j1new = new Jungle("j1.3x3.txt");
		assertEquals(j1new.toString(), s1);
		
		j2 = new Jungle(5);
		j2.randomInit();
		String s2 = j2.toString();
		System.out.println(j2.toString());
		j2.write("j2.5x5.txt");
		Jungle j2new = new Jungle("j2.5x5.txt");
		assertEquals(j2new.toString(), s2);
		
		j3 = new Jungle(10);
		j3.randomInit();
		String s3 = j3.toString();
		System.out.println(j3.toString());
		j3.write("j3.10x10.txt");
		Jungle j3new = new Jungle("j3.10x10.txt");
		assertEquals(j3new.toString(), s3);
	}
	@Test
	public void randomInit()
	{
		j1 = new Jungle(0);
		j1.randomInit();
		System.out.println(j1.toString());
		assertFalse(j1 == null);
		
		j2 = new Jungle(3);
		j2.randomInit();
		
		System.out.println(j2.toString());
		assertFalse(j2 == null);
		for (int i=0; i<j2.getWidth(); i++)
		{
			for (int j=0; j<j2.getWidth(); j++)
			{
				// for all Animal, there age should be in the range of 0 to MAX_AGE
				assertTrue(
						(j2.grid[i][j].who() == State.DEER
						&& ((Animal) j2.grid[i][j]).myAge() <= Living.DEER
						&& ((Animal) j2.grid[i][j]).myAge() >= 0)
					||
						(j2.grid[i][j].who() == State.EMPTY)
					||
						(j2.grid[i][j].who() == State.GRASS)
					||
						(j2.grid[i][j].who() == State.JAGUAR
						&& ((Animal) j2.grid[i][j]).myAge() <= Living.JAGUAR
						&& ((Animal) j2.grid[i][j]).myAge() >= 0)
					||
						(j2.grid[i][j].who() == State.PUMA
						&& ((Animal) j2.grid[i][j]).myAge() <= Living.PUMA
						&& ((Animal) j2.grid[i][j]).myAge() >= 0)
					);
			}
		}
	}
	@Test
	public void testToString() throws FileNotFoundException
	{
		j1 = new Jungle("public1-3x3.txt");
		String s1 = "G  J0 G  " + System.lineSeparator()
				  + "J0 D0 P0 " + System.lineSeparator()
				  + "J0 D0 E  " + System.lineSeparator();
		assertEquals(j1.toString(), s1);
		
		j2 = new Jungle("public2-6x6.txt");
		String s2 = "E  G  D0 D0 J0 P0 " + System.lineSeparator()
				  + "J0 D0 G  J0 P0 G  " + System.lineSeparator()
				  + "J0 G  D0 J0 P0 J0 " + System.lineSeparator()
				  + "J0 P0 J0 E  G  G  " + System.lineSeparator()
				  + "E  P0 E  P0 D0 E  " + System.lineSeparator()
				  + "J0 G  E  P0 J0 G  " + System.lineSeparator();
		assertEquals(j2.toString(), s2);
		
		j3 = new Jungle("public3-10x10.txt");
		String s3 = "D0 P0 E  P0 E  P0 J0 J0 E  J0 " + System.lineSeparator()
				  + "G  G  G  J0 G  D0 P0 E  D0 G  " + System.lineSeparator()
				  + "J0 G  P0 D0 G  D0 G  D0 G  D0 " + System.lineSeparator()
				  + "P0 E  E  D0 D0 D0 G  D0 J0 D0 " + System.lineSeparator()
				  + "D0 D0 D0 P0 D0 J0 J0 P0 G  E  " + System.lineSeparator()
				  + "J0 J0 E  E  P0 J0 P0 E  G  D0 " + System.lineSeparator()
				  + "D0 E  P0 G  D0 E  D0 P0 D0 G  " + System.lineSeparator()
				  + "E  J0 D0 P0 E  G  P0 P0 D0 P0 " + System.lineSeparator()
				  + "P0 J0 D0 J0 P0 E  P0 P0 E  J0 " + System.lineSeparator()
				  + "D0 J0 J0 D0 P0 E  E  D0 G  J0 " + System.lineSeparator();
		assertEquals(j3.toString(), s3);
	}
}
