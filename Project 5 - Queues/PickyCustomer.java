/**
 * Author: Abdullah Shahzad
 * Project 6
 * Section B Lab C
 * Date: 3/31/2022
 */

import java.util.ArrayList;

public class PickyCustomer extends Customer{
    // Constructor for the picky customer
    public PickyCustomer(int numItems, int numLines){
        super(numItems, numLines);
    }

    // Method for choosing the line | returns the index of the shortest line
    @Override
    public int chooseLine(ArrayList<CheckoutAgent> checkouts) {
        int shortestIndex = 0;
        for (CheckoutAgent c : checkouts){
            if (c.getNumInQueue() < checkouts.get(shortestIndex).getNumInQueue()){
                shortestIndex = checkouts.indexOf(c);
            }
        }
        return shortestIndex;
    }
    
}
