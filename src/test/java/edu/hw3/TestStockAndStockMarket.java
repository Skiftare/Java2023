package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestStockAndStockMarket {
    @Nested
    class TestsForStock {

        @Test
        @DisplayName("Проверка корректной обработки инициализации для нескольких акций")
         void testThatGetValuesAndCreateStocks(){
            int [] vars = {1,2,4,67};
            Stock [] stocksByVars = new Stock[vars.length];
            for(int i = 0;i<vars.length;i++){
                stocksByVars[i] = new Stock(vars[i]);
            }

            int [] varsOfStocks = new int[stocksByVars.length];
            for(int i = 0;i<stocksByVars.length;i++){
                varsOfStocks[i] = stocksByVars[i].getPrice();
            }
            assertArrayEquals(varsOfStocks,vars);
        }
    }
    @Nested
    class TestsForStockMarket{
        @Test
        @DisplayName("проверка работы добавления и сортировки в очередь")
        void testThatGetStoksAndReturnedMarketQueue(){
            Integer [] vars = {34,2,4,67};
            StockMarket.Market market = new StockMarket.Market();
            for(int i = 0;i<vars.length;i++){
                market.add(new Stock(vars[i]));
            }
            Arrays.sort(vars);
            Collections.reverse(Arrays.asList(vars));
            Integer [] marketState = market.getMarketState();

            assertArrayEquals(marketState,vars);
        }
        @Test
        @DisplayName("проверка работы удаления из очереди")
        void testThatGetStoksAndReturnedMarketQueueWithoutOneElement(){
            Integer [] vars = {34,2,4,67};
            Integer [] expected = {34,2,67};
            Stock [] stoksByVars = new Stock[vars.length];
            StockMarket.Market market = new StockMarket.Market();
            for(int i = 0;i<vars.length;i++){
                stoksByVars[i] = new Stock(vars[i]);
                market.add(stoksByVars[i]);
            }
            market.remove(stoksByVars[2]);
            Arrays.sort(expected);
            Collections.reverse(Arrays.asList(expected));
            Integer [] marketState = market.getMarketState();

            assertArrayEquals(marketState,expected);
        }

        @Test
        @DisplayName("проверка работы макисмальной стоимости акции из из очереди")
        void testThatGetStoksAndReturnedMostValuableStock(){
            Integer [] vars = {34,2,4,67};
            Integer [] expected = {34,2,4,68};
            Stock [] stoksByVars = new Stock[vars.length];
            StockMarket.Market market = new StockMarket.Market();
            for(int i = 0;i<vars.length;i++){
                stoksByVars[i] = new Stock(vars[i]);
                market.add(stoksByVars[i]);
            }
            market.remove(stoksByVars[3]);
            market.add(new Stock(68));
            //Arrays.sort(expected);
            //Collections.reverse(Arrays.asList(expected));
            //Integer [] marketState = market.getMarketState();
            Arrays.sort(expected);
            assertEquals(expected[expected.length-1], market.mostValuableStock().getPrice());
        }
    }
}
