package edu.hw8;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private int serverPort;
    private ServerSocket serverSocket = null;
    private static final int NUMBER_OF_THREADS = 10;
    private ExecutorService threadPool = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private Map<String, String> quotes;

    public Server(int port) {
        this.serverPort = port;
        this.quotes = new HashMap<>();
        quotes.put("личности", "Не переходи на личности там, где их нет");
        //TODO:добавить ещё.
    }

    public void startServer() throws IOException {
        serverSocket = new ServerSocket(serverPort);
        while (true) {
            Socket socket = serverSocket.accept();
            threadPool.execute(new WorkerThread(socket, quotes));
        }
    }
}

