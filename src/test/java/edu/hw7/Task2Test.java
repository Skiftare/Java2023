package edu.hw7;

import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task2Test {
    @Test
    @DisplayName("Проверка корректности факториала для первых 5 чисел")
    void testThatGetNAndReturnedFactiorialOfIt(){
        //given: testData array
        int testBorder = 5;
        ArrayList<Long> realResults = new ArrayList<>();
        ArrayList<Integer> testData = new ArrayList<>();
        long [] expectedResults = {1,1,2,6,24,120};
        for(int i = 0;i<=testBorder;i++){
            testData.add(i);
        }
        //when for each num we generate factorial
        for(int data: testData){
            realResults.add(edu.hw7.Task2.factorial(data));
        }
        //then: check with expected
        for(int i = 0;i<=testBorder;i++){
            assertEquals(expectedResults[i], realResults.get(i));
        }
    }
    @Test
    @DisplayName("Проверка выбрасывания исключения факториала при отрицательных числах")
    void testThatGetNegateNAndReturnedException(){
        //given: testData num
        int testData = -1;
        //when for each num we generate factorial
        //then we get exception
        Throwable ex = assertThrows(
            RuntimeException.class, ()-> {
                edu.hw7.Task2.factorial(testData);
            }
        );
        assertEquals(ex.getMessage(), "No factorial for negate number");
    }
}
