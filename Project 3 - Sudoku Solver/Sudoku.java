/*
Name: Abdullah Shahzad
Date: 03/12/2022
File: Board.java
Section: B Lab: C
*/

import java.util.Random;


public class Sudoku {
  // Initializing the board and the display
  private Board magicSquare;
  private LandscapeDisplay display;

  // Overriding the default constructor to make an empty board (all entries = 0)
  Sudoku() {
    magicSquare=new Board();
    display=new LandscapeDisplay(magicSquare, 30);
  }

  // Constructor that randomly fills out N boxes with valid values
  Sudoku(final int N) {
    magicSquare=new Board();
    display=new LandscapeDisplay(magicSquare, 30);

    Random generator=new Random();

    int counter=0;

    // While less than N values
    while(counter<N) {
      // Generate random values for row col and value
      int randomRow=generator.nextInt(this.magicSquare.getRows());
      int randomCol=generator.nextInt(this.magicSquare.getCols());
      int randomValue=generator.nextInt(9)+1;

      // If cell is empty and value is valid, assign and increment counter
      if(this.magicSquare.get(randomRow, randomCol).getValue()==0 
      && this.magicSquare.validValue(randomRow, randomCol, randomValue)) {
        this.magicSquare.set(randomRow, randomCol, randomValue, true);
        counter++;
      }
    }
  }

  // Method to solve the board or backtrack when possible and necessary
  public boolean solve(int delay) {
    int numFree=81-this.magicSquare.numLocked();
    CellStack stack=new CellStack(numFree);

    // While stack is smaller than number of free cells
    while(stack.size()<numFree) {


      if( delay > 0 ) {
        try {
            Thread.sleep(delay);
        }
        catch(InterruptedException ex) {
            System.out.println("Interrupted");
        }
        display.repaint();
      }



      // Find the next best cell
      Cell next=this.nextBestCell();

      // If next Cell exists and its value is valid
      if(next!=null && this.nextValidValue(next)<10) {
        // Pushing the cell onto the stack and updating the board
        stack.push(next);
        this.updateBoard(next);
      } else { // While the game isn't solved and the stack isn't empty
        while(!this.magicSquare.validSolution() && !stack.empty()) {
          // Popping a cell and getting the next possible value for the cell
          Cell popped=stack.pop();
          int value=this.nextValidValue(popped);

          if(value<10) {
            // Updating the value of the popped cell and pushing it onto the stack
            popped.setValue(value);
            stack.push(popped);
            break;
          }
          // If value is invalid, sets the value of popped = 0
          else {
            popped.setValue(0);
          }
        }
        // If stack is empty, no more backtracking is possible, so returns false
        if(stack.empty()) {
          return false;
        }
      }
    }

    // The board's been solved so returns true
    return true;
  }

  // Chooses the next best cell to inspect for a valid value placement
  private Cell nextBestCell() {
    Cell best=null;
    int numOfSolutions=9;

    for(int row=0; row<this.magicSquare.getRows(); row++) {
      for(int col=0; col<this.magicSquare.getCols(); col++) {
        // Gets cell at (row,col)
        Cell temp=this.magicSquare.get(row, col);

        // If the cell is locked or if it is already filled, it is skipped
        if(temp.isLocked() || temp.getValue()!=0) {
          continue;
        }

        // Counts the number of solutions
        int tempNumOfSolutions=0;
        for(int value=1; value<10; value++) {
          if(this.magicSquare.validValue(row, col, value)) {
            tempNumOfSolutions++;
          }
        }

        // If the cell at (r,c) has fewer solutions than the previous cell, it is made the next best cell
        if(tempNumOfSolutions<numOfSolutions) {
          best=temp;
          numOfSolutions=tempNumOfSolutions;
        }
      }
    }
    // Returns the best cell
    return best;
  }

  // Method to update the board
  private boolean updateBoard(Cell c) {
    int value=nextValidValue(c);

    // If value is valid, return true and set value
    if(value<10) {
      c.setValue(value);
      return true;
    }
    else {
      return false;
    }
  }

  // Returns the next possible valid value
  private int nextValidValue(Cell temp) {
    int row=temp.getRow();
    int col=temp.getCol();

    int value=0;

    // Checks values from present value+1 till 9
    for(value=temp.getValue()+1; value<10; value++) {
      // If value is valid, break
      if(this.magicSquare.validValue(row, col, value)) {
         break;
      }
    }

    // Returns the other <10 value that's possible
    return value;
  }

  // Main method to check the sudoku class
  public static void main(String[] args) {
    int startNumber=20;
    // If a command line argument is passed, use it as no of locked Cells
    if(args.length>0) {
      startNumber=Integer.parseInt(args[0]);
    }

    Sudoku s=new Sudoku(startNumber);
    System.out.println(s.magicSquare);
    // Recording the start time before the solve method is called
    long start = System.currentTimeMillis();
    s.solve(10);
    // Recording the end time after the solve method was done
    System.out.println(s.magicSquare);
    long end = System.currentTimeMillis();
    System.out.println(end-start);
  }
}
