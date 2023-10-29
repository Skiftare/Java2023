package edu.hw3;

import org.jetbrains.annotations.NotNull;

public class Stock implements Comparable<Stock> {
    Integer price;

    Stock(int incomePrice) {
        this.price = -incomePrice;
    }

    int getPrice() {
        return -this.price;
    }

    @Override
    public int compareTo(@NotNull Stock other) {
        int sum = this.price + other.price;
        return Integer.compare(this.price, other.price);
    }
}


