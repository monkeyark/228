package edu.iastate.cs228.hw4;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class GrahamScanTest
{
	GrahamScan hull;
	@Before
	public void setUp()
	{
//		Point[] pts = {new Point(5,5), new Point(0,0), new Point(1,2), new Point(2,3), new Point(2,1),
//		 		new Point(3,2), new Point(1,1), new Point(2,2), new Point(4,4), new Point(2,1),
//		 		new Point(3,2), new Point(1,1), new Point(2,2), new Point(5,5), new Point(2,1)};
//		//   (0, 0)   (1, 1)   (2, 1)   (1, 2)   (2, 2)   (3, 2)   (2, 3)   (4, 4)   (5, 5)
		
//		Point[] pts = {new Point(0,-20), new Point(15,-15), new Point(20,0), new Point(15,15),
//						new Point(0,20), new Point(-15,15),	new Point(-20,0), new Point(-15,-15),
//						new Point(2,-2), new Point(2,-2), new Point(2,2), new Point(2,2),
//						new Point(-2,2), new Point(-2,2), new Point(-2,-2), new Point(-2,-2)};
//		// (0,-20), (15,-15), (20,0), (15,15), (0,20), (-15,15), (-20,0), (-15,-15)
		
		Point[] pts = {new Point(0,1), new Point(0,2), new Point(-1,-1), new Point(0,0), new Point(1,1), new Point(2,2), new Point(3,3), new Point(4,4), new Point(5,5)};
		hull = new GrahamScan(pts);
	}
	@Test
	public void testSetUpScan()
	{
//		hull.constructHull();
//		Point[] verteices = {new Point(0,-20), new Point(15,-15), new Point(20,0), new Point(15,15),
//				new Point(0,20), new Point(-15,15),	new Point(-20,0), new Point(-15,-15)};
//		System.out.println(verteices);
//		assertEquals(verteices, hull.hullVertices);
//		System.out.println(Arrays.deepToString(verteices));
	}
	@Test
	public void testStats()
	{
		hull.constructHull();
		String s = hull.algorithm + "   " + hull.hullVertices.length + "   " + hull.time;
		System.out.println(s);
		assertEquals(s, hull.stats());
		System.out.println(hull.toString());
	}
}