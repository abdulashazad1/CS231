/**
 * Author: Abdullah Shahzad
 * Project 6
 * Section B Lab C
 * Date: 3/31/2022
 */

import java.util.Random;
import java.util.ArrayList;

public class Pick2CustomerSimulation {
    // test function that creates a new LandscapeDisplay and populates it with 5 checkouts and 99 customers.
    public static void main(String[] args) throws InterruptedException {
        Random gen = new Random();
        ArrayList<CheckoutAgent> checkouts = new ArrayList<CheckoutAgent>(5);

        for(int i=0;i<5;i++) {
            CheckoutAgent checkout = new CheckoutAgent( i*100+50, 480 );
            checkouts.add( checkout );
        }
        Landscape scape = new Landscape(500,500, checkouts);
        LandscapeDisplay display = new LandscapeDisplay(scape);
        
        for (int j = 0; j < 1000; j++) {
            Customer cust = new Pick2Customer(1+gen.nextInt(10));
            int choice = cust.chooseLine( checkouts );
            checkouts.get(choice).addCustomerToQueue( cust );
            scape.updateCheckouts(scape);
            if (scape.customersDone.size() != 0){
                if (scape.customersDone.size()%100 == 0){
                    scape.printFinishedCustomerStatistics();
                    }
                }
            display.repaint();
            Thread.sleep( 250 );
        }

    }

}
