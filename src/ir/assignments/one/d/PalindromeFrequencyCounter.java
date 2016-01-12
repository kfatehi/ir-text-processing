package ir.assignments.one.d;

import ir.assignments.one.a.Frequency;
import ir.assignments.one.a.Utilities;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;

import java.util.Arrays;

public class PalindromeFrequencyCounter {
	/**
	 * This class should not be instantiated.
	 */
	private PalindromeFrequencyCounter() {}
	
	/**
	 * Takes the input list of words and processes it, returning a list
	 * of {@link Frequency}s.
	 * 
	 * This method expects a list of lowercase alphanumeric strings.
	 * If the input list is null, an empty list is returned.
	 * 
	 * There is one frequency in the output list for every 
	 * unique palindrome found in the original list. The frequency of each palindrome
	 * is equal to the number of times that palindrome occurs in the original list.
	 * 
	 * Palindromes can span sequential words in the input list.
	 * 
	 * The returned list is ordered by decreasing size, with tied palindromes sorted
	 * by frequency and further tied palindromes sorted alphabetically. 
	 * 
	 * The original list is not modified.
	 * 
	 * Example:
	 * 
	 * Given the input list of strings 
	 * ["do", "geese", "see", "god", "abba", "bat", "tab"]
	 * 
	 * The output list of palindromes should be 
	 * ["do geese see god:1", "bat tab:1", "abba:1"]
	 *  
	 * @param words A list of words.
	 * @return A list of palindrome frequencies, ordered by decreasing frequency.
	 */
	public static List<Frequency> computePalindromeFrequencies(ArrayList<String> words) {
		if (words != null) {
			HashMap<String,Frequency> map = new HashMap<>();

			for (int i=0; i <= words.size(); i++) {
				final String palindrome = findPalindrome(i, words);
				if (palindrome != null)  {
					Frequency freq = map.getOrDefault(palindrome, new Frequency(palindrome));
					freq.incrementFrequency();
					map.put(palindrome, freq);
				}
			}

			ArrayList<Frequency> freqs = new ArrayList<>(map.values());
			Collections.sort(freqs, Utilities.palindromeFrequencyComparator);
			return freqs;
		} else {
			return new ArrayList<>();
		}
	}

	/**
	 * Scans the word list up to {@param end} in search of a palindrome.
	 *
	 * @param end The index to which to scan
	 * @param words The list of words to scan
	 * @return the palindrome, if found, otherwise null
	 */
	private static String findPalindrome(final int i, final List<String> words) {
		for (int k=0; k < i; k++) {
			String possiblePalindrome = isPalindrome(words.subList(k, i));
			if (possiblePalindrome != null)
				return possiblePalindrome;
		}
		return null;
	}

	/**
	 * Determines if a list of strings is a palindrome.
	 *
	 * @param strings a list of strings
	 * @return the string if it's a palindrome, otherwise null
	 */
	private static String isPalindrome(List<String> strings) {
		String str = "";
		for (String substr : strings) {
			if (str.length() > 0)
				str+= " ";
			str += substr;
		}
		return isPalindrome(str);
	}

	/**
	 * Determines if a string is a palindrome.
	 *
	 * @param string Any string to test
	 * @return the string if it's a palindrome, otherwise null
	 */
	private static String isPalindrome(String string) {
		String normal = string.replaceAll(" ", "");
		if (string.length() > 0 && reverseString(normal).equals(normal))
			return string;
		else
			return null;
	}

	/**
	 * Produces a reversed version of the input string
	 *
	 * @param string the string you want to reverse
	 * @return the reversed version of the input string
	 */
	private static String reverseString(String string) {
		return new StringBuilder(string).reverse().toString();
	}

	/**
	 * Runs the palindrome counter. The input should be the path to a text file.
	 * 
	 * @param args The first element should contain the path to a text file.
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.err.println("Error: Expected input the path to a text file.");
		    System.exit(1);
		}
		File file = new File(args[0]);
		ArrayList<String> words = Utilities.tokenizeFile(file);
		List<Frequency> frequencies = computePalindromeFrequencies(words);
		Utilities.printFrequencies(frequencies);
	}
}
