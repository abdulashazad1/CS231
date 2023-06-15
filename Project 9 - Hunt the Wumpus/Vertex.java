/**
 * Author: Abdullah Shahzad
 * Date: 5/11/2022
 * Class Section: B Lab: C
 * Project # 10
 */

//Imports go here
import java.util.ArrayList;
import java.util.Comparator;
import java.awt.*;

public class Vertex implements Comparable<Vertex>{
    /**
     * This class represents a room in the game and connects to
     * up to 4 rooms in any cardinal direction (arbitrary)
     */

    // Fields
    private ArrayList<Vertex> adjVertices = new ArrayList<Vertex>(); // To store adjacent vertices
    // Coordinates of the vertex (x,y)
    private int xPos;
    private int yPos;
    private boolean visible; // Whether the room should be visible
    private double cost; // Distance from the Wumpus
    private boolean marked; // Tells if a room has been visited
    private Vertex parent; // Parent on the shortest path back to root
    private Vertex N, S, E, W;

    // Overriding the constructor for the Vertex 
    public Vertex(int x, int y){
        this.xPos = x;
        this.yPos = y;
        this.visible = false;
        this.parent = null;
        this.marked = false;
        this.cost = 0.0;
        this.N = null;
        this.S = null;
        this.E = null;
        this.W = null;
    }

    public Vertex(int x, int y, boolean isVisited) {
        this.xPos = x;
        this.yPos = y;
        this.adjVertices = new ArrayList<Vertex>();
        this.visible = false;
        this.cost = 0;
        this.marked = isVisited;
        this.parent = null;
        this.N = null;
        this.S = null;
        this.E = null;
        this.W = null;
      }
    
    public enum Direction {
        NORTH, SOUTH, EAST, WEST
    }

    // Getter and Setter functions for fields

    // Returns the x pos of the room
    public int getX() {
        return xPos;
    }

    // Returns the y pos of the room
    public int getY() {
        return yPos;
    }

    // Sets the position of the vertex
    public void setPos(Integer newX, Integer newY) {
        xPos = newX;
        yPos = newY;
    }

    // Tells us if the rooom should be visible
    public boolean getVisible() {
        return visible;
    }

    // Sets the visibility of a room
    public void setVisible(boolean vis) {
        visible = vis;
    }

    // Returns the cost of travelling to the Wumpus
    public double getCost() {
        return this.cost;
    }

    // Sets the cost of travelling to the Wumpus
    public void setCost(double newCost) {
        this.cost = newCost;
    }

    // Tells us if this node has been visited
    public boolean isMarked() {
        return this.marked;
    }

    // Marks or unmarks a vertex
    public void setMarked(boolean markStatus) {
        this.marked = markStatus;
    }

    // Returns the parent node
    public Vertex getParent() {
        return this.parent;
    }

    // Sets the parent node
    public void setParent(Vertex newParent) {
        this.parent = newParent;
    }

    // Returns the Euclidian distance between two nodes
    public double distance(Vertex other) {
        double x1 = this.getX();
        double x2 = other.getX();
        double y1 = this.getY();
        double y2 = other.getY();

        double xDis = Math.pow((x2 - x1), 2);
        double yDis = Math.pow((y2 - y1), 2);

        double distance = Math.sqrt(xDis + yDis);

        return distance;
    }

    // Creates a unidirectional link between this vertex and other
    public void connect(Vertex other) {
        if (this.adjVertices.contains(other)) {
        } else {
            this.adjVertices.add(other);
        }
    }

    public void connect(Vertex other, Vertex.Direction dir) {
        switch (dir) {
          case NORTH:
            this.connect(other);
            this.N = other;
            break;
          case SOUTH:
            this.connect(other);
            this.S = other;
            break;
          case EAST:
            this.connect(other);
            this.E = other;
            break;
          case WEST:
            this.connect(other);
            this.W = other;
            break;
        }
      }
    // Returns a vertex at (x,y) from the adjacency list or returns null
    public Vertex getNeighbor(int x, int y) {
        for (Vertex v : this.adjVertices) {
            if (v.getX() == x && v.getY() == y) {
                return v;
            }
        }
        return null;
    }

    // Returns an arraylist which contains all of this vertex's neighbors
    public ArrayList<Vertex> getNeighbors() {
        return this.adjVertices;
    }

    // Returns the number of neighbors that this vertex has
    public int numNeighbors() {
        return this.adjVertices.size();
    }

    // Overriding the toString method
    public String toString() {
        String s = "";
        s += "Number of Neighbors: " + this.numNeighbors() + "\n";
        s += "Vertex Cost: " + this.cost + "\n";
        s += "Marked: " + this.marked;

        return s;
    }

    @Override
    public int compareTo(Vertex o) {
        return (int) (this.getCost() - o.getCost());
    }

    // Returns true if the two vertices match
    public static boolean matchPosition(Vertex a, Vertex b) {
        if (a.getX() == b.getX() && a.getY() == b.getY()) {
            return true;
        } else {
            return false;
        }
    }

    public void draw(Graphics g, int scale) {
        if (!this.visible)
          return;
        int xpos = (int) this.getX() * scale + 20;
        int ypos = (int) this.getY() * scale + 20;
        int border = 2;
        int half = scale / 2;
        int eighth = scale / 8;
        int sixteenth = scale / 16;
    
        // draw rectangle for the walls of the room
        if (this.getCost() <= 2)
          // wumpus is nearby
          g.setColor(Color.red);
        else
          // wumpus is not nearby
          g.setColor(Color.gray);
    
        g.drawRect(xpos + border, ypos + border, scale - 2 * border, scale - 2 * border);
    
        // draw doorways as boxes
        g.setColor(Color.black);
        if (this.getNeighbor(this.getX(), this.getY() - 1) != null)
          g.fillRect(xpos + half - sixteenth, ypos, eighth, eighth + sixteenth);
        if (this.getNeighbor(this.getX(), this.getY() + 1) != null)
          g.fillRect(xpos + half - sixteenth, ypos + scale - (eighth + sixteenth),
              eighth, eighth + sixteenth);
        if (this.getNeighbor(this.getX() - 1, this.getY()) != null)
          g.fillRect(xpos, ypos + half - sixteenth, eighth + sixteenth, eighth);
        if (this.getNeighbor(this.getX() + 1, this.getY()) != null)
          g.fillRect(xpos + scale - (eighth + sixteenth), ypos + half - sixteenth,
            eighth + sixteenth, eighth);
      }

    // Main method to test code
    public static void main(String[] args) {
        Vertex v1 = new Vertex(0, 0);
        Vertex v2 = new Vertex(10, 10);
        Vertex v3 = new Vertex(20, 20);

        v1.connect(v2);
        v1.connect(v3);
        v1.setMarked(false);
        System.out.println(v1.toString());
        v1.setMarked(true);
        System.out.println(v1.toString());
    }
}
