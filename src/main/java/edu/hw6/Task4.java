package edu.hw6;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task4 {
    public static void outputStreamComposition(String text, Path filePath) {

        try (OutputStream outputStream = Files.newOutputStream(filePath, StandardOpenOption.CREATE);

             CheckedOutputStream checkedOutputStream = new CheckedOutputStream(outputStream, new CRC32());
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                 bufferedOutputStream,
                 StandardCharsets.UTF_8
             );
             PrintWriter printWriter = new PrintWriter(outputStreamWriter)) {

            printWriter.print(text + '\n');
        } catch (IOException e) {
            throw new RuntimeException("Compositioning has been failed");
        }
    }
}
