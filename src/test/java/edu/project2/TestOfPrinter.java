package edu.project2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestOfPrinter {
    @Test
    @DisplayName("Проверка корректности вывода либиринта")
    void testThatGetMazeAndReturnedPrint(){
        Cell w = new Cell();
        Cell f = new Cell();
        f.makeClear();
        w.makeWall();
        Cell[][] maze = {
            {f,w,w},
            {f,f,f},
            {w,w,w},
        };
        String expected =
                """

                         ██
                          \s
                        ███
                        """;

        MazePrinter.printMaze(maze);

        assertEquals(expected, MazePrinter.prePrintMaze(maze).toString());
    }
}
