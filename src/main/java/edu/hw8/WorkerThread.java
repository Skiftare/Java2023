package edu.hw8;

import java.io.*;
import java.net.Socket;
import java.util.Map;

class WorkerThread implements Runnable {

    private Socket clientSocket = null;
    private Map<String, String> quotes;

    public WorkerThread(Socket clientSocket, Map<String, String> quotes) {
        this.clientSocket = clientSocket;
        this.quotes = quotes;
    }

    public void run() {
        try (InputStream input = clientSocket.getInputStream();
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
