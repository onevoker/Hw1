package edu.hw1;

public class Task4 {

    private Task4() {
    }

    public static String fixString(String str) {
        char[] arr = str.toCharArray();

        for (int i = 0; i < arr.length - 1; i += 2) {
            char temp = arr[i];
            arr[i] = arr[i + 1];
            arr[i + 1] = temp;
        }

        return new String(arr);
    }
}
