package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Test;

public class CircleOfLifeTest{
	private Jungle j, jOld, jNew;
	
	@Test
	public void updateJunglePublic1() throws FileNotFoundException
	{
		jOld = new Jungle("public1-3x3.txt");
		jNew = new Jungle(jOld.getWidth());
		//update jungle
		for (int i=0; i<1; i++)
		{
			jNew = new Jungle(jOld.getWidth());
			CircleOfLife.updateJungle(jOld, jNew);
			jOld = jNew;
		}
		//read and compare with example given by instructor
		j = new Jungle("public1-1cycle.txt");
		System.out.println(j.toString());
		assertEquals(jOld.toString(), j.toString());
	}
	@Test
	public void updateJunglePublic2() throws FileNotFoundException
	{
		jOld = new Jungle("public2-6x6.txt");
		jNew = new Jungle(jOld.getWidth());
		//update jungle
		for (int i=0; i<8; i++)
		{
			jNew = new Jungle(jOld.getWidth());
			CircleOfLife.updateJungle(jOld, jNew);
			jOld = jNew;
		}
		//read and compare with example given by instructor
		j = new Jungle("public2-8cycles.txt");
		System.out.println(j.toString());
		assertEquals(jOld.toString(), j.toString());
	}
	@Test
	public void updateJunglePublic3() throws FileNotFoundException
	{
		jOld = new Jungle("public3-10x10.txt");
		jNew = new Jungle(jOld.getWidth());
		//update jungle
		for (int i=0; i<6; i++)
		{
			jNew = new Jungle(jOld.getWidth());
			CircleOfLife.updateJungle(jOld, jNew);
			jOld = jNew;
		}
		//read and compare with example given by instructor
		j = new Jungle("public3-6cycles.txt");
		System.out.println(j.toString());
		assertEquals(jOld.toString(), j.toString());
	}
}
