package edu.iastate.cs228.hw5;

public class Test
{

	public static void main(String[] args)
	{
		
		Graph g = new Graph(5);
		
		for(int i = 0 ; i < g.getVertices().length-1 ; i++)
			g.addEdge(i, i+1, i, "fu");
		
		
		
		g.addEdge(0, 3, 1, "wahteever");
		
		g.unvisitAll();
		
		
		
		System.out.println(g.toString());
		System.out.println(g.hasCycle());
		
		Visualizer v = new Visualizer();
		v.useGraph(g);
			
	}

}
