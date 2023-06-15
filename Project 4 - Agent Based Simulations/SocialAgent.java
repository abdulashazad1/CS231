/**
 * Author: Abdullah Shahzad
 * Section: B Lab: C
 * Project 4
 * Date: 3/16/22
 */

import java.awt.*;
import java.awt.geom.Ellipse2D;

import java.util.Random;

public class SocialAgent extends Agent{
    public boolean moved;
    private int radius;
    // A constructor that calls the superclass constructor
    public SocialAgent(double x0, double y0, int radius) {
        super(x0, y0);
        this.radius = radius;
    }
    
    // Sets the cells radius of sensitivity to rad
    public void setRadius (int rad){
        radius = rad;
    }

    // Returns the cell's radius of sensitivity
    public int getRadius(){
        return radius;
    }

    // Draws the circles of different colors
    public void draw(Graphics g) {
        Graphics2D gr=(Graphics2D) g;
    
        // If cell is moving, color is cyan
        gr.setColor(Color.CYAN);
        gr.setPaint(Color.CYAN);
    
        // If cell has moved, set color to blue
        if(this.moved) {
          gr.setColor(Color.BLUE);
          gr.setPaint(Color.BLUE);
        }
    
        // Draw circle in circle
        Ellipse2D.Double circle=new Ellipse2D.Double(this.getX(), this.getY(), 10, 10);
        // Fill circle
        gr.fill(circle);
        gr.draw(circle);
      }
      
      // Updates the state of the circle
      public void updateState(Landscape scape) {
        Random gen=new Random();
        this.moved=false;
    
        // If > 3 neighbours in radius
        if(scape.getNeighbors(this.getX(), this.getY(), this.getRadius()).size()>3) {
          // with a 1% probability to move
          if(gen.nextInt(100) == 0) {
            this.moved=true;
            this.setX(this.getX()+gen.nextDouble(-10, 10));
            this.setY(this.getY()+gen.nextDouble(-10, 10));
          }
        }
        // Otherwise, it'll move
        else {
          this.moved=true;
          this.setX(this.getX()+gen.nextDouble(-10, 10));
          this.setY(this.getY()+gen.nextDouble(-10, 10));
        }
      }

      public static void main(String[] args) {
        SocialAgent a=new SocialAgent(1.7, 2.555, 5);
        System.out.println("X: " + a.getX());
        System.out.println("Y: " + a.getY());
        System.out.println("Coordinates: " + a);
        System.out.println("Radius: " + a.getRadius());
    
        System.out.println("changing values now");
    
        a.setX(7.66);
        a.setY(9.677);
        a.setRadius(20);
        System.out.println("X: " + a.getX());
        System.out.println("Y: " + a.getY());
        System.out.println("Coordinates: " + a);
        System.out.println("Radius: " + a.getRadius());
      }
}
