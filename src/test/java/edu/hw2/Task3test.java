package edu.hw2;

import edu.hw2.task3.ConnectionException;
import edu.hw2.task3.DefaultConnectionManager;
import edu.hw2.task3.PopularCommandExecutor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task3test {

    @Test
    @DisplayName("DefaultConnectionManager with 2 maxAttempts")
    public void DefaultConnectionManagerTest(){
        PopularCommandExecutor popularCommandExecutor = new PopularCommandExecutor(new DefaultConnectionManager(), 2);
        Assertions.assertThrows(ConnectionException.class, popularCommandExecutor::updatePackages);
    }

}
