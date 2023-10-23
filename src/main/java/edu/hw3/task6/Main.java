package edu.hw3.task6;

public class Main {
    public static void main(String[] args) {
        StockMarket myStockMarket = new MyStockMarket();
        Stock stock1 = new Stock(12);
        Stock stock2 = new Stock(13);
        Stock stock3 = new Stock(100);
        Stock stock4 = new Stock(10);
        myStockMarket.add(stock1);
        myStockMarket.add(stock2);
        myStockMarket.add(stock3);
        myStockMarket.add(stock4);
        System.out.println(myStockMarket.mostValuableStock().getPrice());
    }
}
