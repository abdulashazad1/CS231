/*
Name: Abdullah Shahzad
Date: 03/12/2022
File: Board.java
Section: B Lab: C
*/

import java.awt.*;

public class Cell {
  // Initializing private variables
  private int row, col, value;
  private boolean isLocked;

  // Overriding the default constructor of the cell
  public Cell() {
    this.row=this.col=this.value=0;
    this.isLocked=false;
  }

  // Another constructor that makes the cell at (r,c) with value = value
  public Cell(int row, int col, int value) {
    this.row=row;
    this.col=col;
    this.value=value;
    this.isLocked=false;
  }

  // Another constructor that makes the cell at (r,c) with value = value and locked = locked
  public Cell(int row, int col, int value, boolean locked) {
    this.row=row;
    this.col=col;
    this.value=value;
    this.isLocked=locked;
  }

  // Returns the row of the cell
  public int getRow() {
    return this.row;
  }
  
  // Returns the column the cell is in
  public int getCol() {
    return this.col;
  }

  // Returns the value of the cell
  public int getValue() {
    return this.value;
  }

  // Sets the value of the cell to val
  public void setValue(int val) {
    // Does nothing if value is locked
    if(!this.isLocked)
      this.value=val;
  }

  // Returns true if the cell is locked
  public boolean isLocked() {
    return this.isLocked;
  }

  // Locks or unlocks the cell
  public void setLocked(boolean lock) {
    this.isLocked=lock;
  }

  // Makes a clone of the cell
  public Cell clone() {
    return new Cell(this.row, this.col, this.value, this.isLocked);
  }

  public void draw(Graphics g, int x0, int y0, int scale) {
    char[] draw={(char)('0' + this.value), 0};
    g.drawChars(draw, 0, 1, x0*scale, y0*scale);
  }

  public String toString() {
    return String.valueOf(this.value);
  }

  // Main method to test the cell class
  public static void main(String[] args) {
    Cell a=new Cell(0, 7, 5, true);
    System.out.println("Row: " + a.getRow());
    System.out.println("Col: " + a.getCol());
    System.out.println("Value: " + a.getValue());
    System.out.println("Locked: " + a.isLocked());

    a.setLocked(true);
    a.setValue(5);

    System.out.println("Value: " + a.getValue());
    System.out.println("Locked: " + a.isLocked());
  }
}
