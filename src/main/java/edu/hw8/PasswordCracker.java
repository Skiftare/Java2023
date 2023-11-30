package edu.hw8;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Objects;
import java.io.*;

public class PasswordCracker {


    private static final int NUM_THREADS = 4;
    private static final String PATH_TO_RESOURCES_FILE = "src/main/resources/hw8/mostFrequentPasswords.txt";
    private static final String PATH_TO_OUTPUT_FOLDER = "src/main/java/edu/hw8/output";




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
                e.printStackTrace();
            }
        }
    }
    private static void cleanOutputFile() {
        try(FileWriter writer = new FileWriter(PATH_TO_OUTPUT_FOLDER+"/decodedPasswords.txt");){
            writer.write("");
        } catch (IOException e){
            ErrorLogger.createLogError(e.getMessage());
        }

    }

    private static class PasswordCrackerThread extends Thread {
        private final int threadId;
        private final ArrayList<String> incomeBaseWithHashedPasswords;


        public PasswordCrackerThread(int threadId, ArrayList<String> incomeBaseWithHashedPasswords1
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
                    String output = username + ": " + decodedPassword;
                    writeOutputToFile(output);
                }
            }
        }
        public static String calculateMD5(String input) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] messageDigest = md.digest(input.getBytes());
                BigInteger no = new BigInteger(1, messageDigest);
                StringBuilder hashText = new StringBuilder(no.toString(16));
                while (hashText.length() < 32) {
                    hashText.insert(0, "0");
                }
                return hashText.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return null;
        }
        private String decodePassword(String hashedPassword) {
            try {
                MessageDigest.getInstance("MD5");

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
                e.printStackTrace();
            }
            return null;
        }

        private synchronized void writeOutputToFile(String output) {
            try {
                FileWriter writer = new FileWriter(PATH_TO_OUTPUT_FOLDER+"/decodedPasswords.txt", true);
                writer.write(output + "\n");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
