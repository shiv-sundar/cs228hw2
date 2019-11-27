package edu.iastate.cs228.hw2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

/**
 * A class representing an ordering of characters that can be queried to know
 * the position of a given character.
 *
 * @author Shivkarthi Sundar
 */
public class Alphabet {
	/**
	 * A lookup table containing characters and their positions.
	 * Sorted by the character of each entry.
	 */
	private CharAndPos[] lookup;

	/**
	 * Constructs and initializes the ordering to have exactly the ordering of
	 * the elements in the given array.
	 *
	 * @param ordering
	 *   the array containing the characters, in the ordering desired
	 * @throws NullPointerException
	 *   if {@code ordering} is {@code null}
	 */
	public Alphabet(char[] ordering) throws NullPointerException {
		lookup = new CharAndPos[ordering.length];
		for (int x = 0; x < ordering.length; x++) {
			lookup[x] = new CharAndPos(ordering[x], x);
		}

		CharAndPos[] temp = new CharAndPos[lookup.length];
		Arrays.sort(ordering);
		for (int x = 0; x < lookup.length; x++) {
			for (int y = 0; y < lookup.length; y++) {
				if (ordering[x] == lookup[y].character) {
					temp[x] = lookup[y];
				}
			}
		}

		lookup = temp;
	}

	/**
	 * Constructs and initializes the ordering by reading from the indicated
	 * file. The file is expected to have a single character on each line, and
	 * the ordering in the file is the order that will be used.
	 *
	 * @param filename
	 *   the name of the file to read
	 * @throws NullPointerException
	 *   if {@code filename} is {@code null}
	 * @throws FileNotFoundException
	 *   if the file cannot be found
	 */
	public Alphabet(String filename) throws NullPointerException, FileNotFoundException {
		File file = new File(filename);
		Scanner scan = new Scanner(file);
		int x = 0;
		ArrayList<Character> ALC = new ArrayList<Character>();
		ArrayList<CharAndPos> ALCAP = new ArrayList<CharAndPos>();
		scan.useDelimiter("(\\b|\\B)");
		while (scan.hasNext()) {
			char newChar = scan.next().toCharArray()[0];
			ALC.add(newChar);
			ALCAP.add(new CharAndPos(newChar, x));
			x += 1;
		}

		char[] newCharArray = new char[ALC.size()];
		for (int y = 0; y < newCharArray.length; y++) {
			newCharArray[y] = ALC.get(y);
		}

		lookup = new CharAndPos[ALCAP.size()];
		for (int i = 0; i < ALCAP.size(); i++) {
			lookup[i] = ALCAP.get(i);
		}

		CharAndPos[] temp = new CharAndPos[newCharArray.length];
		Arrays.sort(newCharArray);
		for (int z = 0; z < newCharArray.length; z++) {
			for (int y = 0; y < newCharArray.length; y++) {
				if (newCharArray[z] == lookup[y].character) {
					temp[z] = lookup[y];
				}
			}
		}

		scan.close();
		lookup = temp;
	}

	/**
	 * Returns true if and only if the given character is present in the
	 * ordering.
	 *
	 * @param c
	 *   the character to test
	 * @return
	 *   true if and only if the given character is present in the ordering
	 */
	public boolean isValid(char c) {
		for (int x = 0; x < lookup.length; x++) {
			if (c == lookup[x].character) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Returns the position of the given character in the ordering.
	 * Returns a negative value if the given character is not present in the
	 * ordering.
	 *
	 * @param c
	 *   the character of which the position will be determined
	 * @return
	 *   the position of the given character, or a negative value if the given
	 *   character is not present in the ordering
	 */
	public int getPosition(char c) {
		int x = binarySearch(c);
		if (x >= 0) {
			return lookup[x].position;
		}

		else {
			return -1;
		}
	}

	/**
	 * Returns the index of the entry containing the given character within the
	 * lookup table {@link #lookup}.
	 * Returns a negative value if the given character does not have an entry in
	 * the table.
	 *
	 * @param toFind
	 *   the character for which to search
	 * @return
	 *   the index of the entry containing the given character, or a negative
	 *   value if the given character does not have an entry in the table
	 */
	private int binarySearch(char toFind) {
		int left = 0;
		int right = lookup.length - 1;
		while (left <= right) {
			int mid = (right + left)/2;
			if (((Character) lookup[mid].character).compareTo(toFind) == 0) {
				return mid;
			}

			else if (((Character) lookup[mid].character).compareTo(toFind) > 0) {
				right = mid - 1;
			}

			else {
				left = mid + 1;
			}
		}

		return -1;
	}


	/**
	 * A PODT class containing a character and a position.
	 * Used as the entry type within {@link Alphabet#lookup lookup}.
	 */
	private static class CharAndPos {
		/**
		 * The character of the entry.
		 */
		public char character;

		/**
		 * The position of the entry in the ordering.
		 */
		public int position;

		/**
		 * Constructs and initializes the entry with the given values.
		 *
		 * @param character
		 *   the character of the entry
		 * @param position
		 *   the position of the entry
		 */
		public CharAndPos(char character, int position) {
			this.character = character;
			this.position = position;
		}


		@Override
		public boolean equals(Object obj) {
			if (null == obj || this.getClass() != obj.getClass()) {
				return false;
			}

			CharAndPos o = (CharAndPos) obj;
			return this.character == o.character && this.position == o.position;
		}

		@Override
		public int hashCode() {
			return character ^ position;
		}

		@Override
		public String toString() {
			return "{" + character + ", " + position + "}";
		}
	}
}
