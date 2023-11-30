package edu.hw8.Task1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int MAX_CONNECTIONS = 6;
    private static final int PORT = 808;

    private Server() {
    }

    public static void startServer() throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_CONNECTIONS);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                executorService.submit(new ClientWorker(clientSocket));
            }
        } finally {
            executorService.shutdown();
        }
    }

    @SuppressWarnings("UncommentedMain")
    public static void main(String[] args) throws IOException {
        startServer();
    }

}
