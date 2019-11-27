package edu.iastate.cs228.hw2;

import java.util.Comparator;

/**
 * An implementation of {@link Sorter} that performs merge sort
 * to sort the list.
 *
 * @author Shivkarthi Sundar
 */
public class MergeSorter extends Sorter {
	public MergeSorter() {
		super();
	}
	
	@Override
	public void sort(WordList toSort, Comparator<String> comp) throws NullPointerException {
		mergeSortRec(toSort, comp, 0, toSort.length() - 1);
	}

	/**
	 * Recursively starts the merge algorithm to sort the given WordList 
	 * @param list
	 * 	the word list to begin sorting
	 * @param comp
	 * 	the comparator to compare letters
	 * @param start
	 * 	which index of the WordList to start sorting from
	 * @param end
	 * 	which index of the WordList to finish sorting at
	 */
	private void mergeSortRec(WordList list, Comparator<String> comp, int start, int end) {
		if (start < end) {
			int m = (start + end)/2;
			mergeSortRec(list, comp, start, m);
			mergeSortRec(list, comp, m + 1, end);
			merge(list, comp, start, m, end);
		}
	}

	/**
	 * merges the two arrays passed in, separated by start, middle, and end
	 * @param list
	 * 	WordList to be sorted
	 * @param comp
	 * 	comparator to use so the strings can be sorted
	 * @param start
	 * 	the index to start sorting from
	 * @param middle
	 * 	the index to use as the middle
	 * @param end
	 * 	the index to finish sorting at
	 */
	private void merge(WordList list, Comparator<String> comp, int start, int middle, int end) {
		int n1 = middle - start + 1;
		int n2 = end - middle;
		String[] list1 = new String[n1];
		String[] list2 = new String[n2];
		for (int i = 0; i < n1; i++) {
			list1[i] = list.get(start + i);
		}

		for (int j = 0; j < n2; j++) {
			list2[j] = list.get(middle + 1 + j);
		}

		int i = 0;
		int j = 0;
		int k = start;
		while (i < n1 && j < n2)
		{
			if (comp.compare(list1[i], list2[j]) <= 0) {
				list.set(k, list1[i]);
				i++;
			}

			else {
				list.set(k, list2[j]);
				j++;
			}

			k++;
		}

		while (i < n1) {
			list.set(k, list1[i]);
			i++;
			k++;
		}

		while (j < n2) {
			list.set(k, list2[j]);
			j++;
			k++;
		}
	}
}