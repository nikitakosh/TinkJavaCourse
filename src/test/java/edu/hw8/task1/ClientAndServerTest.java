package edu.hw8.task1;

import edu.task1.Client;
import edu.task1.Server;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class ClientAndServerTest {


    @Test
    @DisplayName("single client send message to server")
    public void SingleClientConnectToServer() {
        Server server = new Server(6666);
        Client client = new Client(6666);
        try {
            Thread serverThread = new Thread(() -> {
                try {
                    server.start();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            serverThread.start();
            Thread.sleep(5000);
            client.start();
            Assertions.assertEquals(client.sendMessage("личности"), "Не переходи на личности там, где их нет");
            Assertions.assertEquals(client.sendMessage("оскорбления"), "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами");
            Assertions.assertEquals(client.sendMessage("глупый"), "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.");
            Assertions.assertEquals(client.sendMessage("интеллект"), "Чем ниже интеллект, тем громче оскорбления");
            Assertions.assertEquals(client.sendMessage("dweddwed"), "на этот запрос у меня нет ответа (");
            client.stop();
            server.stop();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("multiple client send to message")
    public void MultipleClientConnectToServer() {
        Callable<Void> clientConnect = () -> {
            Client client = new Client(6666);
            try {
                client.start();
                Assertions.assertEquals(client.sendMessage("личности"), "Не переходи на личности там, где их нет");
                Assertions.assertEquals(client.sendMessage("оскорбления"), "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами");
                Assertions.assertEquals(client.sendMessage("глупый"), "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.");
                Assertions.assertEquals(client.sendMessage("интеллект"), "Чем ниже интеллект, тем громче оскорбления");
                Assertions.assertEquals(client.sendMessage("dweddwed"), "на этот запрос у меня нет ответа (");
                client.stop();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return null;
        };
        List<Callable<Void>> clientConnects = new ArrayList<>();
        IntStream.range(0, 20).forEach(i -> clientConnects.add(clientConnect));
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        Server server = new Server(6666);
        Thread serverThread = new Thread(() -> {
            try {
                server.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        serverThread.start();
        try {
            Thread.sleep(5000);
            List<Future<Void>> futuresClientConnects = executorService.invokeAll(clientConnects);
            futuresClientConnects.forEach((futureClientConnect) -> {
                try {
                    futureClientConnect.get();
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            server.stop();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
