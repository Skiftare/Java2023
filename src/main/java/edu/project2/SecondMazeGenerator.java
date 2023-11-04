package edu.project2;


import java.util.ArrayList;
import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("HideUtilityClassConstructor")

public class SecondMazeGenerator {

    final static int MINI_N = 4;

    static Direction makeRandomDesizion() {
        ArrayList<Direction> arr = MazeGenerator.makeDirectionsArray();
        return arr.get(RandomGenerating.nextInt(arr.size()));
    }

    private static boolean isWithinBounds(Point p, int n) {
        return p.getX() >= 0 && p.getX() < n && p.getY() >= 0 && p.getY() < n;
    }

    @SuppressWarnings("MagicNumber")
    private static void generateMaze(Cell[][] maze, int n) {

        int x = 1;
        int y = 1;
        int a = 0;
        int multi;
        while (a <= 1e4) {
            maze[y][x].makeClear();
            a++;

            do {
                Direction c = makeRandomDesizion(); // Генерируем случайное число от 0 до 3
                // Напоминаю, что крот прорывает по две клетки в одном направлении за прыжок
                switch (c) {
                    case WEST:
                    case EAST:
                        multi = c == Direction.WEST ? 1 : -1;
                        if (isWithinBounds(new Point(y - multi * 2, x), n) && maze[y - multi * 2][x].isWall()) {
                            maze[y - multi][x].makeClear();
                            maze[y - multi * 2][x].makeClear();
                            y -= 2 * multi;
                        }
                        break;

                    default:
                        multi = c == Direction.SOUTH ? 1 : -1;
                        if (isWithinBounds(new Point(y, x - multi * 2), n) && maze[y][x - multi * 2].isWall()) {
                            maze[y][x - multi].makeClear();
                            maze[y][x - 2 * multi].makeClear();
                            x -= 2 * multi;
                        }
                        break;
                }

            } while (!deadend(x, y, maze, n));

            if (deadend(x, y, maze, n)) {
                do {
                    x = 2 * (RandomGenerating.nextInt((n - 1) / 2)) + 1;
                    y = 2 * (RandomGenerating.nextInt((n - 1) / 2)) + 1;
                } while (!maze[y][x].isClear());
            }
        }
    }

    public static boolean deadend(int x, int y, Cell[][] maze, int n) {
        int a = 0;

        if (x >= 2) {
            if (maze[y][x - 2].isClear()) {
                a += 1;
            }
        } else {
            a += 1;
        }

        if (y >= 2) {
            if (maze[y - 2][x].isClear()) {
                a += 1;
            }
        } else {
            a += 1;
        }

        if (x + 2 <= n - 1) {
            if (maze[y][x + 2].isClear()) {
                a += 1;
            }
        } else {
            a += 1;
        }

        if (y + 2 <= n - 1) {
            if (maze[y + 2][x].isClear()) {
                a += 1;
            }
        } else {
            a += 1;
        }
        return a == MINI_N;
    }

    @SuppressWarnings("MagicNumber")
    public static Cell[][] generateMaze(int incomeN) {

        if (incomeN <= MINI_N && incomeN > 0) {
            throw new RuntimeException("Too small N, it's boring");
        }
        if (incomeN <= 0) {
            throw new RuntimeException("N is not natural number");
        }

        Cell[][] map = new Cell[incomeN][incomeN];

        Arrays.setAll(map, i -> Arrays.stream(new Cell[incomeN]).map(j -> new Cell()).toArray(Cell[]::new));
        generateMaze(map, incomeN);
        return getCells(incomeN, map);
    }

    @NotNull static Cell[][] getCells(int incomeN, Cell[][] map) {
        for (int i = 0; i < incomeN; i++) {
            map[i][0].makeWall();
            map[i][incomeN - 1].makeWall();
            map[0][i].makeWall();
            map[incomeN - 1][i].makeWall();
        }

        map[incomeN - 1][incomeN - 1].makeClear();
        map[0][0].makeClear();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                map[1][1 + j].makeClear();
                map[1 + i][1].makeClear();

                map[incomeN - 2][incomeN - 2 + j].makeClear();
                map[incomeN - 2 + i][incomeN - 2].makeClear();
            }
        }
        return map;
    }
}
