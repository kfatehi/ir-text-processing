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
		final String[] parts = "an input string this is or is it".split(" ");
		final ArrayList<String> expected = new ArrayList<>(Arrays.asList(parts));
		final ArrayList<String> actual = Utilities.tokenizeFile(Fixtures.get("a.txt"));
		assertEquals(expected, actual);
	}

	public void testPrintFrequenciesExampleOne() throws Exception {
		ArrayList<Frequency> freqs = new ArrayList<>();
		freqs.add(new Frequency("sentence", 2));
		freqs.add(new Frequency("the", 1));
		freqs.add(new Frequency("this", 1));
		freqs.add(new Frequency("repeats", 1));
		freqs.add(new Frequency("word", 1));
		final String expected =
			"sentence            2\n"
			+"the                 1\n"
			+"this                1\n"
			+"repeats             1\n"
			+"word                1\n";
		captureOutput( new CaptureTest() {
			@Override
			public void test(ByteArrayOutputStream outContent, ByteArrayOutputStream errContent) throws Exception {
				Utilities.printFrequencies(freqs);
				String actual = outContent.toString();
				assertEquals(expected, actual);
			}
		});
	}

	public void testPrintFrequenciesExampleTwo() throws Exception {
		ArrayList<Frequency> freqs = new ArrayList<>();
		freqs.add(new Frequency("you think", 2));
		freqs.add(new Frequency("how you", 1));
		freqs.add(new Frequency("know how", 1));
		freqs.add(new Frequency("think you", 1));
		freqs.add(new Frequency("you know", 1));
		final String expected =
			"you think           2\n"
			+"how you             1\n"
			+"know how            1\n"
			+"think you           1\n"
			+"you know            1\n";
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
