package edu.iastate.cs228.hw4;

import java.util.Arrays;
import java.util.Comparator;

/**
 * This class sorts an array of Point objects using a provided Comparator.
 * For the purpose you may adapt your implementation of quicksort from Project 2.  
 */

public class QuickSortPoints
{
	private Point[] points;  	// Array of points to be sorted.
	//public static Point[] points = new Point[10];
	
	/**
	 * Constructor takes an array of Point objects. 
	 * 
	 * @param pts
	 */
	QuickSortPoints(Point[] pts)
	{
		points = Arrays.copyOf(pts, pts.length);
	}
	
	
	/**
	 * Copy the sorted array to pts[]. 
	 * 
	 * @param pts  array to copy onto
	 */
	void getSortedPoints(Point[] pts)
	{
//		for (int i=0; i<pts.length; i++)
//		{
//			pts[i] = points[i];
//		}
		System.arraycopy(points, 0, pts, 0, points.length);
	}
	
	
	/**
	 * Perform quicksort on the array points[] with a supplied comparator. 
	 * 
	 * @param comp
	 */
	public void quickSort(Comparator<Point> comp)
	{
		if (points == null || comp == null) throw new NullPointerException();
		
		quickSortRec(0, points.length-1, comp);
	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last. 
	 * 
	 * @param first  starting index of the subarray
	 * @param last   ending index of the subarray
	 */
	private void quickSortRec(int first, int last, Comparator<Point> comp)
	{
		if (first >= last) return;
		int pivot = partition(first, last, comp);
		quickSortRec(first, pivot-1, comp);
		quickSortRec(pivot+1, last, comp);
	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 * 
	 * @param first
	 * @param last
	 * @return
	 */
	private int partition(int first, int last, Comparator<Point> comp)
	{
		Point pivot = points[last];
		int i = first - 1;
		for(int j=first; j<last; j++)
		{
			if (comp.compare(points[j], pivot) <= 0)
			{
				i++;
				Point temp = points[i];
				points[i] = points[j];
				points[j] = temp;
			}
		}
		Point temp = points[i+1];
		points[i+1] = points[last];
		points[last] = temp;
		return i+1;
	}
}


