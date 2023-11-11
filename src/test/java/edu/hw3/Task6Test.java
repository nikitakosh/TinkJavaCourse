package edu.hw3;

import edu.hw3.task6.MyStockMarket;
import edu.hw3.task6.Stock;
import edu.hw3.task6.StockMarket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task6Test {

    @Test
    @DisplayName("most valuable stock at StockMarket")
    public void mostValuableStockAtStockMarket() {
        StockMarket stockMarket = new MyStockMarket();
        Stock stock1 = new Stock(100);
        Stock stock2 = new Stock(200);
        Stock stock3 = new Stock(300);
        stockMarket.add(stock1);
        stockMarket.add(stock2);
        stockMarket.add(stock3);
        Assertions.assertEquals(stockMarket.mostValuableStock(), stock3);
    }

    @Test
    @DisplayName("remove stock from StockMarket")
    public void removeStockFromStockMarket() {
        StockMarket stockMarket = new MyStockMarket();
        Stock stock1 = new Stock(100);
        Stock stock2 = new Stock(200);
        Stock stock3 = new Stock(300);
        stockMarket.add(stock1);
        stockMarket.add(stock2);
        stockMarket.add(stock3);
        Assertions.assertTrue(stockMarket.remove(stock1));
        Assertions.assertTrue(stockMarket.remove(stock2));
        Assertions.assertTrue(stockMarket.remove(stock3));
        Stock stock4 = new Stock(400);
        Assertions.assertFalse(stockMarket.remove(stock4));
    }

    @Test
    @DisplayName("get and set price of stock")
    public void getAndSetPriceOfStock() {
        Stock stock = new Stock(100);
        Assertions.assertEquals(stock.getPrice(), 100);
        stock.setPrice(200);
        Assertions.assertEquals(stock.getPrice(), 200);
    }
}
