package edu.iastate.cs228.hw1;

/** 
 * Empty squares are competed by various forms of life.
 */
public class Empty extends Living 
{
	/**
	 * Constructor 
	 * @param j: jungle
	 * @param r: row position 
	 * @param c: column position
	 */
	public Empty (Jungle j, int r, int c)
	{
		super(j, r, c);
	}
	
	/**
	 * Gets the identity of the life form on the square.
	 * @return State
	 */
	@Override
	public State who()
	{
		return State.EMPTY;
	}
	
	/**
	 * An empty square will be occupied by a neighboring Deer, Grass, Jaguar, or Puma, or 
	 * remain empty. 
	 * @param jNew     jungle in the next life cycle.
	 * @return Living  life form in the next cycle.   
	 */
	public Living next(Jungle jNew)
	{
		// 1. Obtains counts of life forms in the 3X3 neighborhood of the class object.
		int[] pop = new int[NUM_LIFE_FORMS];
		this.census(pop);
		
		// 2. Applies the survival rules for the life form to determine the life form
		//    (on the same square) in the next cycle.  These rules are given in the
		//    project description.
//		a) Deer, if more than one neighboring Deer;
//		b) otherwise, Puma, if more than one neighboring Puma;
//		c) otherwise, Jaguar, if more than one neighboring Jaguar;
//		d) otherwise, Grass, if at least one neighboring Grass;
//		e) otherwise, Empty.
		// 3. Generate this new life form at the same location in the jungle jNew.
		if (pop[DEER]>1)
		{
			jNew.grid[row][column] = new Deer(jNew, row, column, 0);
		}
		else if (pop[PUMA]>1)
		{
			jNew.grid[row][column] = new Puma(jNew, row, column, 0);
		}
		else if (pop[JAGUAR]>1)
		{
			jNew.grid[row][column] = new Jaguar(jNew, row, column, 0);
		}
		else if (pop[GRASS]>=1)
		{
			jNew.grid[row][column] = new Grass(jNew, row, column);
		}
		else
		{
			jNew.grid[row][column] = new Empty(jNew, row, column);
		}
		
		return jNew.grid[row][column];
	}
	
	/**
	 * print out current living life form with who and age
	 */
	@Override
	public String toString()
	{
		return "E ";
	}
}
