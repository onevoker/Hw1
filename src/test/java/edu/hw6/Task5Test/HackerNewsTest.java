package edu.hw6.Task5Test;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static edu.hw6.Task5.HackerNews.getNews;
import static edu.hw6.Task5.HackerNews.hackerNewsTopStories;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class HackerNewsTest {

    @Nested
    class hackerNewsTopStoriesTest {
        private static final long[] topStories = hackerNewsTopStories();

        private static boolean isContainsElement(long element) {
            return Arrays.stream(topStories)
                .anyMatch(e -> e == element);
        }

        @Test
        void testResultIsNotNull() {
            assertThat(topStories).isNotNull();
        }

        @Test
        void testTopStoriesContainsElement() {
            assertAll(
                () -> assertThat(isContainsElement(38282166)).isTrue(),
                () -> assertThat(isContainsElement(38279459)).isTrue(),
                () -> assertThat(isContainsElement(38285228)).isTrue(),
                () -> assertThat(isContainsElement(38285482)).isTrue(),
                () -> assertThat(isContainsElement(38280472)).isTrue()
            );
        }

        @Test
        void testTopStoriesNotContainsElement() {
            assertAll(
                () -> assertThat(isContainsElement(1)).isFalse(),
                () -> assertThat(isContainsElement(50)).isFalse(),
                () -> assertThat(isContainsElement(140305)).isFalse(),
                () -> assertThat(isContainsElement(99)).isFalse(),
                () -> assertThat(isContainsElement(777)).isFalse()
            );
        }
    }

    @Nested
    class GetNewsTest {
        @Test
        void testGetValidNews() {
            assertAll(
                () -> assertThat(getNews(1L)).isEqualTo("Y Combinator"),
                () -> assertThat(getNews(37570037L)).isEqualTo("JDK 21 Release Notes"),
                () -> assertThat(getNews(38282166L)).isEqualTo(
                    "Blender 16yo winner of UK young animator of the year"),
                () -> assertThat(getNews(38277701L)).isEqualTo(
                    "I visited over 120 EV chargers: why so many were broken"),
                () -> assertThat(getNews(38277598L)).isEqualTo("M1076 Analog Matrix Processor"),
                () -> assertThat(getNews(38270446L)).isEqualTo("Ara2: RVV 1.0 Compliant Open-Source Processor"),
                () -> assertThat(getNews(38269747L)).isEqualTo(
                    "Ask HN: What thought (or idea) has changed your life the most?")
            );
        }

        @Test
        void testGetNewsWithIncorrectId() {
            assertAll(
                () -> assertThat(getNews(0L)).isEqualTo("Incorrect id of news"),
                () -> assertThat(getNews(-10L)).isEqualTo("Incorrect id of news"),
                () -> assertThat(getNews(-3L)).isEqualTo("Incorrect id of news"),
                () -> assertThat(getNews(Long.MIN_VALUE)).isEqualTo("Incorrect id of news"),
                () -> assertThat(getNews(-9L)).isEqualTo("Incorrect id of news")
            );
        }

        @Test
        void testGetNewsWithNoTitle() {
            assertAll(
                () -> assertThat(getNews(80L)).isEqualTo("Title not found"),
                () -> assertThat(getNews(9321L)).isEqualTo("Title not found"),
                () -> assertThat(getNews(93218L)).isEqualTo("Title not found")
            );
        }
    }
}
