
/**
 * Author: Abdullah Shahzad
 * CS 231 Section B Lab C
 * Date: 4/30/2022
 * Project 9
 */

// Imports go here
import java.util.Comparator;

// Comparator class
public class AscendingKVP implements Comparator<KeyValuePair<String, Integer>> {
    @Override
    public int compare(KeyValuePair<String, Integer> o1, KeyValuePair<String, Integer> o2) {
        return o1.getValue().compareTo(o2.getValue());
    }
}
