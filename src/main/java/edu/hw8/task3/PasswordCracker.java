package edu.hw8.task3;

import edu.hw8.ErrorLogger;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PasswordCracker {

    static final int NUM_THREADS = 4;
    static final String PATH_TO_DECODED_FILE = "src/main/java/edu/hw8/output/decodedPasswords.txt";
    private static final String EMPTY_STRING = "";

    private static void cleanOutputFile() {
        try (FileWriter writer = new FileWriter(PATH_TO_DECODED_FILE)) {
            writer.write(EMPTY_STRING);
        } catch (IOException e) {
            ErrorLogger.createLogError(e.getMessage());
        }

    }

    public void crackPasswords(ArrayList<String> incomeBaseWithHashedPasswords) {
        cleanOutputFile();
        PasswordGenerator.run();
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


}
