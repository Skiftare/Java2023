package edu.project1;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

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
            return "Win!";
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
            return "Hit!";
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
            return "Missed, mistake " + attempt() + " out of " + maxAttempts();
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

}
