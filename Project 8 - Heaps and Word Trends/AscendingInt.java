/**
 * Author: Abdullah Shahzad
 * CS 231 Section B Lab C
 * Date: 4/30/2022
 * Project 9
 */

// Imports go here
import java.util.Comparator;

public class AscendingInt implements Comparator<Integer>{
    @Override
    public int compare(Integer o1, Integer o2) {
        return o1.compareTo(o2);
    }
}
