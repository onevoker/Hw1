package edu.hw3;

import edu.hw3.Task6.Stock;
import edu.hw3.Task6.StocksMarket;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class Task6Test {
    private final StocksMarket stocksMarket = new StocksMarket();

    @Test
    void addTest() {
        Stock lenovo = new Stock("Lenovo", 3000);
        Stock macbook = new Stock("Apple", 6000);
        Stock xiaomi = new Stock("Xiaomi", 4000);
        Stock huawei = new Stock("Huawei", 2000);

        stocksMarket.add(lenovo);
        stocksMarket.add(macbook);

        assertAll(
            () -> assertThat(stocksMarket.containsStock(lenovo)).isTrue(),
            () -> assertThat(stocksMarket.containsStock(macbook)).isTrue(),
            () -> assertThat(stocksMarket.containsStock(xiaomi)).isFalse(),
            () -> assertThat(stocksMarket.containsStock(huawei)).isFalse()
        );
    }

    @Test
    void removeTest() {
        Stock lenovo = new Stock("Lenovo", 3000);
        Stock macbook = new Stock("Apple", 6000);

        stocksMarket.add(lenovo);
        stocksMarket.add(macbook);
        stocksMarket.remove(macbook);

        assertAll(
            () -> assertThat(stocksMarket.containsStock(lenovo)).isTrue(),
            () -> assertThat(stocksMarket.containsStock(macbook)).isFalse()
        );
    }

    @Test
    void mostValuableStockTest() {
        Stock lenovo = new Stock("Lenovo", 3000);
        Stock macbook = new Stock("Apple", 6000);
        Stock xiaomi = new Stock("Xiaomi", 4000);
        Stock huawei = new Stock("Huawei", 2000);

        stocksMarket.add(lenovo);
        stocksMarket.add(macbook);
        stocksMarket.add(xiaomi);
        stocksMarket.add(huawei);

        assertThat(stocksMarket.mostValuableStock()).isEqualTo(macbook);
    }
}
