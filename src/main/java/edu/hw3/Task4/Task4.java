package edu.hw3.Task4;

import java.util.List;

public class Task4 {
    private final static int MAX_INT_TO_CONVERT = 4000;
    private final static int THOUSAND = 1000;
    private final static int HUNDRED = 100;
    private final static int TEN = 10;

    public String convertToRoman(int num) {
        if (num < 0 || num >= MAX_INT_TO_CONVERT) {
            return "";
        }
        List<String> numbers = List.of("", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX");
        List<String> tens = List.of("", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC");
        List<String> hundreds = List.of("", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM");
        List<String> thousands = List.of("", "M", "MM", "MMM");

        return thousands.get(num / THOUSAND).concat(hundreds.get(num / HUNDRED % TEN)).concat(tens.get(num / TEN % TEN))
            .concat(numbers.get(num % TEN));
    }
}
