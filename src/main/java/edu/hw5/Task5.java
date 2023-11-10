package edu.hw5;

public class Task5 extends StringChecker {
    public boolean iSCarNumberCorrect(String carNumber) {
        String regex = "[A-Z]\\d{3}[A-Z]{2}\\d{3}";

        return isMatches(carNumber, regex);
    }
}
