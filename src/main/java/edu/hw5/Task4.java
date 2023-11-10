package edu.hw5;

public class Task4 extends StringChecker {
    public boolean isPasswordCorrect(String password) {
        String regex = ".*[~!@#$%^&*|].*";

        return isMatches(password, regex);
    }
}
