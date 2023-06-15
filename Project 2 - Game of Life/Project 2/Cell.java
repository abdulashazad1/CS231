/**
 * Project 2
 * Author: Abdullah Shahzad
 * Coursse and Section: CS231 B & Lab C 
 * File: Cell.java
 * Date: 2/20/22
 */

// Imports go here:
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Cell {

    // Variable storing the state of the cell; whether its alive or dead
    private boolean alive;

    // Constructor method of the cell; the cell is dead by default
    public Cell() {
        alive = false;
    }

    // Another constructor that allows you to set the cell's state as a parameter
    public Cell(boolean alive) {
        this.alive = alive;
    }

    // Returns whether the cell is alive or dead
    public boolean getAlive() {
        return alive;
    }

    // Resets the cell (to dead)
    public void reset() {
        alive = false;
    }

    // Method to change the state of the cell
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    // This method returns 1 if the cell is alive and 0 otherwise
    public String toString() {
        if (alive == true) {
            return "1";
        } else {
            return "0";
        }
    }

    // Draws the cell on the graphics object
    public void draw(Graphics g, int x, int y, int scale) {
        g.drawOval(x, y, 1, 1);
        if (alive) {
            g.setColor(Color.YELLOW);
        } else {
            g.setColor(Color.DARK_GRAY);
        }
        g.fillOval(x, y, scale, scale);

    }

    // This method updates the state of the cell in the next timestep
    public void updateState(ArrayList<Cell> neigbors) {
        int aliveNum = 0;

        for (Cell i : neigbors) {
            if (i.getAlive()) {
                aliveNum++;
            }
        }

        if (alive) {
            if (aliveNum == 1 || aliveNum == 2) {
                alive = true;
            } else {
                alive = false;
            }
        } else {
            if (aliveNum == 3) {
                alive = true;
            } else {
                alive = false;
            }
        }

    }

    // Updates the state of the cells according to the new rules
    public void updateStateNewRules(ArrayList<Cell> neigbors) {
        int aliveNum = 0;

        for (Cell i : neigbors) {
            if (i.getAlive()) {
                aliveNum++;
            }
        }

        if (alive) {
            if (aliveNum == 2 || aliveNum == 3) {
                alive = false;
            } else {
                alive = false;
            }
        } else {
            if (aliveNum == 2) {
                alive = true;
            } else {
                alive = false;
            }
        }

    }

    // Main method to test the Cell class
    public static void main(String[] args) {
        Cell myCell = new Cell(true);
        System.out.println(myCell);
        System.out.println(myCell.getAlive());
        myCell.setAlive(false);
        System.out.println(myCell.getAlive());
        System.out.println(myCell);
    }

}
