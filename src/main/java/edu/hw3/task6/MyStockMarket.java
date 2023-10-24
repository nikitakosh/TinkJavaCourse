package edu.hw3.task6;

import java.util.PriorityQueue;

public class MyStockMarket implements StockMarket {
    private final PriorityQueue<Stock> stocks = new PriorityQueue<>(
            (stock1, stock2) -> stock2.getPrice() - stock1.getPrice()
    );

    @Override
    public void add(Stock stock) {
        stocks.add(stock);
    }

    @Override
    public boolean remove(Stock stock) {
        return stocks.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return stocks.peek();
    }
}
