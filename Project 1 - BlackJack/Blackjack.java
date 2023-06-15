/*
* File: Blackjack.java
* Author: Abdullah Shahzad
*Date: 02/08/2022
*/

import java.util.Scanner;

public class Blackjack {

    // Initializing the deck, both hands, and the reshuffleCutoff internal field.
    Deck cardDeck = new Deck();
    Hand playerHand = new Hand();
    Hand dealerHand = new Hand();
    int reshuffleCutoff;

    public Blackjack(int reshuffleCutoff) {
        // Resetting the game if the size of the deck is smaller than the reshuffle
        // cutoff.
        if (cardDeck.size() > reshuffleCutoff) {
            reset();
        }
    }

    public void reset() {
        /*
         * This method resets the game.
         */

        // Fresh hands for the dealer and player and a fresh card deck if necessary.
        playerHand.reset();
        dealerHand.reset();

        // If the number of cards in the deck are less than 26, then the deck is remade
        // and reshuffled.
        if (cardDeck.size() < 26) {
            cardDeck.build();
            cardDeck.Shuffle();
        }
    }

    public void deal() {
        /*
         * This method deals two cards to the player and the dealer
         */

        for (int i = 0; i < 2; i++) {
            playerHand.add(cardDeck.deal());
            dealerHand.add(cardDeck.deal());
        }
    }

    public boolean playerTurn() {
        /*
         * This method has the player draw cards until he's over 16 at least.
         * Returs true if the player's hand's total is less than 21 and false otherwise.
         */

        if (playerHand.getTotalValue() < 16) {
            playerHand.add(cardDeck.deal());
        }

        if (playerHand.size() < 21) {
            return true;
        } else {
            return false;
        }
    }

    public boolean dealerTurn() {
        /*
         * This method has the dealer draw cards until his total is 17 at least.
         * If the dealer's hand's total goes over 21, returns false, otherwise true.
         */

        while (dealerHand.getTotalValue() < 17) {
            dealerHand.add(cardDeck.deal());
        }

        if (dealerHand.size() < 21) {
            return true;
        } else {
            return false;
        }
    }

    public void setReshuffleCutoff(int cutoff) {
        /*
         * This method sets the value of the reshuffleCutoff interrnal field.
         */

        reshuffleCutoff = cutoff;
    }

    public int getReshuffleCutoff() {
        /*
         * This method returns the reshuffleCutoff internal field
         */

        return reshuffleCutoff;
    }

    public String toString() {
        /*
         * Returns a string representation of the game's state. The two hands and their
         * total values.
         */

        return "Player's " + playerHand.toString() + "\nPlayer's Total: " + playerHand.getTotalValue() + "\nDealer's "
                + dealerHand.toString() + "\nDealer's Total: " + dealerHand.getTotalValue();
    }

    public void playerTurnInteractive() {
        /*
         * This method lets you play Blackjack on the terminal
         */

        try (Scanner playerChoice = new Scanner(System.in)) { // Initializing the scanner for player input
            reset(); // Resetting the game

            System.out.println("Welcome to Blackjack. Type 'play' to begin or 'rules' to get the rules. Goodluck."); // Initial
                                                                                                                     // message

            if (playerChoice.nextLine().contains("rules")) { // If the player types in rules, this prints out rules
                System.out.println("\nRules:" + "\n" + "The player must complete their turn before the dealer plays"
                        + "\n"
                        + "The player and dealer will both get two cards. The player can choose to stand (not take a card) or hit (take a card)"
                        + "\n"
                        + "If the player hits and the total of their hand goes over 21, the player busts and the dealer wins"
                        + "\n"
                        + "If the player stands or does not bust, the dealer draws cards until he has a total of at least 17."
                        + "\n" + "If the dealer's hand's total goes over 21, the player wins" + "\n"
                        + "If the player and dealer both do not bust, the person with the higher hand wins");

                System.out.println("\nTo start, type in 'start' into the terminal"); // Asks the player to, after
                                                                                     // reading the rules, start

                playerChoice.nextLine();
            }

            deal(); // Starts the game by dealing two cards to each player

            // Printing out hand values once the player has decided and then checking end
            // conditions for the game
            // Printing out the result of the game at the end.

            System.out.println("\nYour " + playerHand.toString());
            System.out.println("Press 'hit' to take a card or 'stand' to end your turn.");
            String hitOrStand = playerChoice.nextLine();
            if (hitOrStand.length() == 3) {
                playerHand.add(cardDeck.deal());
                System.out
                        .println("\nNew " + playerHand.toString() + "\n" + "New Total: " + playerHand.getTotalValue());
            } else {
                System.out.println(
                        "\nYour final " + playerHand.toString() + "\nYour total: " + playerHand.getTotalValue());
            }
        }
        if (playerHand.getTotalValue() > 21) {
            System.out.println("Your Total: " + playerHand.getTotalValue() + "\nYou busted! Dealer wins!");
        } else {
            dealerTurn();
            System.out
                    .println("Dealer " + dealerHand.toString() + "\n" + "Dealer Total: " + dealerHand.getTotalValue());
        }
        if (dealerHand.getTotalValue() > 21) {
            System.out.println("The dealer busted! You win!");
        } else if (dealerHand.getTotalValue() > playerHand.getTotalValue()) {
            System.out.println("The dealer has the bigger hand! Dealer wins!");
        } else if (dealerHand.getTotalValue() < playerHand.getTotalValue()) {
            System.out.println("You have the bigger hand! You win!");
        } else if (dealerHand.getTotalValue() == playerHand.getTotalValue()) {
            System.out.println("This game ended in a push.");
        }

    }

    public int game(boolean verbose) {
        /*
         * This method runs a game according to the rules of blackjack
         */

        reset(); // Resets the game each time this function is called

        deal(); // Deals two cards to the player and the dealer

        // Making more hands to hold the values of the initial hands of both players.
        Hand playerStartingHand = new Hand();
        Hand dealerStartingHand = new Hand();
        for (int i = 0; i < 2; i++) {
            playerStartingHand.add(playerHand.getCard(i));
            dealerStartingHand.add(dealerHand.getCard(i));
        }

        playerTurn(); // Player either draws a card or stands

        if (playerHand.getTotalValue() > 21) { // Checks to see if the player busted.
            if (verbose == true) {
                System.out.println("Game Data:-" + "\n" + "Player's Starting " + playerStartingHand.toString()
                        + "\n" + "Dealer's Starting " + dealerStartingHand.toString() + "\n" + "Player's Ending "
                        + playerHand.toString() + "\n" + "Player's Total: " + playerHand.getTotalValue() + "\n"
                        + "Dealer's Ending "
                        + dealerHand.toString() + "\n" + "Dealer's Total: " + dealerHand.getTotalValue() + "\n"
                        + "Result: Dealer Won!");
            }
            return -1;
        }

        dealerTurn(); // Dealer draws cards till he has a total hand value of at least 17

        if (dealerHand.getTotalValue() > 21) { // Checks to see if the dealer busted
            if (verbose == true) {
                System.out.println("Game Data:-" + "\n" + "Player's Starting " + playerStartingHand.toString()
                        + "\n" + "Dealer's Starting " + dealerStartingHand.toString() + "\n" + "Player's Ending "
                        + playerHand.toString() + "\n" + "Player's Total: " + playerHand.getTotalValue() + "\n"
                        + "Dealer's Ending "
                        + dealerHand.toString() + "\n" + "Dealer's Total: " + dealerHand.getTotalValue() + "\n"
                        + "Result: Player Won!");
            }
            return 1;
        }

        // Checking to see if the result is a push
        if (playerHand.getTotalValue() == dealerHand.getTotalValue()) {
            if (verbose == true) {
                System.out.println("Game Data:-" + "\n" + "Player's Starting " + playerStartingHand.toString()
                        + "\n" + "Dealer's Starting " + dealerStartingHand.toString() + "\n" + "Player's Ending "
                        + playerHand.toString() + "\n" + "Player's Total: " + playerHand.getTotalValue() + "\n"
                        + "Dealer's Ending "
                        + dealerHand.toString() + "\n" + "Dealer's Total: " + dealerHand.getTotalValue() + "\n"
                        + "Result: Push.");
            }
            return 0;
        }

        // If neither busted and its not a push, then checks whose hand is higher
        if (playerHand.getTotalValue() > dealerHand.getTotalValue()) {
            if (verbose == true) {
                System.out.println("Game Data:-" + "\n" + "Player's Starting " + playerStartingHand.toString()
                        + "\n" + "Dealer's Starting " + dealerStartingHand.toString() + "\n" + "Player's Ending "
                        + playerHand.toString() + "\n" + "Player's Total: " + playerHand.getTotalValue() + "\n"
                        + "Dealer's Ending "
                        + dealerHand.toString() + "\n" + "Dealer's Total: " + dealerHand.getTotalValue() + "\n"
                        + "Result: Player Won!");
            }
            return 1;
        } else {
            if (verbose == true) {
                System.out.println("Game Data:-" + "\n" + "Player's Starting " + playerStartingHand.toString()
                        + "\n" + "Dealer's Starting " + dealerStartingHand.toString() + "\n" + "Player's Ending "
                        + playerHand.toString() + "\n" + "Player's Total: " + playerHand.getTotalValue() + "\n"
                        + "Dealer's Ending "
                        + dealerHand.toString() + "\n" + "Dealer's Total: " + dealerHand.getTotalValue() + "\n"
                        + "Result: Dealer Won!");
            }
            return -1;
        }
    }

    /*
     * public static void main(String[] args) {
     * // Main method to test the Blackjack class
     * Blackjack blackjack = new Blackjack(26);
     * System.out.println(blackjack.getReshuffleCutoff());
     * blackjack.setReshuffleCutoff(30);
     * System.out.println(blackjack.getReshuffleCutoff());
     * 
     * for (int i = 0; i<3; i++) {
     * System.out.println(blackjack.game(true));}
     * 
     * }
     */
}
