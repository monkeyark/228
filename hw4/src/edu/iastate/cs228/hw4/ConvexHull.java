package edu.iastate.cs228.hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;



/**
 * 
 * This class implements construction of the convex hull of a finite number of points. 
 *
 */
public abstract class ConvexHull 
{
	// ---------------
	// Data Structures 
	// ---------------
	protected String algorithm;  // Its value is either "Graham's scan" or "Jarvis' march". 
	                             // Initialized by a subclass.
	
	protected long time;         // execution time in nanoseconds
	
	
	/**
	 * The array points[] holds an input set of Points, which may be randomly generated or 
	 * input from a file.  Duplicates are possible. 
	 */
	private Point[] points;
	
	
	/**
	 * Lowest point from points[]; and in case of a tie, the leftmost one of all such points. 
	 * To be set by a constructor. 
	 */
	protected Point lowestPoint; 
	
	
	/**
	 * This array stores the same set of points from points[] with all duplicates removed. 
	 * These are the points on which Graham's scan and Jarvis' march will be performed. 
	 */
	protected Point[] pointsNoDuplicate; 
	
	
	/**
	 * Vertices of the convex hull in counterclockwise order are stored in the array 
	 * hullVertices[], with hullVertices[0] storing lowestPoint. 
	 */
	protected Point[] hullVertices;
	
	
	protected QuickSortPoints quicksorter;  // used (and reset) by this class and its subclass GrahamScan
	
	
	// ------------
	// Constructors
	// ------------
	
	
	/**
	 * Constructor over an array of points.  
	 * 
	 *    1) Store the points in the private array points[].
	 *    
	 *    2) Initialize quicksorter. 
	 *    
	 *    3) Call removeDuplicates() to store distinct points from the input in pointsNoDuplicate[].
	 *    
	 *    4) Set lowestPoint to pointsNoDuplicate[0]. 
	 * 
	 * @param pts
	 * @throws IllegalArgumentException  if pts.length == 0
	 */
	public ConvexHull(Point[] pts) throws IllegalArgumentException 
	{
		if (pts.length == 0) throw new IllegalArgumentException();
		points = Arrays.copyOf(pts, pts.length);
		quicksorter = new QuickSortPoints(points);
		removeDuplicates();
		lowestPoint = new Point(pointsNoDuplicate[0]);
		time = 0;
	}
	
	
	/**
	 * Read integers from an input file.  Every pair of integers represent the x- and y-coordinates 
	 * of a point.  Generate the points and store them in the private array points[]. The total 
	 * number of integers in the file must be even.
	 * 
	 * You may declare a Scanner object and call its methods such as hasNext(), hasNextInt() 
	 * and nextInt(). An ArrayList may be used to store the input integers as they are read in 
	 * from the file.  
	 * 
	 * Perform the operations 1)-4) described for the previous constructor. 
	 * 
	 * @param  inputFileName
	 * @throws FileNotFoundException
	 * @throws InputMismatchException   when the input file contains an odd number of integers
	 */
	public ConvexHull(String inputFileName) throws FileNotFoundException, InputMismatchException
	{
		if (inputFileName == null) throw new FileNotFoundException();
    	
		File file = new File(inputFileName);
		Scanner scanner = new Scanner(file);
		ArrayList<Integer> listX = new ArrayList<Integer>();
		ArrayList<Integer> listY = new ArrayList<Integer>();
		
		while (scanner.hasNextLine() && scanner.hasNext())
		{
			listX.add(scanner.nextInt());
			listY.add(scanner.nextInt());
		}
		scanner.close();
		
		if (listX.size() != listY.size()) throw new InputMismatchException();
		points = new Point[listY.size()];
		for (int i=0; i<listY.size(); i++)
		{
			points[i] = new Point(listX.get(i), listY.get(i));
		}
		
		quicksorter = new QuickSortPoints(points);
		removeDuplicates();
		lowestPoint = new Point(pointsNoDuplicate[0]);
		time = 0;
	}
	
	
	/**
	 * Construct the convex hull of the points in the array pointsNoDuplicate[]. 
	 */
	public abstract void constructHull(); 
	
	
	/**
	 * Outputs performance statistics in the format: 
	 * 
	 * <convex hull algorithm> <size>  <time>
	 * 
	 * For instance, 
	 * 
	 * Graham's scan   1000	  9200867
	 *  
	 * Use the spacing in the sample run in Section 5 of the project description. 
	 */
	public String stats()
	{
		return algorithm + "   " + pointsNoDuplicate.length + "   " + time;
		//return algorithm + "   " + pointsNoDuplicate.length + "    " + hullVertices.length + "   " + time; //TODO used for test
	}
	
	
	/**
	 * The string displays the convex hull with vertices in counterclockwise order starting at  
	 * lowestPoint.  When printed out, it will list five points per line with three blanks in 
	 * between. Every point appears in the format "(x, y)".  
	 * 
	 * For illustration, the convex hull example in the project description will have its 
	 * toString() generate the output below: 
	 * 
	 * (-7, -10)   (0, -10)   (10, 5)   (0, 8)   (-10, 0)   
	 * 
	 * lowestPoint is listed only ONCE. 
	 *  
	 * Called only after constructHull().  
	 */
	public String toString()
	{	
		String out = "";
		for (int i=0; i<hullVertices.length; i++)
		{
			if ((i+1) % 5 != 0)
			{
				out += hullVertices[i].toString() + "   ";
			}
			else
			{
				out += hullVertices[i].toString() + System.lineSeparator();
			}
		}
		
		return out;
	}
	
	
	/** 
	 * 
	 * Writes to the file "hull.txt" the vertices of the constructed convex hull in counterclockwise 
	 * order.  These vertices are in the array hullVertices[], starting with lowestPoint.  Every line
	 * in the file displays the x and y coordinates of only one point.  
	 * 
	 * For instance, the file "hull.txt" generated for the convex hull example in the project 
	 * description will have the following content: 
	 * 
     *  -7 -10 
     *  0 -10
     *  10 5
     *  0  8
     *  -10 0
	 * 
	 * The generated file is useful for debugging as well as grading. 
	 * 
	 * Called only after constructHull().  
	 * 
	 * 
	 * @throws IllegalStateException  if hullVertices[] has not been populated (i.e., the convex 
	 *                                   hull has not been constructed)
	 * @throws FileNotFoundException 
	 */
	public void writeHullToFile() throws IllegalStateException, FileNotFoundException 
	{
		if (hullVertices == null) throw new IllegalStateException();
		String result = "";
		File file = new File("hull.txt");
		PrintWriter out = new PrintWriter(file);
		
		for (int i=0; i<hullVertices.length; i++)
		{
			result += hullVertices[i].getX() + " " + hullVertices[i].getY() + System.lineSeparator();
		}
		
		out.print(result);
		out.close();
	}
	
	
	/**
	 * Draw the points and their convex hull.  This method is called after construction of the 
	 * convex hull.  You just need to make use of hullVertices[] to generate a list of segments 
	 * as the edges. Then create a Plot object to call the method myFrame().  
	 */
	public void draw()
	{	
		// Assign their number to numSegs, and store them in segments[] in the order. 
		int numSegs = hullVertices.length;
		Segment[] segments = new Segment[numSegs]; 
		for (int i=0; i<numSegs-1; i++)
		{
			Point p = hullVertices[i];
			Point q = hullVertices[i+1];
			segments[i] = new Segment(p,q);
		}
		
		//add last segment: in hullVertices from last point to first point
		Point last = hullVertices[hullVertices.length-1];
		Point first = hullVertices[0];
		segments[numSegs-1] = new Segment(last,first);
		
		// The following statement creates a window to display the convex hull.
		Plot.myFrame(pointsNoDuplicate, segments, getClass().getName());
	}
	
	
	/**
	 * Sort the array points[] by y-coordinate in the non-decreasing order.  Have quicksorter 
	 * invoke quicksort() with a comparator object which uses the compareTo() method of the Point 
	 * class. Copy the sorted sequence onto the array pointsNoDuplicate[] with duplicates removed.
	 *     
	 * Ought to be private, but is made public for testing convenience. 
	 */
	public void removeDuplicates()
	{
		//sort points
		Point[] pts  = new Point[points.length];
		//Comparator<Point> comp = new PointComparator<Point>();
		//Comparator<Point> comp = (a,b) -> a.compareTo(b);
		//Comparator<Point> comp = Point::compareTo;
		Comparator<Point> comp = Comparator.naturalOrder();
		quicksorter.quickSort(comp);
		quicksorter.getSortedPoints(pts);
		
		
		//remove duplicates
		if (pts.length == 0 || pts.length == 1)
		{
			pointsNoDuplicate = pts;
		}
		
		int j = 0; //index of next unique point
		for (int i=0; i<pts.length-1; i++)
		{
			if (!pts[i].equals(pts[i+1]))
			{
				pts[j++] = pts[i];
			}
		}
		pts[j++] = pts[pts.length-1];
		
		pointsNoDuplicate = Arrays.copyOf(pts, j);
	//	/**
	//	 * 
	//	 * helper class to construct a comparator object
	//	 */
	//	private static class PointComparator<T> implements Comparator<T>
	//	{
	//		@Override
	//		public int compare(T o1, T o2)
	//		{
	//			Point p1 = (Point) o1;
	//			Point p2 = (Point) o2;
	//			
	//			if (p1.getY()< p2.getY() || (p1.getY() == p2.getY() && p1.getX() < p2.getX()))
	//			{
	//				return -1;
	//			}
	//			else if (p1.getY() == p2.getY() && p1.getX() == p2.getX())
	//			{
	//				return 0;
	//			}
	//			else
	//			{
	//				return 1;
	//			}
	//		}
	//	}
	}
	
	
}
