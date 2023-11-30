package edu.hw8.Task1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Client {
    private final static Logger LOGGER = LogManager.getLogger();

    private static final String ADDRESS = "localhost";
    private static final int PORT = 808;

    private Client() {
    }

    public static void startClient() throws IOException, ClassNotFoundException {
        try (Socket socket = new Socket(ADDRESS, PORT);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {

            while (true) {
                String word = scanner.nextLine();
                out.writeObject(word);

                String response = (String) in.readObject();
                LOGGER.info("Server: " + response);
            }
        }
    }

    @SuppressWarnings("UncommentedMain")
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        startClient();
    }
}
