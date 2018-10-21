package edu.iastate.cs228.hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner; 

/**
 * 
 * The jungle is represented as a square grid of size width X width. 
 *
 */
public class Jungle 
{
	private int width; // grid size: width X width 
	
	public Living[][] grid;
	
	/**
	 *  Default constructor reads from a file
	 */
	public Jungle(String inputFileName) throws FileNotFoundException
	{
		// Assumption: The input file is in correct format.
		// 1) Reads the first line to determine the width of the grid.
		File file = new File(inputFileName);
		Scanner nColumn = new Scanner(file);
		String firstLine = nColumn.nextLine();
		String[] s = firstLine.split("\\s+");
		width = s.length;
		nColumn.close();
		
		// 2) Creates a grid object.
		grid = new Living[width][width];
		
		// 3) Fills in the grid according to the input file.
		Scanner scanner = new Scanner(file);
		int row = 0;
		while (scanner.hasNextLine() && scanner.hasNext())
		{
			String line = scanner.nextLine();
			String[] tokens = line.split("\\s+");
			for (int col=0; col<tokens.length; col++)
			{
				if (tokens[col].substring(0,1).equals("D"))
				{
					grid[row][col] = new Deer(this, row, col, Integer.parseInt(tokens[col].substring(1,2)));
				}
				else if (tokens[col].substring(0, 1).equals("P"))
				{
					grid[row][col] = new Puma(this, row, col, Integer.parseInt(tokens[col].substring(1,2)));
				}
				else if (tokens[col].substring(0, 1).equals("J"))
				{
					grid[row][col] = new Jaguar(this, row, col, Integer.parseInt(tokens[col].substring(1,2)));
				}
				else if (tokens[col].substring(0, 1).equals("G"))
				{
					grid[row][col] = new Grass(this, row, col);
				}
				else if (tokens[col].substring(0, 1).equals("E"))
				{
					grid[row][col] = new Empty(this, row, col);
				}
			}
			row++;
		}
		scanner.close();
		// Be sure to close the input file when you are done.
	}
	
	/**
	 * Constructor that builds a w X w grid without initializing it.
	 * @param width  the grid
	 */
	public Jungle(int w)
	{
		width = w;
		grid = new Living[w][w];
	}
	
	public int getWidth()
	{ 
		return width;  // to be modified 
	}
	
	/**
	 * Initialize the jungle by randomly assigning to every square of the grid  
	 * one of Deer, Empty, Grass, Jaguar, or Puma. Every animal starts at age 0.
	 * 
	 * Your random jungle generator may follow the uniform probability distribution so that Deer,
	 * Empty, Grass, Jaguar, and Puma have equal likelihoods to occupy every square. Or you may use
	 * a different distribution, as long as no life form has zero chance to appear on a square.
	 * 
	 */
	public void randomInit()
	{
		Random generator = new Random();
		for (int i=0; i<grid.length; i++)
		{
			for (int j=0; j<grid.length; j++)
			{
				int rand = generator.nextInt(Living.NUM_LIFE_FORMS);
				if (rand==Living.DEER)
					grid[i][j] = new Deer(this, i, j, 0);
				else if (rand==Living.EMPTY)
					grid[i][j] = new Empty(this, i, j);
				else if (rand==Living.GRASS)
					grid[i][j] = new Grass(this, i, j);
				else if (rand==Living.JAGUAR)
					grid[i][j] = new Jaguar(this, i, j, 0);
				else if (rand==Living.PUMA)
					grid[i][j] = new Puma(this, i, j, 0);
			}
		}
	}
	
	/**
	 * Output the jungle grid. For each square, output the first letter of the living form
	 * occupying the square. If the living form is an animal, then output the age of the animal 
	 * followed by a blank space; otherwise, output two blanks.  
	 */
	public String toString()
	{
		String livingGrid = "";
		for (int i=0; i<grid.length; i++)
		{
			for (int j=0; j<grid.length; j++)
			{
				livingGrid += grid[i][j].toString() + " ";
			}
			livingGrid += System.lineSeparator();
		}
		return livingGrid;
	}
	
	/**
	 * Write the jungle grid to an output file.  Also useful for saving a randomly 
	 * generated jungle for debugging purpose. 
	 * @throws FileNotFoundException
	 */
	public void write(String outputFileName) throws FileNotFoundException
	{
		// 1. Open the file.
		File file = new File(outputFileName);
		PrintWriter out = new PrintWriter(file);
		out.print(this.toString());
//		int nthEdition = 1;
//		if (file.exists())
//		{
//			write(outputFileName + "(" + 1 + ").txt");
//		}
//		nthEdition++;
//		String line = "";
//		for (int i=0; i<grid.length; i++)
//		{
//			for (int j=0; j<grid.length; j++)
//			{
//				line += grid[i][j].toString() + " ";
//			}
//			out.println(line);
//			line = "";
//		}
		// 3. Close the file.
		out.close();
	}			
}
