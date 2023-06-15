/*
* File: Hand.java
* Author: Abdullah Shahzad
*Date: 02/08/2022
*/

import java.util.ArrayList;

public class Hand {
    ArrayList<Card> hand = new ArrayList<Card>();

    public void reset() {
        /*
         * This method resets the hand to empty
         */

        hand.clear();
    }

    public void add(Card card) {
        /*
         * This method adds a card object to the hand.
         */

        hand.add(card);
    }

    public int size() {
        /*
         * This method returns the number of cards in the hand arraylist.
         */

        return hand.size();
    }

    public Card getCard(int i) {
        /*
         * This method returns the card in the hand arraylist at index i
         */

        return (Card) hand.get(i);
    }

    public int getTotalValue() {
        /*
         * This method returns an integer value of the total of all the cards in the
         * hand arraylist
         */

        int total = 0; // Initializing the variable that will hold the total

        // Looping over the hand,
        for (int i = 0; i < hand.size(); i++) {
            Card individualCard = hand.get(i); // Getting and storing each individual card from the hand
            int cardValue = individualCard.getValue(); // Getting the value of the stored card
            total += cardValue;
        } // Adding it to the total
        return total;
    }

    public String toString() {
        /*
         * This method returns a string representation of the hand.
         */

        String handOutput = new String("Hand: " + hand.toString());
        return handOutput;
    }

    /*
     * public static void main(String[] args) {
     * // Main method to test the Hand class
     * Hand h = new Hand();
     * h.add(new Card(5));
     * System.out.println(h.toString());
     * h.add(new Card(10));
     * System.out.println(h.toString());
     * System.out.println(h.getTotalValue());
     * System.out.println(h.size());
     * h.reset();
     * System.out.println(h.toString());
     * }
     */
}
