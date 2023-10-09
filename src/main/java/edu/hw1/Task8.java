package edu.hw1;

import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("HideUtilityClassConstructor")

public class Task8 {
    private static final Integer NEED_D = 5;
    private static final Integer HORSE = 1;

    public static boolean knightBoardCapture(int[] @NotNull [] board) {
        ArrayList<int[]> knightCoords = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == HORSE) {
                    knightCoords.add(new int[] {i, j});
                }
            }
        }
        boolean ans = true;
        for (int i = 0; i < knightCoords.size() && ans; i++) {
            for (int j = i + 1; j < knightCoords.size(); j++) {
                int[] coordsOne = knightCoords.get(i);
                int[] coordsTwo = knightCoords.get(j);
                int deltaX = coordsTwo[0] - coordsOne[0];
                int deltaY = coordsTwo[1] - coordsOne[1];
                int resDistance = (int) Math.pow(deltaX, 2) + (int) Math.pow(deltaY, 2);

                if (resDistance == NEED_D) {
                    ans = false;
                    break;
                }
            }
        }
        return ans;
    }
}
