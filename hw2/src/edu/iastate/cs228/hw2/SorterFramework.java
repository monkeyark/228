package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.util.Comparator;
	
/**
 * An class that compares various methods of sorting.
 * 
 */
public class SorterFramework
{
	/**
	 * Loads data necessary to run the sorter statistics output, and runs it.
	 * The only logic within this method should be that necessary to use the
	 * given file names to create the {@link AlphabetComparator},
	 * {@link WordList}, and sorters to use, and then using them to run the
	 * sorter statistics output.
	 * 
	 * @param args
	 *   an array expected to contain two arguments:
	 *    - the name of a file containing the ordering to use to compare
	 *      characters
	 *    - the name of a file containing words containing only characters in the
	 *      other file
	 * @throws FileNotFoundException 
	 * @throws NullPointerException 
	 */
	public static void main(String[] args) throws NullPointerException, FileNotFoundException
	{
		// check arguments
		Alphabet alphabet = new Alphabet(args[0]);
		AlphabetComparator comparator = new AlphabetComparator(alphabet);
		WordList words = new WordList(args[1]);
		Sorter[] sorters = new Sorter[3];
		sorters[0] = new InsertionSorter();
		sorters[1] = new MergeSorter();
		sorters[2] = new QuickSorter();
		
		// create appropriate values
		SorterFramework toRun = new SorterFramework(sorters, comparator, words, Math.max(ONE_MILLION, words.length()));
		toRun.run();
	}
	
	/**
	 * one million sort
	 */
	private static final int ONE_MILLION = 1000000;
	
	/**
	 * The comparator to use for sorting.
	 */
	private Comparator<String> comparator;
	
	/**
	 * The words to sort.
	 */
	private	WordList words;
	
	/**
	 * The array of sorters to use for sorting.
	 */
	private	Sorter[] sorters;
	
	/**
	 * The total amount of words expected to be sorted by each sorter.
	 */
	private int totalToSort;
	
	/**
	 * Constructs and initializes the SorterFramework.
	 * 
	 * @param sorters
	 *   the array of sorters to use for sorting
	 * @param comparator
	 *   the comparator to use for sorting
	 * @param words
	 *   the words to sort
	 * @param totalToSort
	 *   the total amount of words expected to be sorted by each sorter
	 * @throws NullPointerException
	 *   if any of {@code sorters}, {@code comparator}, {@code words}, or
	 *   elements of {@code sorters} are {@code null}
	 * @throws IllegalArgumentException
	 *   if {@code totalToSort} is negative
	 */
	public SorterFramework(Sorter[] sorters, Comparator<String> comparator, WordList words, int totalToSort)
			throws NullPointerException, IllegalArgumentException
	{
		if (sorters == null || comparator == null || words == null) throw new NullPointerException();
		for (int i=0; i<sorters.length; i++)
		{
			if (sorters[i] == null)
			{
				throw new NullPointerException();
			}
		}
		if (totalToSort < 0) throw new IllegalArgumentException();
		
		this.comparator = comparator;
		this.words = words;
		this.totalToSort = totalToSort;
		this.sorters = sorters;
	}
	
	/**
	 * Runs all sorters using
	 * {@link Sorter#sortWithStatistics(WordList, Comparator, int)
	 * sortWithStatistics()}, and then outputs the following information for each
	 * sorter:
	 *  - the name of the sorter
	 *  - the length of the word list sorted each time
	 *  - the total number of words sorted
	 *  - the total time used to sort words
	 *  - the average time to sort the word list
	 *  - the number of elements sorted per second
	 *  - the total number of comparisons performed
	 * @throws FileNotFoundException 
	 * @throws IllegalArgumentException 
	 * @throws NullPointerException 
	 */
	public void	run() throws NullPointerException, IllegalArgumentException, FileNotFoundException
	{
		for (int i=0; i<sorters.length; i++)
		{
			sorters[i].sortWithStatistics(words, comparator, totalToSort);
			
			System.out.println("name of the sorter: " + sorters[i].getName());
			System.out.println("length of the word list sorted each time: " + Math.min(totalToSort, words.length()));
			System.out.println("total number of words sorted: " + sorters[i].getTotalWordsSorted());
			System.out.println("total time used to sort words: " + sorters[i].getTotalSortingTime());
			System.out.println();
			
			if(sorters[i].getTotalSortingTime() == 0) // when sorting size is too small, the time is close to 0, and will throw exception
			{
				System.out.println("average time to sort the word list: " + 0.0);
				System.out.println("number of elements sorted per second: " + 0.0);
				System.out.println();
			}
			else
			{
				System.out.println("average time to sort the word list: " + sorters[i].getTotalSortingTime() / ((double) sorters[i].getTotalWordsSorted() / Math.min(totalToSort, words.length())));
				System.out.println("number of elements sorted per second: " + sorters[i].getTotalWordsSorted() / (sorters[i].getTotalSortingTime()/1000.0));
				System.out.println();
			}
			System.out.println("the total number of comparisons performed: " + sorters[i].getTotalComparisons());
			System.out.println();
			System.out.println("===========================================");
			System.out.println();
		}
	}		
}
