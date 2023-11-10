package edu.hw6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.apache.logging.log4j.LogManager;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task6 {

    private final static org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
    static HashSet<Integer> portSet = new HashSet<>();
    private static final String PORTS_FILE_PATH = "src/main/java/edu/hw6/ports.json";
    private static final String TSP_STRING = "TCP\t";
    private static final String UDP_STRING = "UDP\t";

    public static void readPortsMapFromFile() {

        try (BufferedReader reader = new BufferedReader(new FileReader(PORTS_FILE_PATH))) {
            String json = reader.readLine();
            String[] portStrings = json.substring(1, json.length() - 1).split(", ");
            for (String portString : portStrings) {
                int port = Integer.parseInt(portString);
                portSet.add(port);
            }
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
    }


    public static List<String> getPortsInfo() {
        List<String> resultOfScan = new ArrayList<>();
        readPortsMapFromFile();
        for (int port : portSet) {
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                serverSocket.close();
                resultOfScan.add(TSP_STRING + port + '\n');
            } catch (IOException e) {
                resultOfScan.add(
                    TSP_STRING + port + '\t' + WikipediaParser.getPortDescription(port).getDescription() + '\n');
            }

            try {
                DatagramSocket datagramSocket = new DatagramSocket(port);
                datagramSocket.close();
                resultOfScan.add(UDP_STRING + port + '\n');
            } catch (IOException e) {
                resultOfScan.add(
                    UDP_STRING + port + '\t' + WikipediaParser.getPortDescription(port).getDescription() + '\n');
            }
        }
        return resultOfScan;
    }
}

