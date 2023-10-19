package edu.project1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameSession {
    private final int maxAttempts = 5;
    private int attempts = 0;
    private final String answer;
    private final static Logger LOGGER = LogManager.getLogger();
    private final StringBuilder currentAnswer;

    public GameSession(String answer) {
        this.answer = answer;
        this.currentAnswer = new StringBuilder("*".repeat(answer.length()));

    }

    public int getMaxAttempts() {
        return this.maxAttempts;
    }

    public int getAttempts() {
        return this.attempts;
    }

    public String getAnswer() {
        return this.answer;
    }

    public StringBuilder getCurrentAnswer() {
        return this.currentAnswer;
    }

    public void badAttempt() {
        attempts++;
        LOGGER.info("Missed, mistake " + attempts + " out of " + maxAttempts + ".");
        state();
    }

    public void goodAttempt(String guessLetter) {
        LOGGER.info("Hit");
        pasteLetters(guessLetter);
        state();
    }

    private void state() {
        LOGGER.info("Your progress: " + currentAnswer);
    }

    private void pasteLetters(String guessLetter) {
        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == guessLetter.charAt(0)) {
                currentAnswer.setCharAt(i, guessLetter.charAt(0));
            }
        }
    }
}
