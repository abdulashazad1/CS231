/**
 * Author: Abdullah Shahzad
 * Section: B Lab: C
 * Project 4
 * Date: 3/16/22
 */

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Random;

public class CatSocialAgent extends SocialAgent{
    private int category;

    // Constructor that sets the category of the social agent as well
    public CatSocialAgent(double x0, double y0, int cat) {
        super(x0, y0, 15);
        this.category=cat;
      }
      
      // Constructor that sets all things and the radiusOfSensitivity
      public CatSocialAgent(double x0, double y0, int cat, int radiusOfSensitivity) {
        super(x0, y0, radiusOfSensitivity);
        this.category=cat;
      }
      
      // Returns the category of the agent
      public int getCategory() {
        return this.category;
      }
      
      // Overriding the toString method
      public String toString() {
        return "(" + this.xPos + ", " + this.yPos + ")" + " Category: " + this.category;
      }
      
      // Draws the agent
      public void draw(Graphics g) {
        Graphics2D gr=(Graphics2D) g;
    
        // If category is 0
        // and the cell is moving, color is cyan
        gr.setColor(Color.CYAN);
        gr.setPaint(Color.CYAN);
    
        // If cell has moved, set color to blue
        if(this.moved) {
          gr.setColor(Color.BLUE);
          gr.setPaint(Color.BLUE);      
        }
    
        // If category is 1, the colors will be overwritten
        if(this.getCategory()==1) {
          gr.setColor(Color.GRAY);
          gr.setPaint(Color.GRAY);
          
          if(this.moved) {
            gr.setColor(Color.BLACK);
            gr.setPaint(Color.BLACK);
          }
        }
    
        // Draw circle in circle
        Ellipse2D.Double circle=new Ellipse2D.Double(this.getX(), this.getY(), 10, 10);
        // Fill circle
        gr.fill(circle);
        gr.draw(circle);
      }
    
      public void updateState(Landscape scape) {
        Random gen=new Random();
        this.moved=false;
        ArrayList<Agent> neighbours=scape.getNeighbors(this.getX(), this.getY(), this.getRadius());
    
        // If >=2 neighbours and more are of the same category
        if(neighbours.size()>1 && moreSameCategory(neighbours)) {
          // With a 1% probability
          if(gen.nextInt(100) == 0) {
            // Sets moved to true and move it
            this.moved=true;
            this.setX(this.getX()+gen.nextDouble(-10, 10));
            this.setY(this.getY()+gen.nextDouble(-10, 10));
          }
        }
        else {
          // Sets moved to true and move it
          this.moved=true;
          this.setX(this.getX()+gen.nextDouble(-10, 10));
          this.setY(this.getY()+gen.nextDouble(-10, 10));
        }
      }
    
      private boolean moreSameCategory(ArrayList<Agent> neighbours) {
        int counter=0;
    
        // Iterate over neighbours
        for(Agent e: neighbours) {
          // If same category as given CatSocialAgent
          if(((CatSocialAgent) e).getCategory()==this.getCategory()) {
            counter++;
          }
        }
    
        // Return if number of elements w/ category same as this is more than remaining elements
        return counter>neighbours.size()-counter;
      }

      // Main method to test CatSocialAgent
      public static void main(String[] args) {
        CatSocialAgent a=new CatSocialAgent(1, 1, 0, 15);
        CatSocialAgent b=new CatSocialAgent(2, 2, 0, 15);
        CatSocialAgent c=new CatSocialAgent(14, 14, 0, 15);
        CatSocialAgent d=new CatSocialAgent(5, 5, 1, 15);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
    
        ArrayList<Agent> neighbours=new ArrayList<Agent>();
        neighbours.add(a);
        neighbours.add(b);
        neighbours.add(c);
        neighbours.add(d);
    
        System.out.println("More of same category as first agent? " + a.moreSameCategory(neighbours));
    
        System.out.println("Change category of third agent to 1");
        c=new CatSocialAgent(14, 14, 1, 15);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        neighbours=new ArrayList<Agent>();
        neighbours.add(a);
        neighbours.add(b);
        neighbours.add(c);
        neighbours.add(d);
    
        System.out.println("More of same category as first cell? " + a.moreSameCategory(neighbours));
      }
}
