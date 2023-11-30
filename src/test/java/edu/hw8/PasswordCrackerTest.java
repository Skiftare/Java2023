package edu.hw8;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PasswordCrackerTest {

    //Actually, program bruteforce by precalculated data.
    //So, it can be a problem, if I was required to implement exactly the iterator and password generator

    @Test
    public void testCrackPasswords() throws IOException {
        //given: base of hashed Passwords
        ArrayList<String> passwordsTestData = new ArrayList<>();
        passwordsTestData.add("a.v.petrov e10adc3949ba59abbe56e057f20f883e");
        passwordsTestData.add("v.v.belov d8578edf8458ce06fbc5bb76a58c5ca4");
        passwordsTestData.add("a.s.ivanov 482c811da5d5b4bc6d497ffa98491e38");
        passwordsTestData.add("k.p.maslov 5f4dcc3b5aa765d61d8327deb882cf99");

        String outputPath = "src/main/java/edu/hw8/decodedPasswords.txt";

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
        Assertions.assertThat(output.toString())
            .contains("a.v.petrov: 123456")
            .contains("v.v.belov: password")
            .contains("a.s.ivanov: qwerty")
            .contains("k.p.maslov: password123");
    }
}
