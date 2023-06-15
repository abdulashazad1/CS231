/**
 * Author: Abdullah Shahzad
 * Project 6
 * Section B Lab C
 * Date: 3/31/2022
 */

import java.util.ArrayList;
import java.util.Random;

public class RandomCustomer extends Customer{

    // Constructor for the Random Customer class
    public RandomCustomer(int numItems){
        super(numItems);
    }

    @Override
    public int chooseLine(ArrayList<CheckoutAgent> checkouts) {
        Random rand = new Random();
        return rand.nextInt(checkouts.size());
    }
}
