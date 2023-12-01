package edu.hw8;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("HideUtilityClassConstructor")
public class PasswordGenerator {
    private static final String CHARACTERS = "0123456789abcdefghijklmnopqrstuvwxyz";
    private static final int MIN_LENGTH = 1;
    private static final int MAX_LENGTH = 4;
    private static final String PATH_TO_OUTPUT_FILE = "src/main/resources/hw8/generatedPasswords.txt";
    private static final String EMPTY_STRING = "";

    public static void run() {
        cleanOutputFile();
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_LENGTH - MIN_LENGTH + 1);

        for (int length = MIN_LENGTH; length <= MAX_LENGTH; length++) {
            int finalLength = length;
            executorService.execute(() -> generatePasswords(finalLength));
        }

        executorService.shutdown();
    }

    private static void cleanOutputFile() {
        try (FileWriter writer = new FileWriter(PATH_TO_OUTPUT_FILE);) {
            writer.write(EMPTY_STRING);
        } catch (IOException e) {
            ErrorLogger.createLogError(e.getMessage());
        }

    }

    public static void generatePasswords(int length) {
        StringBuilder password = new StringBuilder();
        generatePasswordsRecursive(password, length);
    }

    public static void generatePasswordsRecursive(StringBuilder password, int length) {
        if (length == 0) {
            writePasswordToFile(password.toString());
            return;
        }

        for (int i = 0; i < CHARACTERS.length(); i++) {
            password.append(CHARACTERS.charAt(i));
            generatePasswordsRecursive(password, length - 1);
            password.deleteCharAt(password.length() - 1);
        }
    }

    public static synchronized void writePasswordToFile(String password) {
        try (FileWriter writer = new FileWriter(PATH_TO_OUTPUT_FILE, true)) {
            writer.write(password + "\n");
        } catch (IOException e) {
            ErrorLogger.createLogError(e.getMessage());
        }
    }
}
