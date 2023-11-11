package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw5.Task8.everyOddCharacterIsOne;
import static edu.hw5.Task8.hasAtLeastTwoZerosAndAtMostOneOne;
import static edu.hw5.Task8.hasMultipleOfThreeZeros;
import static edu.hw5.Task8.hasNoConsecutiveOnes;
import static edu.hw5.Task8.isNotElevenOrOneHundredEleven;
import static edu.hw5.Task8.isOddLength;
import static edu.hw5.Task8.startsWithZeroAndIsOddLengthOrStartsWithOneAndIsEvenLength;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task8Test {
    @Test
    @DisplayName("Проверка, что строка с нечетной длиной возвращает значение true")
    public void testThatGetStringWithOddLengthAndReturnedTrue() {
        String input = "101";
        boolean isOddLength = isOddLength(input);
        assertThat(isOddLength).isTrue();
    }

    @Test
    @DisplayName("Проверка, что строка с четной длиной возвращает значение false")
    public void testThatGetStringWithNotOddLengthAndReturnedFalse() {
        String input = "1010";
        boolean isOddLength = isOddLength(input);
        assertThat(isOddLength).isFalse();
    }

    @Test
    @DisplayName("Проверка, что строка, начинающаяся с нуля и имеющая нечетную длину, или строка, начинающаяся с единицы и имеющая четную длину, возвращает значение true")
    public void testThatGetStringStartsWithZeroAndIsOddLengthOrStartsWithOneAndIsEvenLengthAndReturnedTrue() {
        String input1 = "001";
        String input2 = "1001";
        boolean hasPattern1 = startsWithZeroAndIsOddLengthOrStartsWithOneAndIsEvenLength(input1);
        boolean hasPattern2 = startsWithZeroAndIsOddLengthOrStartsWithOneAndIsEvenLength(input2);
        assertThat(hasPattern1).isTrue();
        assertThat(hasPattern2).isTrue();
    }

    @Test
    @DisplayName("Проверка, что строка, не начинающаяся с нуля и имеющая нечетную длину, или строка, не начинающаяся с единицы и имеющая четную длину, возвращает значение false")
    public void testThatGetStringDoesNotStartsWithZeroAndIsOddLengthOrStartsWithOneAndIsEvenLengthAndReturnedFalse() {
        String input1 = "0001";
        String input2 = "10101";
        boolean hasPattern1 = startsWithZeroAndIsOddLengthOrStartsWithOneAndIsEvenLength(input1);
        boolean hasPattern2 = startsWithZeroAndIsOddLengthOrStartsWithOneAndIsEvenLength(input2);
        assertThat(hasPattern1).isTrue();
        assertThat(hasPattern2).isTrue();
    }

    @Test
    @DisplayName("Проверка, что строка, сумма цифр которой является кратной трём, возвращает значение true")
    public void testThatGetStringWithMultipleOfThreeZerosAtSumAndReturnedTrue() {
        String input = "100100100";
        boolean hasPattern = hasMultipleOfThreeZeros(input);
        assertThat(hasPattern).isTrue();
    }

    @Test
    @DisplayName("Проверка, что строка, сумма цифр которой не является кратной трём, возвращает значение false")
    public void testThatGetStringWithoutMultipleOfThreeZerosAtSumAndReturnedFalse() {
        String input = "10010010";
        boolean hasPattern = hasMultipleOfThreeZeros(input);
        assertThat(hasPattern).isFalse();
    }

    @Test
    @DisplayName("Проверка, что строка, равная '11' или '111', возвращает значение false")
    public void testThatGetStringIsElevenOrOneHundredElevenAndReturnedFalse() {
        String input1 = "11";
        String input2 = "111";
        boolean isNotElevenOrOneHundredEleven1 = isNotElevenOrOneHundredEleven(input1);
        boolean isNotElevenOrOneHundredEleven2 = isNotElevenOrOneHundredEleven(input2);
        assertThat(isNotElevenOrOneHundredEleven1).isFalse();
        assertThat(isNotElevenOrOneHundredEleven2).isFalse();
    }

    @Test
    @DisplayName("Проверка, что строка, не равная '11' или '111', возвращает значение true")
    public void testThatGetStringIsNotElevenOrOneHundredElevenAndReturnedTrue() {
        String input1 = "110";
        String input2 = "1110";
        boolean isNotElevenOrOneHundredEleven1 = isNotElevenOrOneHundredEleven(input1);
        boolean isNotElevenOrOneHundredEleven2 = isNotElevenOrOneHundredEleven(input2);
        assertThat(isNotElevenOrOneHundredEleven1).isTrue();
        assertThat(isNotElevenOrOneHundredEleven2).isTrue();
    }

    @Test
    @DisplayName("Проверка, что строка, в которой каждый нечетный символ является единицей, возвращает значение true")
    public void testThatGetStringWhereEveryOddCharacterIsOneAndReturnedTrue() {
        String input = "10101";
        boolean everyOddCharacterIsOne = everyOddCharacterIsOne(input);
        assertThat(everyOddCharacterIsOne).isTrue();
    }

    @Test
    @DisplayName("Проверка, что строка, в которой не каждый нечетный символ является единицей, возвращает значение false")
    public void testThatGetStringNotEveryOddCharacterIsOneAndReturnedFalse() {
        String input = "10001";
        boolean everyOddCharacterIsOne = everyOddCharacterIsOne(input);
        assertThat(everyOddCharacterIsOne).isFalse();
    }

    @Test
    @DisplayName("Проверка, что строка, содержащая как минимум два нуля и не более одной единицы, возвращает значение true")
    public void testThatGetStringWithAtLeastTwoZerosAndAtMostOneOneAndReturnedTrue() {
        String input1 = "0010";
        String input2 = "00";
        boolean hasPattern1 = hasAtLeastTwoZerosAndAtMostOneOne(input1);
        boolean hasPattern2 = hasAtLeastTwoZerosAndAtMostOneOne(input2);
        assertThat(hasPattern1).isTrue();
        assertThat(hasPattern2).isTrue();
    }

    @Test
    @DisplayName("Проверка, что строка, не содержащая как минимум два нуля или содержащая более одной единицы, возвращает значение false")
    public void testThatGetStringWithoutAtLeastTwoZerosAndAtMostOneOneAndReturnedFalse() {
        String input1 = "10001";
        String input2 = "1010101";
        boolean hasPattern1 = hasAtLeastTwoZerosAndAtMostOneOne(input1);
        boolean hasPattern2 = hasAtLeastTwoZerosAndAtMostOneOne(input2);
        assertThat(hasPattern1).isFalse();
        assertThat(hasPattern2).isFalse();
    }

    @Test
    @DisplayName("Проверка, что строка не содержит последовательных единиц и возвращает значение true")
    public void testThatGetStringHasNoConsecutiveOnesAndReturnedTrue() {
        String input = "10101";
        boolean hasNoConsecutiveOnes = hasNoConsecutiveOnes(input);
        assertThat(hasNoConsecutiveOnes).isTrue();
    }

    @Test
    @DisplayName("Проверка, что строка содержит последовательные единицы и возвращает значение false")
    public void testThatGetStringHasConsecutiveOnesAndReturnedFalse() {
        String input = "10011";
        boolean hasNoConsecutiveOnes = hasNoConsecutiveOnes(input);
        assertThat(hasNoConsecutiveOnes).isFalse();
    }
}
