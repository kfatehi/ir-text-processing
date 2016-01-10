package ir.test;
import junit.framework.TestCase;
import static ir.test.TestHelper.*;

import ir.assignments.one.d.PalindromeFrequencyCounter;
import ir.assignments.one.a.Frequency;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 * Tests Palindrome Frequency Counter
 */
public class PalindromeFrequencyCounterTest extends TestCase {

	/**
	 * Tests that if the input list is null, an empty list is returned
	 */
	public void testComputePalindromeFrequenciesExampleOne() throws Exception {
		final ArrayList<String> words = null;
		final List<Frequency> actual = PalindromeFrequencyCounter.computePalindromeFrequencies(words);
		assertNotNull("should not be null", actual);
		assertEquals(0, actual.size());
	}

	/**
	 * Tests that there is one frequency in the output list for every 
	 * unique palindrome in the original list. The frequency of each palindrome
	 * is equal to the number of times that palindrome occurs in the original list. 
	 * 
	 * The returned list is ordered by decreasing size, with tied palindromes sorted
	 * by frequency and further tied palindromes sorted alphabetically. 
	 *
	 * Example:
	 * 
	 * Given the input list of strings 
	 * ["do", "geese", "see", "god", "abba", "bat", "tab"]
	 * 
	 * The output list of palindromes should be 
	 * ["do geese see god:1", "bat tab:1", "abba:1"]
	 */
	public void testComputePalindromeFrequenciesExampleThree() throws Exception {
		final String str = "do geese see god abba bat tab";
		final ArrayList<String> words = splitOnSpaces(str);
		final List<Frequency> list = PalindromeFrequencyCounter.computePalindromeFrequencies(words);
		final ArrayList<Frequency> actual = new ArrayList<>(list);
		assertEquals(3, actual.size());

		assertEquals("do geese see god", actual.get(0).getText());
		assertEquals(1, actual.get(0).getFrequency());

		assertEquals("bat tab", actual.get(1).getText());
		assertEquals(1, actual.get(1).getFrequency());

		assertEquals("abba", actual.get(2).getText());
		assertEquals(1, actual.get(2).getFrequency());
	}
}
