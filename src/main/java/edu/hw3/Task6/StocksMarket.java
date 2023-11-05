package edu.hw3.Task6;

import java.util.PriorityQueue;

public class StocksMarket implements StockMarket {
    private final PriorityQueue<Stock> stocks =
        new PriorityQueue<>((stock1, stock2) -> stock2.price() - stock1.price());

    @Override
    public void add(Stock stock) {
        stocks.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        stocks.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return stocks.peek();
    }

    public boolean containsStock(Stock stock) {
        return stocks.contains(stock);
    }
}
