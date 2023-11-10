package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task7 extends StringChecker {
    // содержит не менее 3 символов, причем третий символ равен 0
    public boolean subTask1(String string) {
        String regex = "[01]{2}0[01]*";

        return isMatches(string, regex);
    }

    // начинается и заканчивается одним и тем же символом
    public boolean subTask2(String string) {
        String regex = "(0[01]*0+)|(1[01]*1+)|[01]{1}";

        return isMatches(string, regex);
    }

    // длина не менее 1 и не более 3
    public boolean subTask3(String string) {
        String regex = "[01]{1,3}";

        return isMatches(string, regex);
    }
}
