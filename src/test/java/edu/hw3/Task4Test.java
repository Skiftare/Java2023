package edu.hw3;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task4Test {

    @Test
    @DisplayName("Преобразование цифры")
    public void testThatGetCifrusAndReturnedRomanNumber(){
        String res = Task4.convertToRoman(2);
        assertEquals(res, "II");
    }

    @Test
    @DisplayName("Преобразование числа (лёгкое)")
    public void testThatGetNumberAndReturnedRomanNumberEasy(){
        String res = Task4.convertToRoman(16);
        assertEquals(res, "XVI");
    }

    @Test
    @DisplayName("Преобразование числа (сложное)")
    public void testThatGetNumberAndReturnedRomanNumberHard(){
        String res = Task4.convertToRoman(3245);
        assertEquals(res, "MMMCCXLV");
    }
}
