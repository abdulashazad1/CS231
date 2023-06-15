
/**
 * Author: Abdullah Shahzad
 * Project 6
 * Section B Lab C
 * Date: 3/31/2022
 */

import java.util.ArrayList;

public class ItemCounter extends Customer {
    // Total item counter / for timesteps
    public static int totalCounter(ArrayList<CheckoutAgent> checkouts) {
        int totalItems = 0;
        for (int i = 0; i < checkouts.size(); i++) {
            for (Customer c : checkouts.get(i).que) {
                totalItems += c.numItems;
            }
        }
        return totalItems;
    }

    // Constructor for the item counting customer
    public ItemCounter(int numItems, ArrayList<CheckoutAgent> checkouts) {
        super(numItems, totalCounter(checkouts));
    }

    public int chooseLine(ArrayList<CheckoutAgent> checkouts) {
        int smallest = 1000;
        int countPerLine = 0;
        int smallestIndex = 9999; // Unrealistic index number

        for (int i = 0; i < checkouts.size(); i++) {
            countPerLine = 0;
            for (Customer c : checkouts.get(i).que) {
                countPerLine += c.numItems;
            }
            if (countPerLine < smallest) {
                smallest = countPerLine;
                smallestIndex = i;
            }
        }
        return smallestIndex;
    }

}
