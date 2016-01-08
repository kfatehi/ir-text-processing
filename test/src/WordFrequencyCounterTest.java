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
		assertEquals(actual.size(), 0);
	}

	/**
	 * Tests that there is one frequency in the output list for every 
	 * unique word in the original list. The frequency of each word
	 * is equal to the number of times that word occurs in the original list. 
	 */
	public void testComputeWordFrequenciesExampleTwo() throws Exception {
		final String[] parts = "an input string this is or is it".split(" ");
		final ArrayList<String> words = new ArrayList<>(Arrays.asList(parts));
		ArrayList<Frequency> actual = new ArrayList<>(WordFrequencyCounter.computeWordFrequencies(words));
		assertEquals(actual.size(), 7);
		assertEquals(actual.get(0).getText(), "is");
		assertEquals(actual.get(0).getFrequency(), 2);
	}
}
