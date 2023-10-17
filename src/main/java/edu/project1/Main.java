package edu.project1;

import org.jetbrains.annotations.NotNull;
import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.util.Random;
import java.util.Scanner;

class Session {
    private final String answer;
    private final char[] userAnswer;
    private final int maxAttempts;
    private int attempts;
    private final String [] variantsOfWord = {"Squush", "Kickshaw", "Zugzwang", "Ytterbium", "Randkluft", "Yclept"};
    Session() {
        this.answer = randomWord();
        this.userAnswer = " ".toCharArray();
        this.maxAttempts = 5;
        this.attempts = 0;
    }

    @NotNull GuessResult guess(char guess) {
        return null;
    }

    @NotNull GuessResult giveUp() {
        return null;
    }

    public @NotNull String randomWord() {
        return variantsOfWord[new Random().nextInt(variantsOfWord.length)];
    }
}

sealed interface GuessResult {
    char[] state();
    int attempt();
    int maxAttempts();
    @NotNull String message();

    record Defeat() implements GuessResult {
        @Override
        public char[] state() {
            return new char[0];
        }

        @Override
        public int attempt() {
            return 0;
        }

        @Override
        public int maxAttempts() {
            return 0;
        }

        @Override
        public @NotNull String message() {
            return "You have exceeded the maximum allowed number of attempts. Good luck next time!";
        }
    }
    record Win() implements GuessResult {

        @Override
        public char[] state() {
            return new char[0];
        }

        @Override
        public int attempt() {
            return 0;
        }

        @Override
        public int maxAttempts() {
            return 0;
        }

        @Override
        public @NotNull String message() {
            return null;
        }
    }
    record SuccessfulGuess() implements GuessResult {

        @Override
        public char[] state() {
            return new char[0];
        }

        @Override
        public int attempt() {
            return 0;
        }

        @Override
        public int maxAttempts() {
            return 0;
        }

        @Override
        public @NotNull String message() {
            return null;
        }
    }
    record FailedGuess() implements GuessResult {

        @Override
        public char[] state() {
            return new char[0];
        }

        @Override
        public int attempt() {
            return 0;
        }

        @Override
        public int maxAttempts() {
            return 0;
        }

        @Override
        public @NotNull String message() {
            return null;
        }
    }
}


class ConsoleHangman {
    public void run() {
        Session newGame = new Session();
        Scanner input = new Scanner(System.in);
        while (true) {
            String userInput = input.nextLine();

        }
    }
    private GuessResult tryGuess(Session session, String input) {

        return null;
    }

    private void printState(GuessResult guess) {

    }
}
