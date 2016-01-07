package ir.test;

import ir.assignments.one.a.Utilities;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by keyvan on 1/6/16.
 */
public class UtilitiesTest extends TestCase {

	public void testTokenizeFile() throws Exception {
		String[] parts = "an input string this is or is it".split(" ");
		ArrayList<String> expected = new ArrayList<>(Arrays.asList(parts));
		ArrayList<String> actual = Utilities.tokenizeFile(Fixtures.get("a.txt"));
		assertEquals(actual, expected);
	}

	public void testPrintFrequencies() throws Exception {

	}
}
