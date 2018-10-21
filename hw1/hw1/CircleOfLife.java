package edu.iastate.cs228.hw1;

import java.io.FileNotFoundException;
import java.util.Scanner; 

/**
 * 
 * The CircleOfLife class performs simulation over a grid jungle 
 * with squares occupied by deers, jaguars, pumas, grass, or none. 
 *
 */
public class CircleOfLife 
{
	/**
	 * Update the new jungle from the old jungle in one cycle. 
	 * @param jOld  old jungle 
	 * @param jNew  new jungle
	 */
	public static void updateJungle(Jungle jOld, Jungle jNew)
	{ 
		// For every life form (i.e., a Living object) in the grid jOld, generate
		// a Living object in the grid jNew at the corresponding location such that
		// the former life form changes into the latter life form.
		// 
		// Employ the method next() of the Living class.
		
		if (jOld.getWidth() != jNew.getWidth())//should never in this case
		{
			System.out.println("updateJungle: jOld.getWidth() != jNew.getWidth()");
		}
		else
		{
			for (int i=0; i<jOld.grid.length; i++)
			{
				for (int j=0; j<jOld.grid.length; j++)
				{
					jOld.grid[i][j].next(jNew);
				}
			}
		}
	}
	
	/**
	 * Repeatedly generates jungles either randomly or from reading files.
	 * Over each jungle, carries out an input number of cycles of evolution.
	 * @param args args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException
	{
		System.out.println("Circle of Life in the Amazon Jungle" + "\n"
				+ "keys: 1 (random jungle) 2 (file input) 3 (exit)" + "\n");
		int totalTrial = 1;
		Scanner in = new Scanner(System.in);
		while(true)
		{
			System.out.printf("Trial %d: ", totalTrial++);
			int trial = in.nextInt();
			if (trial == 1)
			{
				System.out.println("Random jungle");
				System.out.print("Enter grid width: ");
				int width = in.nextInt();
				System.out.print("Enter the number of cycles: ");
				int cycles = in.nextInt();
				System.out.println("\n" + "Initial jungle:" + "\n");
				Jungle jOld = new Jungle(width);
				jOld.randomInit();
				System.out.println(jOld.toString());
				System.out.println("Final jungle:" + "\n");
				for (int i=0; i<cycles; i++)
				{
					Jungle jNew = new Jungle(width);
					updateJungle(jOld, jNew);//deep copy
					jOld = jNew;//changing reference
				}
				System.out.println(jOld.toString());
			}
			else if (trial == 2)
			{
				System.out.println("Jungle input from a file");
				System.out.print("File name: ");
				String path = in.next();
				System.out.print("Enter the number of cycles: ");
				int cycles = in.nextInt();
				while (cycles<1 && cycles/2 != 2)
				{
					cycles = in.nextInt();
				}
				System.out.println("\n" + "Initial jungle:" + "\n");
				Jungle jOld = new Jungle(path);
				System.out.println(jOld.toString());
				System.out.println("Final jungle:" + "\n");
				for (int i=0; i<cycles; i++)
				{
					Jungle jNew = new Jungle(jOld.getWidth());
					updateJungle(jOld, jNew);
					jOld = jNew;
					//jOld.write(path +  i + "cycles" +".txt");// debugger
				}
				jOld.write(path + "-" + cycles + "cycles" + ".txt");
				System.out.println(jOld.toString());
			}
			else
			{
				in.close();
				break;
			}
		}
		
		// Generate CircleOfLife simulations repeatedly like shown in the 
		// sample run in the project description. 
		// 
		// 1. Enter 1 to generate a random jungle, 2 to read a jungle from an input
		//    file, and 3 to end the simulation. (An input file always ends with 
		//    the suffix .txt.)
		// 2. Print out standard messages as given in the project description. 
		//
		// 3. For convenience, you may define two jungles even and odd as below.
		//    In an even numbered cycle (starting at zero), generate the jungle
		//    odd from the jungle even; in an odd numbered cycle, generate even
		//    from odd.
		// 4. Print out initial and final jungles only.  No intermediate jungles should
		//    appear in the standard output.  (When debugging your program, you can
		//    print intermediate jungles.)
		//
		// 5. You may save some randomly generated jungles as your own test cases.
		//
		// 6. It is not necessary to handle file input & output exceptions for this
		//    project. Assume data in an input file to be correctly formated.
	}
}
