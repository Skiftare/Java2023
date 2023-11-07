package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {
    @Test
    @DisplayName("Тест частотного словаря для массива строк ")
    void testThatGetStringArrayAndReturnedCountOfEveryValue(){
        String [] incomeArray = {"a", "bb", "a", "bb"};
        var realAnswer = Task3.freqDict(incomeArray);
        HashMap<String, Integer> expectedAnswer = new HashMap<>();
        expectedAnswer.put("a", 2);
        expectedAnswer.put("bb",2);
        assertEquals(expectedAnswer, realAnswer);
        incomeArray = new String[] {"this", "and", "that", "and"};
        realAnswer = Task3.freqDict(incomeArray);
        expectedAnswer.clear();
        expectedAnswer.put("that",1);
        expectedAnswer.put("and",2);
        expectedAnswer.put("this",1);
        assertEquals(expectedAnswer, realAnswer);
        //freqDict(["this", "and", "that", "and"]) → {"that": 1, "and": 2, "this": 1}
        //freqDict(["код", "код", "код", "bug"]) → {"код": 3, "bug": 1}
        //freqDict([1, 1, 2, 2]) → {1: 2, 2: 2}
    }

    @Test
    @DisplayName("Тест частотного словаря для массива чисел ")
    void testThatGetIntegerArrayAndReturnedCountOfEveryValue(){
        Integer [] incomeArray = {1, 1, 2, 2};
        var x = Task3.freqDict(incomeArray);

        HashMap<Integer, Integer> expectedAnswer = new HashMap<>();
        expectedAnswer.put(1, 2);
        expectedAnswer.put(2,2);
        assertEquals(expectedAnswer, x);
        //freqDict(["a", "bb", "a", "bb"])// → {"bb": 2, "a": 2}
        //freqDict(["this", "and", "that", "and"]) → {"that": 1, "and": 2, "this": 1}
        //freqDict(["код", "код", "код", "bug"]) → {"код": 3, "bug": 1}
        //freqDict([1, 1, 2, 2]) → {1: 2, 2: 2}
    }
}
