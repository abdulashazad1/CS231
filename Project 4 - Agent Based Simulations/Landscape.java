/**
 * Author: Abdullah Shahzad
 * Section: B Lab: C
 * Project 4
 * Date: 3/16/22
 */

import java.util.ArrayList;
import java.awt.Graphics;

public class Landscape {
    private int w;
    private int h;
    private LinkedList<Agent> list;
    
    // Constructor that initializes all the fields
    public Landscape(int w, int h) {
        this.w = w;
        this.h = h;
        list = new LinkedList<Agent>();
      }
    
    // Returns the height
    public int getHeight(){
        return h;
    }

    // Returns the width
    public int getWidth(){
        return w;
    }

    // Inserts an agent at the beginning of the list of agents
    public void addAgent(Agent a){
        list.addFirst(a);
    }

    // Overriding the toString method
    public String toString(){
        return ""+list.size();
    }

    // Returns a list with all the cells in proximity to x0 and y0
    public ArrayList<Agent> getNeighbors(double x0, double y0, double radius){
        ArrayList<Agent> inProximity = new ArrayList<Agent>();

        for (Agent agent : this.list){
          if (agent != null){
            double distance = this.calcDistance(x0, y0, agent.getX(), agent.getY());
            if (radius>distance){
                inProximity.add(agent);}
            }
        }
        return inProximity;
    }

    // Uses the distance formula to calculate the distance between the circles
    private double calcDistance(double x0, double y0, double x1, double y1) {
        double x=x1-x0;
        double y=y1-y0;
        x=Math.pow(x, 2);
        y=Math.pow(y, 2);
    
        return Math.sqrt(x+y);
    }


    // Draws the cell
    public void draw(Graphics g){
        for(Agent e: this.list) {
            e.draw(g);
          }
    }

    // Update Cell method
    public void updateAgents() {
        // Get a shuffled list from the current list
        ArrayList<Agent> shuffled=list.toShuffledList();
    
        // Create new landscape
        Landscape scape=new Landscape(500, 500);
    
        // Add agents to landscape
        for(Agent e: shuffled) {
          scape.addAgent(e);
        }
        // Update all agents
        for(Agent e: shuffled) {
          e.updateState(scape);
        }
      }


      public static void main(String[] args) {
        Landscape scape=new Landscape(500, 500);
        SocialAgent a=new SocialAgent(5, 5, 5),
        b=new SocialAgent(10, 10, 5),
        c=new SocialAgent(15, 15, 5),
        d=new SocialAgent(20, 20, 5);
        scape.addAgent(a);
        scape.addAgent(b);
        scape.addAgent(c);
        scape.addAgent(d);
    
        a.updateState(scape);
        System.out.println("Neighbours in 10: " + (scape.getNeighbors(5, 5, 10).size()));
    
        System.out.println("Resetting");
        a=new SocialAgent(5, 5, 5);
        b=new SocialAgent(10, 10, 5);
        c=new SocialAgent(15, 15, 5);
        d=new SocialAgent(20, 20, 5);
        a.updateState(scape);
        System.out.println("Neighbours in 15: " + (scape.getNeighbors(5, 5, 15).size()));
    
        System.out.println("Resetting");
        a=new SocialAgent(5, 5, 5);
        b=new SocialAgent(10, 10, 5);
        c=new SocialAgent(15, 15, 5);
        d=new SocialAgent(20, 20, 5);
        a.updateState(scape);
        System.out.println("Neighbours in 25: " + (scape.getNeighbors(5, 5, 25).size()));
      }
    }
