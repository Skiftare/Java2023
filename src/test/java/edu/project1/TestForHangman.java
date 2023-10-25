package edu.project1;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestForHangman {

    @Test
    @DisplayName("Тест для остановки игры игры")
    void testThatGetStopSigndAndReturnedStop() throws IOException {
        RandomGenerating.init();
        ConsoleHangman Game = new ConsoleHangman();
        StringBuilder sb = new StringBuilder();
        sb.append("stop").append('\n');
        String data = sb.toString();
        InputStream is = new ByteArrayInputStream(data.getBytes());
        System.setIn(is);
        assertEquals(Game.run(), "Stop");
    }

    @Test
    @DisplayName("Тест на корректность обработки некорректного ввода")
    void testThatGetManyIncorrectInputsAndReturnedStop() throws IOException {
        RandomGenerating.init();
        ConsoleHangman Game = new ConsoleHangman();
        StringBuilder sb = new StringBuilder();
        //За счёт количества попыток, если некорректный ввод будет считаться - то Defeat
        sb.append("qq").append('\n');
        sb.append("qq").append('\n');
        sb.append("qq").append('\n');
        sb.append("qqq").append('\n');
        sb.append("x2qqq").append('\n');
        sb.append("jeqeq").append('\n');
        sb.append("stop").append('\n');
        String data = sb.toString();
        InputStream is = new ByteArrayInputStream(data.getBytes());
        System.setIn(is);
        assertEquals(Game.run(), "Stop");

    }

    @Test
    @DisplayName("Тест на хорошую игру")
    void testThatGetWinInputsAndReturnedWin() throws IOException {
        RandomGenerating.init();
        ConsoleHangman Game = new ConsoleHangman();
        StringBuilder sb = new StringBuilder();
        //За счёт количества попыток, если некорректный ввод будет считаться - то Defeat
        String ans = "testmessage";
        for(int i = 0;i<ans.length();i++) sb.append(ans.charAt(i)).append('\n');


        String data = sb.toString();
        InputStream is = new ByteArrayInputStream(data.getBytes());
        System.setIn(is);
        assertEquals(Game.run(), "Win");


    }

    @Test
    @DisplayName("Тест на проигрыш")
    void testThatGetManyWrongLettersAndReturnedDefeat() throws IOException {
        RandomGenerating.init();
        ConsoleHangman Game = new ConsoleHangman();
        StringBuilder sb = new StringBuilder();
        //За счёт количества попыток, если некорректный ввод будет считаться - то Defeat
        String ans = "qwryuip";
        for(int i = 0;i<ans.length();i++) sb.append(ans.charAt(i)).append('\n');


        String data = sb.toString();
        InputStream is = new ByteArrayInputStream(data.getBytes());
        System.setIn(is);
        assertEquals(Game.run(), "Defeat");


    }
}
