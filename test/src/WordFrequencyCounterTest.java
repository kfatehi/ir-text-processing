package ir.test;

import ir.assignments.one.b.WordFrequencyCounter;
import ir.assignments.one.a.Frequency;

import static ir.test.TestHelper.*;

import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.List;

/**
 * Tests Word Frequency Counter
 */
public class WordFrequencyCounterTest extends TestCase {

	/**
	 * Tests that if the input list is null, an empty list is returned
	 */
	public void testComputeWordFrequenciesExampleOne() throws Exception {
		List<String> words = null;
		List<Frequency> actual = WordFrequencyCounter.computeWordFrequencies(words);
		assertNotNull("should not be null", actual);
		assertEquals(actual.size(), 0);
	}
}
