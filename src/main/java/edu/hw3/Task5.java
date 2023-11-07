package edu.hw3;


import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task5 {

    static String sortDESC = "DESC";
    static String sortASC = "ASC";


    private static String splitAndReverse(@NotNull String income) {
        String[] splitted = income.split(" ");
        if (splitted.length == 1) {
            return splitted[0];
        }
        if (splitted.length > 2)  {
            throw new RuntimeException("wrong name");
        }
        return splitted[1] + " " + splitted[0];
    }

    static String @NotNull [] parseContacts(String[] incomeNames, String sortFlag) {

        if (!Objects.equals(sortFlag, sortASC) && !Objects.equals(sortFlag, sortDESC)) {
            throw new RuntimeException("wrong input sortFlag");
        }
        String[] sortedContacts = new String[incomeNames.length];
        for (int i = 0; i < incomeNames.length; i++) {
            sortedContacts[i] = splitAndReverse(incomeNames[i]);
        }
        Arrays.sort(sortedContacts);

        for (int i = 0; i < incomeNames.length; i++) {
            sortedContacts[i] = splitAndReverse(sortedContacts[i]);
        }
        if (Objects.equals(sortFlag, sortDESC)) {
            Collections.reverse(Arrays.asList(sortedContacts));
        }
        return sortedContacts;
    }
}
