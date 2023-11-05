package edu.hw3;

import edu.hw3.Task5.Task5;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task5Test {
    private final static Task5 task5 = new Task5();

    @Test
    void parseContactsValidInputTest() {
        assertAll(
            () -> assertThat(task5.parseContacts(new String[] {"John Locke", "Thomas Aquinas", "David Hume",
                "Rene Descartes"}, "ASC"))
                .isEqualTo(new String[] {"Thomas Aquinas", "Rene Descartes", "David Hume", "John Locke"}),

            () -> assertThat(task5.parseContacts(
                new String[] {"Paul Erdos", "Leonhard Euler", "Carl Gauss"}, "DESC"))
                .isEqualTo(new String[] {"Carl Gauss", "Leonhard Euler", "Paul Erdos"}),

            () -> assertThat(task5.parseContacts(
                new String[] {"Paul", "Leonard Euler", "Carl Gauss"}, "ASC"))
                .isEqualTo(new String[] {"Leonard Euler", "Carl Gauss", "Paul"}),

            () -> assertThat(task5.parseContacts(new String[] {"Homer"}, "ASC")).isEqualTo(new String[] {"Homer"})
        );
    }

    @Test
    void parseContactsInvalidInputTest() {
        assertAll(
            () -> assertThat(task5.parseContacts(new String[] {}, "DESC")).isEqualTo(new String[] {}),
            () -> assertThat(task5.parseContacts(null, "DESC")).isEqualTo(new String[] {}),
            () -> assertThat(task5.parseContacts(new String[] {}, "HAHA")).isEqualTo(new String[] {})
        );
    }

    @Test
    void parseContactsInvalidInputThrowsTest() {
        assertAll(
            () -> {
                var exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> task5.parseContacts(new String[] {"Homer"}, "Lol")
                );

                assertThat(exception.getMessage()).isEqualTo("You cant sort like that");
            },
            () -> {
                var exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> task5.parseContacts(new String[] {"Homer"}, "desc")
                );

                assertThat(exception.getMessage()).isEqualTo("You cant sort like that");
            },
            () -> {
                var exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> task5.parseContacts(new String[] {"Homer"}, "")
                );

                assertThat(exception.getMessage()).isEqualTo("You cant sort like that");
            },
            () -> {
                var exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> task5.parseContacts(new String[] {"Homer"}, null)
                );

                assertThat(exception.getMessage()).isEqualTo("You cant sort like that");
            }
        );
    }
}
