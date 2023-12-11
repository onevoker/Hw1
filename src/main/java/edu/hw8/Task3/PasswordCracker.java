package edu.hw8.Task3;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import static edu.hw8.Task3.Hasher.hashPassword;

public class PasswordCracker {
    private static final Map<String, String> PASSWORDS = new ConcurrentHashMap<>();
    private static final Map<String, String> CRACKED_PASSWORDS = new ConcurrentHashMap<>();

    private static final String CHARSET = "0123456qwerty";
    private static final int CHARSET_LENGTH = 13;

    private static final AtomicInteger MIN_PASSWORD_LENGTH = new AtomicInteger(4);
    private static final int MAX_PASSWORD_LENGTH = 6;

    public PasswordCracker(Map<String, String> dataBase) {
        for (var entry : dataBase.entrySet()) {
            PASSWORDS.put(entry.getValue(), entry.getKey());
        }
    }

    public Map<String, String> crackByOneThread() {
        while (!PASSWORDS.isEmpty()) {
            String crackedPassword = nextPassword();
            if (crackedPassword == null) {
                return null;
            }
            String userName = PASSWORDS.remove(hashPassword(crackedPassword));
            CRACKED_PASSWORDS.put(userName, crackedPassword);
        }

        return CRACKED_PASSWORDS;
    }

    public Map<String, String> crackByMultiThreads(int countThreads) {
        while (!PASSWORDS.isEmpty()) {
            try (ExecutorService service = Executors.newFixedThreadPool(countThreads)) {
                service.submit(() -> {
                    String crackedPassword = nextPassword();
                    if (crackedPassword == null) {
                        return;
                    }
                    String userName = PASSWORDS.remove(hashPassword(crackedPassword));
                    CRACKED_PASSWORDS.put(userName, crackedPassword);
                });
            }
        }

        return CRACKED_PASSWORDS;
    }

    private String nextPassword() {
        while (MIN_PASSWORD_LENGTH.get() <= MAX_PASSWORD_LENGTH) {
            StringBuilder passwordBuilder = new StringBuilder(MIN_PASSWORD_LENGTH.get());

            for (int i = 0; i < MIN_PASSWORD_LENGTH.get(); i++) {
                passwordBuilder.append(CHARSET.charAt(0));
            }

            while (true) {
                String currentPassword = passwordBuilder.toString();
                String hashedPassword = hashPassword(currentPassword);
                if (PASSWORDS.containsKey(hashedPassword)) {
                    return currentPassword;
                }
                int index = MIN_PASSWORD_LENGTH.get() - 1;

                while (index >= 0 && passwordBuilder.charAt(index) == CHARSET.charAt(CHARSET_LENGTH - 1)) {
                    passwordBuilder.setCharAt(index, CHARSET.charAt(0));
                    index--;
                }

                if (index < 0) {
                    break;
                } else {
                    char incrementedChar = CHARSET.charAt(CHARSET.indexOf(passwordBuilder.charAt(index)) + 1);
                    passwordBuilder.setCharAt(index, incrementedChar);
                }
            }

            MIN_PASSWORD_LENGTH.incrementAndGet();
        }

        return null;
    }
}
