package edu.iastate.cs228.hw1;

/**
 * A jaguar eats a deer and competes against a puma. 
 */
public class Jaguar extends Animal
{
	/**
	 * Constructor 
	 * @param j: jungle
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Jaguar (Jungle j, int r, int c, int a) 
	{
		super(j, r, c);
		age = a;
	}
	
	/**
	 * Gets the identity of the life form on the square.
	 * A jaguar occupies the square.
	 * @return State
	 */
	public State who()
	{
		return State.JAGUAR; 
	}
	
	/**
	 * A jaguar dies of old age or hunger, from isolation and attack by more numerous pumas.
	 *  
	 * @param jNew     jungle in the next cycle
	 * @return Living  life form occupying the square in the next cycle. 
	 */
	public Living next(Jungle jNew)
	{
//		a) Empty if the Jaguar is at age 5 in the current cycle;
//		b) otherwise, Puma, if there are at least twice as many Pumas as Jaguars in the
//		neighborhood;
//		c) otherwise, Empty, if Jaguars and Pumas together outnumber Deers in the neighborhood;
//		d) otherwise, Jaguar (the Jaguar will live on). 
		int[] pop = new int[NUM_LIFE_FORMS];
		this.census(pop);
		
		if (this.myAge() >= JAGUAR_MAX_AGE)
		{
			jNew.grid[row][column] = new Empty(jNew, row, column);
		}
		else if (pop[PUMA] >= 2*pop[JAGUAR])
		{
			jNew.grid[row][column] = new Puma(jNew, row, column, 0);
		}
		else if (pop[JAGUAR]+pop[PUMA] > pop[DEER])
		{
			jNew.grid[row][column] = new Empty(jNew, row, column);
		}
		else
		{
			jNew.grid[row][column] = new Jaguar(jNew, row, column, this.myAge()+1);
		}
		return jNew.grid[row][column];
	}
	
	/**
	 * print out current living life form with who and age
	 */
	@Override
	public String toString()
	{
		return "J" + age;
	}
}
