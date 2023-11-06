package edu.project2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestOfSecondSolver {
    @Test
    @DisplayName("Доказательсто того, что это разные алгоритмы ")
    void testThatGetMazeAndReturnedWayToEscape() {
        RandomGenerating.init();

        ArrayList<Point> secondPath = SecondMazeSolver.findPath(SecondMazeGenerator.generateMaze(50));
        RandomGenerating.init();

        ArrayList<Point> path = SecondMazeSolver.findPath(MazeGenerator.generateMaze(50));

        assertNotEquals(path,secondPath);
    }

}
