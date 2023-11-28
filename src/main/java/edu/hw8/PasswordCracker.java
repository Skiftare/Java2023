package edu.hw8;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class PasswordCracker {
    private static final String[] PASSWORDS = {
        "a.v.petrov e10adc3949ba59abbe56e057f20f883e",
        "v.v.belov d8578edf8458ce06fbc5bb76a58c5ca4",
        "a.s.ivanov 482c811da5d5b4bc6d497ffa98491e38",
        "k.p.maslov 5f4dcc3b5aa765d61d8327deb882cf99"
    };

    private static final int NUM_THREADS = 4;

    public static void main(String[] args) {
        PasswordCracker passwordCracker = new PasswordCracker();
        passwordCracker.crackPasswords();
    }

    public void crackPasswords() {
        PasswordCrackerThread[] threads = new PasswordCrackerThread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new PasswordCrackerThread(i);
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

    private class PasswordCrackerThread extends Thread {
        private final int threadId;

        public PasswordCrackerThread(int threadId) {
            this.threadId = threadId;
        }

        @Override
        public void run() {
            for (int i = threadId; i < PASSWORDS.length; i += NUM_THREADS) {
                String[] parts = PASSWORDS[i].split(" ");
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
                String hashText = no.toString(16);
                while (hashText.length() < 32) {
                    hashText = "0" + hashText;
                }
                return hashText;
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return null;
        }
        private String decodePassword(String hashedPassword) {
            try {
                System.out.println(hashedPassword);
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] hashBytes = md.digest(hashedPassword.getBytes());

                // Compare the hash with the most frequent passwords
                File file = new File("src/main/resources/hw8/mostFrequentPasswords.txt");
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    //String hashedPasswordFromFile = line.trim();
                    if(line.equals("qwerty")){
                        System.out.println(calculateMD5(line));
                    }
                    //System.out.println(hashedPasswordFromFile);
                    //byte[] hashBytesFromFile = md.digest(hashedPasswordFromFile.getBytes());
                    if (Objects.equals(calculateMD5(line), hashedPassword)) {
                        System.out.println(line);
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
                FileWriter writer = new FileWriter("decodedPasswords.txt", true);
                System.out.println(output);
                writer.write(output + "\n");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
