package edu.hw2;

import edu.hw2.Task4.CallingInfo;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {
    @Test
    void callingInfoTest() {
        CallingInfo callingInfo = CallingInfo.callingInfo(new Throwable());
        String className = callingInfo.className();
        String methodName = callingInfo.methodName();

        assertThat(className).isEqualTo("edu.hw2.Task4Test");
        assertThat(methodName).isEqualTo("callingInfoTest");
    }
}
