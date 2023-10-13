package edu.hw2.task3;

import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    @Override
    public Connection getConnection() {
        Random random = new Random();
        boolean isConnected = random.nextBoolean();
        if (isConnected){
            return new StableConnection();
        }
        else{
            return new FaultyConnection();
        }
    }
}
