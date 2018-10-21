package edu.iastate.cs228.hw4;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class JarvisMarchTest
{

	ConvexHull hull;
	@Before
	public void setUp()
	{
		Point[] pts = {new Point(0,-20), new Point(15,-15), new Point(20,0), new Point(15,15),
				new Point(0,20), new Point(-15,15),	new Point(-20,0), new Point(-15,-15),
				new Point(2,-2), new Point(2,-2), new Point(2,2), new Point(2,2),
				new Point(-2,2), new Point(-2,2), new Point(-2,-2), new Point(-2,-2)};
		// (0,-20), (15,-15), (20,0), (15,15), (0,20), (-15,15), (-20,0), (-15,-15)
		
		//Point[] pts = {new Point(0,-20), new Point(20,0), new Point(0,20), new Point(-20,0)};
		//Point[] pts = {new Point(0,0), new Point(1,1), new Point(0,1), new Point(0,2), new Point(-1,-1)};
		// ()
		hull = new JarvisMarch(pts);
	}
	@Test
	public void testConstructHull()
	{
		hull.constructHull();
		String s = hull.algorithm + "   " + hull.hullVertices.length + "   " + hull.time;
		System.out.println(s);
		assertEquals(s, hull.stats());
		System.out.println(hull.toString());
	}
	@Test
	public void testStats()
	{
//		String s = hull.algorithm + "   " + hull.hullVertices.length + "   " + hull.time;
//		System.out.println(s);
//		assertEquals(s, hull.stats());
	}
}
