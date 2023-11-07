package edu.hw3;


import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;


@SuppressWarnings("HideUtilityClassConstructor")
public class Task2 {

    static ArrayList<String> clusterize(@NotNull String message) {
        ArrayList<String> res = new ArrayList<>();
        char currentChar;
        StringBuilder addStr = new StringBuilder();
        int opened = 0;
        RuntimeException unbalanceException = new RuntimeException("unbalanced string");
        for (int i = 0; i < message.length(); i++) {
            currentChar = message.charAt(i);
            addStr.append(currentChar);
            if (currentChar == '(') {
                opened++;
            } else if (currentChar == ')') {
                opened--;
            } else {
                throw new RuntimeException("wrong symbol");
            }
            if (opened == 0) {
                res.add(addStr.toString());
                addStr = new StringBuilder();
            } else if (opened < 0) {
                throw unbalanceException;
            }
        }
        if (opened != 0) {
            throw unbalanceException;
        }
        return res;
    }
}
