package main.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import se.kth.ict.docaid.Recommender.Recommender;
import se.kth.ict.docaid.algorithms.acronyms.Acronym;
import se.kth.ict.docaid.algorithms.keyphrases.Keyphrase;
import se.kth.ict.docaid.algorithms.keywords.Keyword;
import se.kth.ict.docaid.documents.InputDocument;
import se.kth.ict.docaid.documents.UtilClass;
import se.kth.ict.docaid.exceptions.UnsupportedFileException;
import se.kth.ict.docaid.reader.DocumentReader;
import se.kth.ict.docaid.stopwords.StopwordDictionairy;
@Deprecated
public class Executor {

	// static Version LUCENE_VERSION = Version.LUCENE_CURRENT;

	/**
	 * @param args
	 * @throws TikaException 
	 * @throws SAXException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, SAXException, TikaException {
		// TODO Auto-generated method stub

//		String url = "http://www.kth.se/api/kopps/v1/course/AG2425";

	/*	WebDocument doc = new WebDocument(url);
		WebReader reader = new WebReader(doc);
    	System.out.println(doc.toString());
		System.out.println("\n\n=========================================================\n=========================================================");
		System.out.println(reader.getKeywords());
*/
		
//		System.out.println(AcronymDetector.checkAcronymsOnSight(AcronymDetector.detectAcronyms(Jsoup.parse("<p>Geographic Information Systems (GIS) are being used in a wide variety of applications. A key component of any GIS application is the underlying spatial database which must be designed to support efficient data storage, access and analysis operations.This course focuses on the design and development of spatial databases. Particular emphasis will be placed on the use of data modeling techniques to design a GIS database for a specific application. Students will work in small groups to develop a conceptual design for a GIS database and will then work individually to build a spatial database using digital data available through digital library as well as data digitized from existing maps, imagery and field data collected using GPS. The resulting database will be used to perform some basic spatial analysis.</p>").text()), Jsoup.parse("<p>Geographic Information Systems (GIS) are being used in a wide variety of applications. A key component of any GIS application is the underlying spatial database which must be designed to support efficient data storage, access and analysis operations.This course focuses on the design and development of spatial databases. Particular emphasis will be placed on the use of data modeling techniques to design a GIS database for a specific application. Students will work in small groups to develop a conceptual design for a GIS database and will then work individually to build a spatial database using digital data available through digital library as well as data digitized from existing maps, imagery and field data collected using GPS. The resulting database will be used to perform some basic spatial analysis.</p>").text()));
//		LinkedList<File> files = UtilClass.getTestFiles();
//		for (File f : files) {
//			try {
//				InputDocument thisDoc = UtilClass.getInstance().getInputDocument(f);
//				System.out.println(thisDoc.getTitle() + " " + thisDoc.getNumberOfPages() + " " + thisDoc.getType());
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			} catch (SAXException e) {
//				e.printStackTrace();
//			} catch (TikaException e) {
//				e.printStackTrace();
//			} catch (NullPointerException e) {
//				System.out.println(e + "\n" + "cobra taka taka");
//			}
//		}

		System.out.println("\n\n=========================================================\n=========================================================");
/*		String url = "http://www.kth.se/student/kurser/kurs/ID2216?startterm=20131&l=en";
		WebDocument doc = new WebDocument(url);
		
		String url2 = "http://www.kth.se/student/kurser/kurs/AG2417?l=en";
		WebDocument doc2 = new WebDocument(url2);
		
		System.out.println(Recommender.getWeight(doc, doc2));
		*/

		LinkedList<File> files = UtilClass.getTestFiles();
		for (File f : files) {
			System.out.println("====================================================");
			System.out.println("TESTING DOCUMENT "+f.getName());
			if (f.getName().equalsIgnoreCase("testn3.pdf"))
			try {
				InputDocument thisDoc = UtilClass.getInstance().getInputDocument(f);
				
				//System.out.println(thisDoc.getBody());
				
				DocumentReader readerReg = new DocumentReader(thisDoc, new StopwordDictionairy());
				
				
				
				String[] targetedKeywords = UtilClass.getInstance().getTargetedKeywords(thisDoc.getBody());
				
				System.out.println("-------------------------");
				System.out.print("Targeted keywords: ");
				System.out.println("-------------------------");
				
				for (String s: targetedKeywords) System.out.print(s+",");
				System.out.println();
				
				HashMap<String, Float> unOrderedKeyphrases = new HashMap<String, Float>();
				
				System.out.println("-------------------------");
				System.out.println("DETECTED KEYPHRASES");
				System.out.println("-------------------------");
				
				for (Keyphrase kp : readerReg.getKeyphrases())
					{
					unOrderedKeyphrases.put(kp.getPhrase(), (float)kp.getFactor());
					}
				
				HashMap<String, Float> orderedKeyphrases =  Recommender.sortHashMapByValuesDescending(unOrderedKeyphrases);
				
				for (String s : orderedKeyphrases.keySet()){
					System.out.println(s+" "+orderedKeyphrases.get(s));
				}
				
				System.out.println("-------------------------");
				System.out.println("DETECTED KEYWORDS");
				System.out.println("-------------------------");
				
				for (Keyword kw : readerReg.getKeywords())
					if (kw.getFrequency()>20)
					System.out.println(kw.toString() + kw.getTerms());
				
				System.out.println("-------------------------");
				System.out.println("DETECTED ACRONYMS");
				System.out.println("-------------------------");
				
				for (Acronym acc: readerReg.getAcronyms())
					{
					System.out.println(acc.toString());
					}
				
				/*System.out.println(texty);*/
				
				System.out.println("====================================================");

				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (TikaException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				System.out.println(e + "\n" + "cobra taka taka");
			}
			catch (UnsupportedFileException e) {
				e.printStackTrace();
			}
		}
		
		//InputDocument testDoc = UtilClass.getInstance().getInputDocument(new File("testdata/testpapers/test8.pdf"));
		
		
		
		/*
		
		HashMap<String, Float> unOrderedKeyphrases = new HashMap<String, Float>();
		
		for (Keyphrase kp : readerReg.getKeyphrases())
			{
			unOrderedKeyphrases.put(kp.getPhrase(), (float)kp.getFactor());
			}
		
		HashMap<String, Float> orderedKeyphrases =  Recommender.sortHashMapByValuesDescending(unOrderedKeyphrases);
		
		for (String s : orderedKeyphrases.keySet()){
			System.out.println(s+" "+orderedKeyphrases.get(s));
		}
		
		for (Keyword kw : readerReg.getKeywords())
			System.out.println(kw.toString() + kw.getTerms());
		
		for (Acronym acc: readerReg.getAcronyms())
			System.out.println(acc.toString());*/
			
		/*LinkedList<Course> consideredCourses = new LinkedList<Course>();
		
		for (String s: readerReg.getCourseCodes()){
			System.out.println("TRYING "+s);
			Course curCourse = CourseFetch.getCourseData(s, conn);
			if (curCourse!=null){
			System.out.println(curCourse.toString2());
			consideredCourses.add(curCourse);}
		}

		for (Course c :consideredCourses) System.out.println(c.getCode());
		
		HashMap<String, Float> hM = Recommender.getCourseRecommendation(consideredCourses, CourseFetch.retrieveAllCourses(new DatabaseConnection().getConnection()));
		for (String s : hM.keySet())
			System.out.println(s+" "+hM.get(s));
			*/
		
		/*InputDocument thisDoc = UtilClass.getInstance().getInputDocument(new File("testdata/Exercise 1.doc"));
		 HashMap<String, Float> hM = Recommender.getCourseRecommendation(thisDoc, CourseFetch.retrieveAllCourses(new DatabaseConnection().getConnection()));
		for (String s : hM.keySet())
			System.out.println(s+" "+hM.get(s));*/
		
		/*		StopwordDictionairy stopwords = new StopwordDictionairy();
		WebReader reader = new WebReader(doc);
		@SuppressWarnings("unused")
		ArrayList<Keyword> keywords = reader.getKeywords();
		ArrayList<Keyphrase> keyphrases = reader.getKeyphrases();
		for(Keyphrase keyphrase: keyphrases)
			System.out.println(keyphrase.toString());*/
	}

}
