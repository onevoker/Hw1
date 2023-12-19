package edu.hw10.Task1Test;

import edu.hw10.Task1.classes.MyClass;
import edu.hw10.Task1.classes.MyRecord;
import edu.hw10.Task1.classes.RandomObjectGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class RandomObjectGeneratorTest {
    private static final RandomObjectGenerator ROG = new RandomObjectGenerator();
    @Nested
    class MyClassTest {
        @Test
        void testConstructors() {
            var myClass = ROG.nextObject(MyClass.class);

            assertAll(
                () -> assertThat(myClass.getX()).isBetween(-5, 10),
                () -> assertThat(myClass.getY()).isBetween(15, 100),
                () -> assertThat(myClass.getZ()).isLessThanOrEqualTo(100)
            );
        }

        @Test
        void testMethod() {
            var myClass = ROG.nextObject(MyClass.class, "createDefault");

            assertAll(
                () -> assertThat(myClass.getX()).isEqualTo(5),
                () -> assertThat(myClass.getY()).isEqualTo(20),
                () -> assertThat(myClass.getZ()).isEqualTo(25)
            );
        }
    }

    @Nested
    class MyRecordTest {
        @Test
        void testMyRecord() {
            var myRecord = ROG.nextObject(MyRecord.class);

            assertAll(
                () -> assertThat(myRecord.str()).isNotNull(),
                () -> assertThat(myRecord.x()).isBetween(0, 10),
                () -> assertThat(myRecord.x()).isLessThanOrEqualTo(50)
            );
        }
    }
}
