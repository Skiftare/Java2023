package edu.hw6;

import java.nio.file.Path;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.Checksum;

public class Task4 {
    public static void outputStreamComposition(String text, Path filePath) {
        //String text = "Programming is learned by writing programs. ― Brian Kernighan";
        //Path filePath = Path.of("example.txt");

        try (OutputStream outputStream = Files.newOutputStream(filePath, StandardOpenOption.CREATE);

             CheckedOutputStream checkedOutputStream = new CheckedOutputStream(outputStream, new CRC32());
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(bufferedOutputStream, StandardCharsets.UTF_8);
             PrintWriter printWriter = new PrintWriter(outputStreamWriter)) {

            printWriter.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
