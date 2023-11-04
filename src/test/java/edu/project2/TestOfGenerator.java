package edu.project2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestOfGenerator {
    @Test
    @DisplayName("Проверка генерации RecursiveBacktracker")
    void testThatGetSizeAndGenerateMaze(){

        MazeGenerator generator = new MazeGenerator();
        Cell[][] mas = generator.generateMaze(15);
        String expected = "\n  █████████████\n" +
            "      █ █     █\n" +
            "█ █ ███ █ █ █ █\n" +
            "█ █       █ █ █\n" +
            "█████ █ █ █ ███\n" +
            "█     █ █ █ █ █\n" +
            "███ █ █ █ ███ █\n" +
            "█   █ █ █     █\n" +
            "█ █ █ █ █████ █\n" +
            "█ █ █ █ █ █   █\n" +
            "█ █ ███ █ █████\n" +
            "█ █ █       █ █\n" +
            "█ █ ███ █ ███ █\n" +
            "█ █   █ █      \n" +
            "█████████████  \n";
        MazePrinter printer = new MazePrinter();
        printer.printMaze(mas);
        assertEquals(expected,printer.prePrintMaze(mas).toString());
    }

    @Test
    @DisplayName("Проверка исключения при неверном N - срабатывает при [1;4]")
    void testThatIncorrectNaturalSmallNAndException(){

        MazeGenerator generator = new MazeGenerator();
        Throwable ex = assertThrows(
            RuntimeException.class, ()-> {
                generator.generateMaze(4);
            }
        );
        String expected = "Too small N, it's boring";
        assertEquals(expected,ex.getMessage());
    }

    @Test
    @DisplayName("Проверка исключения при неверном N - срабатывает при <=0")
    void testThatIncorrectNotNaturalNNAndException(){

        MazeGenerator generator = new MazeGenerator();
        Throwable ex = assertThrows(
            RuntimeException.class, ()-> {
                generator.generateMaze(-0);
            }
        );
        String expected = "N is not natural number";
        assertEquals(expected,ex.getMessage());
    }
}
