package edu.hw1;

import java.util.Arrays;
import org.jetbrains.annotations.NotNull;


@SuppressWarnings("HideUtilityClassConstructor")
public class Task3 {
    public static boolean isNestable(int @NotNull [] fstMas, int[] secMas) {
        Arrays.sort(fstMas);
        Arrays.sort(secMas);
        boolean NestedOnTheLeft = fstMas[0] > secMas[0];
        boolean NestedOnTheRight = fstMas[fstMas.length - 1] < secMas[secMas.length - 1];
        return (NestedOnTheLeft && NestedOnTheRight);
    }
}
