package edu.hw1;

import java.util.Arrays;

public class Task6 {

    private static final int MAX = 9999;
    private static final int MIN = 1000;
    private static final int CHECKER = 1111;
    private static final int KAPREKAR = 6174;
    private static final int TEN = 10;

    private Task6() {
    }

    private static void negativeArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] *= -1;
        }
    }

    private static int recursionCounter(int num, int counter) {
        if (num > MAX || num <= MIN || num % CHECKER == 0) {
            return -1;
        }
        if (num == KAPREKAR) {
            return counter;
        }
        int[] arr = new int[] {0, 0, 0, 0};
        int len = arr.length;

        for (int i = 0; i < len; i++) {
            int temp = (int) (num / (Math.pow(TEN, (len - i - 1))) % TEN);
            arr[i] = temp;
        }

        int[] minArr = arr.clone();
        Arrays.sort(minArr);
        int[] maxArr = arr.clone();
        negativeArr(maxArr);
        Arrays.sort(maxArr);
        negativeArr(maxArr);
        int x = 0;

        for (int i = 0; i < len; i++) {
            x += (int) ((maxArr[i] - minArr[i]) * (Math.pow(TEN, (len - i - 1))));
        }

        return recursionCounter(x, counter + 1);
    }

    public static int countK(int num) {
        return recursionCounter(num, 0);
    }
}
