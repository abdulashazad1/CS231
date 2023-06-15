/**
 * Author: Abdullah Shahzad
 * Project 6
 * Section B Lab C
 * Date: 3/31/2022
 */

import java.util.ArrayList;
import java.awt.*;

public class Landscape {
    int width;
    int height;
    ArrayList<CheckoutAgent> agents;
    LinkedList<Customer> customersDone;

    // Constructor for the landscape class
    public Landscape(int w, int h, ArrayList<CheckoutAgent> checkouts){
        this.width = w;
        this.height = h;
        this.agents = checkouts;
        this.customersDone = new LinkedList<>();
    }

    // Returns the height of the Landscape
    public int getHeight(){
        return this.height;
    }

    // Returns the width of the Landscape
    public int getWidth(){
        return this.width;
    }

    // Overrides the toString method
    public String toString(){
        String str = "";
        str += "Checkouts: " + agents.size() + "/n";
        str += "Finished Customers: " + customersDone.size();
        return str;
    }

    // Adds the finished customer to customersDone
    public void addFinishedCustomer(Customer c){
        customersDone.addLast(c); 
    }

    public void draw(Graphics g){
        for (CheckoutAgent c : agents){
            c.draw(g);
        }
    }

    // Updates checkouts
    public void updateCheckouts(Landscape scape){
        for (CheckoutAgent c : agents){
            c.updateState(scape);
        }
    }

    // Standard deviation calculator given the avg
    public int SD(int avg){
        int numeratorTotal = 0;
        for (Customer c : customersDone){
            numeratorTotal += Math.abs(c.getTime()-avg)^2;
        }
        return numeratorTotal/customersDone.size();
    }

    // Prints out the finished statistics for the simulation
    public void printFinishedCustomerStatistics(){
        int totalTimeStep = 0;
        for (Customer c : customersDone){
            totalTimeStep += c.getTime();
        }
        int avg = totalTimeStep/customersDone.size();
        int SD = SD(avg);

        System.out.println("Average Time: " + avg);
        System.out.println("Standard Deviation: " + SD);
    }
}
