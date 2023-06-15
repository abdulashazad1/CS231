
/**
 * Author: Abdullah Shahzad
 * CS 231 Section B Lab C
 * Date: 4/30/2022
 * Project 9
 */

// Imports go here
import java.util.Comparator;

// Comparator class
public class AscendingString implements Comparator<String> {

    // Compares two string and returns a 1, 0, or -1
    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }

}
