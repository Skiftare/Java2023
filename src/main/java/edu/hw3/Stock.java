package edu.hw3;

import org.jetbrains.annotations.NotNull;

public class Stock implements Comparable<Stock> {
    Integer price;
    Stock(int income_price){
        this.price = -income_price;
    }
    int getPrice(){
        return -this.price;
    }

    @Override
    public int compareTo(@NotNull Stock other) {
        int sum = this.price + other.price;
        return Integer.compare(this.price, other.price);
    }
}


