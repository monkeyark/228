package edu.iastate.cs228.hw2;


import java.util.Comparator;

/**
 * An implementation of {@link Sorter} that performs quick sort
 * to sort the list.
 * 
 */
public class QuickSorter extends Sorter
{
	@Override
	public void sort(WordList toSort, Comparator<String> comp) throws NullPointerException
	{
		if (toSort == null || comp == null) throw new NullPointerException();
		
		quickSortRec(toSort, comp, 0, toSort.length()-1);
	}
	
	private void quickSortRec(WordList list, Comparator<String> comp, int start, int end)
	{
		if (start >= end) return;
		int pivot = partition(list, comp, start, end);
		quickSortRec(list, comp, start, pivot-1);
		quickSortRec(list, comp, pivot+1, end);
	}
	
	private	int	partition(WordList list, Comparator<String> comp, int start, int end)
	{
		String pivot = list.get(end);
		int i = start - 1;
		for(int j=start; j<end; j++)
		{
			if (comp.compare(list.get(j), pivot) <= 0)
			{
				i++;
				list.swap(i, j);
			}
		}
		list.swap(i+1, end);
		return i+1;
	}
}
