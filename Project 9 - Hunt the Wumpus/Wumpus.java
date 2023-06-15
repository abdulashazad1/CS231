/*
Name: Abdullah Shahzad
Date: 5/12/2022
File: Wumpus.java
Section: B Lab: C
*/

// Imports go here
import java.awt.Color;
import java.awt.Graphics;

public class Wumpus {
    // Imports go here
    private Vertex current;
    private boolean visible;

    // Constructor for Wumpus
    public Wumpus(Vertex current) {
        this.current = current;
        this.visible = false;
    }

    // Draw method for the Wumpus
    public void draw(Graphics g, int scale) throws InterruptedException {
        if (this.visible) {
            int x = this.current.getX() * scale + 20;
            int y = this.current.getY() * scale + 20;

            g.setColor(Color.red);
            g.fillOval(x + scale / 2 - scale / 8, y + scale / 2 - scale / 8, scale / 4, scale / 4);
        }
    }

    // Sets visible to true
    public void setVisible() {
        this.visible = true;
    }

    // Returns visibility status
    public boolean isVisible() {
        return this.visible;
    }

    // Returns this vertex
    public Vertex getCurrent() {
        return this.current;
    }
}