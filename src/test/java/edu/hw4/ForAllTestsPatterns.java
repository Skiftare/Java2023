package edu.hw4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ForAllTestsPatterns {

    static List<Animal> getEngArray(){
        return Arrays.asList(
            new Animal("Tom", Animal.Type.CAT, Animal.Sex.M, 5, 30, 5, false),
            new Animal("Jerry", Animal.Type.BIRD, Animal.Sex.M, 2, 10, 1, false),
            new Animal("Spike", Animal.Type.DOG, Animal.Sex.M, 3, 40, 15, true),
            new Animal("Fluffy", Animal.Type.CAT, Animal.Sex.F, 4, 25, 4, false),
            new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.F, 101, 5, 0, false)
        );
    }
    static List<Animal> getRusArray(){
        return Arrays.asList(
        new Animal("Кот", Animal.Type.CAT, Animal.Sex.M, 3, 25, 5, false),
        new Animal("Собака", Animal.Type.DOG, Animal.Sex.F, 5, 30, 10, false),
        new Animal("Птица", Animal.Type.BIRD, Animal.Sex.F, 2, 10, 2, false),
        new Animal("Рыба", Animal.Type.FISH, Animal.Sex.M, 1, 5, 1, false),
        new Animal("Паук", Animal.Type.SPIDER, Animal.Sex.M, 1, 2, 0, true)
        );
    }
    static List<Animal> getMixedArray(){
        return Arrays.asList(
            new Animal("Кот", Animal.Type.CAT, Animal.Sex.M, 5, 30, 5, false),
            new Animal("Jerry", Animal.Type.BIRD, Animal.Sex.M, 2, 10, 1, false),
            new Animal("Spike", Animal.Type.DOG, Animal.Sex.M, 3, 40, 15, true),
            new Animal("Fluffy", Animal.Type.CAT, Animal.Sex.F, 4, 25, 4, false),
            new Animal("Tweety(X)", Animal.Type.BIRD, Animal.Sex.F, 1, 5, 0, false),
            new Animal("Кот1", Animal.Type.CAT, Animal.Sex.M, 3, 25, 6, false),
            new Animal("Собака номер два", Animal.Type.DOG, Animal.Sex.F, 5, 30, 10, false),
            new Animal("Большая Птица", Animal.Type.BIRD, Animal.Sex.F, 2, 10, 2, false),
            new Animal("Рыба", Animal.Type.FISH, Animal.Sex.M, 1, 5, 1, false),
            new Animal("Паук", Animal.Type.SPIDER, Animal.Sex.M, 1, 2, 0, true),
            new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.F, 101, 5, 0, false)


        );
    }
}
