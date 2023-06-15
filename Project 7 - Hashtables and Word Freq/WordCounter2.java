
/**
 * Author: Abdullah Shahzad
 * CS 231 Section B Lab C
 * Date: 4/21/2022
 * Project 8
 */

// Imports go here:
import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;

public class WordCounter2 {
    // Fields go here
    private MapSet<String, Integer> dataStructure;
    private int totalWordCount;
    private AscendingString comp = new AscendingString();

    // Constructor for the WordCounter
    public WordCounter2(String dataStructure) {
        totalWordCount = 0;
        // Checking what kind of datastructure we're making
        if (dataStructure == "bst") {
            this.dataStructure = new BSTMap<>(comp);
        }
        if (dataStructure == "hashmap") {
            this.dataStructure = new Hashmap<>(comp);
        }
    }

    // Returns an arraylist of all the words in a textfile "filename"
    public ArrayList<String> readWords(String filename) {
        // Making an array list to store all the words
        ArrayList<String> wordCollection = new ArrayList<String>();

        try {
            // Reading
            FileReader reader = new FileReader(filename);
            // Buffering
            BufferedReader bufferedReader = new BufferedReader(reader);
            // Reading line 1 of the file "filename"
            String line = bufferedReader.readLine();
            // Looping while the next line read isn't empty
            while (line != null) {
                // Seperating words using regex
                String[] words = line.split("[^a-zA-Z0-9']");
                // Looping over the list of words and adding to wordCollection
                for (int i = 0; i < words.length; i++) {
                    if (words[i].length() != 0) {
                        wordCollection.add(words[i]);
                        this.totalWordCount += 1;
                    }
                }
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            return wordCollection;
        } catch (FileNotFoundException ex) {
            System.out.println("Board.read():: unable to open file " + filename);
        } catch (IOException ex) {
            System.out.println("Board.read():: error reading file " + filename);
        }

        return wordCollection;
    }

    // Given an array list of words, returns the time to build
    // a hashmap
    public double buildMap(ArrayList<String> words) {
        // Variable to store the runtime
        double runTime;
        // Clock when build started
        double time1 = System.nanoTime();
        // Looping over all words and adding them to the map
        for (String word : words) {
            if (dataStructure.containsKey(word)) {
                dataStructure.put(word, dataStructure.get(word) + 1);
            } else {
                dataStructure.put(word, 1);
            }
        }
        // Clock when build stopped
        double time2 = System.nanoTime();

        // Calculating runtime
        runTime = time2 - time1;

        return runTime;
    }

    // Clears the map data structure
    public void clearMap() {
        dataStructure.clear();
    }

    // Returns the word count from the last time readWords() was called
    public int totalWordCount() {
        return this.totalWordCount;
    }

    // Returns the unique word count
    public int uniqueWordCount() {
        // This is = size of map
        return dataStructure.size();
    }

    // Returns the number of occurences of the word in the list
    public int getCount(String word) {
        // Checking if the word is in the map
        if (dataStructure.containsKey(word)) {
            return dataStructure.get(word);
        } else { // Else returning 0
            return 0;
        }
    }

    // Returns the frequency of a word in the list
    public double getFrequency(String word) {
        // Checking to see if the word exists in the array
        if (dataStructure.containsKey(word)) {
            // Getting the value associated with the word
            int wc = dataStructure.get(word);
            // Calculating and returning frequency3
            return wc / this.totalWordCount;
        } else {
            // Returning 0 since word not in list
            return 0;
        }
    }

    // Writes a word count file from the data structure
    public boolean writeWordCount(String filename) {
        ArrayList<KeyValuePair<String, Integer>> set = dataStructure.entrySet();
        try {
            File myObj = new File(filename);
            if (myObj.createNewFile()) {
                FileWriter writer = new FileWriter(filename);
                writer.write("Total word count: " + totalWordCount + "\n");
                for (KeyValuePair<String, Integer> pair : set) {
                    writer.write(pair.toString() + "\n");
                }
                writer.close();
                return true;
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }
        return false;
    }

    // Reads a word count file and puts it into the data structure
    public boolean readWordCount(String filename) {
        // Clearing the BST
        dataStructure.clear();
        try {
            FileReader reader = new FileReader(filename);
            try (BufferedReader bufferedReader = new BufferedReader(reader)) {
                // Seperate call to readline for the first line
                String line = bufferedReader.readLine();
                line = bufferedReader.readLine();
                while (line != null) {
                    // splitting up the line
                    String[] words = line.split("[^a-zA-Z0-9']");
                    dataStructure.put(words[0], Integer.parseInt(words[3]));
                    line = bufferedReader.readLine();
                }
                return true;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
        return false;
    }

    // Average calculater: takes a filename, calculates an avg time in ms
    public double averageCalculator(String filename) {
        // Creating a variable to store a sum of times
        double timeSum = 0;
        // Creating an arraylist to store the times
        ArrayList<Double> timeList = new ArrayList<Double>(5);
        // Reading from the file (only once)
        ArrayList<String> wordList = readWords(filename);
        // Loop 5 times, clearing the map each time
        for (int i = 0; i < 5; i++) {
            timeList.add(buildMap(wordList));
            if (i == 4) {
                System.out.println("Total WC: " + totalWordCount);
                System.out.println("Unique WC: " + uniqueWordCount());
            }
            dataStructure.clear();
        }
        // drop lowest and highest, calc the avg of remainders
        Collections.sort(timeList);
        timeList.remove(0);
        timeList.remove(3);
        // return runtime in ms
        for (double time : timeList) {
            timeSum += time;
        }
        return timeSum / 3 * 0.000001;
    }

    // Main method to test code
    // What data structure is used is decided from args
    public static void main(String[] args) {
        WordCounter2 wc2 = new WordCounter2("hashmap");
        // Calculates the avg time of the three best times
        System.out.println("Average time: " + wc2.averageCalculator("reddit_comments_2008.txt") + " ms");

        // ArrayList<String> list = wc2.readWords("reddit_comments_2008.txt");
        // System.out.println(wc2.buildMap(list));
        // Statistics of the build

        /**
         * System.out.println("Should be true: " +
         * wc2.dataStructure.containsKey("text"));
         * System.out.println("This should be 10: " + wc2.dataStructure.size());
         * System.out.println("This should be 2: " + wc2.dataStructure.get("new"));
         * System.out.println("This should be 1: " +
         * wc2.dataStructure.get("something"));
         * System.out.println("This should be 11: " + wc2.totalWordCount);
         * System.out.println(wc2.writeWordCount("counted.txt"));
         * System.out.println(wc2.readWordCount("counted.txt"));
         * 
         * System.out.println("Should be true: " +
         * wc2.dataStructure.containsKey("text"));
         * System.out.println("This should be 10: " + wc2.dataStructure.size());
         * System.out.println("This should be 2: " + wc2.dataStructure.get("new"));
         * System.out.println("This should be 1: " +
         * wc2.dataStructure.get("something"));
         * System.out.println("This should be 11: " + wc2.totalWordCount);
         * // return the total no. of words and the no. of unique words
         */
    }
}