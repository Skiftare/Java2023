package edu.hw1;

import static edu.hw1.Task5.getLength;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task2 {

    public static int countDigits(int n) {
        if (n == 0) {
            return 0;
        }
        int secN = Math.abs(n);
        return getLength(secN);
    }

}
