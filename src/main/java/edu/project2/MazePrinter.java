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
        sb.append('\n');

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j].isPath()) {
                    sb.append("▫"); // проход, по которому мы идем
                } else if (maze[i][j].isClear()) {
                    sb.append(" "); // проход
                } else if (maze[i][j].isWall()) {
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
        for (int i = 0; i < path.size(); i++) {
            int x = path.get(i).getX();
            int y = path.get(i).getY();
            if (x > -1) {
                maze[x][y].makePath();
            }
        }
        sb.append('\n');

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j].isPath()) {
                    sb.append("▫"); // проход, по которому мы идем
                } else if (maze[i][j].isClear()) {
                    sb.append(" "); // проход
                } else if (maze[i][j].isWall()) {
                    sb.append("█"); // стена
                } /*else if (maze[i][j].isWay) {
                    sb.append("▫"); // проход, по которому мы идем
                }*/
            }
            sb.append("\n");
        }

        return sb;
    }

    public static void printPrintPathMaze(Cell[] @NotNull [] maze, ArrayList<Point> path) {
        LOGGER.info(prePrintPathMaze(maze, path).toString());
    }

    public static void printMaze(Cell[][] maze) {
        LOGGER.info(prePrintMaze(maze).toString());
    }
}
