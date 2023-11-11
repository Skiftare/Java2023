package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw5.Task7.hasAtLeastThreeCharactersWithThirdZero;
import static edu.hw5.Task7.hasLengthBetweenOneAndThree;
import static edu.hw5.Task7.startsAndEndsWithSameCharacter;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {
    @Test
    @DisplayName("Строка удовлетворяет: содержит не менее 3 символов, причем третий символ равен 0")
    public void testThatGetStringHasAtLeastThreeCharactersWithThirdZeroAndReturnedTrue() {
        String input = "10101";
        boolean hasPattern = hasAtLeastThreeCharactersWithThirdZero(input);
        assertThat(hasPattern).isTrue();
    }

    @Test
    @DisplayName("Строка не удовлетворяет: содержит не менее 3 символов, причем третий символ равен 0")
    public void testThatGetStringDoesNotHaveAtLeastThreeCharactersWithThirdZeroAndReturnedFalse() {
        String input = "100";
        boolean hasPattern = hasAtLeastThreeCharactersWithThirdZero(input);
        assertThat(hasPattern).isFalse();
    }

    @Test
    @DisplayName("Строка не удовл: начинается и заканчивается одним и тем же символом")
    public void testThatGetStringStartsAndEndsWithNotSameCharacterAndReturnedFalse() {
        String input = "100";
        boolean hasPattern = startsAndEndsWithSameCharacter(input);
        assertThat(hasPattern).isFalse();
    }

    @Test
    @DisplayName("Строка удовл: начинается и заканчивается одним и тем же символом")
    public void testThatGetStringStartAndEndWithSameCharacterAndReturnedFalse() {
        String input = "010";
        boolean hasPattern = startsAndEndsWithSameCharacter(input);
        assertThat(hasPattern).isTrue();
    }

    @Test
    @DisplayName("Строка удовл: длина не менее 1 и не более 3")
    public void testThatGetStringHasLengthBetweenOneAndThreeAndReturnedTrue() {
        String input = "11";
        boolean hasPattern = hasLengthBetweenOneAndThree(input);
        assertThat(hasPattern).isTrue();
    }

    @Test
    @DisplayName("Строка не удовл: длина не менее 1 и не более 3")
    public void testThatGetStringDoesNotHaveLengthBetweenOneAndThreeAndReturnedFalse() {
        String input = "1111";
        boolean hasPattern = hasLengthBetweenOneAndThree(input);
        assertThat(hasPattern).isFalse();
        input = "";
        hasPattern = hasLengthBetweenOneAndThree(input);
        assertThat(hasPattern).isFalse();
    }
}
