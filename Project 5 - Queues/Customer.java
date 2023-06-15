/**
 * Author: Abdullah Shahzad
 * Project 6
 * Section B Lab C
 * Date: 3/31/2022
 */

import java.util.ArrayList;

public abstract class Customer {
    // Fields for the customer class
    int numItems;
    int timeStep = 0;

    // Constructor for the customer class
    public Customer (int numItems){
        this.numItems = numItems;
    }

    // Constructor 2 for customer class
    public Customer(int numItems, int timeSteps){
        this.numItems = numItems;
        this.timeStep = timeSteps;
    }

    // Increments the number of time steps
    public void incrementTime(){
        this.timeStep += 1;
    }

    // Returns the number of time steps
    public int getTime(){
        return this.timeStep;
    }

    // Decrements the number of items by 1
    public void giveUpItem(){
        this.numItems -= 1;
    }

    // Returns the number of items 
    public int getNumItems(){
        return this.numItems;
    }

    // Abstract method for deciding how to choose a line
    public abstract int chooseLine(ArrayList<CheckoutAgent> checkouts);
}
