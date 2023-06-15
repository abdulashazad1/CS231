/*
* File: Deck.java
* Author: Abdullah Shahzad
*Date: 02/08/2022
*/

import java.util.ArrayList;
import java.util.Random;

public class Deck {

    /*
     * Initializing the deck, the random class, and the variable that will store
     * cards that are removed from the deck arraylist.
     */

    ArrayList<Card> cardDeck = new ArrayList<Card>();
    ArrayList<Card> removedCards = new ArrayList<Card>();
    Random ran = new Random();

    public Deck() {
        /*
         * This method builds a shuffled deck at the start of the game.
         */
        build();
        Shuffle();
    }

    public void build() {
        /*
         * This method builds a deck with 52 cards.
         */

        cardDeck.clear(); // Clears the array list before making more cards.

        for (int i = 0; i < 11; i++) { // Adds 4 of i every time it loops.
            for (int j = 0; j < 4; j++) {
                cardDeck.add(new Card(i + 1));
            }
        }
        for (int k = 0; k < 12; k++) { // This for loop adds the remaining 12 10 value cards.
            cardDeck.add(new Card(10));
        }
    }

    public int size() {
        /*
         * This method returns the size of the array "cardDeck".
         */

        return cardDeck.size();
    }

    public Card deal() {
        /*
         * This method returns the card at index 0 in the array "cardDeck".
         */

        Card dealt = (Card) cardDeck.remove(0); // Removes the card at index 0 and stores it in dealt.
        return dealt;
    }

    public Card pick(int i) {
        /*
         * This method returns the card at index i in the array "cardDeck".
         */

        Card picked = (Card) cardDeck.remove(i); // Removes the card at index i and stores it in picked.
        return picked;
    }

    public void Shuffle() {
        /*
         * This method shuffles the cards in the array cardDeck
         */

        for (int i = 0; i < 52; i++) {
            // Generating a random index from the array to remove.
            int index = ran.nextInt(cardDeck.size());
            // Removing the number at that index and storing it in the variable "removed".
            Card removed = (Card) cardDeck.remove(index);
            // Adding the removed cards to a new array list
            removedCards.add(removed);
        }
        cardDeck = removedCards; // The new, shuffled array is now cardDeck
    }

    public String toString() {
        /*
         * Returns a string representation of the deck.
         */

        return cardDeck.toString();
    }

    /*
     * public static void main(String[] args) {
     * // Main method to test the Deck class
     * Deck d = new Deck();
     * Hand h = new Hand();
     * System.out.println(d.size());
     * System.out.println(d.toString());
     * System.out.println(h.toString());
     * h.add(d.deal());
     * h.add(d.pick(5));
     * System.out.println(h.toString());
     * }
     */
}