package edu.hw9.task3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class DFS {
    private final Map<Integer, Boolean> result;
    private final ForkJoinPool forkJoinPool;
    private final Node startNode;

    public DFS(Node[] nodes, int startNodeId) {
        this.result = new ConcurrentHashMap<>();
        this.forkJoinPool = new ForkJoinPool();
        this.startNode = Arrays.stream(nodes).filter(node -> node.getId() == startNodeId).findFirst().orElse(null);

        for (Node node : nodes) {
            if (node.getId() == startNodeId) {
                result.put(startNodeId, true);
            } else {
                result.put(node.getId(), false);
            }
        }
    }

    public void dfs() {
        forkJoinPool.invoke(new DFSTask(startNode));
    }

    public Map<Integer, Boolean> getResult() {
        return result;
    }

    private class DFSTask extends RecursiveAction {
        private final Node currentNode;

        DFSTask(Node currentNode) {
            this.currentNode = currentNode;
        }

        @Override
        protected void compute() {
            List<DFSTask> tasks = new ArrayList<>();

            for (Node neighbor : currentNode.getNeighbors()) {
                if (!result.get(neighbor.getId())) {
                    result.put(neighbor.getId(), true);
                    neighbor.setVisited(true);
                    DFSTask task = new DFSTask(neighbor);
                    task.fork();
                    tasks.add(task);
                }
            }

            for (DFSTask task : tasks) {
                task.join();
            }

        }
    }
}
