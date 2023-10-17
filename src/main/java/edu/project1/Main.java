package edu.project1;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import javax.swing.OverlayLayout;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

class Session {
    private static String answer;
    //К пользовательскому ответу у нас есть доступ
    static String userAnswer;
    static  int maxAttempts;
    static int attempts;
    private final String [] variantsOfWord = {"squush", "kickshaw", "zugzwang", "ytterbium", "randkluft", "yclept"};



    Session() {
        String generationString;
        this.answer = variantsOfWord[new Random().nextInt(variantsOfWord.length)];
        generationString = "";
        for(int i = 0;i<answer.length();i++) {
            generationString +="*";
        }
        this.userAnswer = generationString;
        this.maxAttempts = 5;
        this.attempts = 0;

    }

    @NotNull static GuessResult guess(@NotNull Character guess) {

        StringBuilder newUserAnswer = new StringBuilder(userAnswer);
        GuessResult verdict;

        //На этом этапе мы можем гаранитровать, что этот символ - из алфавита и мы его до этого не видели
        if(answer.contains(guess.toString())){
            for(int i = 0; i < answer.length(); i++) {
                if(answer.charAt(i) == guess) {
                    newUserAnswer.setCharAt(i,guess);
                }
            }
            //Мы угадали, но слово полностью не раскрыто
            if(newUserAnswer.toString().contains("*")) {
                verdict = new GuessResult.SuccessfulGuess();
            } else {
                verdict = new GuessResult.Win();
            }
        } else{
            attempts++;
            if(attempts > maxAttempts) {
                verdict = new GuessResult.Defeat();

            } else{
                verdict = new GuessResult.FailedGuess(maxAttempts,attempts);
            }
        }

        return verdict;
    }

    @NotNull GuessResult giveUp() {
        return new GuessResult.Defeat();
    }

}

sealed interface GuessResult {
    @NotNull String message();
    @NotNull String gameStatus();

    record Defeat() implements GuessResult {

        @Override
        public @NotNull String message() {
            return "You have exceeded the maximum allowed number of attempts. Good luck next time!";
        }

        @Override
        public @NotNull String gameStatus() {
            return "Defeat";
        }
    }
    record Win() implements GuessResult {


        @Override
        public @NotNull String message() {
            return null;
        }

        @Override
        public @NotNull String gameStatus() {
            return "Win";
        }
    }
    record SuccessfulGuess() implements GuessResult {
        @Override
        public @NotNull String message() {
            //Мы должны получать маску ответов
            return  "Hit!";
        }

        @Override
        public @NotNull String gameStatus() {
            return "Succ";
        }
    }
    record FailedGuess(Integer countOfMaxAttempts, Integer countOfAttempts) implements GuessResult {

        @Contract(value = " -> new", pure = true) public @NotNull String attempt() {
            return Integer.toString(countOfAttempts);
        }

        @Contract(value = " -> new", pure = true) public @NotNull String maxAttempts() {
            return Integer.toString(countOfMaxAttempts);
        }

        @Override
        public @NotNull String message() {
            return "Missed, mistake " + attempt() + " out of "+maxAttempts();
        }

        @Override
        public @NotNull String gameStatus() {
            return "Fail";
        }
    }

    record OutOfGame() implements GuessResult {

        @Override
        public @NotNull String message() {
            return "Game interrupted";
        }

        @Override
        public @NotNull String gameStatus() {
            return "Stop";
        }
    }

    record Default() implements GuessResult {

        @Override
        public @NotNull String message() {
            return "You dont see this";
        }

        @Override
        public @NotNull String gameStatus() {
            return "Default";
        }
    }
}


class ConsoleHangman {

    private final HashSet <Character> receivedChars = new HashSet<>();

    public void run() {
        Session newGame = new Session();
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine().toLowerCase();
        String giveUpString = "stop";
        GuessResult verdict = new GuessResult.Default();
        if(Objects.equals(userInput, giveUpString)) {
            verdict = newGame.giveUp();
        }
        else {
            while (!checkInput(userInput)) {
                userInput = input.nextLine().toLowerCase();
                if (Objects.equals(userInput, giveUpString)) {
                    verdict = newGame.giveUp();
                    break;
                }
            }
            //Мы прошли проверку, получили значение и теперь готовы его обрабатывать
            if(!userInput.equals(giveUpString)){
                verdict = Session.guess(userInput.charAt(0));
            }
            //
        }
        //Мы передали символ в guessResult, который находится в Session, получили какой-то мистический ответ.
        //Теперь у нас есть какое-то состояние игры - проигрыш, выигрыш, пауза (без возможности продолжить), верное угадывание, неверное угадывание
        String gameState = verdict.gameStatus();





    }

    private boolean checkInput(@NotNull String s){

        if(s.length() == 1 && s.matches("[a-z]")){
            if(receivedChars.contains(s.charAt(0))){
                return false;
            }
            receivedChars.add(s.charAt(0));
            return true;
        }
        return false;
    }

    private void printState(GuessResult guess) {

    }
}
