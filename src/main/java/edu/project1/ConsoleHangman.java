package edu.project1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleHangman {
    private final static Logger LOGGER = LogManager.getLogger();
    private final static Scanner SCANNER = new Scanner(System.in);
    private final String answer;

    public ConsoleHangman() {
        Dictionary dictionary = new Dictionary();
        this.answer = dictionary.getRandomWord();
    }

    public ConsoleHangman(String answer) {
        this.answer = answer;
    }

    public void run() {
        GameSession gameSession = new GameSession(answer);
        StringBuilder currentAnswer = gameSession.getCurrentAnswer();

        while (gameSession.getAttempts() < gameSession.getMaxAttempts()) {
            LOGGER.info("Guess a letter:");
            String guessLetter = SCANNER.nextLine();

            if (guessLetter.length() != 1) {
                LOGGER.info("Your input is incorrect, try again!");
                continue;
            }
            if (answer.contains(guessLetter)) {
                gameSession.goodAttempt(guessLetter);
                if (currentAnswer.toString().equals(answer)) {
                    LOGGER.info("You won!");
                    break;
                }
            } else {
                gameSession.badAttempt();
                if (gameSession.getAttempts() == gameSession.getMaxAttempts()) {
                    LOGGER.info("You lost!\n");
                    LOGGER.info("The word was: " + answer);
                    break;
                }
            }
        }
    }
}
