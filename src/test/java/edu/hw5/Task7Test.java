package edu.hw5;

import org.junit.jupiter.api.Test;
import static edu.hw5.Task7.hasAtLeastThreeCharactersWithThirdZero;
import static edu.hw5.Task7.hasLengthBetweenOneAndThree;
import static edu.hw5.Task7.startsAndEndsWithSameCharacter;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task7Test {
    @Test
    public void testThatGetStringHasAtLeastThreeCharactersWithThirdZeroAndReturnedTrue() {
        String input = "10101";
        boolean hasPattern = hasAtLeastThreeCharactersWithThirdZero(input);
        assertThat(hasPattern).isTrue();
    }

    @Test
    public void testThatGetStringDoesNotHaveAtLeastThreeCharactersWithThirdZeroAndReturnedFalse() {
        String input = "100";
        boolean hasPattern = hasAtLeastThreeCharactersWithThirdZero(input);
        assertThat(hasPattern).isFalse();
    }

    @Test
    public void testThatGetStringStartsAndEndsWithNotSameCharacterAndReturnedFalse() {
        String input = "100";
        boolean hasPattern = startsAndEndsWithSameCharacter(input);
        assertThat(hasPattern).isFalse();
    }

    @Test
    public void testThatGetStringStartAndEndWithSameCharacterAndReturnedFalse() {
        String input = "010";
        boolean hasPattern = startsAndEndsWithSameCharacter(input);
        assertThat(hasPattern).isTrue();
    }

    @Test
    public void testThatGetStringHasLengthBetweenOneAndThreeAndReturnedTrue() {
        String input = "11";
        boolean hasPattern = hasLengthBetweenOneAndThree(input);
        assertThat(hasPattern).isTrue();
    }

    @Test
    public void testThatGetStringDoesNotHaveLengthBetweenOneAndThreeAndReturnedFalse() {
        String input = "1111";
        boolean hasPattern = hasLengthBetweenOneAndThree(input);
        assertThat(hasPattern).isFalse();
        input = "";
        hasPattern = hasLengthBetweenOneAndThree(input);
        assertThat(hasPattern).isFalse();
    }
}
