package edu.hw6;
import java.net.ServerSocket;
import java.net.DatagramSocket;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Map;

public class Task6 {
    public Task6(Path soursePath){
        CreatePortDescriptions.getPortsMap(soursePath);
    }

        public static void main(Path soursePath) {
            DiskHashSet<Integer> map = new DiskHashSet<>(soursePath);
            //HashSet<Integer> map = (HashSet<Integer>) CreatePortDescriptions.getPortsMap();
            for (int port = 0; port <= 49151; port++) {
                if(map.contains(port)){
                    try {
                        ServerSocket serverSocket = new ServerSocket(port);
                        serverSocket.close();
                        System.out.println("TCP\t" + port);
                    } catch (IOException e) {
                        System.out.println("TCP\t" + port + "\t" + WikipediaParser.getPortDescription(port).getDescription());
                    }

                    try {
                        DatagramSocket datagramSocket = new DatagramSocket(port);
                        datagramSocket.close();
                        //String info = WikipediaParser.getPortDescription(port).getDescription();
                        System.out.println("UDP\t" + port);
                    } catch (IOException e) {
                        System.out.println("UDP\t" + port + "\t"+WikipediaParser.getPortDescription(port).getDescription());
                    }
                }
            }
        }
    }

