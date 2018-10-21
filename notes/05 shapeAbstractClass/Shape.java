// from http://www.javabeginner.com/learn-java/java-abstract-class-and-interface

package shapeAbstractClass;

public abstract class Shape 
{
	protected int x, y; 
	
    public String color;
    
	public Shape() 
	{
		x = 0;
		y = 0;
	}
	
	public Shape(int x, int y)
	{
		this.x = x; 
		this.y = y; 
	}
	
	public void setColor(String c) 
	{
		color = c;
	}
	
	public String getColor() 
	{
		return color;
	}
	
	abstract public double area();
}
