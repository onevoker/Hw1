package edu.hw6.Task6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("MagicNumber")
public class Task6 {
    private Task6() {
    }

    private final static Logger LOGGER = LogManager.getLogger();
    private static final int MIN_PORT = 0;
    private static final int MAX_PORT = 49151;
    private static final String TCP = "TCP";
    private static final String UDP = "UDP";
    private static final String FREE = "Free";
    private static final String OCCUPIED = "Occupied";
    private final static Map<Integer, String> PORTS = new HashMap<>() {
        {
            put(135, "EPMAP");
            put(137, "Служба имен NetBIOS");
            put(138, "Служба датаграмм NetBIOS");
            put(139, "Служба сеансов NetBIOS");
            put(445, " Microsoft-DS Active Directory");
            put(843, "Adobe Flash");
            put(1900, "Simple Service Discovery Protocol (SSDP)");
            put(3702, "Динамическое обнаружение веб-служб");
            put(5040, "");
            put(5050, "");
            put(5353, "Многоадресный DNS");
            put(5355, "Link-Local Multicast Name Resolution (LLMNR)");
            put(5939, "");
            put(6463, "");
            put(6942, "");
            put(17500, "Dropbox");
            put(17600, "");
            put(27017, "MongoDB");
        }
    };

    public static void scanAllPorts() {
        LOGGER.info(String.format("%-10s%-7s%-45s%-9s%n", "Протокол", "Порт", "Сервис", "Состояние"));
        for (int port = MIN_PORT; port <= MAX_PORT; port++) {
            scanUDP(port);
            scanTCP(port);
        }
    }

    public static void scanTCP(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            printPort(TCP, port, FREE);
        } catch (IOException ignored) {
            printPort(TCP, port, OCCUPIED);
        }
    }

    public static void scanUDP(int port) {
        try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
            printPort(UDP, port, FREE);
        } catch (IOException ignored) {
            printPort(UDP, port, OCCUPIED);
        }
    }

    private static String getServiceName(int port) {
        return PORTS.getOrDefault(port, "");
    }

    private static void printPort(String protocol, int port, String condition) {
        if (PORTS.containsKey(port)) {
            String service = getServiceName(port);
            LOGGER.info(String.format("%-10s%-7d%-45s%-8s%n", protocol, port, service, condition));
        }
    }
}
