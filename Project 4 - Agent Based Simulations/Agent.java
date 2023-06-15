/**
 * Author: Abdullah Shahzad
 * Section: B Lab: C
 * Project 4
 * Date: 3/16/22
 */

import java.awt.Graphics;

public class Agent {
    double xPos;
    double yPos;

    // Constructor that sets the position of the agent
    public Agent(double x0, double y0){
        xPos = x0;
        yPos = y0;
    }

    // Gets the x coordinate of the position of the agent
    public double getX(){
        return this.xPos;
    }

    // Gets the y coordinate of the position of the agent
    public double getY(){
        return this.yPos;
    }

    // Sets the position of the x coordinate of the agen
    public void setX (double newX){
        this.xPos = newX;
    }

    // Sets the position of the Y coordinate of the agen
    public void setY (double newY){
        this.yPos = newY;
    }

    // Overriding the toString method
    public String toString(){
        String s = ""; // Empty string to add desired strings to
        // Adding desired strings to may (x, y).
        s += "(";
        s += String.valueOf(xPos);
        s += ", ";
        s += String.valueOf(yPos);
        s += ")";

        // Returning s 
        return s;
    }

    public void updateState(Landscape scape){
        return;
    }

    public void draw(Graphics g) {
        return;
    }

    public static void main(String[] args) {
        Agent a=new Agent(2.6, 1.555);
        System.out.println("X: " + a.getX());
        System.out.println("Y: " + a.getY());
        System.out.println("Coordinates: " + a);
    
        System.out.println("changing values now");
    
        a.setX(7.66);
        a.setY(9.677);
        System.out.println("X: " + a.getX());
        System.out.println("Y: " + a.getY());
        System.out.println("Coordinates: " + a);
      }
}
