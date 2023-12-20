package edu.hw9.task3;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private final int id;
    private final List<Node> neighbors;
    private boolean visited;

    public Node(int id) {
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

