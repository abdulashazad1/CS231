
/**
 * Author: Abdullah Shahzad
 * CS 231 Section B Lab C
 * Date: 4/7/2022
 * Project 7
 */

// Imports go here
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;

// Word counter class
public class WordCounter {
    // Initializing the fields of the WordCounter class
    private AscendingString comp = new AscendingString();
    private BSTMap<String, Integer> tree;
    private Integer wordCount;

    // Constructor for the word counter class
    public WordCounter() {
        tree = new BSTMap<>(comp);
        wordCount = 0;
    }

    // Analyzes a file and produces counts for each word
    public void analyze(String filename) {
        try {
            FileReader reader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line = bufferedReader.readLine();
            while (line != null) {
                String[] words = line.split("[^a-zA-Z0-9']");

                for (int i = 0; i < words.length; i++) {
                    if (words[i].length() != 0) {
                        String word = words[i].trim().toLowerCase();
                        if (tree.containsKey(word)) {
                            tree.put(word, tree.get(word) + 1);
                        } else {
                            tree.put(word, 1);
                        }
                    }
                }
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Board.read():: unable to open file " + filename);
        } catch (IOException ex) {
            System.out.println("Board.read():: error reading file " + filename);
        }
    }

    // Returns the total word count
    public Integer getTotalWordCount() {
        // Refreshing word count
        wordCount = 0;
        // Retreiving a list of values
        ArrayList<Integer> vals = tree.values();
        // Looping over all the values
        for (Integer i : vals) {
            wordCount += i;
        }
        // Returning the word count
        return wordCount;
    }

    // Returns the number of unique words
    public int getUniqueWordCount() {
        return tree.size();
    }

    // Returns the frequency of the string parameter
    public int getCount(String word) {
        return tree.get(word);
    }

    // Returns occurences of the string/total word count
    public double getFrequency(String word) {
        float uniqueWord = tree.get(word).floatValue();
        float totalCount = getTotalWordCount().floatValue();
        return (uniqueWord / totalCount);
    }

    // Writes the total and individual word counts to a file
    // Delete the existing file before running this method to
    // have it write to the file.
    public void writeWordCountFile(String filename) {
        ArrayList<KeyValuePair<String, Integer>> set = tree.entrySet();
        try {
            File myObj = new File(filename);
            if (myObj.createNewFile()) {
                FileWriter writer = new FileWriter(filename);
                writer.write("Total word count: " + getTotalWordCount() + "\n");
                for (KeyValuePair<String, Integer> pair : set) {
                    writer.write(pair.toString() + "\n");
                }
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // Reads the contents of a file to reconstruct the BST
    public void readWordCountFile(String filename) {
        // Clearing the BST
        tree.clear();
        try {
            FileReader reader = new FileReader(filename);
            try (BufferedReader bufferedReader = new BufferedReader(reader)) {
                // Seperate call to readlien for the first line
                String line = bufferedReader.readLine();
                line = bufferedReader.readLine();
                while (line != null) {
                    // splitting up the line
                    String[] words = line.split("[^a-zA-Z0-9']");
                    tree.put(words[0], Integer.parseInt(words[3]));
                    line = bufferedReader.readLine();
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    // Command line arguments that write to a file
    // The first arg is the file read from
    // The second arg is the file written to
    // The key-value pairs are then read from the secodn file and
    // written to the third arg file
    public static void main(String[] args) {
        WordCounter wc = new WordCounter();
        // Variable to store start time
        long timeA = System.currentTimeMillis();
        wc.analyze(args[0]);
        // Variable to store end time
        long timeB = System.currentTimeMillis();
        // Printing out the runtime for analyze()
        System.out.println("Runtime: " + (timeB - timeA));
        // Printing out the Total and Unique WC
        System.out.println("Total WC: " + wc.getTotalWordCount() + "\nUnique WC: " + wc.getUniqueWordCount());
        wc.writeWordCountFile(args[1]);
        wc.readWordCountFile(args[1]);
        wc.writeWordCountFile(args[2]);
    }
}
