package edu.iastate.cs228.hw2;

import java.util.Comparator;

/**
 * An implementation of {@link Sorter} that performs insertion sort
 * to sort the list.
 *
 * @author Shivkarthi Sundar
 */
public class InsertionSorter extends Sorter {
	public InsertionSorter() {
		super();
	}
	
 	@Override
	public void sort(WordList toSort, Comparator<String> comp) throws NullPointerException {
		int n = toSort.length();
        for (int x = 1; x < n; x++) {
        	int y = x - 1;
            String temp1 = toSort.get(x);
            while (y > -1 && comp.compare(toSort.get(y), temp1) > 0) {
            	toSort.set(y + 1, toSort.get(y));
                y--;
            }
            
            toSort.set(y + 1, temp1);
        }
	}
}
