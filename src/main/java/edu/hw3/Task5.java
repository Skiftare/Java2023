package edu.hw3;

import org.jetbrains.annotations.NotNull;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Task5 {
    private static String splitAndReverse(@NotNull String income){
        String[] splitted = income.split(" ");
        if(splitted.length == 1){
            return splitted[0];
        }
        if(splitted.length>2){
            throw new RuntimeException("wrong name");
        }
        return splitted[1]+" "+splitted[0];
    }
    static String[] parseContacts(String [] incomeNames, String sortFlag){
        if(!Objects.equals(sortFlag, "ASC") && !Objects.equals(sortFlag, "DESC")){
            throw new RuntimeException("wrong input sortFlag");
        }
        String[] sortedContacts = new String[incomeNames.length];
        for(int i = 0;i<incomeNames.length;i++){
            sortedContacts[i] = splitAndReverse(incomeNames[i]);
        }
        Arrays.sort(sortedContacts);

        for(int i = 0;i<incomeNames.length;i++){
            sortedContacts[i] = splitAndReverse(sortedContacts[i]);
        }
        if(sortFlag.equals("DESC")){
            Collections.reverse(Arrays.asList(sortedContacts));
        }
        return sortedContacts;
    }
}
