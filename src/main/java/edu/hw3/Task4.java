package edu.hw3;

public class Task4 {
    public String IntToRoman(int num)
    {
        var keys = new String[] { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        var vals = new int[] { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };

        StringBuilder ret = new StringBuilder();
        int ind = 0;
        int countOfNums;
        while(ind < keys.length)
        {
            while(num >= vals[ind])
            {
                countOfNums = num / vals[ind];
                num = num % vals[ind];
                while(countOfNums-->0)
                    ret.append(keys[ind]);
            }
            ind++;
        }

        return ret.toString();
    }
}
