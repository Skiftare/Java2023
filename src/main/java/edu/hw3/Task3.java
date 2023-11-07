package edu.hw3;

import java.util.HashMap;
import org.jetbrains.annotations.NotNull;


@SuppressWarnings("HideUtilityClassConstructor")
public class Task3 {

    static <T> HashMap<T, Integer> freqDict(T @NotNull [] incomeArray) {
        HashMap<T, Integer> cnt = new HashMap<>();
        //System.out.println(incomeArray.length);
        for (int i = 0; i < incomeArray.length; i++) {
            T currentValue = incomeArray[i];
            //cnt[incomeArray[i]]++;
            if (!cnt.containsKey(currentValue)) {
                cnt.put(currentValue, 1);
                continue;
            }
            int currnetCountOfValue = cnt.get(currentValue);
            cnt.remove(currentValue);
            cnt.put(currentValue, currnetCountOfValue + 1);
            //System.out.println(cnt.get(currentValue));
            //System.out.println(currentValue);

        }
        return cnt;
    }
}
