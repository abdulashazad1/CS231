/*
* File: Simulation.java
* Author: Abdullah Shahzad
*Date: 02/08/2022
*/

public class Simulation {
    // Simulation class that runs the game 1000 times
    // You can change this by changing the 1000 in the for loop

    public static void main(String[] args) {
        /* 
        * Main method that runs games and adds 1 to each variable that represents 
        * the result of that game
        */

        Blackjack blackjack = new Blackjack(26);
        int playerWins = 0;
        int dealerWins = 0;
        int pushes = 0;
        for (int i = 0; i < 1000; i++) {
            int result = blackjack.game(false);

            if (result == 0) {
                pushes += 1;
            } else if (result == 1) {
                playerWins += 1;
            } else if (result == -1) {
                dealerWins += 1;
            }
        }
        System.out
                .println("Player Wins: " + playerWins + "\n" + "Dealer Wins: " + dealerWins + "\n" + "Pushes: " + pushes
                        + "\n" + "Player Win %: " + (float) playerWins / 10 + "\n" + "Dealer Win %: "
                        + (float) dealerWins / 10 + "\n" + "Push %: " + (float) pushes / 10);
    }
}
