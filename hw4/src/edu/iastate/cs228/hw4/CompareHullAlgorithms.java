package edu.iastate.cs228.hw4;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner; 


public class CompareHullAlgorithms
{
	/**
	 * Repeatedly take points either randomly generated or read from files. Perform Graham's scan and 
	 * Jarvis' march over the input set of points, comparing their performances.  
	 * 
	 * @param args
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException 
	 **/
	public static void main(String[] args) throws InputMismatchException, FileNotFoundException
	{
		// Conducts multiple rounds of convex hull construction. Within each round, performs the following: 
		// 
		//    1) If the input are random points, calls generateRandomPoints() to initialize an array 
		//       pts[] of random points. Use pts[] to create two objects of GrahamScan and JarvisMarch, 
		//       respectively.
		//
		//    2) If the input is from a file, construct two objects of the classes GrahamScan and  
		//       JarvisMarch, respectively, using the file.     
		//
		//    3) Have each object call constructHull() to build the convex hull of the input points.
		//
		//    4) Meanwhile, prints out the table of runtime statistics.
		
		System.out.println("Comparison between Convex Hull Algorithms\n");
		int totalTrial = 1;
		Scanner in = new Scanner(System.in);
		
		while(true)
		{
			System.out.printf("Trial %d: ", totalTrial++);
			int trial = in.nextInt();
			if (trial == 1)
			{
				int numPts = 0;
				System.out.print("Enter number of random points: ");
				numPts = in.nextInt();
				System.out.println("\nalgorithm       size      time (ns)\n" + "---------------------------------------");
				
				Random rand = new Random();
				Point[] pts = generateRandomPoints(numPts, rand);
				ConvexHull[] algorithms = new ConvexHull[2];
				algorithms[0] = new GrahamScan(pts);
				algorithms[1] = new JarvisMarch(pts);
				
				for (int i=0; i<algorithms.length; i++)
				{
					algorithms[i].constructHull();
					System.out.println(algorithms[i].stats());
					algorithms[i].draw(); //TODO for test
					//System.out.println(algorithms[i].toString());
					//algorithms[i].writeHullToFile();
				}
				System.out.println("---------------------------------------\n\n");
			}
			else if (trial == 2)
			{
				System.out.println("Points from a file");
				System.out.print("File name: ");
				String fileName = in.next();
				
				ConvexHull[] algorithms = new ConvexHull[2];
				algorithms[0] = new GrahamScan(fileName);
				algorithms[1] = new JarvisMarch(fileName);
				
				System.out.println("\nalgorithm       size      time (ns)\n" + "---------------------------------------");
				for (int i=0; i<algorithms.length; i++)
				{
					algorithms[i].constructHull();
					System.out.println(algorithms[i].stats());
					algorithms[i].draw(); //TODO for test
				}
				System.out.println("---------------------------------------\n\n");
			}
			else
			{
				System.out.println("End.");
				break;
			}
		}
		in.close();
		
		
		// Within a hull construction round, have each algorithm call the constructHull() and draw()
		// methods in the ConvexHull class.  You can visually check the result. (Windows 
		// have to be closed manually before rerun.)  Also, print out the statistics table 
		// (see Section 5). 
		
	}
	
	
	/**
	 * This method generates a given number of random points.  The coordinates of these points are 
	 * pseudo-random numbers within the range [-50,50] * [-50,50]. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	private static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
	{
		if (numPts < 1) throw new IllegalArgumentException();
		
		Point[] pts = new Point[numPts];
		for (int i=0; i<numPts; i++)
		{
			int x = rand.nextInt(101) - 50;
			int y = rand.nextInt(101) - 50;
			pts[i] = new Point(x,y);
		}
		
		return pts;
	}
}
