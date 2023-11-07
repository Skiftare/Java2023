package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw3.Task5.parseContacts;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task5Test {
    @Test
    @DisplayName("сортировка по возрастанию")
    void testThatGetUnsortedContatsAndReturnedSortedToIncrease(){
        String [] income = {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};
        String [] expect = {"Thomas Aquinas", "Rene Descartes", "David Hume", "John Locke"};

        assertArrayEquals(parseContacts(income,"ASC"), expect);

    }

    @Test
    @DisplayName("сортировка по убыванию")
    void testThatGetUnsortedContatsAndReturnedSortedToDecrease(){
        String [] income = {"Paul Erdos", "Leonhard Euler", "Carl Gauss"};
        String [] expect = {"Carl Gauss", "Leonhard Euler", "Paul Erdos"};

        assertArrayEquals(parseContacts(income,"DESC"), expect);

    }

    @Test
    @DisplayName("проверка случая пустых данных")
    void testThatGetNoContactsAndReturnedEmptyArray(){
        String [] income = {};
        String [] expect = {};

        assertArrayEquals(parseContacts(income,"DESC"), expect);
        assertArrayEquals(parseContacts(income,"ASC"), expect);

    }

    @Test
    @DisplayName("проверка случая неверного параметра (флага) сорировки")
    void testThatGetWrongSortFlagAndReturnedException(){
        String [] income = {"Paul Erdos", "Leonhard Euler", "Carl Gauss"};
        String wrongFlag = "wrong";
        Throwable ex = assertThrows(
            RuntimeException.class, ()-> {
                parseContacts(income, wrongFlag);
            }
        );
        assertEquals(ex.getMessage(), "wrong input sortFlag");
    }
    @Test
    @DisplayName("проверка случая неверного формата данных массива")
    void testThatGetWrongArrayDataAndReturnedException(){
        String [] income = {"Paul Erdos", "Leonhard Euler", "Carl Gauss Ecobaro"};
        String wrongFlag = "wrong";
        Throwable ex = assertThrows(
            RuntimeException.class, ()-> {
                parseContacts(income, "ASC");
            }
        );
        assertEquals(ex.getMessage(), "wrong name");
    }

}
