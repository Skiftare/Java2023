package edu.hw6;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CreatePortDescriptions {
    public static void getPortsMap(Path soursePath) {
        Set<Integer> portSet = new HashSet<>();

        portSet.add(80);
        portSet.add(443);
        portSet.add(22);
        portSet.add(21);
        portSet.add(25);
        portSet.add(110);
        portSet.add(143);
        portSet.add(3389);
        portSet.add(53);
        portSet.add(137);
        portSet.add(138);
        portSet.add(139);
        portSet.add(445);
        portSet.add(587);
        portSet.add(3306);
        portSet.add(5432);
        portSet.add(1521);
        portSet.add(1433);
        portSet.add(27017);
        portSet.add(8080);
        portSet.add(8443);
        portSet.add(123);
        portSet.add(995);
        portSet.add(993);
        portSet.add(3306);
        portSet.add(5432);
        portSet.add(6379);
        portSet.add(5439);
        portSet.add(1521);
        portSet.add(1433);
        portSet.add(3306);
        portSet.add(5432);
        portSet.add(27017);
        portSet.add(27017);
        portSet.add(5672);
        portSet.add(1883);
        portSet.add(8883);
        portSet.add(9200);
        portSet.add(9300);
        portSet.add(5601);
        portSet.add(9200);
        portSet.add(9300);
        portSet.add(5671);
        portSet.add(4369);
        portSet.add(5672);
        portSet.add(15672);
        portSet.add(61613);
        portSet.add(61616);
        portSet.add(8883);
        DiskHashSet<Integer> diskSet = new DiskHashSet<Integer>(soursePath);
        // Вывод информации о портах
        //return portSet;
    }

}
