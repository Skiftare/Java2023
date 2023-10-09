package edu.hw1;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task3 {
    public static boolean isNestable(int @NotNull [] fstMas, int[] secMas) {
        if (fstMas.length == 0 && secMas.length != 0) {
            return true;
        }
        if (secMas.length == 0) {
            return false;
        }
        int minFst = fstMas[0];
        int maxFst = fstMas[0];
        int minSec = secMas[0];
        int maxSec = secMas[0];

        for (int i = 1; i < fstMas.length; i++) {
            minFst = Math.min(minFst, fstMas[i]);
            maxFst = Math.max(maxFst, fstMas[i]);
        }
        for (int i = 1; i < secMas.length; i++) {
            minSec = Math.min(minSec, secMas[i]);
            maxSec = Math.max(maxSec, secMas[i]);
        }

        return (minFst > minSec && maxFst < maxSec);
    }
}
