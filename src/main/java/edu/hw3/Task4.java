package edu.hw3;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task4 {

    @SuppressWarnings("MagicNumber")
    int[] valuesROMAN = new int[] {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] keysANCIENT = new String[] {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public String intToRoman(int incomeNum) {
        int num = incomeNum;

        StringBuilder ret = new StringBuilder();
        int ind = 0;
        int countOfNums;
        while (ind < keysANCIENT.length) {
            while (num >= valuesROMAN[ind]) {
                countOfNums = num / valuesROMAN[ind];
                num = num % valuesROMAN[ind];
                while (countOfNums-- > 0) {
                    ret.append(keysANCIENT[ind]);
                }
            }
            ind++;
        }

        return ret.toString();
    }
}
