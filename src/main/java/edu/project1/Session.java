package edu.project1;

import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class Session {
    private static String answer;
    //К пользовательскому ответу у нас есть доступ

    private static final int MAX_ATTEMPTS = 5;
    static int attempts;
    static String userAnswer;

    Session() {
        StringBuilder generationString;
        String[] variantsOfWord = {"testmessage"};
        answer = variantsOfWord[new Random().nextInt(variantsOfWord.length)];
        generationString = new StringBuilder();
        generationString.append("*".repeat(answer.length()));
        userAnswer = generationString.toString();
        attempts = 0;

    }

    @NotNull static GuessResult guess(@NotNull Character guess) {

        StringBuilder newUserAnswer = new StringBuilder(userAnswer);
        GuessResult verdict;

        //На этом этапе мы можем гаранитровать, что этот символ - из алфавита и мы его до этого не видели
        if (answer.contains(guess.toString())) {
            for (int i = 0; i < answer.length(); i++) {
                if (answer.charAt(i) == guess) {
                    newUserAnswer.setCharAt(i, guess);
                }
            }
            userAnswer = newUserAnswer.toString();
            //Мы угадали, но слово полностью не раскрыто
            if (newUserAnswer.toString().contains("*")) {
                verdict = new GuessResult.SuccessfulGuess();
            } else {
                verdict = new GuessResult.Win();
            }
        } else {
            attempts++;
            if (attempts > MAX_ATTEMPTS) {
                verdict = new GuessResult.Defeat();

            } else {
                verdict = new GuessResult.FailedGuess(MAX_ATTEMPTS, attempts);
            }
        }

        return verdict;
    }

    @NotNull GuessResult giveUp() {
        return new GuessResult.OutOfGame();
    }
}
