package edu.hw2;

import edu.hw2.task3.ConnectionException;
import edu.hw2.task3.ConnectionManager;
import edu.hw2.task3.DefaultConnectionManager;
import edu.hw2.task3.FaultyConnectionManager;
import edu.hw2.task3.PopularCommandExecutor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Task3Test {

    static Arguments[] connectionManagers() {
        return new Arguments[] {
            Arguments.of(new DefaultConnectionManager()),
            Arguments.of(new FaultyConnectionManager())
        };
    }

    @ParameterizedTest
    @MethodSource("connectionManagers")
    @DisplayName("DefaultConnectionManager and FaultyConnectionManager throw ConnectionException")
    public void ConnectionManagersThrowsConnectionException(ConnectionManager connectionManager) {
        PopularCommandExecutor popularCommandExecutor = new PopularCommandExecutor(connectionManager, 2);
        Assertions.assertThrows(ConnectionException.class, () -> {
            for (int i = 0; i < 10000; i++) {
                popularCommandExecutor.updatePackages();
            }
        });
    }

    @ParameterizedTest
    @MethodSource("connectionManagers")
    @DisplayName("DefaultConnectionManager and FaultyConnectionManager not throw ConnectionException")
    public void ConnectionManagersNotThrowsConnectionException(ConnectionManager connectionManager) {
        PopularCommandExecutor popularCommandExecutor = new PopularCommandExecutor(connectionManager, 1000);
        Assertions.assertDoesNotThrow(popularCommandExecutor::updatePackages);

    }

}
