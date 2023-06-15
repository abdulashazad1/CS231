/**
 * Author: Abdullah Shahzad
 * Project 6
 * Section B Lab C
 * Date: 3/31/2022
 */

import java.util.ArrayList;
import java.util.Random;

public class Pick3Customer extends Customer {
    // Constructor for the Pick3 Customer
    public Pick3Customer(int numItems){
        super(numItems, 3);
    }

    // Method for choosing two lines at random and then the shorter one of the 2
    @Override
    public int chooseLine(ArrayList<CheckoutAgent> checkouts) {
        // Initializing a random object
        Random rand = new Random();
        // Getting two random ints
        int random1 = rand.nextInt(checkouts.size());
        int random2 = rand.nextInt(checkouts.size());
        int random3 = rand.nextInt(checkouts.size());
        // Making sure borth randoms are not the same
        while (random2 == random1 || random2 == random3){
            random2 = rand.nextInt(checkouts.size());
        }
        // Making sure 3rd random is not the same
        while (random3 == random1 || random3 == random2){
            random2 = rand.nextInt(checkouts.size());
        }
        if (checkouts.get(random1).getNumInQueue()<=checkouts.get(random2).getNumInQueue()){
            if (checkouts.get(random1).getNumInQueue()<=checkouts.get(random3).getNumInQueue()){
                return random1;
            } else {
                return random3;
            }
        } else {
            if (checkouts.get(random2).getNumInQueue()<=checkouts.get(random3).getNumInQueue()){
                return random2;
            } else {
                return random3;
            }
        }
    }
}
