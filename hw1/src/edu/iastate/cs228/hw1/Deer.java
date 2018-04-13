package edu.iastate.cs228.hw1;

/*
 * A deer eats grass and lives no more than six years.
 */
public class Deer extends Animal
{	
	/**
	 * Creates a Deer object.
	 * @param j: jungle  
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Deer (Jungle j, int r, int c, int a) 
	{
		super(j, r, c);
		age = a;
	}
		
	// Deer occupies the square.
	
	/**
	 * Gets the identity of the life form on the square.
	 * @return State
	 */
	@Override
	public State who()
	{
		return State.DEER;
	}
	
	/**
	 * @param jNew     jungle in the next cycle 
	 * @return Living  new life form occupying the same square
	 */
	public Living next(Jungle jNew)
	{
//		a) Empty if the Deer's current age is 6;
//		b) otherwise, Empty if there is no Grass in the neighborhood (the Deer needs food);
//		c) otherwise, Puma if in the neighborhood there are more Pumas and Jaguars together than
//		Deers, and furthermore, if there are at least twice as many Pumas as Jaguars;
//		d) otherwise, Jaguar if there are more Pumas and Jaquars together than Deers, and if there
//		are at least as many Jaguars as Pumas;
//		e) otherwise, Deer (the Deer will live on). 
		int[] pop = new int[NUM_LIFE_FORMS];
		this.census(pop);
		
		if (myAge() >= DEER_MAX_AGE)
		{
			jNew.grid[row][column] = new Empty(jNew, row, column);
		}
		else if (pop[GRASS] == 0)
		{
			jNew.grid[row][column] = new Empty(jNew, row, column);
		}
		else if (pop[PUMA]+pop[JAGUAR] > pop[DEER]
				&& pop[PUMA] >= 2*pop[JAGUAR])
		{
			jNew.grid[row][column] = new Puma(jNew, row, column, 0);
		}
		else if (pop[PUMA]+pop[JAGUAR] > pop[DEER]
				&& pop[JAGUAR] >= pop[PUMA])
		{
			jNew.grid[row][column] = new Jaguar(jNew, row, column, 0);
		}
		else
		{
			jNew.grid[row][column] = new Deer(jNew, row, column, this.myAge()+1);
		}
		return jNew.grid[row][column];
	}
	
	/**
	 * print out current living life form with who and age
	 */
	@Override
	public String toString()
	{
		return "D" + age;
	}
}
