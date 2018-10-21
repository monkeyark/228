// from http://www.java-samples.com/showtutorial.php?tutorialid=344

package testClone;

public class CloneDemo 
{
	public static void main(String args[]) 
	{ 
		TestClone x1 = new TestClone(); 
		TestClone x2; 
		x1.a = 10; 
		x1.b = 20.98; 
		
		// here, clone() is called directly. 
		x2 = (TestClone) x1.clone(); 
		System.out.println("x1: " + x1.a + " " + x1.b); 
		System.out.println("x2: " + x2.a + " " + x2.b); 		
	} 
}
