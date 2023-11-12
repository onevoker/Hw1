package edu.hw2.Task3;

import edu.hw2.Task3.Interfaces.Connection;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private final static Logger LOGGER = LogManager.getLogger();
    private static final Random RANDOM = new Random();

    @Override
    public void execute(String command) {
        if (RANDOM.nextInt() % 2 == 1) {
            LOGGER.info("Execute command " + command + "by FaultyConnection");
            return;
        }
        throw new ConnectionException();
    }

    @Override
    public void close() {
        LOGGER.info("Connection was closed");
    }
}
