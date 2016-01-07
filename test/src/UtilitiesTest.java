package ir.test;

import ir.assignments.one.a.Utilities;
import ir.assignments.one.a.Frequency;

import static ir.test.TestHelper.*;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.ByteArrayOutputStream;

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
		captureOutput( new CaptureTest() {
			@Override
			public void test(ByteArrayOutputStream outContent, ByteArrayOutputStream errContent) throws Exception {

				ArrayList<Frequency> frequencies = new ArrayList<>();
				Utilities.printFrequencies(frequencies);

				assertEquals( "aha\n", outContent.toString() );
			}
		});
	}
}
