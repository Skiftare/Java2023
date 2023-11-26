package edu.hw8;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class PasswordCrakerMultiThread {



        private static final String[] PASSWORDS = {
            "a.v.petrov e10adc3949ba59abbe56e057f20f883e",
            "v.v.belov d8578edf8458ce06fbc5bb76a58c5ca4",
            "a.s.ivanov 482c811da5d5b4bc6d497ffa98491e38",
            "k.p.maslov 5f4dcc3b5aa765d61d8327deb882cf99"
        };

        private static final int NUM_THREADS = 4;

    public static void main(String[] args) {
        executeCracken(PASSWORDS, 4);
    }

        public static void executeCracken(String[] passwords, int numberOfThreads) {
            Map<String, String> passwordMap = new HashMap<>();

            for (String password : PASSWORDS) {
                String[] parts = password.split(" ");
                String username = parts[0];
                String hash = parts[1];
                passwordMap.put(hash, username);
            }

            ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);

            long startTime = System.currentTimeMillis();

            HashCrypter.init();
            System.out.println("start");

            for (long j = HashCrypter.hashCodeString("0000"); j < HashCrypter.hashCodeString("ZZZZ"); j++) {
                String password = HashCrypter.hashDecodeString(j);
                String hash = md5Hash(password);

                if (passwordMap.containsKey(hash)) {
                    String username = passwordMap.get(hash);
                    System.out.println("Username: " + username + ", Password: " + password);
                }
            }
            System.out.println("4-d");
            for (long j = HashCrypter.hashCodeString("00000"); j < HashCrypter.hashCodeString("ZZZZZ"); j++) {
                String password = HashCrypter.hashDecodeString(j);
                String hash = md5Hash(password);

                if (passwordMap.containsKey(hash)) {
                    String username = passwordMap.get(hash);
                    System.out.println("Username: " + username + ", Password: " + password);
                }

            }
            System.out.println("5-d");

            for (long j = HashCrypter.hashCodeString("000000"); j < HashCrypter.hashCodeString("ZZZZZZ"); j++) {
                String password = HashCrypter.hashDecodeString(j);
                String hash = md5Hash(password);

                if (passwordMap.containsKey(hash)) {
                    String username = passwordMap.get(hash);
                    System.out.println("Username: " + username + ", Password: " + password);
                }
            }
            System.out.println("6-d");



            executorService.shutdown();

            while (!executorService.isTerminated()) {
                // Wait for all tasks to complete
            }

            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            System.out.println("Total time: " + totalTime + " ms");
        }

        private static String generatePassword(int number) {
            return String.format("%04d", number);
        }

        private static String md5Hash(String input) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] hashBytes = md.digest(input.getBytes());

                StringBuilder sb = new StringBuilder();
                for (byte b : hashBytes) {
                    sb.append(String.format("%02x", b));
                }

                return sb.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return null;
            }
        }

}
