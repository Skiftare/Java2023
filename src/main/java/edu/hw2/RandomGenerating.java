package edu.hw2;

import java.util.Random;

//Для удобства тестирования, мы создаём собственный рандомизатор, который может быть не совсем рандомным

public class RandomGenerating {
    int RandomGen(int n){
        if(n !=0) return n%2;
        else{
            return RandomGen();
        }
    }
    int RandomGen(){
        Random rnd = new Random();
        int number = rnd.nextInt(100);
        return number%2;
    }
}
