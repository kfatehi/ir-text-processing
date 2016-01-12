package ir.test;

import ir.assignments.two.a.Utilities;
import ir.assignments.two.a.Frequency;

import static ir.test.TestHelper.*;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.ByteArrayOutputStream;

/**
 * Created by keyvan on 1/6/16.
 */
public class UtilitiesTest extends TestCase {

	/**
	 * Tests that our fixture file is correctly tokenized as a lowercase list of words.
	 */
	public void testTokenizeFileExampleOne() throws Exception {
		final ArrayList<String> expected = splitOnSpaces("an input string this is or is it");
		final ArrayList<String> actual = Utilities.tokenizeFile(Fixtures.get("a.txt"));
		assertEquals(expected, actual);
	}

	/**
	 * Tests that a more aggressive fixture is tokenized correctly
	 */
	public void testTokenizeFileExampleTwo() throws Exception {
		final ArrayList<String> expected = splitOnSpaces("t2 ti double f spaceez02 35 andnumbers");
		final ArrayList<String> actual = Utilities.tokenizeFile(Fixtures.get("aggressive.txt"));
		assertEquals(expected, actual);
	}

	/**
	 * Tests that frequencies are printed correctly in a tabulated fashion
	 */
	public void testPrintFrequenciesExampleOne() throws Exception {
		ArrayList<Frequency> freqs = new ArrayList<>();
		freqs.add(new Frequency("sentence", 2));
		freqs.add(new Frequency("the", 1));
		freqs.add(new Frequency("this", 1));
		freqs.add(new Frequency("repeats", 1));
		freqs.add(new Frequency("word", 1));
		final String expected =
			"Total item count: 6\n"
			+"Unique item count: 5\n"
			+"\n"
			+"sentence    2\n"
			+"the         1\n"
			+"this        1\n"
			+"repeats     1\n"
			+"word        1\n";
		captureOutput( new CaptureTest() {
			@Override
			public void test(ByteArrayOutputStream outContent, ByteArrayOutputStream errContent) throws Exception {
				Utilities.printFrequencies(freqs);
				String actual = outContent.toString();
				assertEquals(expected, actual);
			}
		});
	}

	/**
	 * Tests that 2-gram frequencies are printed correctly in a tabulated fashion
	 */
	public void testPrintFrequenciesExampleTwo() throws Exception {
		ArrayList<Frequency> freqs = new ArrayList<>();
		freqs.add(new Frequency("you think", 2));
		freqs.add(new Frequency("how you", 1));
		freqs.add(new Frequency("know how", 1));
		freqs.add(new Frequency("think you", 1));
		freqs.add(new Frequency("you know", 1));
		final String expected =
			"Total 2-gram count: 6\n"
			+"Unique 2-gram count: 5\n"
			+"\n"
			+"you think    2\n"
			+"how you      1\n"
			+"know how     1\n"
			+"think you    1\n"
			+"you know     1\n";
		captureOutput( new CaptureTest() {
			@Override
			public void test(ByteArrayOutputStream outContent, ByteArrayOutputStream errContent) throws Exception {
				Utilities.printFrequencies(freqs);
				String actual = outContent.toString();
				assertEquals(expected, actual);
			}
		});
	}
}
