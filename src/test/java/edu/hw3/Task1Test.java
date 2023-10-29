package edu.hw3;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static edu.hw3.Task1.atbash;

public class Task1Test {
    @Test
    @DisplayName("Проверка из примера")
    public void testThatGetHelloReturnedAtbash() {
        String standartStrind = "Hello world!";
        String reversedString = "Svool dliow!";
        String result = atbash(standartStrind);
        assertEquals(result, reversedString);

    }
    @Test
    @DisplayName("Чуть более сложный текст, суть та же")
    public void testThatGetLongStringReturnedAtbash() {
        String standtrtString = "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler";
        String reversedString =  "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi";
        String result = atbash(standtrtString);
        assertEquals(reversedString, result);
    }
}
