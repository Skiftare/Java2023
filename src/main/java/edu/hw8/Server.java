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

class WorkerThread implements Runnable {

    private Socket clientSocket = null;
    private Map<String, String> quotes;

    public WorkerThread(Socket clientSocket, Map<String, String> quotes) {
        this.clientSocket = clientSocket;
        this.quotes = quotes;
    }

    public void run() {
        try (InputStream input  = clientSocket.getInputStream();
             OutputStream output = clientSocket.getOutputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            String keyword = reader.readLine();
            String quote = quotes.get(keyword);
            if (quote != null) {
                output.write(quote.getBytes());
            } else {
                output.write("Нет цитаты для данного ключевого слова".getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка обработки запроса", e);
        }
    }
}
