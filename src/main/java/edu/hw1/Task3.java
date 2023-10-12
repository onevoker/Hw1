package edu.hw1;

public class Task3 {

    private Task3() {
    }

    public static int max(int[] arr) throws Exception {
        if (arr == null) {
            throw new NullPointerException("Array is null, cant find max");
        }
        if (arr.length == 0) {
            throw new Exception("Cant find max, array length equals zero");
        }
        int max = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }

        return max;
    }

    public static int min(int[] arr) throws Exception {
        if (arr == null) {
            throw new NullPointerException("Array is null, cant find min");
        }
        if (arr.length == 0) {
            throw new Exception("Cant find min, array length equals zero");
        }
        int min = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (min > arr[i]) {
                min = arr[i];
            }
        }

        return min;
    }

    public static boolean isNestable(int[] arr1, int[] arr2) throws Exception {
        if (arr1 == null || arr2 == null) {
            throw new NullPointerException("Some array is null");
        }
        if (arr1.length == 0 || arr2.length == 0) {
            throw new Exception("Some array length equals zero");
        }
        return min(arr1) > min(arr2) && max(arr1) < max(arr2);
    }
}
