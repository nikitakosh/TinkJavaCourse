package edu.task1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {

    public static final int CAPACITY = 1024;
    private final int port;
    private SocketChannel clientChannel;

    public Client(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        clientChannel = SocketChannel.open();
        clientChannel.socket().connect(new InetSocketAddress("localhost", port));
    }

    public String sendMessage(String msg) throws IOException {
        ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
        clientChannel.write(buffer);
        buffer = ByteBuffer.allocate(CAPACITY);
        int bytesRead = clientChannel.read(buffer);
        return new String(buffer.array(), 0, bytesRead);
    }

    public void stop() throws IOException {
        clientChannel.close();
    }
}
