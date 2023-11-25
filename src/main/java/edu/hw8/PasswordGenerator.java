package edu.hw8;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PasswordGenerator {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static void main(String[] args) {
        BlockingQueue<String> passwordQueue = new LinkedBlockingQueue<>();

        Thread[] threads = new Thread[1];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                generatePasswords(passwordQueue);
            });
            threads[i].start();
        }
я
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Вывод паролей из очереди
        while (!passwordQueue.isEmpty()) {
            String password = passwordQueue.poll();
            System.out.println(password);
        }
    }

    private static void generatePasswords(BlockingQueue<String> passwordQueue) {
        for (int length = 4; length <= 6; length++) {
            generatePasswordsForLength(length, passwordQueue);
        }
    }

    private static void generatePasswordsForLength(int length, BlockingQueue<String> passwordQueue) {
        for (int i = 0; i < CHARACTERS.length(); i++) {
            for (int j = 0; j < CHARACTERS.length(); j++) {
                for (int k = 0; k < CHARACTERS.length(); k++) {
                    String password = "" + CHARACTERS.charAt(i) + CHARACTERS.charAt(j) + CHARACTERS.charAt(k);
                    if (length >= 4) {
                        passwordQueue.add(password);
                    }
                    if (length >= 5) {
                        for (int l = 0; l < CHARACTERS.length(); l++) {
                            String newPassword = password + CHARACTERS.charAt(l);
                            passwordQueue.add(newPassword);
                        }
                    }
                    if (length >= 6) {
                        for (int l = 0; l < CHARACTERS.length(); l++) {
                            for (int m = 0; m < CHARACTERS.length(); m++) {
                                String newPassword = password + CHARACTERS.charAt(l) + CHARACTERS.charAt(m);
                                passwordQueue.add(newPassword);
                            }
                        }
                    }
                }
            }
        }
    }

}
