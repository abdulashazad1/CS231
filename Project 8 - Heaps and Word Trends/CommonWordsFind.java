/**
 * Author: Abdullah Shahzad
 * CS 231 Section B Lab C
 * Date: 4/30/2022
 * Project 9
 */

// Imports go here
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CommonWordsFind {
    private AscendingKVP cmp = new AscendingKVP();
    private PQHeap<KeyValuePair<String, Integer>> myHeap = new PQHeap<KeyValuePair<String, Integer>>(cmp);
    private int totalWC;
    // Reads a word count file and puts it into the data structure
    public boolean readWordCount(String filename) {
        // Clearing the BST
        myHeap.clear();
        try {
            FileReader reader = new FileReader(filename);
            try (BufferedReader bufferedReader = new BufferedReader(reader)) {
                // Seperate call to readline for the first line
                String line = bufferedReader.readLine();
                line = bufferedReader.readLine();
                String[] wc = line.split("[^a-zA-Z0-9']");
                this.totalWC = Integer.parseInt(wc[3]);

                while (line != null) {
                    // splitting up the line
                    String[] words = line.split("[^a-zA-Z0-9']");
                    // Adding to the heap
                    myHeap.add(new KeyValuePair<String, Integer>(words[0], Integer.parseInt(words[3])));
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

    public static void main(String[] args) {
        // Takes command line args
        // The first arg is the number of KVPs to print out
        // The rest are to be used for text files to analyze
        
        int numToPrint = Integer.parseInt(args[0]);
        CommonWordsFind cwf = new CommonWordsFind();

        long time1 = System.currentTimeMillis();
        for (int i=1;i<args.length;i++){
            cwf.readWordCount(args[i]);
            System.out.println("\n");
            for (int k=0; k<numToPrint;k++){
                KeyValuePair<String, Integer> pair = cwf.myHeap.getVal(0);
                System.out.print(pair.getKey() + ": ");
                System.out.print(pair.getValue()/cwf.totalWC + "\n");
                cwf.myHeap.remove();
            }
        }
        long time2 = System.currentTimeMillis();
        System.out.println(time2-time1);
    }
}
