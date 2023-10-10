package edu.hw1;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Collections;

@SuppressWarnings("HideUtilityClassConstructor")

public class Task7 {

    static public int degreeOfTwo(int n) {
        return ((int) Math.pow(2, n));
    }

    static public @NotNull ArrayList<Integer> createBinaryView(int n){
        ArrayList<Integer> binaryView = new ArrayList<>();
        int secN = n;
        while (secN > 0) {
            binaryView.add(secN % 2);
            secN /= 2;
        }
        return  binaryView;
    }
    static public int rotateRight(int n, int shift) {
        if (shift < 0) {
            return -1;
        }

        ArrayList<Integer> binaryView = createBinaryView(n);
        int lenOfN = binaryView.size();
        Collections.reverse(binaryView);
        int rotatedNumber = 0;
        int power = lenOfN - 1;
        for (int i = lenOfN - shift; i < lenOfN; i++) {
            rotatedNumber = rotatedNumber + binaryView.get(i) * degreeOfTwo(power);
            power -= 1;
        }
        for (int i = 0; i < lenOfN; i++) {
            rotatedNumber = rotatedNumber + binaryView.get(i) * degreeOfTwo(power);
            power -= 1;
        }
        return rotatedNumber;
    }

    static public int rotateLeft(int n, int shift) {
        if (shift < 0) {
            return -1;
        }
        ArrayList<Integer> binaryView = createBinaryView(n);
        int lenOfN = binaryView.size();
        binaryView.reversed();
        int rotatedNumber = 0;
        for (int power = 0; power < binaryView.size(); power++) {
            rotatedNumber = rotatedNumber + binaryView.get((2 * lenOfN - shift + power) % lenOfN) * degreeOfTwo(power);
        }
        return rotatedNumber;
    }
}
