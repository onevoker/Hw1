package edu.hw2.Task3;

import edu.hw2.Task3.Interfaces.Connection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StableConnection implements Connection {
    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public void execute(String command) {
        LOGGER.info("Execute command " + command + "by StableConnection");
    }

    @Override
    public void close() {
        LOGGER.info("Connection was closed");
    }
}
