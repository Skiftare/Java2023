package edu.hw9;

import java.util.ArrayList;
import java.util.List;

class Node {
    private int id;
    private boolean visited;
    private List<Node> neighbors;

    Node(int id) {
        this.id = id;
        this.visited = false;
        this.neighbors = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public List<Node> getNeighbors() {
        return neighbors;
    }

    public void addNeighbor(Node neighbor) {
        this.neighbors.add(neighbor);
    }
}

