/**
 * File: Shuffle.java
 * Author: Abdullah Shahzad
 * Date: 02/08/2022
 */

// Import statements go here:
import java.util.ArrayList;
import java.util.Random;

public class Shuffle {
    public static void main(String[] args) { // Making a main method
        // Making an array list to contain the random numbers (now numbers 1 through 10)
        ArrayList arr = new ArrayList<Integer>();// specify the type of the data in the arrow brackets, since its an array of numbers, <Integer>
                                                 /*If i didn't specify what type of object would be contained in the
                                                   array, it would be an array list of type object and contain
                                                   references to classes*/

        // A seperate array to contain a random permutation of removed numbers
        ArrayList removedNumbers = new ArrayList<Integer>();

        // Initializing the random object
        Random ran = new Random();

        // Looping to get random numbers (changed)
        // Looping to add the number of iteration+1 to the array list to get a sequence (1 through 10)
        for (int i = 0; i < 10; i++) {
            // arr.add(ran.nextInt(99));
            arr.add(i + 1);
            // System.out.println(arr.get(i));
        }
        // Looping over the array
        for (int j = 0; j < 10; j++) {
            // Generating a random index from the array to remove.
            int index = ran.nextInt(arr.size());
            // Removing the number at that index and storing it in the variable "removed"
            int removed = (int) arr.remove(index);
            // Adding the stored and removed number from our local variable to this list
            // Contains all the numbers removed in the order they were removed
            removedNumbers.add(removed);
            // Output statement for the user
            System.out.println("Integer Array List: " + arr + " Number removed: " + removed);
            System.out.println("Randomized List: " + removedNumbers);
        }
    }
}