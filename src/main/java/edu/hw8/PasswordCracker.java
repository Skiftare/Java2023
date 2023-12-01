package edu.hw8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Pattern;

public class PasswordCracker {

    private static final int NUM_THREADS = 4;
    private static final String PATH_TO_RESOURCES_FILE = "src/main/resources/hw8/mostFrequentPasswords.txt";
    private static final String PATH_TO_OUTPUT_FILE = "src/main/java/edu/hw8/output/decodedPasswords.txt";
    private static final String EMPTY_STRING = "";

    public void crackPasswords(ArrayList<String> incomeBaseWithHashedPasswords) {
        cleanOutputFile();
        PasswordCrackerThread[] threads = new PasswordCrackerThread[NUM_THREADS];

        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new PasswordCrackerThread(i, incomeBaseWithHashedPasswords);
            threads[i].start();
        }

        for (int i = 0; i < NUM_THREADS; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                ErrorLogger.createLogError(e.getMessage());
            }
        }
    }

    private static void cleanOutputFile() {
        try (FileWriter writer = new FileWriter(PATH_TO_OUTPUT_FILE);) {
            writer.write(EMPTY_STRING);
        } catch (IOException e) {
            ErrorLogger.createLogError(e.getMessage());
        }

    }

    private static class PasswordCrackerThread extends Thread {
        private final int threadId;
        private final ArrayList<String> incomeBaseWithHashedPasswords;
        private static final String ALGO_NAME = "MD5";
        private static final String ENDL_CHAR = "\n";
        private static final String SEPARATOR_FOR_RESULT = ": ";
        private static final int BASE_SIXTEEN = 16;
        private static final int NEEDED_HASH_LENGTH = 32;
        private static final String PATTERN_CHECKER_FOR_MD5 = "^[a-fA-F0-9]{32}$";
        private static final String NOT_A_HASH_MESSAGE = "this is not MD5 HASH";
        private static final String TOO_STRONG_PASSWORD = "Password too string, i can't decode it";

        PasswordCrackerThread(
            int threadId, ArrayList<String> incomeBaseWithHashedPasswords1
        ) {
            this.threadId = threadId;

            this.incomeBaseWithHashedPasswords = incomeBaseWithHashedPasswords1;
        }

        @Override
        public void run() {
            for (int i = threadId; i < incomeBaseWithHashedPasswords.size(); i += NUM_THREADS) {
                String[] parts = incomeBaseWithHashedPasswords.get(i).split(" ");
                String username = parts[0];
                String hashedPassword = parts[1];

                String decodedPassword = decodePassword(hashedPassword);
                if (decodedPassword != null) {
                    String output = username + SEPARATOR_FOR_RESULT + decodedPassword;
                    writeOutputToFile(output);
                }
            }
        }

        public static String calculateMD5(String input) {
            try {
                MessageDigest md = MessageDigest.getInstance(ALGO_NAME);
                byte[] messageDigest = md.digest(input.getBytes());
                BigInteger no = new BigInteger(1, messageDigest);
                StringBuilder hashText = new StringBuilder(no.toString(BASE_SIXTEEN));
                while (hashText.length() < NEEDED_HASH_LENGTH) {
                    hashText.insert(0, "0");
                }
                return hashText.toString();
            } catch (NoSuchAlgorithmException e) {
                ErrorLogger.createLogError(e.getMessage());
            }
            return null;
        }

        private String decodePassword(String hashedPassword) {

            if (!Pattern.matches(PATTERN_CHECKER_FOR_MD5, hashedPassword)) {
                return  NOT_A_HASH_MESSAGE;
            }

            try {
                MessageDigest.getInstance(ALGO_NAME);

                // Compare the hash with the most frequent passwords
                File file = new File(PATH_TO_RESOURCES_FILE);
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;

                while ((line = reader.readLine()) != null) {
                    line = line.trim();

                    if (Objects.equals(calculateMD5(line), hashedPassword)) {
                        return line;
                    }
                }
                reader.close();
            } catch (NoSuchAlgorithmException | IOException e) {
                ErrorLogger.createLogError(e.getMessage());
            }
            return TOO_STRONG_PASSWORD;
        }

        private synchronized void writeOutputToFile(String output) {
            try {
                FileWriter writer = new FileWriter(PATH_TO_OUTPUT_FILE, true);
                writer.write(output + ENDL_CHAR);
                writer.close();
            } catch (IOException e) {
                ErrorLogger.createLogError(e.getMessage());
            }
        }
    }
}
