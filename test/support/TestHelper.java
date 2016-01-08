package ir.test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Defines static methods for use in tests.
 *
 * It is not meant to be instantiated. Instead, import it into your tests with
 * <code>import static ir.test.TestHelper.*</code>
 */
public class TestHelper {

	/**
	 * Capture standard out and standard error in order to test functions that print.
	 *
	 * @param test A {@link CaptureTest} object that defines a {@code test} function.
	 * @see <a href="http://stackoverflow.com/a/12233390">StackOverflow Answer</a>
	 *
	 * <h2>Example</h2>
	 *
	 * <p>In your test function ... </p>
	 *
	 * <pre><code>
	 * captureOutput( new CaptureTest() {
	 * 	&#64;Override
	 * 	public void test(ByteArrayOutputStream out, ByteArrayOutputStream err) throws Exception {
	 * 		assertEquals("hello\n", out.toString());
	 * 	}
	 * });
	 * </code></pre>
	 */
	public static void captureOutput( CaptureTest test ) throws Exception {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		ByteArrayOutputStream errContent = new ByteArrayOutputStream();

		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));

		test.test( outContent, errContent );

		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
		System.setErr(new PrintStream(new FileOutputStream(FileDescriptor.out)));
	}

	/**
	 * Convert a string of words into an {@link ArrayList} of words.
	 *
	 * @param input a string of words you want split
	 * @return an ArrayList of words in input
	 *
	 * <h2>Example</h2>
	 * {@code ArrayList<String> words = splitOnSpaces("hello world"); }
	 */
	public static ArrayList<String> splitOnSpaces(String input) {
		return new ArrayList<>(Arrays.asList(input.split(" ")));
	}
}

abstract class CaptureTest {
	public abstract void test( ByteArrayOutputStream outContent, ByteArrayOutputStream errContent ) throws Exception;
}
