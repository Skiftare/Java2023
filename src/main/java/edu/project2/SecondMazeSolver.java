package edu.project2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

@SuppressWarnings("HideUtilityClassConstructor")
public class SecondMazeSolver {
    private static boolean[][] vis;
    private static Cell[][] map;
    private static int n;

    private static boolean isWithinBounds(Point p) {
        return p.getX() >= 0 && p.getX() < n && p.getY() >= 0 && p.getY() < n;
    }

    private static ArrayList<Point> getNeighbors(Point point) {
        ArrayList<Point> neighbors = new ArrayList<>();
        int x = point.getX();
        int y = point.getY();

        if (isWithinBounds(new Point(x, y - 1)) && map[x][y - 1].isClear() && !vis[x][y - 1]) {
            neighbors.add(new Point(x, y - 1));
        }
        if (isWithinBounds(new Point(x, y + 1)) && map[x][y + 1].isClear() && !vis[x][y + 1]) {
            neighbors.add(new Point(x, y + 1));
        }
        if (isWithinBounds(new Point(x - 1, y)) && map[x - 1][y].isClear() && !vis[x - 1][y]) {
            neighbors.add(new Point(x - 1, y));
        }
        if (isWithinBounds(new Point(x + 1, y)) && map[x + 1][y].isClear() && !vis[x + 1][y]) {
            neighbors.add(new Point(x + 1, y));
        }

        return neighbors;
    }

    public static ArrayList<Point> findPath(Cell[][] incomeMap) {
        SecondMazeSolver.n = incomeMap.length;
        vis = new boolean[n][n];
        map = incomeMap;


        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0));
        vis[0][0] = true;

        HashMap<Point, Point> parent = new HashMap<>();
        parent.put(new Point(0, 0), null);

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            if (current.getX() == n - 1 && current.getY() == n - 1) {
                // Path found, reconstruct it
                ArrayList<Point> path = new ArrayList<>();
                Point node = current;
                while (node != null) {
                    path.add(node);
                    node = parent.get(node);
                }
                Collections.reverse(path);
                return path;
            }

            ArrayList<Point> neighbors = getNeighbors(current);
            for (Point neighbor : neighbors) {
                queue.add(neighbor);
                vis[neighbor.getX()][neighbor.getY()] = true;
                parent.put(neighbor, current);
            }
        }

        // No path found
        return new ArrayList<>();
    }
}
