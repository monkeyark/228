package edu.iastate.cs228.hw2;


import java.util.Comparator;


/**
 * An implementation of {@link Sorter} that performs merge sort
 * to sort the list.
 * 
 */
public class MergeSorter extends Sorter
{
	/**
	 * Performs a recursive merge sort on the given array. This version
	 * requires only 50% additional memory.
	 */
	@Override
	public void sort(WordList toSort, Comparator<String> comp) throws NullPointerException
	{
		if (toSort == null || comp == null) throw new NullPointerException();
		
		mergeSortRec(toSort, comp, 0, toSort.length()-1);
	}
	
	/**
	 * 
	 * Performs a recursive merge sort of the elements {@link WordList}
	 * @param list
	 * @param comp
	 * @param start
	 * @param end
	 */
	private void mergeSortRec(WordList list, Comparator<String> comp, int start, int end)
	{
		if (start >= end) return;
		int mid = (start + end) / 2;
		mergeSortRec(list, comp, start, mid);
		mergeSortRec(list, comp, mid+1, end);
		merge(list, comp, start, end, mid);
	}
	
	/**
	 *  Merges two sorted subarrays of a given array, storing the result back in 
	 * the given array.
	 * @param list
	 * @param comp
	 * @param start
	 * @param end
	 * @param mid
	 */
	private void merge(WordList list, Comparator<String> comp, int start, int end, int mid)
	{
		// copy first half of WordList to a temporary array
		int firstLength = mid - start + 1;
		String[] firstWords = new String[firstLength];
		
		for (int i=0; i<firstLength; i++)
		{
			firstWords[i] = list.get(i+start);
		}
		WordList first = new WordList(firstWords);
		WordList second = list; //save 50% memory by reusing original WordList
		WordList result = list;	//result goes in original array WordList, save 50% memory
		
		int i = 0;
		int j = mid + 1;
		final int iMax = first.length();
		final int jMax = end + 1;
		int k = start;
		
		while (i < iMax && j < jMax)
		{
			if (comp.compare(first.get(i), second.get(j)) <= 0)
			{
				result.set(k, first.get(i));
				i++;
				k++;
			}
			else
			{
				result.set(k, second.get(j));
				j++;
				k++;
			}
		}
		
		// pick up the rest
		while (i < iMax)
		{
			result.set(k, first.get(i));
			i++;
			k++;
		}
		while (j < jMax)
		{
			result.set(k, second.get(j));
			j++;
			k++;
		}
	}
}
