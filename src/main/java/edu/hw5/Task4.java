package edu.hw5;

import java.util.regex.Pattern;

public class Task4 {
    public boolean passwordValidator(String incomePassword){
        boolean containsRequiredSymbol = Pattern.matches(".*[~!@#$%^&*|].*", incomePassword);
        return containsRequiredSymbol;
    }
}
