package edu.project2;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestOfSolver {
    @Test
    @DisplayName("Проверка работы поиска пути (это точно-не DFS)")
    void testThatGetMazeAndReturnedWayToEscape(){
        RandomGenerating.init();
        MazeGenerator generator = new MazeGenerator();
        Cell[][] mas = generator.generateMaze(10);
        ArrayList<Point> path = MazeSolver.solveTheMazeTotallyNotDFS(mas);

        MazePrinter printer = new MazePrinter();
        @NotNull StringBuilder income = printer.prePrintPathMaze(mas, path);
        printer.printPrintPathMaze(mas, path);
        String expected =
            "\n"+"▫ ████████\n" +
            "▫▫▫▫    ██\n" +
            "█ █▫██████\n" +
            "█ █▫▫▫   █\n" +
            "█ ███▫█ ██\n" +
            "█    ▫█ ██\n" +
            "█ █ █▫████\n" +
            "█ █ █▫▫▫ █\n" +
            "█ █ ███▫▫ \n" +
            "████████▫▫\n";
        assertEquals(expected, income.toString());

    }

    @Test
    @DisplayName("Попытка решить лабиринт без выхода")
    void testThatGetUnsolvedMazeAndThrowError(){
        Cell w = new Cell();
        Cell f = new Cell();
        f.makeClear();
        w.makeWall();



        Cell[][] maze = {

            {f,w,w,w,w,w},
            {f,f,w,w,w,w},
            {w,w,w,w,w,w},
            {w,w,w,w,w,f},
            {w,w,w,w,f,f}
        };
        Throwable ex = assertThrows(
            RuntimeException.class, ()-> {
                ArrayList<Point> path = MazeSolver.solveTheMazeTotallyNotDFS(maze);
            }
        );
        String expected = "NO WAY! It is labyrinth without escape!\n"
            + "...Or we didn't find it";
        assertEquals(expected,ex.getMessage());
    }
}
