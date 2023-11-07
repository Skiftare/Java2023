package edu.hw3;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static edu.hw3.Task4.convertToRoman;
public class Task4Test {

    @Test
    @DisplayName("Преобразование цифры")
    public void testThatGetCifrusAndReturnedRomanNumber(){
        String res = convertToRoman(2);
        assertEquals(res, "II");
    }

    @Test
    @DisplayName("Преобразование числа (лёгкое)")
    public void testThatGetNumberAndReturnedRomanNumberEasy(){
        String res = convertToRoman(16);
        assertEquals(res, "XVI");
    }

    @Test
    @DisplayName("Преобразование числа (сложное)")
    public void testThatGetNumberAndReturnedRomanNumberHard(){
        String res = convertToRoman(3245);
        assertEquals(res, "MMMCCXLV");
    }
}
