package edu.iastate.cs228.hw1;

/**
 * A puma eats deers and competes against a jaguar. 
 */
public class Puma extends Animal 
{
	/**
	 * Constructor 
	 * @param j: jungle
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Puma (Jungle j, int r, int c, int a) 
	{
		super(j, r, c);
		age = a;
	}
	
	@Override
	/**
	 * A puma occupies the square. 	 
	 */
	public State who()
	{
		return State.PUMA; 
	}
	
	/**
	 * A puma dies of old age or hunger, or from attack by a jaguar. 
	 * @param jNew     jungle in the next cycle
	 * @return Living  life form occupying the square in the next cycle. 
	 */
	public Living next(Jungle jNew)
	{
//		a) Empty if the Puma is currently at age 4;
//		b) otherwise, Jaguar, if there are more Jaguars than Pumas in the neighborhood;
//		c) otherwise, Empty, if Jaguars and Pumas together outnumber Deers in the neighborhood;
//		d) otherwise, Puma (the Puma will live on).
		int[] pop = new int[NUM_LIFE_FORMS];
		this.census(pop);
		
		if (this.myAge() >= PUMA_MAX_AGE)
		{
			jNew.grid[row][column] = new Empty(jNew, row, column);
		}
		else if (pop[JAGUAR] > pop[PUMA])
		{
			jNew.grid[row][column] = new Jaguar(jNew, row, column, 0);
		}
		else if (pop[JAGUAR]+pop[PUMA] > pop[DEER])
		{
			jNew.grid[row][column] = new Empty(jNew, row, column);
		}
		else
		{
			jNew.grid[row][column] = new Puma(jNew, row, column, this.myAge()+1);
		}
		return jNew.grid[row][column];
	}
	
	/**
	 * print out current living life form with who and age
	 */
	@Override
	public String toString()
	{
		return "P" + age;
	}
}
