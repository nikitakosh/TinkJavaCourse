package edu.hw1;

import org.apache.logging.log4j.LogManager;

@SuppressWarnings("UncommentedMain")
public class Task0 {
    private final static org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    private Task0() {
    }

    public static void main(String[] args) {
        LOGGER.info("Привет, мир!");
    }
}
