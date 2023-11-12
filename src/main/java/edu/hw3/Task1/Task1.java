package edu.hw3.Task1;

public class Task1 {
    public String atbash(String str) {
        if (str == null) {
            return null;
        }

        char[] charArr = str.toCharArray();
        StringBuilder result = new StringBuilder();

        for (char ch : charArr) {
            if (Character.isUpperCase(ch)) {
                result.append(encodeUpperLetter(ch));
            } else if (Character.isLowerCase(ch)) {
                result.append(encodeLowerLetter(ch));
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }

    public char encodeUpperLetter(char ch) {
        return (char) ('Z' - ch + 'A');
    }

    public char encodeLowerLetter(char ch) {
        return (char) ('z' - ch + 'a');
    }
}
