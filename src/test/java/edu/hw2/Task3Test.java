package edu.hw2;

import edu.hw2.task3.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.anyString;


public class Task3Test {


        static Stream<Arguments> connectionManagersThrowException() {
        DefaultConnectionManager defaultConnectionManager = Mockito.mock(DefaultConnectionManager.class);
        FaultyConnectionManager faultyConnectionManager = Mockito.mock(FaultyConnectionManager.class);
        FaultyConnection faultyConnection = Mockito.mock(FaultyConnection.class);
        Mockito.doThrow(new ConnectionException("fault connection")).when(faultyConnection).execute("apt update && apt upgrade -y");
        Mockito.when(defaultConnectionManager.getConnection()).thenReturn(faultyConnection);
        Mockito.when(faultyConnectionManager.getConnection()).thenReturn(faultyConnection);

        return Stream.of(
                Arguments.of(defaultConnectionManager),
                Arguments.of(defaultConnectionManager)
        );
    }

    @ParameterizedTest
    @MethodSource("connectionManagersThrowException")
    @DisplayName("DefaultConnectionManager and FaultyConnectionManager throw ConnectionException")
    public void ConnectionManagersThrowsConnectionException(ConnectionManager connectionManager) {
        PopularCommandExecutor popularCommandExecutor = new PopularCommandExecutor(connectionManager, 10);
        Assertions.assertThrows(ConnectionException.class, popularCommandExecutor::updatePackages);
    }



    static Stream<Arguments> connectionManagersNotThrowException() {
        DefaultConnectionManager defaultConnectionManager = Mockito.mock(DefaultConnectionManager.class);
        FaultyConnectionManager faultyConnectionManager = Mockito.mock(FaultyConnectionManager.class);
        StableConnection stableConnection = Mockito.mock(StableConnection.class);
        FaultyConnection faultyConnection = Mockito.mock(FaultyConnection.class);
        Mockito.doNothing().when(stableConnection).execute("apt update && apt upgrade -y");
        Mockito.doNothing().when(faultyConnection).execute("apt update && apt upgrade -y");
        Mockito.when(defaultConnectionManager.getConnection()).thenReturn(stableConnection);
        Mockito.when(faultyConnectionManager.getConnection()).thenReturn(faultyConnection);

        return Stream.of(
                Arguments.of(defaultConnectionManager),
                Arguments.of(defaultConnectionManager)
        );
    }
    @ParameterizedTest
    @MethodSource("connectionManagersNotThrowException")
    @DisplayName("DefaultConnectionManager and FaultyConnectionManager not throw ConnectionException")
    public void ConnectionManagersNotThrowsConnectionException(ConnectionManager connectionManager) {
        PopularCommandExecutor popularCommandExecutor = new PopularCommandExecutor(connectionManager, 10);
        Assertions.assertDoesNotThrow(popularCommandExecutor::updatePackages);

    }


}
