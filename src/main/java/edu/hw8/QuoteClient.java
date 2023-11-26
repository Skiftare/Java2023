package edu.hw8;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class QuoteClient {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 1234;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
             InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream();
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Введите ключевое слово: ");
            String keyword = scanner.nextLine();

            outputStream.write(keyword.getBytes());

            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);

            if (bytesRead > 0) {
                String quote = new String(buffer, 0, bytesRead);
                System.out.println("Сервер: " + quote);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
