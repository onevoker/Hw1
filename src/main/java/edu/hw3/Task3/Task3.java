package edu.hw3.Task3;

import java.util.HashMap;
import java.util.List;

public class Task3 {
    public HashMap<Object, Integer> freqDict(List<Object> list) {
        if (list == null) {
            return null;
        }

        HashMap<Object, Integer> hashMap = new HashMap<>();

        for (Object object : list) {
            hashMap.put(object, hashMap.getOrDefault(object, 0) + 1);
        }

        return hashMap;
    }
}
