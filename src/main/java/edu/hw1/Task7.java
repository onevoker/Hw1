package edu.hw1;

public class Task7 {

    private Task7() {
    }

    public static int rotateLeft(int n, int shift) {
        if (n <= 0 || shift < 0) {
            throw new IllegalArgumentException("Cant rotate left, invalid input");
        }
        String binaryNum = Integer.toBinaryString(n);
        int remainder = shift % binaryNum.length();
        String res = binaryNum.substring(remainder) + binaryNum.substring(0, remainder);
        return Integer.valueOf(res, 2);
    }

    public static int rotateRight(int n, int shift) {
        if (n <= 0 || shift < 0) {
            throw new IllegalArgumentException("Cant rotate right, invalid input");
        }
        String binaryNum = Integer.toBinaryString(n);
        int len = binaryNum.length();
        int remainder = shift % len;
        String res = binaryNum.substring(len - remainder) + binaryNum.substring(0, len - remainder);
        return Integer.valueOf(res, 2);
    }
}
