/**
 * Author: Abdullah Shahzad
 * CS 231 Section B Lab C
 * Date: 4/30/2022
 * Project 9
 */

public class KeyValuePair<Key, Value> {
    // Fields go here
    private Key key;
    private Value val;

    // Constructor for the KVP class
    public KeyValuePair(Key k, Value v) {
        this.key = k;
        this.val = v;
    }

    // Getter for the key
    public Key getKey() {
        return this.key;
    }

    // Getter for the value function
    public Value getValue() {
        return this.val;
    }

    // Setter for the value of the pair
    public void setValue(Value v) {
        this.val = v;
    }

    // Overriding the toString method
    public String toString() {
        String str = "";
        str += this.key + " : ";
        str += this.val;
        return str;
    }

    // Main method to test the function
    public static void main(String[] args) {
        // Initializing the key value pair
        KeyValuePair<String, Integer> pair1 = new KeyValuePair<String, Integer>("this", 5);
        // Printing out the key and value seperately
        System.out.println("Key:" + pair1.getKey());
        System.out.println("Value: " + pair1.getValue());
        // Setting the value of the pair to a different int
        pair1.setValue(10);
        // Printing out the new int
        System.out.println("New Value: " + pair1.getValue());
        // Checking the toString method
        System.out.println(pair1.toString());
    }
}