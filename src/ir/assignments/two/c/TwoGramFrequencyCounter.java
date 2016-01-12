package ir.assignments.two.c;

import ir.assignments.two.a.Frequency;
import ir.assignments.two.a.Utilities;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;

/**
 * Count the total number of 2-grams and their frequencies in a text file.
 */
public final class TwoGramFrequencyCounter {
	/**
	 * This class should not be instantiated.
	 */
	private TwoGramFrequencyCounter() {}
	
	/**
	 * Takes the input list of words and processes it, returning a list
	 * of {@link Frequency}s.
	 * 
	 * This method expects a list of lowercase alphanumeric strings.
	 * If the input list is null, an empty list is returned.
	 * 
	 * There is one frequency in the output list for every 
	 * unique 2-gram in the original list. The frequency of each 2-grams
	 * is equal to the number of times that two-gram occurs in the original list. 
	 * 
	 * The returned list is ordered by decreasing frequency, with tied 2-grams sorted
	 * alphabetically. 
	 * 
	 * 
	 * 
	 * Example:
	 * 
	 * Given the input list of strings 
	 * ["you", "think", "you", "know", "how", "you", "think"]
	 * 
	 * The output list of 2-gram frequencies should be 
	 * ["you think:2", "how you:1", "know how:1", "think you:1", "you know:1"]
	 *  
	 * @param words A list of words.
	 * @return A list of two gram frequencies, ordered by decreasing frequency.
	 */
	public static List<Frequency> computeTwoGramFrequencies(ArrayList<String> words) {
		if (words != null) {
			HashMap<String,Frequency> map = new HashMap<>();

			// we are looking ahead 1, so we stop short of the last element
			for (int i=0; i < words.size() - 1; i++) {
				String twoGram = words.get(i)+" "+words.get(i+1);
			    Frequency freq = map.getOrDefault(twoGram, new Frequency(twoGram));
				freq.incrementFrequency();
				map.put(twoGram, freq);
			}

			ArrayList<Frequency> freqs = new ArrayList<>(map.values());
			
			Collections.sort(freqs, Utilities.frequencyComparator);

			return freqs;
		} else {
			return new ArrayList<>();
		}
	}
	
	/**
	 * Runs the 2-gram counter. The input should be the path to a text file.
	 * 
	 * @param args The first element should contain the path to a text file.
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.err.println("Error: Expected input the path to a text file.");
		    System.exit(1);
		}
		File file = new File(args[0]);
		ArrayList<String> words = Utilities.tokenizeFile(file);
		List<Frequency> frequencies = computeTwoGramFrequencies(words);
		Utilities.printFrequencies(frequencies);
	}
}
