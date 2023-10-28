package edu.hw3;

import java.util.PriorityQueue;

interface StockMarket {
    /** Добавить акцию */
    <Stock> void add(Stock stock);
    /** Удалить акцию */
    <Stock> void remove(Stock stock);
    /** Самая дорогая акция */
    <Stock> Stock mostValuableStock();
    class Market implements StockMarket{
        PriorityQueue<Stok> allAviableActions = new PriorityQueue<>();

        @Override
        public <Stock> void add(Stock stock) {

        }

        @Override
        public <Stock> void remove(Stock stock) {

        }

        @Override
        public <Stock> Stock mostValuableStock() {
            return null;
        }
    }
}
