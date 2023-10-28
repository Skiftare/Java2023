package edu.hw3;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.Stream;

interface StockMarket {
    /** Добавить акцию */

    void add(Stock stock);

    /** Удалить акцию */
    void remove(Stock stock);
    /** Самая дорогая акция */
    Stock mostValuableStock();
    class Market implements StockMarket{
        PriorityQueue<Stock> allAviableActions = new PriorityQueue<>();


        @Override
        public void add(Stock stock) {
            allAviableActions.add(stock);
        }

        @Override
        public void remove(Stock stock) {
            allAviableActions.remove(stock);
        }

        @Override
        public Stock mostValuableStock() {
            return allAviableActions.poll();
        }

        public Integer[] getMarketState(){

            PriorityQueue<Stock> cloned = allAviableActions;
            Integer [] arr = new Integer[allAviableActions.toArray().length];
            int i = 0;
            while (!cloned.isEmpty()) {
                arr[i] = (cloned.poll().getPrice());
                i++;
            }
            return arr;
        }
    }
}
