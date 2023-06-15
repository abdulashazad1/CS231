/**
 * Project 2
 * Author: Abdullah Shahzad
 * Coursse and Section: CS231 B & Lab C 
 * File: LifeSimulation.java
 * Date: 2/20/22
 */

// Imports go here
import java.util.Random;
import java.util.Scanner;

// This class runs a simulation of the game of life with any set of specified rules
public class LifeSimulation {
    public static void main(String[] args) throws InterruptedException {

        // Boolean that specifies which set of rules the simulation should be run with
        boolean newRules;
        // String that stores the user's input
        String input;

        // Initializing the scanner
        try (Scanner userInput = new Scanner(System.in)) {
            // Printing out a preference question for the user to answer
            System.out.println("Should this simulation be run with the new version of rules (Yes) or not (No)?");
            input = userInput.nextLine();
        }

        // Checking user imput
        if (input.equalsIgnoreCase("Yes")) {
            newRules = true;
        } else {
            newRules = false;
        }

        // Printing out a usage statement if the program isn't run correctly
        if (args.length < 4) {
            System.out.println("Error: Please run the program again with the desired number of rows, columns, initial board density and the number of iterations as command line arguments.");

        // Running the simulation with arguments from the command line
        } else {
            int numRows = Integer.parseInt(args[0]);
            int numCols = Integer.parseInt(args[1]);
            double boardDensity = Double.parseDouble(args[2]);
            int numIteration = Integer.parseInt(args[3]);

            Landscape landScape = new Landscape(numRows, numCols);
            Random rand = new Random();

            // initialize the grid to be 30% full
            for (int i = 0; i < landScape.getRows(); i++) {
                for (int j = 0; j < landScape.getCols(); j++) {
                    landScape.getCell(i, j).setAlive(rand.nextDouble() <= boardDensity);
                }
            }
            LandscapeDisplay display = new LandscapeDisplay(landScape, 10);

            for (int i = 0; i < numIteration; i++) {
                if (newRules) {
                    landScape.advanceNewRules();
                } else {

                    landScape.advance();
                }
                display.repaint();
                Thread.sleep(250);
            }

        }

    }
}
