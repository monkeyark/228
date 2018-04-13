package edu.iastate.cs228.hw2;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;

import org.junit.Test;

/**
 * JUnit Class that tests the sorting of WordLists. All sorters must be implemented as well as clone() for WordList
 * and getArray for WordList. For the sake of time I have excluded the 1000000 word lists in the tests. 
 * 
 *
 */
public class SorterTest {
	private Alphabet alpha_10;
	private Alphabet alpha_100;
	private Alphabet alpha_1000;
	private Alphabet alpha_10000;
//	private Alphabet alpha_100000;
//	private Alphabet alpha_1000000;
	
	private AlphabetComparator alphaC_10;
	private AlphabetComparator alphaC_100;
	private AlphabetComparator alphaC_1000;
	private AlphabetComparator alphaC_10000;
//	private AlphabetComparator alphaC_100000;
//	private AlphabetComparator alphaC_1000000;
	
	private WordList w_10;
	private WordList w_100;
	private WordList w_1000;
	private WordList w_10000;
//	private WordList w_100000;
//	private WordList w_1000000;
	
	private WordList w_10_sorted;
	private WordList w_100_sorted;
	private WordList w_1000_sorted;
	private WordList w_10000_sorted;
//	private WordList w_100000_sorted;
//	private WordList w_1000000_sorted;
	
	private MergeSorter mSort;
	private QuickSorter qSort;
	private InsertionSorter iSort;
	
	public SorterTest() throws NullPointerException, FileNotFoundException
	{
		alpha_10 = new Alphabet("10.alphabet.txt");
		alpha_100 = new Alphabet("100.alphabet.txt");
		alpha_1000 = new Alphabet("1000.alphabet.txt");
		alpha_10000 = new Alphabet("10000.alphabet.txt");
//		alpha_100000 = new Alphabet("100000.alphabet.txt");
//		alpha_1000000 = new Alphabet("1000000.alphabet.txt");
		
		alphaC_10 = new AlphabetComparator(alpha_10);
		alphaC_100 = new AlphabetComparator(alpha_100);
		alphaC_1000 = new AlphabetComparator(alpha_1000);
		alphaC_10000 = new AlphabetComparator(alpha_10000);
//		alphaC_100000 = new AlphabetComparator(alpha_100000);
//		alphaC_1000000 = new AlphabetComparator(alpha_1000000);
		
		w_10 = new WordList("10.wordlist.txt");
		w_100 = new WordList("100.wordlist.txt");
		w_1000 = new WordList("1000.wordlist.txt");
		w_10000 = new WordList("10000.wordlist.txt");
//		w_100000 = new WordList("100000.wordlist.txt");
//		w_1000000 = new WordList("1000000.wordlist.txt");
		
		w_10_sorted = new WordList("10.sortedlist.txt");
		w_100_sorted = new WordList("100.sortedlist.txt");
		w_1000_sorted = new WordList("1000.sortedlist.txt");
		w_10000_sorted = new WordList("10000.sortedlist.txt");
//		w_100000_sorted = new WordList("100000.sortedlist.txt");
//		w_1000000_sorted = new WordList("1000000.sortedlist.txt");
		
		mSort = new MergeSorter();
		qSort = new QuickSorter();
		iSort = new InsertionSorter();
		
	}
	/**
	 * Tests the three sorting algorithms with the provided WordList and size 10 alphabet.
	 */
	@Test
	public void SortTest10()
	{
		WordList w_10_1 = w_10.clone();
		mSort.sort(w_10_1, alphaC_10);
		assertTrue(arrayCompare(w_10_1.getArray(),w_10_sorted.getArray()));
		
		WordList w_10_2 = w_10.clone();
		qSort.sort(w_10_2, alphaC_10);
		assertTrue(arrayCompare(w_10_2.getArray(), w_10_sorted.getArray()));
		
		WordList w_10_3 = w_10.clone();
		iSort.sort(w_10_3, alphaC_10);
		assertTrue(arrayCompare(w_10_3.getArray(), w_10_sorted.getArray()));
	}
	/*
	 * Tests the three sorting algorithms with the provided WordList and size 100 alphabet.
	 */
	
	@Test
	public void SortTest100()
	{
		WordList w_100_1 = w_100.clone();
		mSort.sort(w_100_1, alphaC_100);
		assertTrue(arrayCompare(w_100_1.getArray(), w_100_sorted.getArray()));
		
		WordList w_100_2 = w_100.clone();
		qSort.sort(w_100_2, alphaC_100);
		assertTrue(arrayCompare(w_100_2.getArray(), w_100_sorted.getArray()));
		
		WordList w_100_3 = w_100.clone();
		iSort.sort(w_100_3, alphaC_100);
		assertTrue(arrayCompare(w_100_3.getArray(), w_100_sorted.getArray()));
	}
	/*
	 * Tests the three sorting algorithms with the provided WordList and size 1000 alphabet.
	 */
	@Test
	public void SortTest1000()
	{
		WordList w_1000_1 = w_1000.clone();
		mSort.sort(w_1000_1, alphaC_1000);
		assertTrue(arrayCompare(w_1000_1.getArray(), w_1000_sorted.getArray()));
		
		WordList w_1000_2 = w_1000.clone();
		qSort.sort(w_1000_2, alphaC_1000);
		assertTrue(arrayCompare(w_1000_2.getArray(), w_1000_sorted.getArray()));
		
		WordList w_1000_3 = w_1000.clone();
		iSort.sort(w_1000_3, alphaC_1000);
		assertTrue(arrayCompare(w_1000_3.getArray(), w_1000_sorted.getArray()));
	}
	@Test
	public void SortTest10000()
	{
		WordList w_10000_1 = w_10000.clone();
		mSort.sort(w_10000_1, alphaC_10000);
		assertTrue(arrayCompare(w_10000_1.getArray(), w_10000_sorted.getArray()));
		
		WordList w_10000_2 = w_10000.clone();
		qSort.sort(w_10000_2, alphaC_10000);
		assertTrue(arrayCompare(w_10000_2.getArray(), w_10000_sorted.getArray()));
		
		WordList w_10000_3 = w_10000.clone();
		iSort.sort(w_10000_3, alphaC_10000);
		assertTrue(arrayCompare(w_10000_3.getArray(), w_10000_sorted.getArray()));
	}
//	@Test
//	public void SortTest100000()
//	{
//		WordList w_100000_1 = w_100000.clone();
//		mSort.sort(w_100000_1, alphaC_100000);
//		assertTrue(arrayCompare(w_100000_1.getArray(), w_100000_sorted.getArray()));
//		
//		WordList w_100000_2 = w_100000.clone();
//		qSort.sort(w_100000_2, alphaC_100000);
//		assertTrue(arrayCompare(w_100000_2.getArray(), w_100000_sorted.getArray()));
//		
//		WordList w_100000_3 = w_100000.clone();
//		iSort.sort(w_100000_3, alphaC_100000);
//		assertTrue(arrayCompare(w_100000_3.getArray(), w_100000_sorted.getArray()));
//	}
//	@Test
//	public void SortTest1000000()
//	{
//		WordList w_1000000_1 = w_1000000.clone();
//		mSort.sort(w_1000000_1, alphaC_1000000);
//		assertTrue(arrayCompare(w_1000000_1.getArray(), w_1000000_sorted.getArray()));
//		
//		WordList w_1000000_2 = w_1000000.clone();
//		qSort.sort(w_1000000_2, alphaC_1000000);
//		assertTrue(arrayCompare(w_1000000_2.getArray(), w_1000000_sorted.getArray()));
//		
//		WordList w_1000000_3 = w_1000000.clone();
//		iSort.sort(w_1000000_3, alphaC_1000000);
//		assertTrue(arrayCompare(w_1000000_3.getArray(), w_1000000_sorted.getArray()));
//	}
	
	private boolean arrayCompare(String[] arr1, String[] arr2)
	{
		if(arr1.length!=arr2.length)
		{
			return false;
		}
		for(int i = 0; i<arr1.length; i++)
		{
			if(!arr1[i].equals(arr2[i]))
			{
				return false;
			}
		}
		return true;
	}

}