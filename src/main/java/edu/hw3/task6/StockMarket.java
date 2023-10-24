package edu.hw3.task6;

public interface StockMarket {
    /** Добавить акцию */
    void add(Stock stock);

    /** Удалить акцию */
    boolean remove(Stock stock);

    /** Самая дорогая акция */
    Stock mostValuableStock();
}
