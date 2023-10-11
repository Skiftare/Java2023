package edu.hw1;

import static edu.hw1.Task5.getLength;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task2 {

    public static int countDigits(int n) {
        return getLength(Math.abs(n));
    }

}
