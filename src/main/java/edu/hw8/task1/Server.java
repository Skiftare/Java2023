package edu.hw8.task1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final int NUMBER_OF_THREADS = 10;
    private final int serverPort;
    private ServerSocket serverSocket = null;
    private final ExecutorService threadPool = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private final Map<String, String> quotes;
    private boolean isRunning = true;

    public Server(int port) {
        this.serverPort = port;
        this.quotes = new HashMap<>();
        quotes.put("личности", "Не переходи на личности там, где их нет");
    }

    public void startServer() throws IOException {
        serverSocket = new ServerSocket(serverPort);
        isRunning = true;
        while (isRunning) {
            Socket socket = serverSocket.accept();
            threadPool.execute(new WorkerThread(socket, quotes));
        }
    }

    public void stopServer() throws IOException {
        isRunning = false;
        serverSocket.close();
    }
}

