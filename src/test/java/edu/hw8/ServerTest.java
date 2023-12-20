package edu.hw8;

import edu.hw8.task1.Server;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ServerTest {


    @Test
    @DisplayName("Сервер получает строку, которая есть в его кодовой базе")
    public void testThatGetMessageThatIsInTheBaseAndReturnedPhrase() throws IOException, InterruptedException {
            //given: server at localhost
            Server server = new Server(8080);
            String expected = "Не переходи на личности там, где их нет";
            String inputWord = "личности";
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
            //when: we give server a word
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println(inputWord);
            String response = in.readLine();
            //then: we get phrase
            assertThat(response).isEqualTo(expected);

            server.stopServer();
            out.close();
        }

    @Test
    @DisplayName("Сервер получает строку, которой нет в его кодовой базе")
    public void testThatGetMessageThatIsNotInTheBaseAndReturnedDefaultPhrase() throws IOException, InterruptedException {
        //given: server at localhost
        Server server = new Server(8080);
        String expected = "Хм. Не хочу тебя унижать дальше, так что оставлю твою чушь без ответа";
        String inputWord = "Нет в кодовой базе";
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
        //when: we give server a word, with is not expected
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        out.println(inputWord);
        String response = in.readLine();
        //then: we get phrase
        assertThat(response).isEqualTo(expected);

        server.stopServer();
        out.close();
    }

    @Test //Т.к. у нас есть NullPointer, да и в целом граничный случай - спец.тест
    @DisplayName("Сервер получает null-строку")
    public void testThatGetNullMessageAndReturnedDefaultPhrase() throws IOException, InterruptedException {
        //given: server at localhost
        Server server = new Server(8080);
        String expected = "Хм. Не хочу тебя унижать дальше, так что оставлю твою чушь без ответа";
        String inputWord = null;
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
        //when: we give server a word, with is not expected
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        out.println(inputWord);
        String response = in.readLine();
        //then: we get phrase
        assertThat(response).isEqualTo(expected);

        server.stopServer();
        out.close();
    }
}
