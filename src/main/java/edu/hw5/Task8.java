package edu.hw5;

public class Task8 extends StringChecker {
    // нечетной длины
    public boolean subTask1(String string) {
        String regex = "[01]([01]{2})*";

        return isMatches(string, regex);
    }

    // начинается с 0 и имеет нечетную длину, или начинается с 1 и имеет четную длину
    public boolean subTask2(String string) {
        String regex = "0([01]{2})*|1([01]{2})*[01]";

        return isMatches(string, regex);
    }

    // любая строка, кроме 11 или 111
    public boolean subTask4(String string) {
        String regex = "(?!^11$|^111$)[01]*";

        return isMatches(string, regex);
    }

    // каждый нечетный символ равен 1
    public boolean subTask5(String string) {
        String regex = "1([01]1)*[01]?";

        return isMatches(string, regex);
    }

    // содержит не менее двух 0 и не более одной 1
    public boolean subTask6(String string) {
        String regex = "0{2,}1|10{2,}|0+10+";

        return isMatches(string, regex);
    }

    // нет последовательных 1
    public boolean subTask7(String string) {
        String regex = "(?![01]*11)[01]*";

        return isMatches(string, regex);
    }
}
