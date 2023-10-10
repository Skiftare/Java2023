package edu.hw1;

import java.util.ArrayList;
import java.util.Collections;

@SuppressWarnings("HideUtilityClassConstructor")

public class Task7 {

    static public int degreeOfTwo(int n) {
        return ((int) Math.pow(2, n));
    }


    static public int rotateRight(int n, int shift) {
        if (shift < 0) {
            return -1;
        }
        int len = 0;
        int secN = n;
        ArrayList<Integer> binaryView = new ArrayList<>();
        while (secN > 0) {
            len += 1;
            binaryView.add(secN % 2);
            secN /= 2;
        }
        Collections.reverse(binaryView);
        int rotatedNumber = 0;
        int power = len - 1;
        for (int i = len - shift; i < len; i++) {
            rotatedNumber = rotatedNumber + binaryView.get(i) * degreeOfTwo(power);
            power -= 1;
        }
        for (int i = 0; i < len; i++) {
            rotatedNumber = rotatedNumber + binaryView.get(i) * degreeOfTwo(power);
            power -= 1;
        }
        return rotatedNumber;
    }

    static public int rotateLeft(int n, int shift) {
        if (shift < 0) {
            return -1;
        }
        int len = 0;
        int secN = n;
        ArrayList<Integer> binaryView = new ArrayList<>();
        while (secN > 0) {
            len += 1;
            binaryView.add(secN % 2);
            secN /= 2;
        }
        binaryView.reversed();
        int rotatedNumber = 0;
        for (int power = 0; power < binaryView.size(); power++) {
            rotatedNumber = rotatedNumber + binaryView.get((2 * len - shift + power) % len) * degreeOfTwo(power);
        }
        return rotatedNumber;
    }
}
