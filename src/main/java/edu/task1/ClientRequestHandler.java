package edu.task1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Map;

public class ClientRequestHandler extends Thread {


    public static final int CAPACITY = 1024;
    private final Map<String, String> answers = Map.of(
            "личности", "Не переходи на личности там, где их нет",
            "оскорбления", "Если твои противники перешли на личные оскорбления,"
                    + " будь уверена — твоя победа не за горами",
            "глупый", "А я тебе говорил, что ты глупый? "
                    + "Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
            "интеллект", "Чем ниже интеллект, тем громче оскорбления"
    );

    private final SocketChannel clientChannel;

    public ClientRequestHandler(SocketChannel clientChannel) {
        this.clientChannel = clientChannel;
    }

    @Override
    public void run() {
        try {
            handleMessage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void handleMessage() throws IOException {

        while (true) {
            ByteBuffer buffer = ByteBuffer.allocate(CAPACITY);
            int bytesRead = clientChannel.read(buffer);
            if (bytesRead > 0) {
                String clientMessage = new String(buffer.array(), 0, bytesRead);
                String serverMessage = answers.getOrDefault(clientMessage, "на этот запрос у меня нет ответа (");
                clientChannel.write(ByteBuffer.wrap(serverMessage.getBytes()));
            } else {
                break;
            }
        }
    }
}
