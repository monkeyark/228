// from http://www.java-samples.com/showtutorial.php?tutorialid=344

package testClone;

public class TestClone implements Cloneable 
{
	int a; 
	double b; 
	
	// clone() is now overridden and is public. 
	public Object clone() 
	{ 
		try { 
			// call clone in Object. 
			return super.clone(); 
		} 
		
		catch(CloneNotSupportedException e) 
		{ 
			System.out.println("Cloning not allowed."); 
			return this; 
		} 
	} 
}
