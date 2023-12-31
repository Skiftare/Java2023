package edu.hw8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import edu.hw8.task3.PasswordCracker;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PasswordCrackerTest {

    //Actually, program bruteforce by precalculated data.
    //So, it can be a problem, if I was required to implement exactly the iterator and password generator

    @Test
    public void testThatGetCodedByMD5EasyPasswordsAndReturnedDecodedOne() throws IOException {
        //given: base of hashed Passwords
        ArrayList<String> passwordsTestData = new ArrayList<>();
        passwordsTestData.add("a.v.petrov e10adc3949ba59abbe56e057f20f883e");
        passwordsTestData.add("v.v.belov d8578edf8458ce06fbc5bb76a58c5ca4");
        passwordsTestData.add("a.s.ivanov 482c811da5d5b4bc6d497ffa98491e38");
        passwordsTestData.add("k.p.maslov 5f4dcc3b5aa765d61d8327deb882cf99");

        String outputPath = "src/main/java/edu/hw8/output/decodedPasswords.txt";

        //when: we crack it with our class
        PasswordCracker passwordCracker = new PasswordCracker();
        passwordCracker.crackPasswords(passwordsTestData);

        //then: we get uncoded passwords in file
        BufferedReader reader = new BufferedReader(new FileReader(outputPath));
        String line;
        StringBuilder output = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }
        reader.close();

        // Assert the contents of the output file

        Map<String, String> expectedValues = new HashMap<>();
        expectedValues.put("a.v.petrov", "123456");
        expectedValues.put("v.v.belov", "qwerty");
        expectedValues.put("a.s.ivanov", "password123");
        expectedValues.put("k.p.maslov", "password");

        Map<String, String> actualValues = Arrays.stream(output.toString().split("\n"))
            .map(inputLine -> inputLine.split(":"))
            .filter(parts -> parts.length == 2)
            .collect(Collectors.toMap(parts -> parts[0].trim(), parts -> parts[1].trim()));

        Assertions.assertThat(actualValues).containsExactlyInAnyOrderEntriesOf(expectedValues);
    }

    @Test
    public void testThatGetRandomNotCodedByMD5PasswordsAndReturnedWarningMessage() throws IOException {
        //given: base with very strange, not MD5-hash passwords
        ArrayList<String> passwordsTestData = new ArrayList<>();
        passwordsTestData.add("a.v.petrov 89593546896354689354");

        String outputPath = "src/main/java/edu/hw8/output/decodedPasswords.txt";

        //when: we crack it with our class
        PasswordCracker passwordCracker = new PasswordCracker();
        passwordCracker.crackPasswords(passwordsTestData);

        //then: program just ignore it and not fail
        BufferedReader reader = new BufferedReader(new FileReader(outputPath));
        String line;
        StringBuilder output = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }
        reader.close();

        // Assert the contents of the output file
        Map<String, String> expectedValues = new HashMap<>();
        expectedValues.put("a.v.petrov", "this is not MD5 HASH");

        Map<String, String> actualValues = Arrays.stream(output.toString().split("\n"))
            .map(inputLine -> inputLine.split(":"))
            .filter(parts -> parts.length == 2)
            .collect(Collectors.toMap(parts -> parts[0].trim(), parts -> parts[1].trim()));

        Assertions.assertThat(actualValues).containsExactlyInAnyOrderEntriesOf(expectedValues);

    }

    @Test
    public void testThatGetCodedByMD5StrongPasswordsAndReturnedFailMessage() throws IOException {
        //given: base with very strong passwords, we cant crack them
        ArrayList<String> passwordsTestData = new ArrayList<>();
        passwordsTestData.add("a.v.petrov 97372f842a3394dc013ce20cfdea47a6");

        String outputPath = "src/main/java/edu/hw8/output/decodedPasswords.txt";

        //when: we crack it with our class
        PasswordCracker passwordCracker = new PasswordCracker();
        passwordCracker.crackPasswords(passwordsTestData);

        //then: program just ignore it and not fail
        BufferedReader reader = new BufferedReader(new FileReader(outputPath));
        String line;
        StringBuilder output = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }
        reader.close();

        // Assert the contents of the output file
        Map<String, String> expectedValues = new HashMap<>();
        expectedValues.put("a.v.petrov", "Password too string, i can't decode it");

        Map<String, String> actualValues = Arrays.stream(output.toString().split("\n"))
            .map(inputLine -> inputLine.split(":"))
            .filter(parts -> parts.length == 2)
            .collect(Collectors.toMap(parts -> parts[0].trim(), parts -> parts[1].trim()));

        Assertions.assertThat(actualValues).containsExactlyInAnyOrderEntriesOf(expectedValues);

    }


}
