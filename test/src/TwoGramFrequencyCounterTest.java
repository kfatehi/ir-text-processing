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
}
