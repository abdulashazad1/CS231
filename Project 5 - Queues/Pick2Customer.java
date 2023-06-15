/**
 * Author: Abdullah Shahzad
 * Project 6
 * Section B Lab C
 * Date: 3/31/2022
 */

import java.util.ArrayList;
import java.util.Random;

public class Pick2Customer extends Customer {
    // Constructor for the Pick2 Customer
    public Pick2Customer(int numItems){
        super(numItems, 2);
    }

    // Method for choosing two lines at random and then the shorter one of the 2
    @Override
    public int chooseLine(ArrayList<CheckoutAgent> checkouts) {
        // Initializing a random object
        Random rand = new Random();
        // Getting two random ints
        int random1 = rand.nextInt(checkouts.size());
        int random2 = rand.nextInt(checkouts.size());
        // Making sure borth randoms are not the same
        while (random1 == random2){
            random2 = rand.nextInt(checkouts.size());
        }
        if (checkouts.get(random1).getNumInQueue()<=checkouts.get(random2).getNumInQueue()){
            return random1;
        } else {
            return random2;
        }
    }
}
