package edu.hw5;

import org.junit.jupiter.api.Test;
import static edu.hw5.Task8.everyOddCharacterIsOne;
import static edu.hw5.Task8.hasAtLeastTwoZerosAndAtMostOneOne;
import static edu.hw5.Task8.hasMultipleOfThreeZeros;
import static edu.hw5.Task8.hasNoConsecutiveOnes;
import static edu.hw5.Task8.isNotElevenOrOneHundredEleven;
import static edu.hw5.Task8.isOddLength;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task8Test {
    @Test
    public void testThatGetStringWithOddLengthAndReturnedTrue() {
        String input = "101";
        boolean isOddLength = isOddLength(input);
        assertThat(isOddLength).isTrue();
    }

    @Test
    public void testThatGetStringWithNotOddLengthAndReturnedFalse() {
        String input = "1010";
        boolean isOddLength = isOddLength(input);
        assertThat(isOddLength).isFalse();
    }

    /*@Test
    public void testStartsWithZeroAndIsOddLengthOrStartsWithOneAndIsEvenLength() {
        String input1 = "0101";
        String input2 = "10101";
        boolean hasPattern1 = startsWithZeroAndIsOddLengthOrStartsWithOneAndIsEvenLength(input1);
        boolean hasPattern2 = startsWithZeroAndIsOddLengthOrStartsWithOneAndIsEvenLength(input2);
        assertThat(hasPattern1).isFalse();
        assertThat(hasPattern2).isFalse();
    }

    @Test
    public void testDoesNotStartWithZeroAndIsOddLengthOrStartsWithOneAndIsEvenLength() {
        String input1 = "001";
        String input2 = "1001";
        boolean hasPattern1 = startsWithZeroAndIsOddLengthOrStartsWithOneAndIsEvenLength(input1);
        boolean hasPattern2 = startsWithZeroAndIsOddLengthOrStartsWithOneAndIsEvenLength(input2);
        assertThat(hasPattern1).isTrue();

        assertThat(hasPattern2).isTrue();
    }*/

    @Test
    public void testThatGetStringWithMultipleOfThreeZerosAtSumAndReturnedTrue() {
        String input = "100100100";
        boolean hasPattern = hasMultipleOfThreeZeros(input);
        assertThat(hasPattern).isTrue();
    }

    @Test
    public void testThatGetStringWithoutMultipleOfThreeZerosAtSumAndReturnedFalse() {
        String input = "10010010";
        boolean hasPattern = hasMultipleOfThreeZeros(input);
        assertThat(hasPattern).isFalse();
    }

    @Test
    public void testThatGetStringIsElevenOrOneHundredElevenAndReturnedFalse() {
        String input1 = "11";
        String input2 = "111";
        boolean isNotElevenOrOneHundredEleven1 = isNotElevenOrOneHundredEleven(input1);
        boolean isNotElevenOrOneHundredEleven2 = isNotElevenOrOneHundredEleven(input2);
        assertThat(isNotElevenOrOneHundredEleven1).isFalse();
        assertThat(isNotElevenOrOneHundredEleven2).isFalse();
    }

    @Test
    public void testThatGetStringIsNotElevenOrOneHundredElevenAndReturnedTrue() {
        String input1 = "110";
        String input2 = "1110";
        boolean isNotElevenOrOneHundredEleven1 = isNotElevenOrOneHundredEleven(input1);
        boolean isNotElevenOrOneHundredEleven2 = isNotElevenOrOneHundredEleven(input2);
        assertThat(isNotElevenOrOneHundredEleven1).isTrue();
        assertThat(isNotElevenOrOneHundredEleven2).isTrue();
    }

    @Test
    public void testThatGetStringWhereEveryOddCharacterIsOneAndReturnedTrue() {
        String input = "10101";
        boolean everyOddCharacterIsOne = everyOddCharacterIsOne(input);
        assertThat(everyOddCharacterIsOne).isTrue();
    }

    @Test
    public void testThatGetStringNotEveryOddCharacterIsOneAndReturnedFalse() {
        String input = "10001";
        boolean everyOddCharacterIsOne = everyOddCharacterIsOne(input);
        assertThat(everyOddCharacterIsOne).isFalse();
    }

    @Test
    public void testThatGetStringWithAtLeastTwoZerosAndAtMostOneOneAndReturnedTrue() {
        String input1 = "0010";
        String input2 = "00";
        boolean hasPattern1 = hasAtLeastTwoZerosAndAtMostOneOne(input1);
        boolean hasPattern2 = hasAtLeastTwoZerosAndAtMostOneOne(input2);
        assertThat(hasPattern1).isTrue();
        assertThat(hasPattern2).isTrue();
    }

    @Test
    public void testThatGetStringWithoutAtLeastTwoZerosAndAtMostOneOneAndReturnedFalse() {
        String input1 = "10001";
        String input2 = "1010101";
        boolean hasPattern1 = hasAtLeastTwoZerosAndAtMostOneOne(input1);
        boolean hasPattern2 = hasAtLeastTwoZerosAndAtMostOneOne(input2);
        assertThat(hasPattern1).isFalse();
        assertThat(hasPattern2).isFalse();
    }

    @Test
    public void testThatGetStringHasNoConsecutiveOnesAndReturnedTrue() {
        String input = "10101";
        boolean hasNoConsecutiveOnes = hasNoConsecutiveOnes(input);
        assertThat(hasNoConsecutiveOnes).isTrue();
    }

    @Test
    public void testThatGetStringHasConsecutiveOnesAndReturnedFalse() {
        String input = "10011";
        boolean hasNoConsecutiveOnes = hasNoConsecutiveOnes(input);
        assertThat(hasNoConsecutiveOnes).isFalse();
    }

}
