package edu.hw8;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ServerTest {


        @Test
        public void testServerResponse() throws IOException, InterruptedException {
            Server server = new Server(8080);
            // Запуск сервера в отдельном потоке, чтобы он мог работать параллельно с клиентом
            new Thread(() -> {
                try {
                    server.startServer();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();

            // Ждём прогрева ламп, на которых наш сервер работает
            Thread.sleep(1000);

            Socket socket = new Socket("localhost", 8080);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("личности");
            String response = in.readLine();

            assertThat(response).isEqualTo("Не переходи на личности там, где их нет");

            out.close();
        }
}
