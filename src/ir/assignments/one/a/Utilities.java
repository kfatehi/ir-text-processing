package ir.assignments.one.a;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.Arrays;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * A collection of utility methods for text processing.
 */
public class Utilities {
	/**
	 * Reads the input text file and splits it into alphanumeric tokens.
	 * Returns an ArrayList of these tokens, ordered according to their
	 * occurrence in the original text file.
	 * 
	 * Non-alphanumeric characters delineate tokens, and are discarded.
	 *
	 * Words are also normalized to lower case. 
	 * 
	 * Example:
	 * 
	 * Given this input string
	 * "An input string, this is! (or is it?)"
	 * 
	 * The output list of strings should be
	 * ["an", "input", "string", "this", "is", "or", "is", "it"]
	 * 
	 * @param input The file to read in and tokenize.
	 * @return The list of tokens (words) from the input file, ordered by occurrence.
	 */
	public static ArrayList<String> tokenizeFile(File input) {
    	ArrayList<String> out = new ArrayList<>();
		final Pattern whitelist = Pattern.compile("([^A-z0-9])");
		final Scanner sc;
		try {
			sc = new Scanner(input);
			while (sc.hasNext()) {
				out.add(
						whitelist
						.matcher(sc.next())
						.replaceAll("")
						.toLowerCase()
					   );
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return out;
	}
	
	/**
	 * Takes a list of {@link Frequency}s and prints it to standard out. It also
	 * prints out the total number of items, and the total number of unique items.
	 * 
	 * Example one:
	 * 
	 * Given the input list of word frequencies
	 * ["sentence:2", "the:1", "this:1", "repeats:1",  "word:1"]
	 * 
	 * The following should be printed to standard out
	 * 
	 * Total item count: 6
	 * Unique item count: 5
	 * 
	 * sentence	2
	 * the		1
	 * this		1
	 * repeats	1
	 * word		1
	 * 
	 * 
	 * Example two:
	 * 
	 * Given the input list of 2-gram frequencies
	 * ["you think:2", "how you:1", "know how:1", "think you:1", "you know:1"]
	 * 
	 * The following should be printed to standard out
	 * 
	 * Total 2-gram count: 6
	 * Unique 2-gram count: 5
	 * 
	 * you think	2
	 * how you		1
	 * know how		1
	 * think you	1
	 * you know		1
	 * 
	 * @param frequencies A list of frequencies.
	 */
	public static void printFrequencies(List<Frequency> frequencies) {
		final ByteArrayOutputStream tableContent = new ByteArrayOutputStream();
		final PrintStream table = new PrintStream(tableContent);
		String type = "item";
		boolean twoGram = true;
		int total = 0;
		int len = frequencies.size();

		// Do a single loop and figure it all out
		for (int i=0; i < len; i++) {
			Frequency freq = frequencies.get(i);

			// Update totals
			total = total + freq.getFrequency();

			// Determine 2-gram-ness of the list
			int grams = freq.getText().split(" ").length;
			if (grams != 2) twoGram = false;
			if (twoGram && i == len-1)
				type = "2-gram";

			// Print the frequencies in a tabular format
			table.printf("%-20s%s%n", freq.getText(), freq.getFrequency());
		}

		// Print the summary information
		System.out.printf("Total %s count: %s%n", type, total);
		System.out.printf("Unique %s count: %s%n%n", type, len);

		// Print table
		System.out.print(tableContent.toString());

	}
}
