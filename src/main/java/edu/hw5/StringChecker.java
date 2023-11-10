package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class StringChecker {
    public boolean isMatches(String string, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);

        return matcher.matches();
    }

    public boolean isFind(String string, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }
}
