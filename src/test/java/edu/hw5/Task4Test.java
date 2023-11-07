package edu.hw5;

import org.junit.jupiter.api.Test;
import static edu.hw5.Task4.passwordValidator;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {
    @Test
    public void testThatGetValidPasswordAndReturnedTrue() {
        String validPassword = "Password123!";
        boolean isValid = passwordValidator(validPassword);
        assertThat(isValid).isTrue();
    }

    @Test
    public void testThatGetInvalidPasswordNoSpecialCharacterAndReturnedFalse() {
        String invalidPassword = "Password123";
        boolean isValid = passwordValidator(invalidPassword);
        assertThat(isValid).isFalse();
    }

    @Test
    public void testThatGetPasswordWithSpecialCharacterNoNumbersAndReturnedTrue() {
        String invalidPassword = "Password~";
        boolean isValid = passwordValidator(invalidPassword);
        assertThat(isValid).isTrue();
    }

    @Test
    public void testThatGetPasswordWithSpecialCharacterNoUppercaseAndReturnedTrue() {
        String invalidPassword = "password123@";
        boolean isValid = passwordValidator(invalidPassword);
        assertThat(isValid).isTrue();
    }

    @Test
    public void testThatGetPasswordWithSpecialCharacterButShortLenghtAndReturnedTrue() {
        String invalidPassword = "P~!@#$%^&*|";
        boolean isValid = passwordValidator(invalidPassword);
        assertThat(isValid).isTrue();
    }
}
