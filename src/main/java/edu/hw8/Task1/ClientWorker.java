package edu.hw8.Task1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientWorker implements Runnable {
    private final Socket clientSocket;

    public ClientWorker(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())
        ) {
            while (true) {
                String request = (String) in.readObject();

                String response = getResponse(request);
                out.writeObject(response);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String getResponse(String word) {
        return switch (word) {
            case "личности" -> "Не переходи на личности там, где их нет";
            case "оскорбления" ->
                "Если твои противники перешли на личные оскорбления, будь уверен — твоя победа не за горами";
            case "глупый" ->
                "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.";
            case "интеллект" -> "Чем ниже интеллект, тем громче оскорбления";
            default -> "Извини, не могу найти подходящую цитату для этого ключевого слова.";
        };
    }
}
