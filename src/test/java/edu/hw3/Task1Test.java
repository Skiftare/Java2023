package edu.hw3;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {
    @Test
    @DisplayName("Проверка из примера")
    public void testThatGetHelloReturnedAtbash() {
        assertEquals("Svool dliow!", Task1.atbash("Hello world!"));
        String standtrtString = "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler";
        String reversedString =  "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi";
        assertEquals(reversedString, Task1.atbash(standtrtString));
    }
}
