package edu.task1;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
    public static final int N_THREADS = 10;
    private final int port;
    private Boolean isWork = true;
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;

    public Server(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        ExecutorService executorService = Executors.newFixedThreadPool(N_THREADS);
        while (isWork) {
            int readyChannels = selector.select();
            if (readyChannels == 0) {
                continue;
            }
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    executorService.execute(new ClientRequestHandler(((ServerSocketChannel) key.channel()).accept()));
                }
            }
        }
    }

    public void stop() throws IOException {
        isWork = false;
        serverSocketChannel.close();
        selector.close();
    }

}
