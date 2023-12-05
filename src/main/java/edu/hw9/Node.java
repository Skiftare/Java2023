package edu.hw9;

import java.util.ArrayList;
import java.util.List;

class Node {
    private final int id;
    private boolean visited;
    private final List<Node> neighbors;

    Node(int id) {
        this.id = id;
        this.visited = false;
        this.neighbors = new ArrayList<>();
    }

    public int getId() {
        return id;
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

