package edu.hw4;

public record Animal(
    String name,
    Type type,
    Sex sex,
    int age,
    int height,
    int weight,
    boolean bites
) {
    public int getHeight() {
        return this.height;
    }
    public int getWeight() {
        return this.weight;
    }
    public String getName() {
        return this.name;
    }
    public Sex getSex() {
        return this.sex;
    }
    public int getAge(){
        return this.age;
    }
    public Type getType() {
        return this.type;
    }

    enum Type {
        CAT, DOG, BIRD, FISH, SPIDER
    }

    enum Sex {
        M, F
    }

    public int paws() {
        return switch (type) {
            case CAT, DOG -> 4;
            case BIRD -> 2;
            case FISH -> 0;
            case SPIDER -> 8;
        };
    }
}
