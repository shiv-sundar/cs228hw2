package edu.iastate.cs228.hw2;

import java.util.Comparator;

/**
 * A string comparator that uses an ordering of an {@link Alphabet} to
 * determine how to compare individual characters.
 *
 * @author Shivkarthi Sundar
 */
public class AlphabetComparator implements Comparator<String> {
	/**
	 * The ordering used to compare characters.
	 */
	private Alphabet alphabet;

	/**
	 * Constructs and initializes the comparator to use the given ordering.
	 *
	 * @param ordering
	 *   the ordering to use to compare characters
	 * @throws NullPointerException
	 *   if {@code ordering} is {@code null}
	 */
	public AlphabetComparator(Alphabet ordering) throws NullPointerException {
		alphabet = ordering;
	}

	/**
	 * Compares the two given strings based on the ordering used by this
	 * comparator.
	 *
	 * Returns a negative value if the first string is considered less than the
	 * second, a positive value if greater than the second, and zero if the two
	 * strings are equal. Note that an exception must be thrown if the strings
	 * contain invalid characters, even if the two strings are equal.
	 *
	 * For each character of the given strings, the ordering is consulted to
	 * determine which of the two characters should go first, with the one with a
	 * lesser position in the ordering being determined to be lesser. If the
	 * position of the characters are the same, the next character is examined.
	 * After the end of one of the strings is reached, the shorter string is
	 * considered to be lesser than the other.
	 *
	 * @throws NullPointerException
	 *   if either of {@code a} or {@code b} are {@code null}
	 * @throws IllegalArgumentException
	 *   if either of {@code a} or {@code b} contain a character not found in
	 *   this comparator's ordering
	 */
	@Override
	public int compare(String a, String b) throws NullPointerException,	IllegalArgumentException {
		for (int x = 0; x < Math.min(a.length(), b.length()); x++) {
			if (alphabet.getPosition(a.charAt(x)) == -1 || alphabet.getPosition(b.charAt(x)) == -1) {
				if (alphabet.getPosition(a.charAt(x)) == -1) {
					throw new IllegalArgumentException("The character " + a.charAt(x) + " is not in the alphabet");
				}
				
				else {
					throw new IllegalArgumentException("The character " + b.charAt(x) + " is not in the alphabet");
				}
			}
			
			if (alphabet.getPosition(a.charAt(x)) < alphabet.getPosition(b.charAt(x))) {
				return -1;
			}
			
			else if (alphabet.getPosition(a.charAt(x)) > alphabet.getPosition(b.charAt(x))) {
				return 1;
			}
		}
		
		if (a.length() < b.length()) {
			return -1;
		}
		
		if (a.length() > b.length()) {
			return 1;
		}
		
		return 0;
	}
}