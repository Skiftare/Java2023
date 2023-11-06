package edu.hw2;


//Для удобства тестирования, мы создаём собственный рандомизатор, который может быть не совсем рандомным

class RandomGenerating {
    private static final int VAL_A = 41;
    private static final int VAL_C = 11119;
    private static final int VAL_MOD = 11113;
    private static int seed = 1;

    static void init() {

        seed = 1;
    }

    public static int randomGen() {
        seed = (VAL_A * seed + VAL_C) % VAL_MOD;
        /*
        47 -> 1 1933 -> 1 1468 -> 0 4629 -> 1

        */
        //System.out.println(seed);
        return seed;
    }

    private RandomGenerating() {
        init();
    }


}
