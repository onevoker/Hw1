package edu.hw2.Task3;

import edu.hw2.Task3.Interfaces.Connection;
import edu.hw2.Task3.Interfaces.ConnectionManager;
import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    private static final Random RANDOM = new Random();

    @Override
    public Connection getConnection() {
        if (RANDOM.nextInt() % 2 == 1) {
            return new FaultyConnection();
        }

        return new StableConnection();
    }
}
