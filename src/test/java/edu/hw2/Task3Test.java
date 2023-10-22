package edu.hw2;

import edu.hw2.Task3.ConnectionException;
import edu.hw2.Task3.DefaultConnectionManager;
import edu.hw2.Task3.FaultyConnection;
import edu.hw2.Task3.FaultyConnectionManager;
import edu.hw2.Task3.Interfaces.Connection;
import edu.hw2.Task3.Interfaces.ConnectionManager;
import edu.hw2.Task3.PopularCommandExecutor;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task3Test {
    @Test
    void faultyConnectionManagerGetConnectionTest() {
        ConnectionManager connectionManager = new FaultyConnectionManager();
        Connection connection = connectionManager.getConnection();

        assertThat(connection).isInstanceOf(FaultyConnection.class);
    }

    @Test
    void defaultConnectionManagerGetConnectionTest() {
        ConnectionManager connectionManager = new DefaultConnectionManager();
        Connection connection = connectionManager.getConnection();

        assertThat(connection).isInstanceOf(Connection.class);
    }

    @Test
    void faultyConnectionManagerWithZeroAttemptsTest() {
        FaultyConnectionManager connectionManager = new FaultyConnectionManager();
        PopularCommandExecutor popularCommandExecutor = new PopularCommandExecutor(connectionManager, 0);
        var exception =
            assertThrows(Exception.class, popularCommandExecutor::updatePackages);

        assertThat(exception).isInstanceOf(ConnectionException.class);
    }

    @Test
    void defaultConnectionManagerWithZeroAttemptsTest() {
        DefaultConnectionManager connectionManager = new DefaultConnectionManager();
        PopularCommandExecutor popularCommandExecutor = new PopularCommandExecutor(connectionManager, 0);
        var exception =
            assertThrows(Exception.class, popularCommandExecutor::updatePackages);

        assertThat(exception).isInstanceOf(ConnectionException.class);
    }
}
