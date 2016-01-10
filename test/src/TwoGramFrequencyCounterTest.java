package ir.test;
import junit.framework.TestCase;
import static ir.test.TestHelper.*;

import ir.assignments.one.c.TwoGramFrequencyCounter;
import ir.assignments.one.a.Frequency;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 * Tests Word Frequency Counter
 */
public class TwoGramFrequencyCounterTest extends TestCase {

	/**
	 * Tests that if the input list is null, an empty list is returned
	 */
	public void testComputeTwoGramFrequenciesExampleOne() throws Exception {
		final ArrayList<String> words = null;
		final List<Frequency> actual = TwoGramFrequencyCounter.computeTwoGramFrequencies(words);
		assertNotNull("should not be null", actual);
		assertEquals(0, actual.size());
	}

	/**
	 * Tests that it returns an empty array if input contains only one word
	 */
	public void testComputeTwoGramFrequenciesExampleTwo() throws Exception {
		final String str = "hello";
		final ArrayList<String> words = splitOnSpaces(str);
		final List<Frequency> list = TwoGramFrequencyCounter.computeTwoGramFrequencies(words);
		final ArrayList<Frequency> actual = new ArrayList<>(list);
		assertEquals(0, actual.size());
	}

	/**
	 *
	 * Tests that there is one frequency in the output list for every 
	 * unique 2-gram in the original list. The frequency of each 2-gram
	 * is equal to the number of times that two-gram occurs in the original list. 
	 * 
	 * The returned list is ordered by decreasing frequency, with tied 2-grams sorted
	 * alphabetically. 
	 *
	 * Example:
	 *
	 * Given the input list of strings 
	 * ["you", "think", "you", "know", "how", "you", "think"]
	 * 
	 * The output list of 2-gram frequencies should be 
	 * ["you think:2", "how you:1", "know how:1", "think you:1", "you know:1"]
	 *
	 */
	public void testComputeTwoGramFrequenciesExampleThree() throws Exception {
		final String str = "you think you know how you think";
		final ArrayList<String> words = splitOnSpaces(str);
		final List<Frequency> list = TwoGramFrequencyCounter.computeTwoGramFrequencies(words);
		final ArrayList<Frequency> actual = new ArrayList<>(list);
		assertEquals(5, actual.size());

		assertEquals("you think", actual.get(0).getText());
		assertEquals(2, actual.get(0).getFrequency());

		assertEquals("how you", actual.get(1).getText());
		assertEquals(1, actual.get(1).getFrequency());

		assertEquals("know how", actual.get(2).getText());
		assertEquals(1, actual.get(2).getFrequency());

		assertEquals("think you", actual.get(3).getText());
		assertEquals(1, actual.get(3).getFrequency());

		assertEquals("you know", actual.get(4).getText());
		assertEquals(1, actual.get(4).getFrequency());
	}

	/**
	 * Tests that it works for a simple two word input
	 */
	public void testComputeTwoGramFrequenciesExampleFour() throws Exception {
		final String str = "hello world";
		final ArrayList<String> words = splitOnSpaces(str);
		final List<Frequency> list = TwoGramFrequencyCounter.computeTwoGramFrequencies(words);
		final ArrayList<Frequency> actual = new ArrayList<>(list);
		assertEquals(1, actual.size());
		assertEquals("hello world", actual.get(0).getText());
		assertEquals(1, actual.get(0).getFrequency());
	}

	/**
	 * Tests that it works for a three word input
	 */
	public void testComputeTwoGramFrequenciesExampleFive() throws Exception {
		final String str = "hello world goodbye";
		final ArrayList<String> words = splitOnSpaces(str);
		final List<Frequency> list = TwoGramFrequencyCounter.computeTwoGramFrequencies(words);
		final ArrayList<Frequency> actual = new ArrayList<>(list);
		assertEquals(2, actual.size());
		assertEquals("hello world", actual.get(0).getText());
		assertEquals(1, actual.get(0).getFrequency());
		assertEquals("world goodbye", actual.get(1).getText());
		assertEquals(1, actual.get(1).getFrequency());
	}
}
