package edu.hw1;

public class Task5 {

    private static final int TEN = 10;

    private static final int DIFFERENCE = 48;

    private Task5() {
    }

    public static boolean isPalindromeDescendant(int num) {
        return recursionCheck(num, 0);
    }

    public static boolean isPalindrome(int num) {
        if (num < 0 || num != 0 && num % TEN == 0) {
            return false;
        }
        int checker = 0;
        int n = num;

        while (n > checker) {
            checker = checker * TEN + n % TEN;
            n /= TEN;
        }

        return (n == checker || n == checker / TEN);
    }

    private static boolean recursionCheck(int num, int counter) {
        if (num < 0) {
            return false;
        }
        String str = Integer.toString(num);
        StringBuilder result = new StringBuilder();
        if (str.length() == 1 && counter > 0) {
            return false;
        }
        if (isPalindrome(num)) {
            return true;
        }

        for (int i = 0; i < str.length() - 1; i += 2) {
            int x = (int) (str.charAt(i)) - DIFFERENCE;
            int y = (int) (str.charAt(i + 1)) - DIFFERENCE;
            int temp = x + y;
            result.append(temp);
        }

        if (str.length() % 2 == 1) {
            result.append(str.charAt(str.length() - 1));
        }
        int check = Integer.parseInt(result.toString());
        return recursionCheck(check, counter + 1);
    }
}
