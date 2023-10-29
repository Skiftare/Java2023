package edu.hw3;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task2 {

    static ArrayList<String> clusterize(@NotNull String message) {
        ArrayList<String> res = new ArrayList<>();
        char currentChar = ' ';
        String addStr = "";
        int opened = 0;

        for (int i = 0; i < message.length(); i++) {
            currentChar = message.charAt(i);
            addStr += currentChar;
            if (currentChar == '(') {
                opened++;
            } else if (currentChar == ')') {
                opened--;
            } else{
                throw new RuntimeException("wrong symbol");
            }
            if (opened == 0) {
                res.add(addStr);
                addStr = "";
            } else if (opened < 0) {
                throw new RuntimeException("unbalanced string");
            }
        }
        if(opened!=0){
            throw new RuntimeException("unbalanced string");
        }
        return res;
    }
}
