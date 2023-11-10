package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task6Test {

    @Test
    @DisplayName("Тест открытых/закрытых портов - считаем количество и проверяем первые вхождения")
    void testThatGetPortsAndReturnedStateOfMostPopularPorts(){

        List<String> result = Task6.getPortsInfo();
        int openedCount = 0;
        int closedCount = 0;
        int expectedOpened = 69;
        int expectedClosed = 5;
        for(var it:result){
            if(it.split(" ").length < 3) {
                if(openedCount == 0){
                    assertEquals("TCP\t137\n", it);
                }
                openedCount++;
            } else{
                if(closedCount == 0){
                    assertEquals("TCP\t137\tNETBIOS-NS (NetBIOS Name Service)\n", it);
                }
                closedCount++;
            }
        }
        assertEquals(expectedClosed,closedCount);
        assertEquals(expectedOpened,openedCount);
    }

}
