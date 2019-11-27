package edu.iastate.cs228.hw2;


import java.util.Comparator;


/**
 * An implementation of {@link Sorter} that performs quick sort
 * to sort the list.
 *
 * @author Shivkarthi Sundar
 */
public class QuickSorter extends Sorter {
	public QuickSorter() {
		super();
	}

	@Override
	public void sort(WordList toSort, Comparator<String> comp) throws NullPointerException {
		quickSortRec(toSort, comp, 0, toSort.length() - 1);
	}

	/**
	 * recursive portion of the quicksort algorithm
	 * @param list
	 * 	WordList to sort
	 * @param comp
	 * 	comparator used to compare wordlist
	 * @param start
	 * 	index to start sorting at
	 * @param end
	 * 	index to stop sorting at
	 */
	private void quickSortRec(WordList list, Comparator<String> comp, int start, int end) {
		if (start < end) {
			int x = partition(list, comp, start, end);
			quickSortRec(list, comp, start, x - 1);
			quickSortRec(list, comp, x + 1, end);
		}
	}

	/**
	 * partition method of quicksort algorithm
	 * @param list
	 * 	WordList to partition
	 * @param comp
	 * 	comparator to use to compare elements
	 * @param start
	 * 	index to start partitioning at
	 * @param end
	 * 	index to stop partitioning at
	 * @return
	 *  index of partition element
	 */
	private int partition(WordList list, Comparator<String> comp, int start, int end) {
		String pivot = list.get(end); 
		int i = start - 1;
		for (int j = start; j < end; j++) {
			if (comp.compare(list.get(j), pivot) <= 0) {
				i++;
				list.swap(i, j);
			}
		}

		list.swap(i + 1, end);
		return i+1;
	}
}