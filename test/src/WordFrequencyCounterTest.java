package ir.test;
import junit.framework.TestCase;
import static ir.test.TestHelper.*;

import ir.assignments.one.b.WordFrequencyCounter;
import ir.assignments.one.a.Frequency;

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
		final String str = "top top cat bat rat cat rat bat last top";
		ArrayList<String> words = splitOnSpaces(str);
		List<Frequency> list = WordFrequencyCounter.computeWordFrequencies(words);
		ArrayList<Frequency> actual = new ArrayList<>(list);
		assertEquals(5, actual.size());
		assertEquals("top", actual.get(0).getText());
		assertEquals(3, actual.get(0).getFrequency());

		assertEquals("bat", actual.get(1).getText());
		assertEquals(2, actual.get(1).getFrequency());

		assertEquals("cat", actual.get(2).getText());
		assertEquals(2, actual.get(2).getFrequency());

		assertEquals("rat", actual.get(3).getText());
		assertEquals(2, actual.get(3).getFrequency());

		assertEquals("last", actual.get(4).getText());
		assertEquals(1, actual.get(4).getFrequency());
	}

	/**
	 * Tests that the original list is not modified
	 */
	public void testComputeWordFrequenciesExampleFour() throws Exception {
		final String str = "top top cat bat rat cat rat bat last top";
		ArrayList<String> words = splitOnSpaces(str);
		assertEquals(words, splitOnSpaces(str));
		WordFrequencyCounter.computeWordFrequencies(words);
		assertEquals(words, splitOnSpaces(str));
	}

	/**
	 * Tests that the following case is true.
	 *
	 * Given the input list of strings
	 * ["this", "sentence", "repeats", "the", "word", "sentence"]
	 *
	 * The output list of frequencies should be
	 * ["sentence:2", "repeats:1", "the:1", "this:1", "word:1"]
	 */
	public void testComputeWordFrequenciesExampleFive() throws Exception {
		final String str = "this sentence repeats the word sentence";
		ArrayList<String> words = splitOnSpaces(str);
		List<Frequency> list = WordFrequencyCounter.computeWordFrequencies(words);
		ArrayList<Frequency> actual = new ArrayList<>(list);
		assertEquals(5, actual.size());

		assertEquals("sentence", actual.get(0).getText());
		assertEquals(2, actual.get(0).getFrequency());

		assertEquals("repeats", actual.get(1).getText());
		assertEquals(1, actual.get(1).getFrequency());

		assertEquals("the", actual.get(2).getText());
		assertEquals(1, actual.get(2).getFrequency());

		assertEquals("this", actual.get(3).getText());
		assertEquals(1, actual.get(3).getFrequency());

		assertEquals("word", actual.get(4).getText());
		assertEquals(1, actual.get(4).getFrequency());
	}
}
