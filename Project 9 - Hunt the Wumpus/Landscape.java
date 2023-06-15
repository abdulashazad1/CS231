/**
 * Author: Abdullah Shahzad
 * Section: B Lab: C
 * Project 4
 * Date: 3/16/22
 */

import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;

public class Landscape {
    private int w;
    private int h;
    private Hunter hunter;
    private Wumpus wumpus;
    private LinkedList<Vertex> vertexList;
    
    // Constructor that initializes all the fields
    public Landscape(int w, int h, Hunter hunter, Wumpus wumpus) {
        this.w = w;
        this.h = h;
        vertexList = new LinkedList<Vertex>();
        this.hunter = hunter;
        this.wumpus = wumpus;

      }
    
    // Returns the height
    public int getHeight(){
        return h;
    }

    // Returns the width
    public int getWidth(){
        return w;
    }

    public void draw(Graphics g, int scale, int state) throws InterruptedException {
      for(Vertex v : vertexList) {
        v.draw(g, scale);
      }
      hunter.draw(g, scale);
      wumpus.draw(g, scale);
  
      if(state==1) {
        g.setColor(Color.black);
        g.fillRect(0, 0, g.getClipBounds().width, g.getClipBounds().height);
        g.setColor(Color.red);
        g.drawString("You lost because the Wumpus ate your hunter!", g.getClipBounds().width / 2 - g.getFontMetrics().stringWidth("You lost because the Wumpus ate your hunter!") / 2, g.getClipBounds().height / 2);
      }
  
      if(state==2) {
        g.setColor(Color.black);
        g.fillRect(0, 0, g.getClipBounds().width, g.getClipBounds().height);
        g.setColor(Color.green);
        g.drawString("You Won!", g.getClipBounds().width / 2 - g.getFontMetrics().stringWidth("You Won!") / 2, g.getClipBounds().height / 2);
      }
  
      if(state==3) {
        g.setColor(Color.black);
        g.fillRect(0, 0, g.getClipBounds().width, g.getClipBounds().height);
        g.setColor(Color.red);
        g.drawString("You shot the arrow in an empty room!", g.getClipBounds().width / 2 - g.getFontMetrics().stringWidth("You shot the arrow in an empty room!") / 2, g.getClipBounds().height / 2);
      }
    }
  
    public void addBackgroundAgent(Vertex v1) {
      this.vertexList.addLast(v1);
    }
  }
