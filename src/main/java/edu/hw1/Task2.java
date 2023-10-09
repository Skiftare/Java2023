package edu.hw1;

@SuppressWarnings("HideUtilityClassConstructor")

public class Task2 {
    private static final int TEN_N = 10;

    public static int countDigits(int n) {
        if (n == 0) {
            return 0;
        }
        int secN = Math.abs(n);
        int cnt = 0;
        while (secN > 0) {
            cnt += 1;
            secN /= TEN_N;
        }
        return cnt;
    }

}
