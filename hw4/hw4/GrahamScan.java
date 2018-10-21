package edu.iastate.cs228.hw4;

import java.io.FileNotFoundException;
import java.util.InputMismatchException; 

public class GrahamScan extends ConvexHull
{
	/**
	 * Stack used by Grahma's scan to store the vertices of the convex hull of the points 
	 * scanned so far.  At the end of the scan, it stores the hull vertices in the 
	 * counterclockwise order. 
	 */
	private PureStack<Point> vertexStack;
	
	/**
	 * Call corresponding constructor of the super class.  Initialize two variables: algorithm 
	 * (from the class ConvexHull) and vertexStack. 
	 * 
	 * @throws IllegalArgumentException  if pts.length == 0
	 */
	public GrahamScan(Point[] pts) throws IllegalArgumentException 
	{
		super(pts);
		algorithm = "Graham's scan";
		vertexStack = new ArrayBasedStack<Point>();
	}
	
	/**
	 * Call corresponding constructor of the super class.  Initialize algorithm and vertexStack.  
	 * 
	 * @param  inputFileName
	 * @throws FileNotFoundException
	 * @throws InputMismatchException   when the input file contains an odd number of integers
	 */
	public GrahamScan(String inputFileName) throws FileNotFoundException, InputMismatchException
	{
		super(inputFileName);
		algorithm = "Graham's scan";
		vertexStack = new ArrayBasedStack<Point>();
	}
	
	// -------------
	// Graham's scan
	// -------------
	
	/**
	 * This method carries out Graham's scan in several steps below: 
	 * 
	 *     1) Call the private method setUpScan() to sort all the points in the array 
	 *        pointsNoDuplicate[] by polar angle with respect to lowestPoint.    
	 *        
	 *     2) Perform Graham's scan. To initialize the scan, push pointsNoDuplicate[0] and 
	 *        pointsNoDuplicate[1] onto vertexStack.  
	 * 
     *     3) As the scan terminates, vertexStack holds the vertices of the convex hull.  Pop the 
     *        vertices out of the stack and add them to the array hullVertices[], starting at index
     *        vertexStack.size() - 1, and decreasing the index toward 0.    
     *        
     * Two degenerate cases below must be handled: 
     * 
     *     1) The array pointsNoDuplicates[] contains just one point, in which case the convex
     *        hull is the point itself. 
     *     
     *     2) The array contains only collinear points, in which case the hull is the line segment 
     *        connecting the two extreme points.   
	 */
	public void constructHull()
	{
		time = System.nanoTime();
		setUpScan();
		if (pointsNoDuplicate.length == 1) //convexHull of just one point
		{
			hullVertices = new Point[1];
			hullVertices[0] = pointsNoDuplicate[0];
			time = System.nanoTime() - time;
			return;
		}
		
		//stack initialization
		vertexStack.push(pointsNoDuplicate[0]);
		vertexStack.push(pointsNoDuplicate[1]);
		
		for (int i=2; i<pointsNoDuplicate.length; i++) //starting from the third point
		{
			//Before push top, pop the collinear point between reference and top.
			//In order to find collinear points, visit point on the stack backward(go through points clockwise)
			//left turn points in counterclockwise(angle.compare<0) behave the same with right turn points clockwise(collinearComp.compare()>0).
			//However, when go through collinear points (angle.compare<0) and (collinearComp.compare()>0) with counterclockwise and clockwise visit respectively
			Point top = pointsNoDuplicate[i];
			Point mid = vertexStack.pop();
			Point bot = vertexStack.peek();
			PolarAngleComparator comp = new PolarAngleComparator(top, true);
			
			if (comp.compare(mid, bot) < 0) //clockwise right turn when cross product < 0
			{
				if (isCollinear(top, mid, bot))
				{
					vertexStack.push(top);
				}
				else
				{
					i--;
				}
			}
			else if (comp.compare(mid, bot) > 0) //clockwise left turn when cross product < 0
			{
				vertexStack.push(mid);
				vertexStack.push(top);
			}
			else if (comp.compare(mid, bot) == 0) //only happens for only two points in hull
			{
				vertexStack.push(top);
			}
		}
		
		/**
		 *TODO DEBUG
		 *this the old version of the construct, collinear points will not be popped out
		 *when input size is over 1000
		 *  
		Point ref = pointsNoDuplicate[0];
		Point next = pointsNoDuplicate[1];
		
		for (int i=2; i<pointsNoDuplicate.length; i++) //starting from the third point
		{
			Point top = pointsNoDuplicate[i];
			
			PolarAngleComparator angle = new PolarAngleComparator(ref, true);
			
			if (angle.compare(next, top) < 0) //left turn when cross product < 0
			{
				//Before push top (new point), pop the collinear point between reference and top.
				//In order to find collinear points, visit point on the stack backward(go through points clockwise)
				//left turn points in counterclockwise(angle.compare<0) behave the same with right turn points clockwise(collinearComp.compare()>0).
				//However, when go through collinear points (angle.compare<0) and (collinearComp.compare()>0) with counterclockwise and clockwise visit respectively
				Point p0 = top;
				Point p1 = vertexStack.pop();
				Point p2 = vertexStack.pop();
				PolarAngleComparator collinearComp = new PolarAngleComparator(p0, true);
				if (collinearComp.compare(p1, p2) < 0) //collinear in backward
				{
					ref = p2;
					vertexStack.push(ref);
				}
				else if (collinearComp.compare(p1, p2) > 0) //turn right in backward
				{
					ref = p2;
					vertexStack.push(ref);
					next = p1;
					vertexStack.push(next);
					ref = next; //push forward reference
				}
				next = top;
				vertexStack.push(top);
			}
			else if (angle.compare(next, top) > 0) //right turn when cross product > 0
			{
				while (angle.compare(next, top) > 0) //keep popping from stack to find the correct reference
				{
					next = vertexStack.pop();
					ref = vertexStack.pop();
					vertexStack.push(ref);
					angle = new PolarAngleComparator(ref, true);
				}
				vertexStack.push(next);
				vertexStack.push(top);
				next = top;
			}
			else if (angle.compare(next, top) == 0) //should never happen
			{
				vertexStack.push(pointsNoDuplicate[i]);
			}
		}
		*/
		
		// move points from vertextStack to hullVertices
		int vertexSize = vertexStack.size();
		hullVertices = new Point[vertexSize];
		for (int i=vertexSize-1; i>=0; i--)
		{
			hullVertices[i] = vertexStack.pop();
		}
		time = System.nanoTime() - time;
	}
	
	/**
	 * 
	 * helper method to check if three points are on the same line
	 * @param ref
	 * @param mid
	 * @param top
	 * @return
	 */
	private boolean isCollinear(Point ref, Point mid, Point top)
	{
		int x1 = mid.getX() - ref.getX();
		int y1 = mid.getY() - ref.getY();
		int x2 = top.getX() - ref.getX();
		int y2 = top.getY() - ref.getY();
		
		return x1*y2-x2*y1 == 0;
	}
	
	
	/**
	 * Set the variable quicksorter from the class ConvexHull to sort by polar angle with respect 
	 * to lowestPoint, and call quickSort() from the QuickSortPoints class on pointsNoDupliate[]. 
	 * The argument supplied to quickSort() is an object created by the constructor call 
	 * PolarAngleComparator(lowestPoint, true).       
	 * 
	 * Ought to be private, but is made public for testing convenience.
	 *
	 */
	public void setUpScan()
	{
		PolarAngleComparator comp = new PolarAngleComparator(lowestPoint, true);
		quicksorter = new QuickSortPoints(pointsNoDuplicate);
		quicksorter.quickSort(comp);
		quicksorter.getSortedPoints(pointsNoDuplicate);
	}	
}
