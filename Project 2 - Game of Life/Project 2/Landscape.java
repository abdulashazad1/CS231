/**
 * Project 2
 * Author: Abdullah Shahzad
 * Coursse and Section: CS231 B & Lab C 
 * File: Landscape.java
 * Date: 2/20/22
 */

// Imports go here:
import java.awt.Graphics;
import java.util.ArrayList;

public class Landscape {

    // Fields for the number of rows and columns
    int Rows;
    int Columns;

    // 2D grid to hold the cells
    Cell[][] Landscape;

    // Constructor for the Landscape class
    public Landscape(int rows, int cols) {
        Rows = rows;
        Columns = cols;
        Landscape = new Cell[Rows][Columns];

        for (int i = 0; i < Rows; i++) {
            for (int j = 0; j < Columns; j++) {
                Cell newCell = new Cell();
                Landscape[i][j] = newCell;
            }
        }
    }

    // Calls the reset method for each cell in the grid
    public void reset() {
        for (int i = 0; i < Rows; i++) {
            for (int j = 0; j < Columns; j++) {
                Landscape[i][j].reset();
            }
        }
    }

    // Returns the number of rows in the grid
    public int getRows() {
        return Rows;
    }

    // Returns the number of columns in the grid
    public int getCols() {
        return Columns;
    }

    // Returns a reference to the cell at (row, col)
    public Cell getCell(int row, int col) {
        return Landscape[row][col];
    }

    // Uses the cell toString method implicitly to make
    // toString for the entire grid
    public String toString() {
        String neighborhood = "";
        for (int i = 0; i < Rows; i++) {
            for (int j = 0; j < Columns; j++) {
                neighborhood += Landscape[i][j].toString();
            }
            neighborhood += "\n";
        }
        return neighborhood;
    }

    // Returns an Arraylist of references for the neighbors of the Cell at (row,col)
    public ArrayList<Cell> getNeighbors(int row, int col) {

        ArrayList<Cell> neighbors = new ArrayList<Cell>();

        for (int i = row - 1; i < row + 2; i++) {
            for (int j = col - 1; j < col + 2; j++) {
                if (i >= 0 && i <= Rows - 1 && j >= 0 && j <= Columns - 1) {
                    if (getCell(i, j) != getCell(row, col)) {
                        neighbors.add(getCell(i, j));
                    }
                }
            }
        }
        return neighbors;
    }

    // Draws a landscape by drawing all the cells
    public void draw(Graphics g, int gridScale) {
        for (int i = 0; i < Rows; i++) {
            for (int j = 0; j < Columns; j++) {
                Landscape[i][j].draw(g, i * gridScale, j * gridScale, gridScale);
            }
        }
    }

    // Moves all the cells forward one generation
    public void advance() {
        Cell[][] newLs = new Cell[Rows][Columns];

        for (int i = 0; i < Rows; i++) {
            for (int j = 0; j < Columns; j++) {
                Cell newCell = new Cell(Landscape[i][j].getAlive());
                newLs[i][j] = newCell;
            }
        }

        for (int i = 0; i < Rows; i++) {
            for (int j = 0; j < Columns; j++) {

                newLs[i][j].updateState(getNeighbors(i, j));

            }
        }

        Landscape = newLs;
    }

    // Advances the game according to the new rules
    public void advanceNewRules() { // extension
        Cell[][] newLs = new Cell[Rows][Columns];

        for (int i = 0; i < Rows; i++) {
            for (int j = 0; j < Columns; j++) {
                Cell newCell = new Cell(Landscape[i][j].getAlive());
                newLs[i][j] = newCell;
            }
        }

        for (int i = 0; i < Rows; i++) {
            for (int j = 0; j < Columns; j++) {
                newLs[i][j].updateStateNewRules(getNeighbors(i, j));
            }
        }
        Landscape = newLs;
    }

    // Main method to test the landscape class
    public static void main(String[] args) {
        Landscape land = new Landscape(20, 20);
        System.out.println(land);
        System.out.println(land.getNeighbors(19, 19));

    }

}
