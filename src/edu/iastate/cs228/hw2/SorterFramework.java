package edu.iastate.cs228.hw2;

import java.io.*;
import java.util.Comparator;

/**
 * An class that compares various methods of sorting.
 *
 * @author Shivkarthi Sundar
 */
public class SorterFramework {
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
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Alphabet alphabet;
		AlphabetComparator comparator;
		WordList words;
		Sorter[] sorters;
		alphabet = new Alphabet(args[0]);
		comparator = new AlphabetComparator(alphabet);
		words = new WordList(args[1]);
		sorters = new Sorter[3];
		sorters[0] = new MergeSorter();
		sorters[1] = new QuickSorter();
		sorters[2] = new InsertionSorter();
		SorterFramework toRun = new SorterFramework(sorters, comparator, words, 1000000);
		toRun.run();
	}

	/**
	 * The comparator to use for sorting.
	 */
	private Comparator<String> comparator;

	/**
	 * The words to sort.
	 */
	private WordList words;

	/**
	 * The array of sorters to use for sorting.
	 */
	private Sorter[] sorters;

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
	public SorterFramework(Sorter[] sorters, Comparator<String> comparator, WordList words, int totalToSort) throws NullPointerException, IllegalArgumentException {
		this.totalToSort = totalToSort;
		this.sorters = sorters;
		this.comparator = comparator;
		this.words = words;
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
	 */
	public void run() throws FileNotFoundException {
		
		for (Sorter sorter: sorters) {
			sorter.sortWithStatistics(words, comparator, totalToSort);
			System.out.println("Sorter Name: " + sorter.getName());
			System.out.println("WordList Length: " + words.length() + " words");
			System.out.println("Total Words Sorted: " + sorter.getTotalWordsSorted() + " words");
			System.out.println("Sorting Time: " + (long) sorter.getTotalSortingTime() + " milliseconds");
			System.out.println("Average Time to Sort WordList: " + sorter.getTotalSortingTime()/words.length() + " milliseconds");
			System.out.println("Elements Sorted per Second: " + ((long) sorter.getTotalWordsSorted())/(sorter.getTotalSortingTime()) + " elements per millisecond");
			System.out.println("Total Comparisons Performed: " + (long) sorter.getTotalComparisons() + " comparisons");
			System.out.println();
		}
	}
}
