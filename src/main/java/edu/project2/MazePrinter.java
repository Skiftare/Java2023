package edu.project2;

import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;


@SuppressWarnings("HideUtilityClassConstructor")
public class MazePrinter {
    private final static Logger LOGGER = LogManager.getLogger();

    public static @NotNull StringBuilder prePrintMaze(Cell[] @NotNull [] maze) {
        StringBuilder sb = new StringBuilder();
        return getStringBuilder(maze, sb);
    }

    private static @NotNull StringBuilder getStringBuilder(Cell[] @NotNull [] maze, StringBuilder sb) {
        sb.append('\n');

        for (Cell[] cells : maze) {
            for (Cell cell : cells) {
                if (cell.isPath()) {
                    sb.append("▫"); // проход, по которому мы идем
                } else if (cell.isClear()) {
                    sb.append(" "); // проход
                } else if (cell.isWall()) {
                    sb.append("█"); // стена
                } /*else if (maze[i][j].isWay) {
                    sb.append("▫"); // проход, по которому мы идем
                }*/
            }
            sb.append("\n");
        }

        return sb;
    }

    public static @NotNull StringBuilder prePrintPathMaze(Cell[] @NotNull [] maze, ArrayList<Point> path) {
        StringBuilder sb = new StringBuilder();
        for (Point point : path) {
            int x = point.getX();
            int y = point.getY();
            if (x > -1) {
                maze[x][y].makePath();
            }
        }
        return getStringBuilder(maze, sb);
    }

    public static void printPrintPathMaze(Cell[] @NotNull [] maze, ArrayList<Point> path) {
        LOGGER.info(prePrintPathMaze(maze, path).toString());
    }

    public static void printMaze(Cell[][] maze) {
        LOGGER.info(prePrintMaze(maze).toString());
    }
}
