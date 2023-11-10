package edu.hw6;
import java.net.ServerSocket;
import java.net.DatagramSocket;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Task6 {
    static HashSet<Integer> portSet = new HashSet<>();
    private static HashSet<Integer> getPortsMap() {

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
        portSet.add(6379);
        portSet.add(5439);
        portSet.add(5672);
        portSet.add(1883);
        portSet.add(8883);
        portSet.add(9200);
        portSet.add(9300);
        portSet.add(5601);
        portSet.add(5671);
        portSet.add(4369);
        portSet.add(15672);
        portSet.add(61613);
        portSet.add(61616);


        return portSet;
    }

        public static List<String> getPortsInfo() {
            List<String> resultOfScan = new ArrayList<>();
            getPortsMap();
            for (int port : portSet) {
                //for (int port = 0; port <= 49151; port++) {
                //if (map.contains(port)) {
                try {
                    ServerSocket serverSocket = new ServerSocket(port);
                    serverSocket.close();
                    resultOfScan.add("TCP\t" + port+'\n');
                } catch (IOException e) {
                    resultOfScan.add(
                            "TCP\t" + port + "\t" + WikipediaParser.getPortDescription(port).getDescription()+'\n');
                }

                try {
                    DatagramSocket datagramSocket = new DatagramSocket(port);
                    datagramSocket.close();
                    //String info = WikipediaParser.getPortDescription(port).getDescription();
                    resultOfScan.add("UDP\t" + port+'\n');
                } catch (IOException e) {
                    resultOfScan.add(
                            "UDP\t" + port + "\t" + WikipediaParser.getPortDescription(port).getDescription()+'\n');
                }
                //}
                // }
            }
            return resultOfScan;
        }
    }

