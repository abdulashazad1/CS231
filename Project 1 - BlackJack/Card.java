/*
* File: Card.java
* Author: Abdullah Shahzad
*Date: 02/08/2022
*/

public class Card {
    private int v; // An attribute containing the numerical value of the card.

    public Card(int v) {
        /*
         * A constructor for the card class.
         */

        this.v = v; // On construction, v is given a value v.
    }

    public int getValue() {
        /*
         * A method that returns the value of the card.
         */

        return v;
    }

    public String toString() {
        /*
         * A method that returns a string of the card value
         *
         */

        return String.valueOf(v);
    }

    /*
     * public static void main(String[] args) {
     * // Main method to test the Card class
     * Card c = new Card(10);
     * System.out.println(c.getValue() + " " + c.toString());
     * }
     */
}
