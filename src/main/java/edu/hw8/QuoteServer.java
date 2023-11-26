package edu.hw8;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QuoteServer {
    private static final int PORT = 1234;
    private static final int MAX_CONNECTIONS = 10;
    private static final String[] KEYWORDS = {"личности", "оскорбления", "глупый", "интеллект"};
    private static final String[] QUOTES = {
        "Не переходи на личности там, где их нет",
        "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
        "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
        "Чем ниже интеллект, тем громче оскорбления"
    };

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_CONNECTIONS);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен. Ожидание соединений...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                executorService.execute(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try (InputStream inputStream = clientSocket.getInputStream();
                 OutputStream outputStream = clientSocket.getOutputStream()) {
                byte[] buffer = new byte[1024];
                int bytesRead = inputStream.read(buffer);

                if (bytesRead > 0) {
                    String keyword = new String(buffer, 0, bytesRead).trim();
                    String quote = getQuoteByKeyword(keyword);
                    outputStream.write(quote.getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private String getQuoteByKeyword(String keyword) {
            for (int i = 0; i < KEYWORDS.length; i++) {
                if (KEYWORDS[i].equalsIgnoreCase(keyword)) {
                    return QUOTES[i];
                }
            }
            return "К сожалению, я не знаю цитаты по данному ключевому слову.";
        }
    }
}
