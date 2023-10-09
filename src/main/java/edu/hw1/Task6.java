package edu.hw1;

import java.util.ArrayList;
import java.util.Collections;
import static edu.hw1.Task5.delLast;
import static edu.hw1.Task5.getLast;
import static edu.hw1.Task5.movDischarge;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task6 {
    private static final Integer THOUSAND_NUM = 1000;
    private static final Integer NEED_L = 4;
    private static final Integer FINAL_NUM = 6174;

    public static int funK(int n) {
        if (n > THOUSAND_NUM && n % THOUSAND_NUM > 0) {
            return funK(n, 0);
        }
        return -1;
    }

    public static int funK(int n, int cnt) {
        int secN = n;

        if (n == FINAL_NUM) {
            return cnt;
        }

        ArrayList<Integer> charsForN = new ArrayList<>();
        while (secN > 0) {
            charsForN.add(getLast(secN));
            secN = delLast(secN);
        }
        if (charsForN.size() < NEED_L) {
            while (charsForN.size() < NEED_L) {
                charsForN.add(0);
            }
        }
        Collections.sort(charsForN);
        int minNum = 0;
        int maxNum = 0;
        for (int i = 0; i < charsForN.size(); i++) {
            minNum = movDischarge(minNum) + charsForN.get(i);
            maxNum = movDischarge(maxNum) + charsForN.get(charsForN.size() - i - 1);
        }
        return funK(maxNum - minNum, cnt + 1);

    }
}
