package ir.test;

import ir.assignments.one.b.WordFrequencyCounter;
import ir.assignments.one.a.Frequency;

import static ir.test.TestHelper.*;

import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 * Tests Word Frequency Counter
 */
public class WordFrequencyCounterTest extends TestCase {

	/**
	 * Tests that if the input list is null, an empty list is returned
	 */
	public void testComputeWordFrequenciesExampleOne() throws Exception {
		final ArrayList<String> words = null;
		final List<Frequency> actual = WordFrequencyCounter.computeWordFrequencies(words);
		assertNotNull("should not be null", actual);
		assertEquals(0, actual.size());
	}

	/**
	 * Tests that there is one frequency in the output list for every 
	 * unique word in the original list and that the frequency of each word
	 * is equal to the number of times that word occurs in the original list. 
	 */
	public void testComputeWordFrequenciesExampleTwo() throws Exception {
		ArrayList<String> words = splitOnSpaces("an input string this is or is it");
		List<Frequency> list = WordFrequencyCounter.computeWordFrequencies(words);
		ArrayList<Frequency> actual = new ArrayList<>(list);
		assertEquals(7, actual.size());
		assertEquals("is", actual.get(0).getText());
		assertEquals(2, actual.get(0).getFrequency());
	}

	/**
	 * Tests that the returned list is ordered by decreasing frequency and that
	 * tied words are sorted alphabetically.
	 */
	public void testComputeWordFrequenciesExampleThree() throws Exception {
		ArrayList<String> words = splitOnSpaces("top top top cat bat rat cat rat bat last");
		List<Frequency> list = WordFrequencyCounter.computeWordFrequencies(words);
		ArrayList<Frequency> actual = new ArrayList<>(WordFrequencyCounter.computeWordFrequencies(words));
		assertEquals(7, actual.size());
		assertEquals(actual.get(0).getText(), "is");
		assertEquals(actual.get(0).getFrequency(), 2);
	}
}
