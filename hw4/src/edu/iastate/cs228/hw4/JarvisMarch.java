package edu.iastate.cs228.hw4;

import java.io.FileNotFoundException;
import java.util.InputMismatchException; 

public class JarvisMarch extends ConvexHull
{
	// last element in pointsNoDuplicate(), i.e., highest of all points (and the rightmost one in case of a tie)
	private Point highestPoint; 
	
	// left chain of the convex hull counterclockwise from highestPoint to lowestPoint
	private PureStack<Point> leftChain; 
	
	// right chain of the convex hull counterclockwise from lowestPoint to highestPoint
	private PureStack<Point> rightChain; 
	
	/**
	 * Call corresponding constructor of the super class.  Initialize the variable algorithm 
	 * (from the class ConvexHull). Set highestPoint. Initialize the two stacks leftChain 
	 * and rightChain. 
	 * 
	 * @throws IllegalArgumentException  when pts.length == 0
	 */
	public JarvisMarch(Point[] pts) throws IllegalArgumentException 
	{
		super(pts);
		highestPoint = pointsNoDuplicate[pointsNoDuplicate.length-1];
		algorithm = "Jarvis' march";
		leftChain = new ArrayBasedStack<Point>();
		rightChain = new ArrayBasedStack<Point>();
	}
	
	/**
	 * Call corresponding constructor of the superclass.  Initialize the variable algorithm.
	 * Set highestPoint.  Initialize leftChain and rightChain.  
	 * 
	 * @param  inputFileName
	 * @throws FileNotFoundException
	 * @throws InputMismatchException   when the input file contains an odd number of integers
	 */
	public JarvisMarch(String inputFileName) throws FileNotFoundException, InputMismatchException
	{
		super(inputFileName);
		highestPoint = pointsNoDuplicate[pointsNoDuplicate.length-1];
		algorithm = "Jarvis' march";
		leftChain = new ArrayBasedStack<Point>();
		rightChain = new ArrayBasedStack<Point>();
	}
	
	// ------------
	// Jarvis' march
	// ------------
	
	/**
	 * Calls createRightChain() and createLeftChain().  Merge the two chains stored on the stacks  
	 * rightChain and leftChain into the array hullVertices[].
	 * 
     * Two degenerate cases below must be handled: 
     * 
     *     1) The array pointsNoDuplicates[] contains just one point, in which case the convex
     *        hull is the point itself. 
     *     
     *     2) The array contains collinear points, in which case the hull is the line segment 
     *        connecting the two extreme points from them.   
	 */
	public void constructHull()
	{
		time = System.nanoTime();
		
		
		if (pointsNoDuplicate.length == 1) //convexHull of just one point
		{
			hullVertices = new Point[1];
			hullVertices[0] = pointsNoDuplicate[0];
			time = System.nanoTime() - time;
			return;
		}
		
		createRightChain(); //find all the varieties
		createLeftChain();
		
		hullVertices = new Point[leftChain.size() + rightChain.size()];
		for (int i=0; i<hullVertices.length; i++)
		{
			if (leftChain.size()!=0)
			{
				hullVertices[hullVertices.length - 1 - i] = leftChain.pop();
			}
			else
			{
				hullVertices[hullVertices.length - 1 - i] = rightChain.pop();
			}
		}
		
		time = System.nanoTime() - time;
	}
	
	
	/**
	 * Construct the right chain of the convex hull.  Starts at lowestPoint and wrap around the 
	 * points counterclockwise.  For every new vertex v of the convex hull, call nextVertex()
	 * to determine the next vertex, which has the smallest polar angle with respect to v.  Stop 
	 * when the highest point is reached.  
	 * 
	 * Use the stack rightChain to carry out the operation.  
	 * 
	 * Ought to be private, but is made public for testing convenience. 
	 */
	public void createRightChain()
	{
		Point vertex = lowestPoint;
		while (!vertex.equals(highestPoint))
		{
			rightChain.push(vertex);
			vertex = nextVertex(vertex);
		}
	}
	
	
	/**
	 * Construct the left chain of the convex hull.  Starts at highestPoint and continues the 
	 * counterclockwise wrapping.  Stop when lowestPoint is reached.  
	 * 
	 * Use the stack leftChain to carry out the operation. 
	 * 
	 * Ought to be private, but is made public for testing convenience. 
	 */
	public void createLeftChain()
	{
		Point vertex = highestPoint;
		while (!vertex.equals(lowestPoint))
		{
			leftChain.push(vertex);
			vertex = nextVertex(vertex);
		}
	}
	
	
	/**
	 * Return the next vertex, which is less than all other points by polar angle with respect
	 * to the current vertex v. When there is a tie, pick the point furthest from v. Comparison 
	 * is done using a PolarAngleComparator object created by the constructor call 
	 * PolarAngleComparator(v, false).
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param v  current vertex 
	 * @return
	 */
	public Point nextVertex(Point v)
	{
		PolarAngleComparator angle = new PolarAngleComparator(v, false);
		
		Point vertex = pointsNoDuplicate[0];
		for (int i=0; i<pointsNoDuplicate.length; i++)
		{
			Point next = pointsNoDuplicate[i];
			if (angle.compare(vertex, next) > 0)
			{
				vertex = next;
			}
		}
		return vertex;
	}
}