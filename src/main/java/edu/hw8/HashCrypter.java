package edu.hw8;

import java.util.HashMap;
import java.util.Map;

public class HashCrypter {

    private  static Map<String, Integer> dictionary = new HashMap<>();
    private  static Map<Long, String> reverseDictionary = new HashMap<>();
    private static int BASE_NUM = 67;
    public static long hashCodeString(String s){
        long res = 0;
        for(int i =0;i<s.length();i++){
            res  = res*BASE_NUM+(dictionary.get(String.valueOf(s.charAt(i))));
        }
        return res;
    }
    public static String hashDecodeString(long n){
        StringBuilder sb = new StringBuilder();
        while(n>0){
            long tempVal = n%BASE_NUM;
            n/=BASE_NUM;
            sb.insert(0,reverseDictionary.get(tempVal));
        }
        return sb.toString();
    }
    public static int init(){

        int i = 0;
        for (; i < 10; i++) {
            dictionary.put(String.valueOf(i), i + 1);
            reverseDictionary.put((long) (i+1), String.valueOf(i));
        }

        char letter = 'a';
        for (; i <= 10+26; i++) {
            dictionary.put(String.valueOf(letter), i);
            reverseDictionary.put((long) i, String.valueOf(letter));

            letter++;
        }
        letter = 'A';
        for(;i<=10+26+26;i++){
            dictionary.put(String.valueOf(letter), i);
            reverseDictionary.put((long) i, String.valueOf(letter));
            letter++;
        }
        return 0;
    }

}
