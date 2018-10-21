package edu.iastate.cs228.hw1;


/*
 * This class is to be extended by the Deer, Jaguar, and Puma classes. 
 */
public abstract class Animal extends Living implements MyAge
{
	protected int age;   // age of the animal 
	
	/**
	 * Constructor 
	 * @param j: jungle
	 * @param r: row position 
	 * @param c: column position
	 */
	public Animal(Jungle j, int r, int c)
	{
		super(j, r, c);
	}
	
	@Override
	/**
	 * 
	 * @return age of the animal 
	 */
	public int myAge()
	{
		return age;
	}
}
