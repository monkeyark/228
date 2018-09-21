package shortestPaths;

/**
 * 
 * @author Xiaoqiu Huang 
 * 
 */

public interface PurePriorityQueue<E> {
	int size();
	boolean isEmpty();
	void add(E element);
	// Returns a high-priority element without removing it.
	E getMin();
	// Removes a high-priority element.
	E removeMin();
}
