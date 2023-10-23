package edu.hw3;

import java.util.ArrayList;

public class Task2 {


    ArrayList<String> clusterize(String message) {
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
            }
            if (opened == 0) {
                res.add(addStr);
                addStr = "";
            } else if (opened < 0) {
                res.clear();
                return res;
            }
        }
        return res;
    }
}
