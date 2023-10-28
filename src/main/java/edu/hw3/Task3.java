package edu.hw3;

import org.jetbrains.annotations.NotNull;
import java.util.HashMap;

public class Task3 {
    static <Object> HashMap<Object, Integer> freqDict(Object @NotNull [] incomeArray){
        HashMap<Object, Integer> cnt = new HashMap<>();
        //System.out.println(incomeArray.length);
        for(int i = 0;i<incomeArray.length;i++){
            Object currentValue = incomeArray[i];
            //cnt[incomeArray[i]]++;
            if(!cnt.containsKey(currentValue)){
                cnt.put(currentValue,1);
                continue;
            }
            int currnetCountOfValue = cnt.get(currentValue);
            cnt.remove(currentValue);
            cnt.put(currentValue, currnetCountOfValue+1);
            //System.out.println(cnt.get(currentValue));
            //System.out.println(currentValue);

        }
        return cnt;
    }
}
