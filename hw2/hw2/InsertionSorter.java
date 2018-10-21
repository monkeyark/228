package edu.iastate.cs228.hw2;


import java.util.Comparator;

/**
 * An implementation of {@link Sorter} that performs insertion sort
 * to sort the list.
 * 
 */
public class InsertionSorter extends Sorter
{
	@Override
	public void	sort(WordList toSort, Comparator<String> comp) throws NullPointerException
	{
		if (toSort == null || comp == null) throw new NullPointerException();
		
		for (int i=1; i<toSort.length(); i++)
		{
			String temp = toSort.get(i);
			int j = i - 1;
			while (j > -1 && comp.compare(temp, toSort.get(j)) < 0)
			{
				toSort.set(j+1, toSort.get(j));
				j--;
			}
			toSort.set(j+1, temp);
		}
	}
}
