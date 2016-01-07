package ir.assignments.one.a;

import junit.framework.TestCase;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by keyvan on 1/6/16.
 */
public class UtilitiesTest extends TestCase {

	public void testTokenizeFile() throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("test/fixtures/a.txt").getFile());
		ArrayList<String> res = Utilities.tokenizeFile(file);
		ArrayList<String> expected = new ArrayList<>();
		expected.add("an");
		expected.add("input");
		expected.add("string");
		expected.add("this");
		expected.add("is");
		expected.add("or");
		expected.add("is");
		expected.add("it");
		assertEquals(res, expected);
	}

	public void testPrintFrequencies() throws Exception {

	}
}
