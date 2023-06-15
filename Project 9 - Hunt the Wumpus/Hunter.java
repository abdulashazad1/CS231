/*
Name: Abdullah Shahzad
Date: 5/12/2022
File: Hunter.java
Section: B Lab: C
*/

// Imports go here
import java.awt.Color;
import java.awt.Graphics;

public class Hunter {
    // Fields go here
    private Vertex current;

    // Constructor for the hunter class
    public Hunter(Vertex current) {
        this.current = current;
    }

    // Draw method for the hunter class
    public void draw(Graphics g, int scale) {
        this.current.setVisible(true);
        this.current.draw(g, scale);
        int x = this.current.getX() * scale + 20;
        int y = this.current.getY() * scale + 20;

        g.setColor(Color.gray);
        g.fillOval(x + scale / 2 - scale / 8, y + scale / 2 - scale / 8, scale / 4, scale / 4);
    }

    // Moves the hunter
    public void move(Vertex v) {
        this.current = v;
    }

    // Returns the vertex the hunter is on
    public Vertex getCurrent() {
        return this.current;
    }
}