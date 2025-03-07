package se.kth.ict.docaid.algorithms.keyphrases;

import java.util.ArrayList;
import java.util.Arrays;

import se.kth.ict.docaid.exceptions.InvalidTextLengthException;
import maui.main.MauiWrapper;
import weka.core.Instance;

/**
 * 
 * Extracts keyphrases from a text source using of <a href="https://code.google.com/p/maui-indexer/">maui-ndexer</a>
 * 
 * @author Andreas Kokkalis <a.kokkalis@kth.se>
 * @author Adrian C. Prelipcean <acpr@kth.se>
 * 
 */
public class KeyphraseExtractor {
	private static final int MAX_TOPICS = 50;

	/**
	 * Extracts the topics using maui-indexer and then formats them in keyphrases.
	 * 
	 * @param content The text source from which topics are extracted.
	 * @return A list of Keyphrase topics.
	 * @throws InvalidTextLengthException
	 */
	public static ArrayList<Keyphrase> getKeyphrases(String content) throws InvalidTextLengthException {
		ArrayList<Keyphrase> phrases = new ArrayList<Keyphrase>();
		MauiWrapper wrap = new MauiWrapper("", "", "keyphrextr");
		Instance[] keyphrases;
		try {
			keyphrases = wrap.extractTopicsFromText(content, MAX_TOPICS);
			for (Instance s : keyphrases) {
				String stemStream = s.stringValue(0);
				ArrayList<String> stems = new ArrayList<String>(Arrays.asList(stemStream.split(" ")));
				String phrase = s.stringValue(1);
				// System.out.println(phrase +" " +s.value(1)+","+s.value(2)+","+s.value(3)+","+s.value(4)+","+s.value(5)+","+s.value(6));
				double freq = s.value(2);
				phrases.add(new Keyphrase(phrase, freq, stems));
			}
		} catch (Exception e) {
			// throw new InvalidTextLengthException(e.getMessage());
			return new ArrayList<Keyphrase>();
		}

		return phrases;
	}

	public static void main(String[] args) {
		// String url =
		// "http://www.kth.se/student/kurser/kurs/ID2216?startterm=20131&l=en";
		// try {
		// WebDocument webDocument = new WebDocument(url);
		// ArrayList<String> keyphrases =
		// KeyphraseExtractor.getKeyphrases(webDocument.getBody());
		// for(String string: keyphrases)
		// System.out.println(string);
		// // KeyphraseExtractor ke = new
		// KeyphraseExtractor(webDocument.getBody());
		// // Instance[] st = ke.getKeyPhraseInstances();
		// // for (Instance s : st)
		// // System.out.println("xx " + s.stringValue(1) + " " + s.value(4));
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

}
