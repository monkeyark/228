package edu.iastate.cs228.hw4;

public class TestDraw
{
	

	public static void main(String[] args)
	{
//		Point[] pts = {new Point(0,-20), new Point(15,-15), new Point(20,0), new Point(15,15),
//		new Point(0,20), new Point(-15,15),	new Point(-20,0), new Point(-15,-15),
//		new Point(2,-2), new Point(2,-2), new Point(2,2), new Point(2,2),
//		new Point(-2,2), new Point(-2,2), new Point(-2,-2), new Point(-2,-2)};
//
//		Point[] pts = {new Point(0,-20)};
//
//		Point[] pts = {new Point(0,0), new Point(0,0), new Point(-1,-1), new Point(-1,-1), new Point(2,2),
// 		new Point(2,2), new Point(2,2), new Point(4,4), new Point(-3,-3), new Point(5,5)};
//
//		Point[] pts = {new Point(-7, -10), new Point(0, -10),
//				new Point(0, -10), new Point(10, 5),
//				new Point(10, 5), new Point(0, 8),
//				new Point(0,8), new Point(-10,0),
//				new Point(-10,0), new Point(-7,-10)};
		Point[] pts = {new Point(-7, -7), new Point(-10, -10),
				new Point(0, 0), new Point(5, 5),
				new Point(10, 10), new Point(8, 8),
				new Point(3,3), new Point(-15,-15),
				new Point(-40,-40), new Point(25,25),
				new Point(15,25)};
		
		ConvexHull[] algorithms = new ConvexHull[2];
		algorithms[0] = new GrahamScan(pts);
		algorithms[1] = new JarvisMarch(pts);
		
		for (int i=0; i<algorithms.length; i++)
		{
			algorithms[i].constructHull();
			algorithms[i].draw();
			System.out.println(algorithms[i].stats());
			System.out.println(algorithms[i].toString());
		}
	}

}