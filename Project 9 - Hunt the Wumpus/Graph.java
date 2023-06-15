import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Author: Abdullah Shahzad
 * Date: 5/11/2022
 * Class Section: B Lab: C
 * Project # 10
 */

public class Graph {
    ArrayList<Vertex> list = new ArrayList<Vertex>();

    // Returns the number of vertices in the graph
    public int vertexCount(){
        return list.size();
    }

    // Returns true if the vertex is in the graph; false otherwise
    public boolean inGraph(Vertex query){
        if (this.list.contains(query)){
            return true;
        } else {
            return false;
        }
    }

    // Adds two vertices to the graph and connects v1 to v2
    public void addUniEdge(Vertex v1, Vertex v2){
        if (inGraph(v1)){
        } else {
            this.list.add(v1);
        }

        if (inGraph(v2)){
        } else {
            this.list.add(v2);
        }

        v1.connect(v2);
    }

    // Adds two vertices to the graph and connects v1 and v2 (both)
    public void addBiEdge(Vertex v1, Vertex v2){
        if (inGraph(v1)){
        } else {
            this.list.add(v1);
        }

        if (inGraph(v2)){
        } else {
            this.list.add(v2);
        }

        v1.connect(v2);
        v2.connect(v1);
    }

    // Shortest path implementation
    public void shortestPath(Vertex v0){
        for (Vertex vert : list){
            vert.setMarked(false);
            vert.setParent(null);
            vert.setCost(1e+7);
        }
        
        PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
        v0.setCost(0);
        pq.add(v0);

        while (pq.size() != 0){
            Vertex v = pq.poll();
            if (v.isMarked()){
                continue;
            } else {
                v.setMarked(true);
            }

            for (Vertex w : v.getNeighbors()){
                double dist = v.distance(w);
                if (w.isMarked() == false && v.getCost() + dist < w.getCost()){
                    w.setCost(v.getCost()+dist);
                    w.setParent(v);
                    pq.add(w);
                }

            }

        }

    }
    
    public static void main(String[] args) {
        Vertex v1 = new Vertex(10, 10);
        
        Vertex v2 = new Vertex(10, 5);
        
        Vertex v3 = new Vertex(20, 5);
    
        Vertex v4 = new Vertex(20, 10);
        
        Vertex v5 = new Vertex(30, 7);
        

        Graph graph = new Graph();

        graph.addUniEdge(v1, v2);
        graph.addUniEdge(v2, v3);
        graph.addBiEdge(v3, v4);
        graph.addUniEdge(v2, v4);
        graph.addUniEdge(v1, v4);
        graph.addBiEdge(v4, v5);
        graph.addUniEdge(v3, v5);

        System.out.println(v1.toString());
        System.out.println(v2.toString());
        System.out.println(v3.toString());
        System.out.println(v4.toString());
        System.out.println(v5.toString());
        
        graph.shortestPath(v1);

        System.out.println(v1.toString());
        System.out.println(v2.toString());
        System.out.println(v3.toString());
        System.out.println(v4.toString());
        System.out.println(v5.toString());


    }
}
