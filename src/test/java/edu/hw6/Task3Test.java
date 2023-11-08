package edu.hw6;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import static edu.hw6.AbstractFilter.globMatches;
import static edu.hw6.AbstractFilter.largerThan;
import static edu.hw6.AbstractFilter.magicNumber;
import static edu.hw6.AbstractFilter.regexContains;

public class Task3Test {
    public static final AbstractFilter regularFile = Files::isRegularFile;
    public static final AbstractFilter readable = Files::isReadable;


    Path dir = Path.of("src/test/java/edu/hw6/Task3.java");
    DirectoryStream.Filter<Path> filter = regularFile
        .and(readable)
        .and(largerThan(100_000))
        .and(magicNumber(0x89, 'P', 'N', 'G'))
        .and(globMatches("*.png"))
        .and(regexContains("[-]"));


    try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)
    ) {
        entries.forEach(System.out::println);
    }

    public Task3Test() throws IOException {
    }
}
