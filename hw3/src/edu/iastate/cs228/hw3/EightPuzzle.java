package edu.iastate.cs228.hw3;

import java.util.ArrayList;
import java.util.Collections;


public class EightPuzzle
{
	
	/**
	 * This static method solves an 8-puzzle with a given initial state using two heuristics which 
	 * compare the board configuration with the goal configuration by the number of mismatched tiles, 
	 * and by the Manhattan distance, respectively.  The goal configuration is set for all puzzles as
	 * 
	 * 			1 2 3
	 * 			8   4
	 * 			7 6 5
	 * 
	 * @param s0
	 * @return
	 */
	public static String solve8Puzzle(State s0)
	{
		
		// 1) Return "No solution exists for the following initial state:" if the puzzle is not solvable. 
		if (!s0.solvable())
		{
			return "No solution exists for the following initial state:" + System.lineSeparator() + s0.toString();
		}
		// 2) Otherwise, solve the puzzle with two heuristics.  The two solutions may be different
		//    but must have the same length for optimality. 
		if (s0.isGoalState())
		{
			return "initial puzzle is GoalState" + System.lineSeparator() + s0.toString();
		}
		
		// 3) Combine the two solution strings into one that would print out in the 
		//    output format specified in Section 5 of the project description.
		String solution = "";
		Heuristic h[] = {Heuristic.TileMismatch, Heuristic.ManhattanDist};
		String [] moves = new String[2];
		
		for (int i = 0; i < 2; i++)
		{
			moves[i] = AStar(s0, h[i]);
			solution += moves[i];
		}
		return solution;
	}

	
	/**
	 * This method implements the A* algorithm to solve the 8-puzzle with an input initial state s0. 
	 * The algorithm is described in Section 3 of the project description. 
	 * 
	 * Precondition: the puzzle is solvable with the initial state s0.
	 * 
	 * @param s0  initial state
	 * @param h   heuristic
	 * @return    solution string
	 */
	public static String AStar(State s0, Heuristic h)
	{
		// Initialize the two lists used by the algorithm. 
		OrderedStateList OPEN = new OrderedStateList(h, true); 
		OrderedStateList CLOSE = new OrderedStateList(h, false);
		
		//1. Put the start state s0 on OPEN. Let g(s0) = 0 and estimate h(s0).
		State cur = s0;
		OPEN.addState(cur);
		EightPuzzle puzzle = new EightPuzzle();
		String solution = "";
		//2. If OPEN is empty, exit with failure. This will not happen with an 8-puzzle if before calling A*
		//you use the method from Appendix to check that the puzzle is indeed solvable.
		//(The situation may occur when applying A* to solve a different type of problem.)
		while (OPEN.size() != 0)
		{
			//3. The states on the list OPEN will be ordered in the non-decreasing value of f.
			//Remove the first state s from OPEN and place on the list CLOSED.
			State temp = OPEN.remove();
			CLOSE.addState(temp);
			//4. If s is the goal state, exit successfully and print out the entire solution path
			//(step-by-step state transitions).
			if (temp.isGoalState())
			{
				solution += puzzle.solutionPath(temp);
				break;
			}
			//5. Otherwise, generate s's all possible successor states in one valid move and set their
			//predecessor links back to s.
			ArrayList<State> successor = new ArrayList<State>();
			try
			{
				successor.add(temp.successorState(Move.LEFT));
			}
			catch (IllegalArgumentException e){}
			try
			{
				successor.add(temp.successorState(Move.RIGHT));
			}
			catch (IllegalArgumentException e){}
			try
			{
				successor.add(temp.successorState(Move.UP));
			}
			catch (IllegalArgumentException e){}
			try
			{
				successor.add(temp.successorState(Move.DOWN));
			}
			catch (IllegalArgumentException e){}
			
			//		For every successor state t of s:
			//		a. Estimate h(t) and compute f(t) = g(t) + h(t) = g(s) + 1 + h(t).
			//		b. If t is not already on OPEN or CLOSED, then put it on OPEN.
			//		c. If t is already on OPEN, compare its old and new f values and choose the minimum.
			//			In the case that the new f value is chosen, reset the predecessor link
			//			of t (along the path yielding the lowest g(t)).
			//		d. If t is on CLOSED and its new f value is less than the old one. The state may
			//			become promising again because of this value decrease. Put t back on OPEN and
			//			reset its predecessor link.
			for (State s : successor)
			{
				if (OPEN.findState(s) == null && CLOSE.findState(s) == null)
				{
					OPEN.addState(s);
				}
				else if (OPEN.findState(s) != null)
				{
					if (OPEN.findState(s).compareTo(s) > 0) //estimate h(t)
					{
						OPEN.removeState(s); //remove s
						OPEN.addState(s); //add s to correct location
					}
				}
				else if (CLOSE.findState(s) != null)
				{
					if (CLOSE.findState(s).compareTo(s) > 0) //estimate h(t)
					{
						OPEN.addState(s);
						CLOSE.removeState(s);
					}
				}
			}
			//6. Go to step 2.
		}
		// Once a goal state s is reached, call solutionPath(s) and return the solution string.
		return solution; 
	}
	
	
	
	/**
	 * From a goal state, follow the predecessor link to trace all the way back to the initial state. 
	 * Meanwhile, generate a string to represent board configurations in the reverse order, with 
	 * the initial configuration appearing first. Between every two consecutive configurations 
	 * is the move that causes their transition. A blank line separates a move and a configuration.  
	 * In the string, the sequence is preceded by the total number of moves and a blank line. 
	 * 
	 * See Section 5 in the projection description for an example. 
	 * 
	 * Call the toString() method of the State class. 
	 * 
	 * @param goal
	 * @return
	 */
	private String solutionPath(State goal)
	{
		State temp = goal;
		ArrayList<State> list = new ArrayList<State>();
		//while (temp.predecessor != null)
		while (temp != null)
		{
			list.add(temp);
			temp = temp.predecessor;
		}
		Collections.reverse(list);
		
		String solution = "";
		 if (State.heu  == Heuristic.TileMismatch)
		 {
			 solution += list.size() + " move in total (heuristic: the Manhattan distance)" + System.lineSeparator();
		 }
		 if (State.heu == Heuristic.ManhattanDist)
		 {
			 solution += list.size() + " move in total (heuristic: number of mismatched tiles)" + System.lineSeparator();
		 }
		 solution += list.get(0).toString() + System.lineSeparator();
		 
		 for (State s : list)
		 {
			 if (s.move != null)
			 {
				 solution += s.move.toString() + System.lineSeparator() + s.toString() + System.lineSeparator();
			 }
		 }
		return solution;

	}
	
}