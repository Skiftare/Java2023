package edu.hw1;

import java.util.ArrayList;
import java.util.Collections;
import static edu.hw1.Task5.delLast;
import static edu.hw1.Task5.getLast;
import static edu.hw1.Task5.movDischarge;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task6 {
    private static final Integer MINIMUM_NEED_NUM = 1000;
    private static final Integer NEED_LENGTH = 4;
    private static final Integer FINAL_NUM = 6174;

    public static int funK(int n) {
        if (n > MINIMUM_NEED_NUM && n % MINIMUM_NEED_NUM > 0) {
            return funK(n, 0);
        }
        return -1;
    }

    public static int funK(int n, int depthOfRecursion) {
        int secN = n;

        if (n == FINAL_NUM) {
            return depthOfRecursion;
        }

        ArrayList<Integer> charsForN = new ArrayList<>();
        while (secN > 0) {
            charsForN.add(getLast(secN));
            secN = delLast(secN);
        }
        if (charsForN.size() < NEED_LENGTH) {
            while (charsForN.size() < NEED_LENGTH) {
                charsForN.add(0);
            }
        }
        Collections.sort(charsForN);
        int minNumber = 0;
        int maxNumber = 0;
        for (int i = 0; i < charsForN.size(); i++) {
            minNumber = movDischarge(minNumber) + charsForN.get(i);
            maxNumber = movDischarge(maxNumber) + charsForN.get(charsForN.size() - i - 1);
        }
        return funK(maxNumber - minNumber, depthOfRecursion + 1);

    }
}
