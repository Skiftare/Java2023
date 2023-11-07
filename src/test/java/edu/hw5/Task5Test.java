package edu.hw5;

import org.junit.jupiter.api.Test;
import static edu.hw5.Task5.validateLicensePlate;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    @Test
    public void testThatGetValidLicensePlateAndReturnedTrue() {
        String validLicensePlate = "А123ВЕ77";
        boolean isValid = validateLicensePlate(validLicensePlate);
        assertThat(isValid).isTrue();
    }

    @Test
    public void testThatGetInvalidLicensePlateInvalidCharactersAndReturnedFalse() {
        String invalidLicensePlate = "А123ВЕ77!";
        boolean isValid = validateLicensePlate(invalidLicensePlate);
        assertThat(isValid).isFalse();
    }

    @Test
    public void testThatGetInvalidLicensePlateInvalidFormatAndReturnedFalse() {
        String invalidLicensePlate = "А12ВЕ77";
        boolean isValid = validateLicensePlate(invalidLicensePlate);
        assertThat(isValid).isFalse();
    }

    @Test
    public void testThatGetInvalidLicensePlateTooShortAndReturnedFalse() {
        String invalidLicensePlate = "А123ВЕ7";
        boolean isValid = validateLicensePlate(invalidLicensePlate);
        assertThat(isValid).isFalse();
    }

    @Test
    public void testThatGetInvalidLicensePlateTooLongAndReturnedFalse() {
        String invalidLicensePlate = "А123ВЕ7777";
        boolean isValid = validateLicensePlate(invalidLicensePlate);
        assertThat(isValid).isFalse();
    }
}
