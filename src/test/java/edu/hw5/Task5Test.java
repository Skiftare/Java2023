package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw5.Task5.validateLicensePlate;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    @Test
    @DisplayName("Подтверждение корректности стандартного авто номера")
    public void testThatGetValidLicensePlateAndReturnedTrue() {
        String validLicensePlate = "А123ВЕ77";
        boolean isValid = validateLicensePlate(validLicensePlate);
        assertThat(isValid).isTrue();
    }

    @Test
    @DisplayName("Провал проверки пароля с спецсимволом")
    public void testThatGetInvalidLicensePlateInvalidCharactersAndReturnedFalse() {
        String invalidLicensePlate = "А123ВЕ77!";
        boolean isValid = validateLicensePlate(invalidLicensePlate);
        assertThat(isValid).isFalse();
    }

    @Test
    @DisplayName("Провал проверки пароля - мало цифр посередине")
    public void testThatGetInvalidLicensePlateInvalidFormatAndReturnedFalse() {
        String invalidLicensePlate = "А12ВЕ77";
        boolean isValid = validateLicensePlate(invalidLicensePlate);
        assertThat(isValid).isFalse();
    }

    @Test
    @DisplayName("Провал проверки пароля - слишком короткий номер")
    public void testThatGetInvalidLicensePlateTooShortAndReturnedFalse() {
        String invalidLicensePlate = "А123ВЕ7";
        boolean isValid = validateLicensePlate(invalidLicensePlate);
        assertThat(isValid).isFalse();
    }

    @Test
    @DisplayName("Провал проверки пароля - слишком длинный номер")
    public void testThatGetInvalidLicensePlateTooLongAndReturnedFalse() {
        String invalidLicensePlate = "А123ВЕ7777";
        boolean isValid = validateLicensePlate(invalidLicensePlate);
        assertThat(isValid).isFalse();
    }
}
