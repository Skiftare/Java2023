package edu.project2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestOfSecondGenerator {
    @Test
    @DisplayName("Проверка генерации второго алгоритма (оны выглядит по-другому)")
    void testThatGetSizeAndGenerateMaze(){

        SecondMazeGenerator generator = new SecondMazeGenerator();
        Cell[][] mas = generator.generateMaze(20);

        MazePrinter.printMaze(mas);
        String expected = """

                               ██████████████████
                                 █ █         █  █
                             █ █ █ █ █████████ ██
                             █     █ █ █     █  █
                             █ █████ █ █ █ █ ████
                             █           █ █    █
                             ███████████████ ████
                             █   █             ██
                             █ █ ███ █████████ ██
                             █ █     █     █   ██
                             █ ███████ ███ █ ████
                             █     █ █   █ █ █  █
                             █████ █ ███ █ █ █ ██
                             █   █ █   █ █   █ ██
                             █ █ █ ███ █ █████ ██
                             █ █   █     █      █
                             █████ █ █████ █ ████
                             █   █ █ █     █    █
                             █ █ █ █ █ ███ ███  \s
                             ██████████████████ \s
                             """;
        MazePrinter.printMaze(mas);
        assertEquals(expected, MazePrinter.prePrintMaze(mas).toString());
        //assertEquals(expected, MazePrinter.prePrintMaze(mas).toString());
    }


}
