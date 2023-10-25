package edu.hw2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {
    static Arguments[] rectangles() {
        return new Arguments[]{
            Arguments.of(new Rectangle(1,1)),
            Arguments.of(new Square(1))
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    void testThatGetArgumentsAsSquareOrRectangleAndReturnedSameAreaResult(Rectangle rect) {
        rect = rect.setWidth(20);
        rect = rect.setHeight(10);

        assertThat(rect.area()).isEqualTo(200.0);
    }



//Не совесм осознал, чего от меня хотят, но сейчас у меня квадрат точно пройдёт все тесты для прямоугольника
    @Test
    @DisplayName("Тест на то, что квадрат - на самом деле не обязан оставаться квадратом")
    void testThatGetArgumentsAsSquareOrRectangleAndReturnedSameResults(){
        var squr = new Square(100);
        var rect = new Rectangle(100, 100);
        assertEquals(squr.area(), 100*100);
        assertEquals(rect.area(), 100*100);

        squr = squr.setHeight(20);
        rect = rect.setHeight(20);
        assertEquals(squr.area(), rect.area());
        squr = squr.setWidth(30);
        rect = rect.setWidth(30);
        assertEquals(squr.area(), rect.area());
    }
}
