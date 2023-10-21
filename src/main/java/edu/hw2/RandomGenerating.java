package edu.hw2;

import java.util.Random;

//Для удобства тестирования, мы создаём собственный рандомизатор, который может быть не совсем рандомным

class RandomGenerating {
    private static int a = 41;
    private static int c = 11119;
    private static int m = 11113;
    private static int seed = 1;
    static void init() {

        seed = 1;
    }

    public static int RandomGen()
    {
        seed = (a * seed + c) % m;
        /*
        47 -> 1
        1933 -> 1
        1468 -> 0

        4629 -> 1
        874 -> 0
        2501 -> 1

        2530 -> 0
        3719 -> 1
        8016 -> 0
        6385
        */
        //System.out.println(seed);
        return seed;
    }


}
