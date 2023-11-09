package edu.hw2.task3;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public void execute(String command) throws ConnectionException {
        Random random = new Random();
        boolean isConnected = random.nextBoolean();
        if (isConnected) {
            LOGGER.info("execute command: " + command);
        } else {
            throw new ConnectionException("fault connection");
        }
    }

    @Override
    public void close() {
        LOGGER.info("FaultyConnection closed");
    }
}
