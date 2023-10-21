package edu.hw2;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class hw4Test {
    @Test
    @DisplayName("Проверка вызова")
    void thisIsAnswer(){

        assertEquals("edu.hw2.hw4Test->thisIsAnswer", Hw4.callingInfo());
    }
    private @NotNull String someFunc(){
        return Hw4.callingInfo();
    }
    @Test
    @DisplayName("Проверка вызова приватной функции")
    void thisIsPrivateTest(){
        assertEquals("edu.hw2.hw4Test->someFunc", someFunc());
    }

}