package edu.iastate.cs228.hw4;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class JunitTests {
	Point[] pointsTen;
	Point[] pointsTenSorted;
	Point[] pointsFive;
	Point[] pointsFiveSorted;
	Point[] pointsThirtySorted;
	Point[] pointsThirty;
	
	
	Point[] manyDups;
	Point[] pointsThirtyNoDups;
	Point[] manyDupsNoDups;
	Point[] dups;
	Point[] reverseDups;
	
	ConvexHull grahamFive;
	ConvexHull grahamTen;
	ConvexHull grahamThirty;
	
	ConvexHull jarvisFive;
	ConvexHull jarvisTen;
	ConvexHull jarvisThirty;
	
	
	
 @Before
 public void setup() {
	 
		
	pointsFive = new Point[5];
		pointsFive[0] = new Point(3,9);
		pointsFive[1] = new Point(7,14);
		pointsFive[2] = new Point(-40, 35);
		pointsFive[3] = new Point(-10,-10);
		pointsFive[4] = new Point(-13, -40);
	
	pointsFiveSorted = new Point[5];
		pointsFiveSorted[0] = new Point(-13,-40);
		pointsFiveSorted[1] = new Point(7,14);
		pointsFiveSorted[2] = new Point(3, 9);
		pointsFiveSorted[3] = new Point(-10,-10);
		pointsFiveSorted[4] = new Point(-40, 35);
		 
	 
	 pointsTen = new Point[10];
		pointsTen[0] = new Point(-10, 20);
		pointsTen[1] = new Point(4, 13);
		pointsTen[2] = new Point(2, 8);
		pointsTen[3] = new Point(1, 9); 
		pointsTen[4] = new Point(-4, -9);
		pointsTen[5] = new Point(5,7);
		pointsTen[6] = new Point(2,30);
		pointsTen[7] = new Point(-10,-3);
		pointsTen[8] = new Point(-4,-4);
		pointsTen[9] = new Point(-4, -12);
		
		
	pointsTenSorted = new Point[10];
		pointsTenSorted[0]= new Point(-4,-12);
		pointsTenSorted[1]= new Point(5, 7);
		pointsTenSorted[2]= new Point(4, 13);
		pointsTenSorted[3] = new Point(2,8);
		pointsTenSorted[4] = new Point(1,9);
		pointsTenSorted[5]= new Point(2,30);
		pointsTenSorted[6] = new Point(-4,-9);
		pointsTenSorted[7] = new Point(-4,-4);
		pointsTenSorted[8] = new Point(-10, 20);
		pointsTenSorted[9] = new Point(-10,-3);
		
	
	pointsThirty = new Point[30]; 
		pointsThirty[0] = new Point(-50, 20);
		pointsThirty[1] = new Point(4, 13);
		pointsThirty[2] =new Point(2, 8);
		pointsThirty[3] =  new Point(1, 9); 
		pointsThirty[4] = new Point(-4, -9);
		pointsThirty[5] = new Point(45,7);
		pointsThirty[6] = new Point(50,30);
		pointsThirty[7] = new Point(-50,-3);
		pointsThirty[8] = new Point(-40,-40);
		pointsThirty[9] = new Point(-4, -30);
		pointsThirty[10] = new Point(-30, 20);
		pointsThirty[11] = new Point(4, 34);
		pointsThirty[12] =new Point(29, 8);
		pointsThirty[13] =  new Point(1, 49); 
		pointsThirty[14] = new Point(-42, -9);
		pointsThirty[15] = new Point(4,-32);
		pointsThirty[16] = new Point(21,30);
		pointsThirty[17] = new Point(-34,-45);
		pointsThirty[18] = new Point(-40,-47);
		pointsThirty[19] = new Point(-4, -30);
		pointsThirty[20] = new Point(-12, 20);
		pointsThirty[21] = new Point(29, 13);
		pointsThirty[22] = new Point(41, 8);
		pointsThirty[23] =  new Point(9, 9); 
		pointsThirty[24] = new Point(-36, -9);
		pointsThirty[25] = new Point(45,17);
		pointsThirty[26] = new Point(-23,-50);
		pointsThirty[27] = new Point(19,-3);
		pointsThirty[28] = new Point(-46,-50);
		pointsThirty[29] = new Point(-13, -30);
		
	pointsThirtySorted = new Point[30];
		pointsThirtySorted[0] = new Point(-46, -50);
		pointsThirtySorted[1] = new Point(-23, -50);
		pointsThirtySorted[2] =new Point(4, -32);
		pointsThirtySorted[3] =  new Point(-34, -45); 
		pointsThirtySorted[4] = new Point(-4, -30);
		pointsThirtySorted[5] = new Point(-4,-30);
		pointsThirtySorted[6] = new Point(-40,-47);
		pointsThirtySorted[7] = new Point(-13,-30);
		pointsThirtySorted[8] = new Point(45,7);
		pointsThirtySorted[9] = new Point(41, 8);
		pointsThirtySorted[10] = new Point(19, -3);
		pointsThirtySorted[11] = new Point(45, 17);
		pointsThirtySorted[12] =new Point(29, 8);
		pointsThirtySorted[13] =  new Point(50, 30); 
		pointsThirtySorted[14] = new Point(29, 13);
		pointsThirtySorted[15] = new Point(-4,-9);
		pointsThirtySorted[16] = new Point(9,9);
		pointsThirtySorted[17] = new Point(21,30);
		pointsThirtySorted[18] = new Point(2,8);
		pointsThirtySorted[19] = new Point(1, 9);
		pointsThirtySorted[20] = new Point(4, 13);
		pointsThirtySorted[21] = new Point(-40, -40);
		pointsThirtySorted[22] = new Point(4, 34);
		pointsThirtySorted[23] =  new Point(-12, 20); 
		pointsThirtySorted[24] = new Point(1, 49);
		pointsThirtySorted[25] = new Point(-36,-9);
		pointsThirtySorted[26] = new Point(-30, 20);
		pointsThirtySorted[27] = new Point(-42,-9);
		pointsThirtySorted[28] = new Point(-50,20);
		pointsThirtySorted[29] = new Point(-50, -3);
		
	pointsThirtyNoDups = new Point[29];
		pointsThirtyNoDups[0] = new Point(-46,-50);
		pointsThirtyNoDups[1] = new Point(-23,-50);
		pointsThirtyNoDups[2] = new Point(-40,-47);
		pointsThirtyNoDups[3] = new Point(-34,-45);
		pointsThirtyNoDups[4] = new Point(-40,-40);
		pointsThirtyNoDups[5] = new Point(4,-32);
		pointsThirtyNoDups[6] = new Point(-13,-30);
		pointsThirtyNoDups[7] = new Point(-4,-30);
		pointsThirtyNoDups[8] = new Point(-42,-9);
		pointsThirtyNoDups[9] = new Point(-36,-9);
		pointsThirtyNoDups[10] = new Point(-4,-9);
		pointsThirtyNoDups[11] = new Point(-50,-3);
		pointsThirtyNoDups[12] = new Point(19,-3);
		pointsThirtyNoDups[13] = new Point(45,7);
		pointsThirtyNoDups[14] = new Point(2,8);
		pointsThirtyNoDups[15] = new Point(29,8);
		pointsThirtyNoDups[16] = new Point(41,8);
		pointsThirtyNoDups[17] = new Point(1,9);
		pointsThirtyNoDups[18] = new Point(9, 9);
		pointsThirtyNoDups[19] = new Point(4,13);
		pointsThirtyNoDups[20] = new Point(29,13);
		pointsThirtyNoDups[21] = new Point(45,17);
		pointsThirtyNoDups[22] = new Point(-50,20);
		pointsThirtyNoDups[23] = new Point(-30,20);
		pointsThirtyNoDups[24] = new Point(-12,20);
		pointsThirtyNoDups[25] = new Point(21,30);
		pointsThirtyNoDups[26] = new Point(50,30);
		pointsThirtyNoDups[27] = new Point(4,34);
		pointsThirtyNoDups[28] = new Point(1,49);
		
	manyDups = new Point[5];
	
		manyDups[0] = new Point(0,5);
		manyDups[1] = new Point(1,6);
		manyDups[2] = new Point(0,5);
		manyDups[3] = new Point(1,6);
		manyDups[4] = new Point(0,0);
		
		
	manyDupsNoDups = new Point[3];
	
		manyDupsNoDups[0] = new Point(0,0);
		manyDupsNoDups[1] = new Point(0,5);
		manyDupsNoDups[2] = new Point(1,6);
		
	dups = new Point[5];
	
		dups[0] = new Point(0,10);
		dups[1] = new Point(0,9);
		dups[2] = new Point(0,8);
		dups[3] = new Point(0,7);
		dups[4] = new Point(0,6);
		
	reverseDups = new Point[5];
	
		reverseDups[0] = dups[4];
		reverseDups[1] = dups[3];
		reverseDups[2] = dups[2];
		reverseDups[3] = dups[1];
		reverseDups[4] = dups[0];
	
	

	
	
	
	
	
 }
 
 
 
 @Test
 public void testQuickSortFivePoints() {
	 //using the lowest point as a reference point and flag true
	 
	 
	 Point[] test = pointsFive.clone();
	 QuickSortPoints quick = new QuickSortPoints(test);
	 
	 
	 PolarAngleComparator comp = new PolarAngleComparator(pointsFive[4], true);
	 quick.quickSort(comp);
	 
	 Point[] testResult = new Point[5];
	 quick.getSortedPoints(testResult);
	 
	 assertArrayEquals(testResult, pointsFiveSorted);
 }
 
 @Test
 public void testQuickSortTenPoints() {
	 //ensure clone method works
	 Point[] test = pointsTen.clone();
	 QuickSortPoints quick = new QuickSortPoints(test);
	 
	  
	 //using the lowest point as the reference point
	 PolarAngleComparator comp = new PolarAngleComparator(pointsTen[9], true);
	 quick.quickSort(comp);
	
	  
	 //ensure get sorted points works
	 Point[] testResult = new Point[10];
	 quick.getSortedPoints(testResult);
	 
	 assertArrayEquals(testResult, pointsTenSorted);
	 
 }
 
 @Test
 public void testQuickSortThirtyPoints() {
	//ensure clone method works
	 Point[] test = pointsThirty.clone();
	 QuickSortPoints quick = new QuickSortPoints(test);
	
	 //using the lowest point as the reference point
	 PolarAngleComparator comp = new PolarAngleComparator(pointsThirty[28], true);
	 quick.quickSort(comp);
	 
	//ensure get sorted points works 
	 Point[] testResult = new Point[30];
	 quick.getSortedPoints(testResult);
	 
	 assertArrayEquals(testResult, pointsThirtySorted);
 }
 
 @Test
 public void testNoDups() {
	
	 Point[] noDups = manyDups.clone();
	 Point[] thirtyNoDups = pointsThirty.clone();
	 
	 grahamThirty = new GrahamScan(thirtyNoDups); 
	 jarvisThirty = new JarvisMarch(thirtyNoDups);
	
	 grahamTen = new GrahamScan(noDups);
	 jarvisTen = new JarvisMarch(noDups);
	 
	 grahamFive = new GrahamScan(dups);
	 jarvisFive = new JarvisMarch(dups);
	 
	 assertArrayEquals(pointsThirtyNoDups, grahamThirty.pointsNoDuplicate);
	 assertArrayEquals(pointsThirtyNoDups, jarvisThirty.pointsNoDuplicate);
	 
	 assertArrayEquals(manyDupsNoDups, grahamTen.pointsNoDuplicate);
	 assertArrayEquals(manyDupsNoDups, jarvisTen.pointsNoDuplicate);
	 
	 assertArrayEquals(reverseDups, grahamFive.pointsNoDuplicate);
	 assertArrayEquals(reverseDups, jarvisFive.pointsNoDuplicate);
	 
	 
	 
	 
 }
 
 
 
 @Test
 public void testLowestPoints() {
	 Point[] fiveTest = pointsFive.clone();
	 Point[] tenTest = pointsTen.clone();
	 Point[] thirtyTest = pointsThirty.clone();
	 grahamFive = new GrahamScan(fiveTest);
	 grahamTen = new GrahamScan(tenTest);
	 grahamThirty = new GrahamScan(thirtyTest);
	 jarvisFive = new JarvisMarch(fiveTest);
	 jarvisTen = new JarvisMarch(tenTest);
	 jarvisThirty = new JarvisMarch(thirtyTest);
	 
	 assertEquals(pointsFive[4], grahamFive.lowestPoint);
	 assertEquals(pointsTen[9], grahamTen.lowestPoint	);
	 assertEquals(pointsThirty[28], grahamThirty.lowestPoint);
	 assertEquals(pointsFive[4], jarvisFive.lowestPoint);
	 assertEquals(pointsTen[9], jarvisTen.lowestPoint	);
	 assertEquals(pointsThirty[28], jarvisThirty.lowestPoint);
 }
 

 
 @Test
 public void testGrahamFive() {
	 Point[] test = pointsFive.clone();
	 grahamFive = new GrahamScan(test);
	 grahamFive.constructHull();
	 	 
	 //if drawn out on a xy-plane, these should be the hull vertices 
	 Point[] vertices = new Point[3];
	 vertices[0] = new Point(-13,-40);
	 vertices[1] = new Point(7,14);
	 vertices[2] = new Point(-40, 35);
	 	 
	 
	 
	 assertArrayEquals(vertices, grahamFive.hullVertices);	 
 }
 
 @Test
 public void testGrahamTen() {
	 Point[] test = pointsTen.clone();
	 grahamTen = new GrahamScan(test);
	 grahamTen.constructHull();
	 
	 //if drawn out on a xy-plane, these should be he hull vertices  
	Point[] vertices = new Point[5];
	vertices[0] = new Point(-4,-12);
	vertices[1] = new Point(5, 7);
	vertices[2] = new Point(2,30);
	vertices[3] = new Point(-10,20);
	vertices[4]= new Point(-10,-3);
	
	assertArrayEquals(vertices, grahamTen.hullVertices);
 }
 
 @Test
 public void testGrahamThirty() {
	 Point[] test = pointsThirty.clone();
	 grahamThirty = new GrahamScan(test);
	 grahamThirty.constructHull();
	 
	//if drawn out on a xy-plane, these should be he hull vertices  
	 Point[] vertices = new Point[8];
	 vertices[0] = new Point(-46, -50);
	 vertices[1] = new Point(-23,-50);
	 vertices[2] = new Point(4, -32);
	 vertices[3] = new Point(45, 7);
	 vertices[4] = new Point(50, 30);
	 vertices[5] = new Point(1,49);
	 vertices[6] = new Point(-50, 20);
	 vertices[7] = new Point(-50,-3);
	 
	 assertArrayEquals(vertices, grahamThirty.hullVertices);
	 
 }
 
 @Test
 public void testJarvisFive() {
	 Point[] test = pointsFive.clone();
	 jarvisFive = new JarvisMarch(test);
	 jarvisFive.constructHull();
	 	 
	 //if drawn out on a xy-plane, these should be the hull vertices 
	 Point[] vertices = new Point[3];
	 vertices[0] = new Point(-13,-40);
	 vertices[1] = new Point(7,14);
	 vertices[2] = new Point(-40, 35);
	 	 
	 
	 
	 assertArrayEquals(vertices, jarvisFive.hullVertices);	 
 }
 
 @Test
 public void testJarvisTen() {
	 Point[] test = pointsTen.clone();
	 jarvisTen = new JarvisMarch(test);
	 jarvisTen.constructHull();
	 
	//if drawn out on a xy-plane, these should be he hull vertices 
	Point[] vertices = new Point[5];
	vertices[0] = new Point(-4,-12);
	vertices[1] = new Point(5, 7);
	vertices[2] = new Point(2,30);
	vertices[3] = new Point(-10,20);
	vertices[4]= new Point(-10,-3);
	
	assertArrayEquals(vertices, jarvisTen.hullVertices);
 }
 
 @Test
 public void jarvisThirty() {
	 Point[] test = pointsThirty.clone();
	 jarvisThirty = new JarvisMarch(test);
	 jarvisThirty.constructHull();
	 
	//if drawn out on a xy-plane, these should be he hull vertices 
	 Point[] vertices = new Point[8];
	 vertices[0] = new Point(-46, -50);
	 vertices[1] = new Point(-23,-50);
	 vertices[2] = new Point(4, -32);
	 vertices[3] = new Point(45, 7);
	 vertices[4] = new Point(50, 30);
	 vertices[5]	 = new Point(1,49);
	 vertices[6] = new Point(-50, 20);
	 vertices[7] = new Point(-50,-3);
	 
	 assertArrayEquals(vertices, jarvisThirty.hullVertices);
	 
 }
 
}
