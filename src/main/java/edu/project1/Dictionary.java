package edu.project1;

import java.util.List;
import java.util.Random;

public class Dictionary {

    private final static Random RANDOM = new Random();
    private final List<String> dictionary = List.of(
        "mother", "father", "brother", "sister", "grandmother", "grandfather"
    );

    public String getRandomWord() {
        return dictionary.get(RANDOM.nextInt(dictionary.size()));
    }

    public List<String> getDictionary() {
        return dictionary;
    }
}
