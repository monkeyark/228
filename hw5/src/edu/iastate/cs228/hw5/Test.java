package edu.iastate.cs228.hw5;

import java.util.List;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Random;

public class Test
{

	public static void main(String[] args)
	{
//		Random rng = new Random();
//		String outputClassName = "fd";
//		OutputStream output = System.out;
//		List<String> words = Arrays.asList("abcdefghij");
//		PerfectHashGenerator p = new PerfectHashGenerator();
//		p.generate(words, output, outputClassName, rng);
		
		
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
