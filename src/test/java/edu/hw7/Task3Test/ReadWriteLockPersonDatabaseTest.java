package edu.hw7.Task3Test;

import edu.hw7.Task3.Person;
import edu.hw7.Task3.ReadWriteLockPersonDatabase;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ReadWriteLockPersonDatabaseTest {
    private static final ReadWriteLockPersonDatabase R_W_L_PERSON_DB = new ReadWriteLockPersonDatabase();
    private static final Person PERSON_1 = new Person(1, "Alex", "Kirova", "+1234");
    private static final Person PERSON_2 = new Person(2, "Ragnar", "Lenina", "+999");
    private static final Person PERSON_3 = new Person(3, "Good", "Nope", "9232");

    private void setUpTest() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            R_W_L_PERSON_DB.add(PERSON_1);
        });
        Thread thread2 = new Thread(() -> {
            R_W_L_PERSON_DB.add(PERSON_2);
        });
        Thread thread3 = new Thread(() -> {
            R_W_L_PERSON_DB.add(PERSON_3);
        });

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
    }

    @Test
    void testAddAndDelete() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            R_W_L_PERSON_DB.add(PERSON_1);
            R_W_L_PERSON_DB.delete(1);
        });
        Thread thread2 = new Thread(() -> {
            R_W_L_PERSON_DB.add(PERSON_2);
            R_W_L_PERSON_DB.delete(2);
        });
        Thread thread3 = new Thread(() -> {
            R_W_L_PERSON_DB.add(PERSON_3);
            R_W_L_PERSON_DB.delete(3);
        });

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        assertThat(R_W_L_PERSON_DB.getPersons()).isEqualTo(Map.of());
    }

    @Test
    void testFindByName() throws InterruptedException {
        setUpTest();

        assertAll(
            () -> assertThat(R_W_L_PERSON_DB.findByName("Alex")).isEqualTo(List.of(PERSON_1)),
            () -> assertThat(R_W_L_PERSON_DB.findByName("Ragnar")).isEqualTo(List.of(PERSON_2)),
            () -> assertThat(R_W_L_PERSON_DB.findByName("Good")).isEqualTo(List.of(PERSON_3)),
            () -> assertThat(R_W_L_PERSON_DB.findByName("No name")).isEqualTo(List.of())

        );
    }

    @Test
    void testFindByAddress() throws InterruptedException {
        setUpTest();

        assertAll(
            () -> assertThat(R_W_L_PERSON_DB.findByAddress("Kirova")).isEqualTo(List.of(PERSON_1)),
            () -> assertThat(R_W_L_PERSON_DB.findByAddress("Lenina")).isEqualTo(List.of(PERSON_2)),
            () -> assertThat(R_W_L_PERSON_DB.findByAddress("Nope")).isEqualTo(List.of(PERSON_3)),
            () -> assertThat(R_W_L_PERSON_DB.findByAddress("No address")).isEqualTo(List.of())

        );
    }

    @Test
    void testFindByPhone() throws InterruptedException {
        setUpTest();

        assertAll(
            () -> assertThat(R_W_L_PERSON_DB.findByPhone("+1234")).isEqualTo(List.of(PERSON_1)),
            () -> assertThat(R_W_L_PERSON_DB.findByPhone("+999")).isEqualTo(List.of(PERSON_2)),
            () -> assertThat(R_W_L_PERSON_DB.findByPhone("9232")).isEqualTo(List.of(PERSON_3)),
            () -> assertThat(R_W_L_PERSON_DB.findByPhone("No phone")).isEqualTo(List.of())
        );
    }

    @Test
    void testMultiFinding() throws InterruptedException {
        setUpTest();

        List<Person> expectedPersons = List.of(PERSON_1);

        assertThat(
            R_W_L_PERSON_DB.findByName("Alex"))
            .isEqualTo(R_W_L_PERSON_DB.findByAddress("Kirova"))
            .isEqualTo(R_W_L_PERSON_DB.findByPhone("+1234"))
            .isEqualTo(expectedPersons);
    }
}
