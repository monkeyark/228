package edu.iastate.cs228.hw4;

import org.junit.Test;

public class ConvexHullTest
{
	@Test
	public void testRemoveDuplicates()
	{
//		Point[] pts = {new Point(5,5), new Point(0,0), new Point(1,2), new Point(2,3), new Point(2,1),
//		 		new Point(3,2), new Point(1,1), new Point(2,2), new Point(4,4), new Point(2,1),
//		 		new Point(3,2), new Point(1,1), new Point(2,2), new Point(5,5), new Point(2,1)};
//		Point[] pts = {new Point(20,0), new Point(10,10), new Point(0,20), new Point(-10,10),
//				new Point(-20,0), new Point(-10,-10), new Point(0,-20), new Point(10,-10),
//				new Point(1,1), new Point(1,1), new Point(2,2), new Point(2,2), new Point(3,3), new Point(3,3),
//				new Point(-1,-1), new Point(-1,-1), new Point(-2,-2), new Point(-2,-2), new Point(-3,-3), new Point(-3,-3)};
//		//(0, -20)   (-10, -10)   (10, -10)   (-3, -3)   (-2, -2)   (-1, -1)   (-20, 0)   (20, 0)   (1, 1)   (2, 2)   (3, 3)   (-10, 10)   (10, 10)   (0, 20)
		Point[] pts = {new Point(0,-20), new Point(15,-15), new Point(20,0), new Point(15,15),
				new Point(0,20), new Point(-15,15),	new Point(-20,0), new Point(-15,-15),
				new Point(2,-2), new Point(2,-2), new Point(2,2), new Point(2,2),
				new Point(-2,2), new Point(-2,2), new Point(-2,-2), new Point(-2,-2),};
		// (0,-20) (15,-15) (20,0) (15,15) (0,20) (-15,15) (-20,0) (-15,-15)
		ConvexHull hull = new GrahamScan(pts);
		
		System.out.println(System.lineSeparator() + "Original:");
		for (int i=0; i<pts.length; i++)
		{
			System.out.print(pts[i] + "   ");
		}
		
		hull.removeDuplicates();
		
		
		System.out.println(System.lineSeparator() + "pointsNoDuplicate:");
		for (int i=0; i<hull.pointsNoDuplicate.length; i++)
		{
			System.out.print(hull.pointsNoDuplicate[i] + "   ");
		}
		
	}
	@Test
	public void testToString()
	{
//		Point[] pts = {new Point(0,0), new Point(-1,0), new Point(0,-1), new Point(-1,-1), new Point(2,2),
//		 		new Point(2,2), new Point(2,2), new Point(-3,4), new Point(-3,4), new Point(2,4)};
		
//		Point[] pts = {new Point(0,-20), new Point(15,-15), new Point(20,0), new Point(15,15),
//				new Point(0,20), new Point(-15,15),	new Point(-20,0), new Point(-15,-15),
//				new Point(2,-2), new Point(2,-2), new Point(2,2), new Point(2,2),
//				new Point(-2,2), new Point(-2,2), new Point(-2,-2), new Point(-2,-2),};
		
		Point[] pts = {new Point(0,0), new Point(0,0), new Point(-1,-1), new Point(-1,-1), new Point(2,2),
		 		new Point(2,2), new Point(2,2), new Point(4,4), new Point(-3,-3), new Point(11,11)};
		
		ConvexHull hull = new GrahamScan(pts);
		hull.constructHull();
		//System.out.println(hull.toString());
	}
	@Test
	public void testDraw()
	{
		Point[] pts = {new Point(0,-20), new Point(15,-15), new Point(20,0), new Point(15,15),
				new Point(0,20), new Point(-15,15),	new Point(-20,0), new Point(-15,-15),
				new Point(2,-2), new Point(2,-2), new Point(2,2), new Point(2,2),
				new Point(-2,2), new Point(-2,2), new Point(-2,-2), new Point(-2,-2),};
		
		ConvexHull hull = new GrahamScan(pts);
		hull.constructHull();
		hull.draw();
		System.out.println( "\n \n \n draw");
		
	}

}
