package ir.assignments.one.a;

import junit.framework.TestCase;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by keyvan on 1/6/16.
 */
public class UtilitiesTest extends TestCase {

	public void testTokenizeFile() throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("test/fixtures/a.txt").getFile());
		String[] parts = "an input string this is or is it".split(" ");
		ArrayList<String> expected = new ArrayList<>(Arrays.asList(parts));
		ArrayList<String> actual = Utilities.tokenizeFile(file);
		assertEquals(actual, expected);
	}

	public void testPrintFrequencies() throws Exception {

	}
}
