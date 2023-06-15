/*
Name: Abdullah Shahzad
Date: 03/12/2022
File: Board.java
Section: B Lab: C
*/

public class CellStack {
  // Initializing the stack and the top index
  Cell[] stack;
  int pos;

  // Overriding the default constructor
  CellStack() {
    this.stack=new Cell[10];
    this.pos=-1;
  }

  // Constructor but it makes the array of length = max
  CellStack(int max) {
    this.stack=new Cell[max]; 
    this.pos=-1;
  }

  // Pushes a cell onto the stack
  public void push(Cell c) {
    // If the stack is full, creates a stack of double the size
    if(this.pos==this.stack.length-1) {
      Cell[] temp=new Cell[this.stack.length*2];
      for(int i=0; i<this.stack.length; i++) {
        temp[i]=this.stack[i];
      }
      // Making the temp stack the original stack
      this.stack=temp;
    }
    // Just adding the cell to the stack if it isn't full
    this.stack[++this.pos]=c;
  }

  // Pops the cell off the top of the stack
  public Cell pop() {
    if(this.pos>-1) {
      return this.stack[this.pos--];
    }
    // if stack is empty return invalid cell
    return new Cell(-1, -1, -1, false);
  }

  public int size() {
    return this.pos+1;
  }

  public boolean empty() {
    return this.pos==-1;
  }

  // Testing the stack
  public static void main(String[] args) {
    CellStack s=new CellStack(10);
    System.out.println("Size: " + s.size());
    System.out.println("Empty: " + s.empty());

    System.out.println("Pushing");
    s.push(new Cell(0,0,5));
    System.out.println("Size: " + s.size());
    System.out.println("Empty: " + s.empty());

    System.out.println("Popping");
    s.pop();
    System.out.println("Size: " + s.size());
    System.out.println("Empty: " + s.empty());
  }
}
