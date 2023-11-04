package edu.project2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;
import org.jetbrains.annotations.NotNull;
import static edu.project2.Direction.EAST;
import static edu.project2.Direction.NORTH;

@SuppressWarnings("HideUtilityClassConstructor")
public class MazeSolver {
    private static int endX;
    private static int endY;
    static boolean foundPath = false;


    public static ArrayList<Point> solveTheMazeTotallyNotDFS(
        @NotNull Point curr, Cell[][] map, boolean[][] visited, ArrayList<Point> path
    ) {
        ArrayList<Direction> directions = new ArrayList<>();

        directions.add(NORTH);
        directions.add(EAST);
        directions.add(Direction.SOUTH);
        directions.add(Direction.WEST);

        int y = curr.getY();
        int x = curr.getX();
        if (x == endX && y == endY) {
            path.add(new Point(-1, -1));
            return path;
        }
        int multi;

        while (!directions.isEmpty()) {
            int dirIndex = RandomGenerating.nextInt(directions.size());
            Direction direction = directions.get(dirIndex);
            directions.remove(dirIndex);

            switch (direction) {
                case NORTH:
                case SOUTH:
                    multi = (direction == NORTH ? 1 : -1);
                    processDir(x, y - multi, map, visited, path,
                        buf -> buf.get(buf.size() - 1).getX() == -1
                    );
                    break;

                default:
                    multi = (direction == EAST ? 1 : -1);
                    processDir(x - multi, y, map, visited, path,
                        buf -> buf.get(buf.size() - 1).getX() == -1
                    );
                    break;

            }
        }

        return path;
    }

    private static void processDir(int x, int y, Cell[][] map, boolean[][] visited, ArrayList<Point> path,
        Predicate<ArrayList<Point>> condition) {
        if (canMoveTo(x, y, map, visited)) {
            path.add(new Point(x, y));
            visited[x][y] = true;
            ArrayList<Point> buf = solveTheMazeTotallyNotDFS(
                new Point(x, y),
                map, visited, path
            );
            if (condition.test(buf)) {
                foundPath = true;
                return;
            }
            path.remove(buf.size() - 1);
        }

    }

    private static boolean canMoveTo(int x, int y, Cell[][] map, boolean[][] visited) {
        return x >= 0 && x < map.length && y >= 0 && y < map[x].length && map[x][y].isClear() && !visited[x][y];
    }

    public static ArrayList<Point> solveTheMazeTotallyNotDFS(Cell[][] maze) {
        int n = maze.length;
        int startX = 0;
        int startY = 0;
        endX = n - 1;
        endY = n - 1;
        boolean[][] visited = new boolean[n][n];
        for (int i = 0; i  < n; i++) {
            Arrays.fill(visited[i], false);
        }
        ArrayList<Point> path = new ArrayList<>();
        path.add(new Point(0, 0));
        solveTheMazeTotallyNotDFS(
                new Point(startX, startY), maze, visited, path
        );
        if (path.get(path.size() - 1).getX() != -1) {
            throw new RuntimeException("NO WAY! It is labyrinth without escape!\n"
                + "...Or we didn't find it");
        }
        return path;

    }

}
