package edu.hw6;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task6Test {

    @Test
    @DisplayName("Тест открытых/закрытых портов - считаем количество и проверяем первые вхождения")
    void testThatGetPortsAndReturnedStateOfMostPopularPorts(){
        //Given: ports
        List<String> result = Task6.getPortsInfo();
        int expectedOpened = 74;
        int expectedClosed = 0;
        List<String> expectOpened = new ArrayList<>();
        List<String> expectClosed = new ArrayList<>();
        //When: get lists of opened and closed ports
        for(var it:result){
            if(it.split("\t").length < 3) {
                expectOpened.add(it);
                System.out.println(it);
            } else{
                expectClosed.add(it);
                System.out.println(it);
            }
        }

        //Then: check for right scan of ports
        assertEquals(expectClosed.size(),expectedClosed);
        assertEquals(expectOpened.size(), expectedOpened);
        //assertEquals(expectClosed.get(0), "TCP\t137\tNETBIOS-NS (NetBIOS Name Service)\n");
        assertEquals(expectOpened.get(0), "TCP\t27017\n");
    }

}
