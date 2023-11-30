package edu.hw8;

import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.net.*;
import java.io.*;
import java.util.concurrent.*;

public class Server {

    private int serverPort;
    private ServerSocket serverSocket = null;
    private ExecutorService threadPool = Executors.newFixedThreadPool(10);
    private Map<String, String> quotes;

    public Server(int port){
        this.serverPort = port;
        this.quotes = new HashMap<>();
        quotes.put("личности", "Не переходи на личности там, где их нет");
        // добавьте остальные цитаты
    }

    public void startServer() throws IOException {
        serverSocket = new ServerSocket(serverPort);
        while(true){
            Socket socket = serverSocket.accept();
            threadPool.execute(new WorkerThread(socket, quotes));
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(8080);
        server.startServer();
    }
}

