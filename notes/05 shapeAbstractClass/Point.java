// from http://www.javabeginner.com/learn-java/java-abstract-class-and-interface

package shapeAbstractClass;

public class Point extends Shape 
{	
	public Point() 
	{
		super(); 
	}
	
	public Point(int x, int y)
	{
		super(x, y); 
	}
	
	public double area() 
	{
		return 0;
	}
	
	public double perimeter() 
	{
		return 0;
	}
	
	public void print() 
	{
		System.out.println("point: " + x + "," + y);
	}
	
	public static void main(String args[]) 
	{
		Point p = new Point();
		p.print();
	}
}
