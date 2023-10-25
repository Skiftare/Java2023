package edu.hw1;

import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task8 {
    private static final Integer NEED_FIGHT_DISTANCE = 5;
    private static final Integer THIS_CELL_IS_HORSE = 1;

    public static boolean knightBoardCapture(int[] @NotNull [] board) {
        ArrayList<int[]> knightCoords = new ArrayList<>();
        for (int posByI = 0; posByI < board.length; posByI++) {
            for (int posByJ = 0; posByJ < board.length; posByJ++) {
                if (board[posByI][posByJ] == THIS_CELL_IS_HORSE) {
                    knightCoords.add(new int[] {posByI, posByJ});
                }
            }
        }
        boolean statusOfConflict = true;
        for (int posByI = 0; posByI < knightCoords.size() && statusOfConflict; posByI++) {
            for (int posByJ = posByI + 1; posByJ < knightCoords.size(); posByJ++) {
                int[] coordsOne = knightCoords.get(posByI);
                int[] coordsTwo = knightCoords.get(posByJ);
                int deltaX = coordsTwo[0] - coordsOne[0];
                int deltaY = coordsTwo[1] - coordsOne[1];
                int resDistance = (int) Math.pow(deltaX, 2) + (int) Math.pow(deltaY, 2);

                if (resDistance == NEED_FIGHT_DISTANCE) {
                    statusOfConflict = false;
                    break;
                }
            }
        }
        return statusOfConflict;
    }
}
