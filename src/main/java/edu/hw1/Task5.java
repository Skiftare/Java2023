package edu.hw1;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task5 {
    private static final Integer LAST_ELEMENT = 10;

    public static int getLast(int n) {
        return n % LAST_ELEMENT;
    }

    public static int delLast(int n) {
        return n / LAST_ELEMENT;
    }

    public static int movDischarge(int n) {
        return n * LAST_ELEMENT;
    }

    public static int getLength(int n) {
        int secN = n;
        int countOfDigits = 0;
        while (secN > 0) {
            countOfDigits += 1;
            secN = delLast(secN);
        }
        return countOfDigits;
    }

    private static boolean isPalindrome(int n, int len) {

        int[] massNums;
        int secN = n;
        massNums = new int[len];
        int i = 0;
        while (secN > 0) {
            massNums[i] = getLast(secN);
            secN = delLast(secN);
            i += 1;
        }
        i = 0;

        for (; i < len / 2; i++) {
            if (massNums[i] != massNums[len - i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindromeDescendant(int n) {
        int len = getLength(n);
        int partOfResult = -1;
        int secN = n;

        //Случай 111 всё же подходит под условия.
        //Да, в моей реализации У 111 нет потомков.
        // Но само оно палиндромом является
        while (len % 2 == 0 || partOfResult == -1) {
            if (isPalindrome(secN, len)) {
                return true;
            }
            partOfResult = 0;
            for (int i = 0; i < len; i += 2) {
                if (delLast(getLast(secN) + getLast(delLast(secN))) > 0) {
                    partOfResult = movDischarge(movDischarge(partOfResult)) + getLast(secN) + getLast(delLast(secN));
                } else {
                    partOfResult = movDischarge(partOfResult) + (getLast(secN) + getLast(delLast(secN)));
                }
                secN = delLast(delLast(secN));

            }
            secN = partOfResult;
            len = getLength(secN);
        }
        return false;

    }
}
