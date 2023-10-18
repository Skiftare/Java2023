package edu.project1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;


class ConsoleHangman {

    private final HashSet<Character> receivedChars = new HashSet<>();
    private final static Logger LOGGER = LogManager.getLogger();
    String giveUpString = "stop";
    HashSet<String>  endOfGameWords = new HashSet<>();


    public String run() throws IOException {
        LOGGER.info("Start");
        Session newGame = new Session();
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(isr);

        endOfGameWords.add("Stop");
        endOfGameWords.add("Win");
        endOfGameWords.add("Defeat");

        LOGGER.info("Word has been generated\n To stop the game, use <stop> without brackets");
        LOGGER.info("CaPSlocK DOnT InFLUenCe tO the gaME");
        while (true) {

            String userInput = reader.readLine().toLowerCase();

            //LOGGER.info("User's input caught");

            GuessResult verdict = newGame.giveUp();
            if (Objects.equals(userInput, giveUpString)) {
                verdict = newGame.giveUp();
            } else {
                while (!checkInput(userInput)) {
                    userInput = reader.readLine().toLowerCase();
                    if (Objects.equals(userInput, giveUpString)) {
                        break;
                    }
                }
                //Мы прошли проверку, получили значение и теперь готовы его обрабатывать
                if (!userInput.equals(giveUpString)) {
                    verdict = Session.guess(userInput.charAt(0));
                }
                //
            }
            //Мы передали символ в guessResult, который находится в Session, получили какой-то мистический ответ.
            //Теперь у нас есть какое-то состояние игры - проигрыш, выигрыш, пауза (без возможности продолжить)
            // ..верное угадывание, неверное угадывание

            LOGGER.info(verdict.message());
            if (endOfGameWords.contains(verdict.gameStatus())) {
                return verdict.gameStatus();
            }
            if (verdict.gameStatus().equals("Succ")) {
                LOGGER.info(Session.userAnswer);
            }
        }
    }

    private boolean checkInput(@NotNull String s) {

        if (s.length() == 1 && s.matches("[a-z]")) {
            if (receivedChars.contains(s.charAt(0))) {
                return false;
            }
            receivedChars.add(s.charAt(0));
            return true;
        }
        return false;
    }

}
