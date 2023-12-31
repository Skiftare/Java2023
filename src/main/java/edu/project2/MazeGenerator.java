package edu.project2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;
import static edu.project2.SecondMazeGenerator.getCells;

@SuppressWarnings("HideUtilityClassConstructor")
public class MazeGenerator {
    private static boolean[][] vis;
    private static Cell[][] map;
    private static int n;

    static @NotNull ArrayList<Direction> makeDirectionsArray() {
        ArrayList<Direction> directions = new ArrayList<>();
        directions.add(Direction.NORTH);
        directions.add(Direction.EAST);
        directions.add(Direction.SOUTH);
        directions.add(Direction.WEST);
        return directions;
    }

    private static boolean isWithinBounds(Point p) {
        return p.getX() >= 0 && p.getX() < n && p.getY() >= 0 && p.getY() < n;
    }

    private static void addMorePoints(ArrayList<Point> income, int x, int y) {
        Consumer<Point> addToCheckList = point -> {
            if (
                isWithinBounds(point) && map[point.getX()][point.getY()].isWall()
                    && !vis[point.getX()][point.getY()]
            ) {

                income.add(point);
            }
        };

        addToCheckList.accept(new Point(x, y - 2));
        addToCheckList.accept(new Point(x, y + 2));
        addToCheckList.accept(new Point(x - 2, y));
        addToCheckList.accept(new Point(x + 2, y));
    }

    static Cell[] @NotNull [] generatePreMaze(int incomeN) {
        n = incomeN;

        map = new Cell[n][n];
        vis = new boolean[n][n];
        Arrays.setAll(map, i -> Arrays.stream(new Cell[n]).map(j -> new Cell()).toArray(Cell[]::new));
        Arrays.setAll(vis, i -> new boolean[n]);

        int x = 1;
        int y = 1;
        map[x][y].makeClear();
        vis[x][y] = true;

        ArrayList<Point> toCheck = new ArrayList<>();
        toCheck.add(new Point(x, y + 2));
        toCheck.add(new Point(x + 2, y));

        Consumer<Point> makeCellClear = point -> map[point.getX()][point.getY()].makeClear();

        while (!toCheck.isEmpty()) {
            int index = RandomGenerating.nextInt(toCheck.size());
            int multi;
            Point cell = toCheck.get(index);
            Point p;
            toCheck.remove(index);
            x = cell.getX();
            y = cell.getY();

            if (vis[x][y]) {
                continue;
            }

            vis[x][y] = true;
            makeCellClear.accept(cell);

            ArrayList<Direction> directions = makeDirectionsArray();

            // Handle directions and update the map
            while (!directions.isEmpty()) {
                int dirIndex = RandomGenerating.nextInt(directions.size());
                Direction direction = directions.get(dirIndex);

                switch (direction) {

                    case NORTH:
                    case SOUTH:
                        multi = (direction == Direction.NORTH ? 1 : -1);
                        p = new Point(x, y - 2 * multi);
                        if (isWithinBounds(p) && map[x][y - 2 * multi].isClear()) {
                            makeCellClear.accept(new Point(x, y - multi));
                            directions.clear();
                        }
                        break;


                    default:
                        multi = (direction == Direction.EAST ? 1  : -1);
                        p = new Point(x - 2 * multi, y);
                        if (isWithinBounds(p) && map[x - 2 * multi][y].isClear()) {
                            makeCellClear.accept(new Point(x - multi, y));
                            directions.clear();
                        }
                }

                if (!directions.isEmpty()) {
                    directions.remove(dirIndex);
                }
            }
            addMorePoints(toCheck, x, y);


        }



        return map;
    }

    @SuppressWarnings("MagicNumber")
    public static Cell[][] generateMaze(int incomeN) {

        int miniN = 4;
        if (incomeN <= miniN && incomeN > 0) {
            throw new RuntimeException("Too small N, it's boring");
        }
        if (incomeN <= 0) {
            throw new RuntimeException("N is not natural number");
        }
        map = generatePreMaze(incomeN);
        return getCells(incomeN, map);
    }


}




