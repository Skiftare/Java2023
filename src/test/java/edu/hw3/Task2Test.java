package edu.hw3;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class Task2Test {
    @Test
    @DisplayName("Тест на расстановку скобок при исходно хорошей строке")
    void testThatGetStringAndReturnedArrayOfBalancedStrings() {
        String income = "((()))(())()()(()())";
        ArrayList<String> expected = new ArrayList<>();
        expected.add("((()))");
        expected.add("(())");
        expected.add("()");
        expected.add("()");
        expected.add("(()())");
        //System.out.println(Task2.clusterize(income));
        //System.out.println(expected);
        assertArrayEquals(new ArrayList[] {expected}, new ArrayList[] {Task2.clusterize(income)});

    }
    @Test
    @DisplayName("Тест на ошибку при неверном символе")
    void testThatGetStringWithWrongSymbolAndREturnedException() {
        String income = "((()))!(())()()(()())";
        Throwable ex = assertThrows(
            RuntimeException.class, ()-> {
                Task2.clusterize(income);
            }
        );
        assertEquals(ex.getMessage(), "wrong symbol");
    }
    @Test
    @DisplayName("Тест на ошибку при несбалансированной строке в (")
    void testThatGetUnbalancedStringPlusAndReturnedException() {
        String income = "((()))((())()()(()())";
        Throwable ex = assertThrows(
            RuntimeException.class, ()-> {
                Task2.clusterize(income);
            }
        );
        assertEquals(ex.getMessage(), "unbalanced string");

    }
    @Test
    @DisplayName("Тест на ошибку при несбалансированной строке в )")
    void testThatGetUnbalancedStringMinusAndReturnedException(){
        String income = "((())))))((())()()(()())";
        Throwable ex = assertThrows(
            RuntimeException.class, ()-> {
                Task2.clusterize(income);
            }
        );
        assertEquals(ex.getMessage(), "unbalanced string");
    }
}
