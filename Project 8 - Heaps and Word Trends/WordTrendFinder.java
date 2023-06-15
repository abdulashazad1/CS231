/**
 * Author: Abdullah Shahzad
 * CS 231 Section B Lab C
 * Date: 4/30/2022
 * Project 9
 */

 // Imports go here
import java.util.ArrayList;

public class WordTrendFinder{

    // Analyzes a file for a list of words
    public void analyze(String base, String Year, ArrayList<String> words){
        WordCounter2 wc2 = new WordCounter2("hashmap");
            //Compiling the String
            String fullName = "";
            fullName += base;
            fullName += Year;
            fullName += ".txt";

            // Building the map for that reddit file
            wc2.buildMap(wc2.readWords(fullName));

            // Outputting in readable format
            System.out.println("Year: " + Year);
            for (int i=0; i<words.size(); i++){
                if (wc2.dataStructure.containsKey(words.get(i))){
                    System.out.println(/*words.get(i) + ": " + */wc2.getFrequency(words.get(i)));
                } else {
                    System.out.println(/*words.get(i) + ": "+*/0);
                }
            }

        }

        // Main function to test code
        public static void main(String[] args) {
            /*
            When I tried using command line arguments to run this program,
            I ran into heap space errors which i realized I could avoid by 
            performing a function call with normal arguments and so I did that.
            This code could be very concisely put into a for loop
            */


            WordTrendFinder wtf = new WordTrendFinder();

            ArrayList<String> words = new ArrayList<String>();
            words.add("clinton");
            words.add("sanders");
            words.add("trump");
            words.add("obama");
            words.add("romney");
            words.add("cruz");

            wtf.analyze("reddit_comments_", "2008", words);
            System.out.println();
            wtf.analyze("reddit_comments_", "2009", words);
            System.out.println();
            wtf.analyze("reddit_comments_", "2010", words);
            System.out.println();
            wtf.analyze("reddit_comments_", "2011", words);
            System.out.println();
            wtf.analyze("reddit_comments_", "2012", words);
            System.out.println();
            wtf.analyze("reddit_comments_", "2013", words);
            System.out.println();
            wtf.analyze("reddit_comments_", "2014", words);
            System.out.println();
            wtf.analyze("reddit_comments_", "2015", words);
            System.out.println();            
        }
}