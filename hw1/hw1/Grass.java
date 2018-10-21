package edu.iastate.cs228.hw1;

/**
 * Grass may be eaten out or taken over by deers. 
 */
public class Grass extends Living 
{
	/**
	 * Constructor 
	 * @param j: jungle
	 * @param r: row position 
	 * @param c: column position
	 */
	public Grass (Jungle j, int r, int c) 
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
		return State.GRASS; 
	}
	
	
	/**
	 * Grass can be eaten out by too many deers in the neighborhood. Deers may also 
	 * multiply fast enough to take over Grass. 
	 * 
	 * @param jNew     jungle in the next cycle
	 * @return Living  life form occupying the square in the next cycle. 
	 */
	public Living next(Jungle jNew)
	{
//		a) Empty if at least three times as many Deers as Grasses in the neighborhood;
//		b) otherwise, Deer if there are at least four Deers in the neighborhood;
//		c) otherwise, Grass. 
		int[] pop = new int[NUM_LIFE_FORMS];
		this.census(pop);
		
		if (pop[DEER]>=3*pop[GRASS])
		{
			jNew.grid[row][column] = new Empty(jNew, row, column);
		}
		else if (pop[DEER]>=4)
		{
			jNew.grid[row][column] = new Deer(jNew, row, column, 0);
		}
		else
		{
			jNew.grid[row][column] = new Grass(jNew, row, column);
		}
		return jNew.grid[row][column];
	}
	
	/**
	 * print out current living life form with who and age
	 */
	@Override
	public String toString()
	{
		return "G ";
	}
}
