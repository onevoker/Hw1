package edu.project1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DictionaryTest {
    private final Dictionary dictionary = new Dictionary();

    @Test
    void testGetRandomWord() {
        String randomWord = dictionary.getRandomWord();

        assertThat(randomWord).isInstanceOf(String.class);
        assertThat(dictionary.getDictionary()).contains(randomWord);
    }
}
