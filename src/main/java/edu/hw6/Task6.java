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
    private static final String PORTS_FILE_PATH = "src/main/java/edu/hw6/ports.json";
    private static final Character ENDL_CHAR = '\n';
    private static final Character TAB_CHAR = '\t';
    private static final String TSP_STRING = "TCP" + TAB_CHAR;
    private static final String UDP_STRING = "UDP" + TAB_CHAR;
    private static final HashSet<Integer> PORT_SET = new HashSet<>();
    private static final String SPLIT_STRING = ", ";

    public static void readPortsMapFromFile() {

        try (BufferedReader reader = new BufferedReader(new FileReader(PORTS_FILE_PATH))) {
            String json = reader.readLine();
            String[] portStrings = json.substring(1, json.length() - 1).split(SPLIT_STRING);
            for (String portString : portStrings) {
                int port = Integer.parseInt(portString);
                PORT_SET.add(port);
            }
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
    }

    private static String tspScan(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.close();
            return TSP_STRING + port + ENDL_CHAR;
        } catch (IOException e) {
            return
                TSP_STRING + port + TAB_CHAR + WikipediaParser.getPortDescription(port).description() + ENDL_CHAR;
        }
    }

    private static String updScan(int port) {
        try {
            DatagramSocket datagramSocket = new DatagramSocket(port);
            datagramSocket.close();
            return UDP_STRING + port + ENDL_CHAR;
        } catch (IOException e) {
            return
                UDP_STRING + port + TAB_CHAR + WikipediaParser.getPortDescription(port).description() + ENDL_CHAR;
        }
    }

    public static List<String> getPortsInfo() {
        List<String> resultOfScan = new ArrayList<>();
        readPortsMapFromFile();
        for (int port : PORT_SET) {
            resultOfScan.add(tspScan(port));
            resultOfScan.add(updScan(port));
        }
        return resultOfScan;
    }
}

