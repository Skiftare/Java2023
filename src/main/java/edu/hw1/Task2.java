package edu.hw1;

import static edu.hw1.Task5.delLast;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task2 {

    public static int countDigits(int n) {
        if (n == 0) {
            return 0;
        }
        int secN = Math.abs(n);
        int cnt = 0;
        while (secN > 0) {
            secN = delLast(secN);
            cnt++;
        }
        return cnt;
    }

}
