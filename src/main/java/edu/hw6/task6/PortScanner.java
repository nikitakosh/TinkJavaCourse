package edu.hw6.task6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.SocketException;
import org.apache.logging.log4j.LogManager;

public class PortScanner {
    private final static org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
    public static final int MAX_PORT = 49151;

    public void start() {
        LOGGER.info("Протокол\tПорт\tСервис");
        for (int i = 0; i < MAX_PORT; i++) {
            startServerSocket(i);
            startDatagramSocket(i);
        }
    }

    private void startServerSocket(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            LOGGER.info(PortInfoUtils.getPortInfo(port, Protocol.TCP));
        }
    }

    private void startDatagramSocket(int port) {
        try {
            DatagramSocket datagramSocket = new DatagramSocket(port);
            datagramSocket.close();
        } catch (SocketException e) {
            LOGGER.info(PortInfoUtils.getPortInfo(port, Protocol.UDP));
        }
    }

}
