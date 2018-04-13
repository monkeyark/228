package edu.iastate.cs228.hw4;

import java.util.Comparator;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class QuickSortPointsTest
{
	
	private Point[] pts;
	private Random rand;
	
	@Before
	public void setUp()
	{
//		int size = 20;
//		pts = new Point[size];
//		rand = new Random();
//		for (int i=0; i<size; i++)
//		{
//			int x = rand.nextInt(20)-10;
//			int y = rand.nextInt(20)-10;
//			pts[i] = new Point(x,y);
//		}
//		QuickSortPoints sort = new QuickSortPoints(pts);
	}
	@Test
	public void test1()
	{
		Comparator<Point> comp = Comparator.naturalOrder();
		int size = 10;
		pts = new Point[size];
		rand = new Random();
		for (int i=0; i<size; i++)
		{
			int x = rand.nextInt(20)-10;
			int y = rand.nextInt(20)-10;
			pts[i] = new Point(x,y);
		}
		System.out.println("Original:");
		for (int i=0; i<pts.length; i++)
		{
			System.out.print(pts[i] + "   ");
		}
		
		QuickSortPoints sort = new QuickSortPoints(pts);
		
		sort.quickSort(comp);
		
		
		Point[] point = new Point[pts.length];
		sort.getSortedPoints(point);
		
		System.out.println(System.lineSeparator() + "Sorted:");
		for (int i=0; i<point.length; i++)
		{
			System.out.print(point[i] + "   ");
		}
		
	}
	@After
	public void reset()
	{
		
	}
	
}
