package shortestPaths;

/**
 * 
 * @author Xiaoqiu Huang 
 * 
 */

public class TestDiGraph {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        DiGraph<String> G = new DiGraph<String>();
        G.addEdge("A", "B", 3);
        G.addEdge("A", "C", 5);
        G.addEdge("B", "C", 1);
        G.addEdge("B", "D", 6);
        G.addEdge("C", "B", 2);
        G.addEdge("C", "D", 3);
        G.addEdge("C", "E", 6);
        G.addEdge("D", "E", 2);
        G.addEdge("E", "D", 7);
        G.addEdge("E", "A", 3);
        System.out.println( " Edges of A");
	for ( Edge<String, Integer> e: G.adjacentTo("A") )
	{
           System.out.println(
                "[" + e.getVertex().toString() + ", "
		         + e.getCost().toString() + "] " );

	}

        System.out.println( " end \n ");
        System.out.println( " Vertices \n ");
	for ( String n : G.vertices() )
          System.out.println( n.toString() + ", ");
        System.out.println( " end \n ");


        // print out graph
        System.out.println(G);
        DiGraph.Dijkstra(G, "A");

        G = new DiGraph<String>();
	G.addEdge("A", "B", 1);
	G.addEdge("A", "C", 10);
	G.addEdge("A", "D", 15);
	G.addEdge("B", "E", 2);
	G.addEdge("B", "C", 20);
	G.addEdge("C", "D", 35);
	G.addEdge("C", "F", 4);
	G.addEdge("D", "G", 6);
	G.addEdge("E", "C", 3);
	G.addEdge("E", "F", 25);
	G.addEdge("F", "D", 5);
	G.addEdge("F", "G", 30);
	G.addEdge("G", "A", 7);
        DiGraph.Dijkstra(G, "A");
/*
        // print out graph again by iterating over vertices and edges
        for (String v : G.vertices()) {
            StdOut.print(v + ": ");
            for (String w : G.adjacentTo(v)) {
                StdOut.print(w + " ");
            }
            StdOut.println();
        }
	*/

    }
}

