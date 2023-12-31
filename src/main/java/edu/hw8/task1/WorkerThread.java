package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.Objects;

public class WorkerThread implements Runnable {

    private static final String DEFAULT_PHRASE =
        "Хм. Не хочу тебя унижать дальше, так что оставлю твою чушь без ответа";
    private Socket clientSocket = null;
    private final Map<String, String> quotes;

    WorkerThread(Socket clientSocket, Map<String, String> quotes) {
        this.clientSocket = clientSocket;
        this.quotes = quotes;
    }

    public void run() {
        try (InputStream input = clientSocket.getInputStream();
             OutputStream output = clientSocket.getOutputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            String keyword = reader.readLine();
            String quote = quotes.get(keyword);
            output.write(Objects.requireNonNullElse(quote, DEFAULT_PHRASE).getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Ошибка обработки запроса", e);
        }
    }
}
