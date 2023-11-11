package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw5.Task4.passwordValidator;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {
    @Test
    @DisplayName("Тест на валидацию сильного стандартного пароля")
    public void testThatGetValidPasswordAndReturnedTrue() {
        String validPassword = "Password123!";
        boolean isValid = passwordValidator(validPassword);
        assertThat(isValid).isTrue();
    }

    @Test
    @DisplayName("Тест на провал пароля без спец.сивола")
    public void testThatGetInvalidPasswordNoSpecialCharacterAndReturnedFalse() {
        String invalidPassword = "Password123";
        boolean isValid = passwordValidator(invalidPassword);
        assertThat(isValid).isFalse();
    }

    @Test
    @DisplayName("Тест на валидацию пароля без цифр")
    public void testThatGetPasswordWithSpecialCharacterNoNumbersAndReturnedTrue() {
        String invalidPassword = "Password~";
        boolean isValid = passwordValidator(invalidPassword);
        assertThat(isValid).isTrue();
    }

    @Test
    @DisplayName("Тест на валидацию пароля без заглавных букв")
    public void testThatGetPasswordWithSpecialCharacterNoUppercaseAndReturnedTrue() {
        String invalidPassword = "password123@";
        boolean isValid = passwordValidator(invalidPassword);
        assertThat(isValid).isTrue();
    }

    @Test
    @DisplayName("Тест на валидацию пароля, состоящего только из спецсимволов")
    public void testThatGetPasswordWithSpecialCharacterButShortLenghtAndReturnedTrue() {
        String invalidPassword = "~!@#$%^&*|";
        boolean isValid = passwordValidator(invalidPassword);
        assertThat(isValid).isTrue();
    }
}
