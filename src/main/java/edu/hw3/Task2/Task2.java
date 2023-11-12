package edu.hw3.Task2;

import java.util.ArrayList;

public class Task2 {

    public ArrayList<String> clusterize(String str) {
        if (str == null) {
            return null;
        }

        int counterParenthesis = 0;
        int index = 0;
        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                counterParenthesis++;
            } else if (str.charAt(i) == ')') {
                counterParenthesis--;
            }

            if (counterParenthesis == 0) {
                StringBuilder temp = new StringBuilder();

                while (index <= i) {
                    temp.append(str.charAt(index));
                    index++;
                }

                result.add(temp.toString());
            }
        }

        return result;
    }
}

