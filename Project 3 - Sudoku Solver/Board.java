/*
Name: Abdullah Shahzad
Date: 03/12/2022
File: Board.java
Section: B Lab: C
*/

import java.io.*;
import java.awt.*;

public class Board {

  private Cell[][] grid;
  public static final int DIMENSIONS=9;

  // Overriding the default constructor
  Board() {
    // Initializing a 2d array
    this.grid=new Cell[Board.DIMENSIONS][Board.DIMENSIONS];

    // Looping over the grid
    for(int i=0; i<Board.DIMENSIONS; i++) {
      for(int j=0; j<Board.DIMENSIONS; j++) {
        // Creating a new cell
        this.grid[i][j]=new Cell(i, j, 0);
      }
    }
  }

  //Read method to read sudoku board from .txt files
  public boolean read(String filename) {
    try {
      // assign to a variable of type FileReader a new FileReader object, passing filename to the constructor
      // assign to a variable of type BufferedReader a new BufferedReader, passing the FileReader variable to the constructor
      FileReader fr=new FileReader(filename);
      BufferedReader br=new BufferedReader(fr);

      // assign to a variable of type String line the result of calling the readLine method of your BufferedReader object.
      String line=br.readLine();

      int row=0;

      // start a while loop that loops while line isn't null
      while(line!=null) {
        // assign to an array of type String the result of calling split on the line with the argument "[ ]+"
        String[] split=line.split("[ ]+");

        // create cells in row row
        for(int j=0; j<this.getCols(); j++) {
          // create new cell at row,j with the value read from the board and set locked
          this.grid[row][j]=new Cell(row, j, Integer.parseInt(split[j]), Integer.parseInt(split[j])!=0);
        }

        row++;
        line=br.readLine();
      }

      // call the close method of the BufferedReader
      // return true
      br.close();
      return true;
    }
    catch(FileNotFoundException ex) {
      System.out.println("Board.read():: unable to open file " + filename );
    }
    catch(IOException ex) {
      System.out.println("Board.read():: error reading file " + filename);
    }

    return false;
  }

  // Returns the number of rows in the grid
  public int getRows() {
    return this.grid.length;
  }

  // Returns the number of columns in the grid
  public int getCols() {
    return this.grid[0].length;
  }

  //Returns the cell at (r,c)
  public Cell get(int r, int c) {
    return grid[r][c];
  }

  // Returns true if the cell is locked
  public boolean isLocked(int r, int c) {
    return this.grid[r][c].isLocked();
  }
  
  // Returns the number of locked cells in the grip
  public int numLocked() {
    int countLocked=0;
    
    // Looping over the grid
    for(int i=0; i<this.getRows(); i++) {
      for(int j=0; j<this.getCols(); j++) {
        // Counting locked cells
        if(this.isLocked(i, j)) {
          countLocked++;
        }
      }
    }

    return countLocked;
  }

  // Returns the value of the cell at (r,c)
  public int value(int r, int c) {
    return this.grid[r][c].getValue();
  }

  // Sets the value of the cell at (r,c)
  public void set(int r, int c, int value) {
    this.grid[r][c].setValue(value);
  }

  // Sets the value and the locked status of the cell at (r,c)
  public void set(int r, int c, int value, boolean locked) {
    this.grid[r][c].setValue(value);
    this.grid[r][c].setLocked(locked);
  }

  // Checks if a value is valid if placed at (r,c)
  public boolean validValue(int row, int col, int value) {


    // Seeing if the value exists in the row
    for(int i=0; i<this.getCols(); i++) {
      if(i==col) {
        continue;
      }
      if(this.grid[row][i].getValue()==value) {
        return false;
      }
    }

    // Seeing if the value exists in the column
    for(int i=0; i<this.getRows(); i++) {
      if(i==row) {
        continue;
      }
      if(this.grid[i][col].getValue()==value) {
        return false;
      }
    }

    // Checking if the value exists in the local 3x3 square
    int topBound=row-(row%3);
    int leftBound=col-(col%3);

    for(int i=topBound; i<topBound+3; i++) {
      for(int j=leftBound; j<leftBound+3; j++) {
        if(i==row && j==col) {
          continue;
        }
        if(this.grid[i][j].getValue()==value) {
          return false;
        }
      }
    }

    return true;
  }


  // Checking if a filled out board is a valid solution to the game
  public boolean validSolution() {
    for(int i=0; i<this.getRows(); i++) {
      for(int j=0; j<this.getCols(); j++) {
        // If value 0, invalid. return false
        if(this.grid[i][j].getValue()==0)
          return false;
        // If value invalid, return false
        if(!this.validValue(i, j, this.grid[i][j].getValue())) {
          return false;
        }
      }
    }
    return true;
  }

  // Overriding the ToString function to present the board
  public String toString() {
    String result="";
    
    // Looping over grid
    for(int i=0; i<Board.DIMENSIONS; i++) {
      for(int j=0; j<Board.DIMENSIONS; j++) {
        // Adding to the result
        result+=this.grid[i][j]+" ";

      // Adding a line between 3x3 local squares 
        result+=(j==2 || j==5)?"| ":"";
      }
      result+=(i==2 || i==5)?"\n------|-------|------":"";
      result+='\n';
    }

    return result;
  }

  // Drawing the board
  public void draw(Graphics g, int scale) {
    for(int row=0; row<this.getRows(); row++) {
      for(int col=0; col<this.getCols(); col++) {
        this.grid[row][col].draw(g, col+1, row+1, scale);
      }
    }
  }

  // Main function to test the board class
  public static void main(String[] args) {
    Board tester=new Board();
    String file="test.txt";
    if(args.length>0)
      file=args[0];
    
    tester.read(file);

    tester.set(0, 0, 100);
    System.out.println(tester.get(0, 0));
    System.out.println(tester.validValue(0, 0, 100));

    System.out.println(tester);
    System.out.println(tester.validSolution());
  }
}
