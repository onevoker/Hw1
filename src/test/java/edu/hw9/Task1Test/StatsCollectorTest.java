package edu.hw9.Task1Test;

import edu.hw9.Task1.Metric;
import edu.hw9.Task1.StatsCollector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class StatsCollectorTest {
    private static StatsCollector COLLECTOR;

    @BeforeEach void setUp() {
        COLLECTOR = new StatsCollector();
    }

    @Test
    void oneMetricTest() throws InterruptedException {
        String metricName = "metric";
        double[] numbers = {1, 2, 0.5, 0.5};
        COLLECTOR.push(metricName, numbers);
        List<Metric> expected = List.of(
            new Metric(metricName, 4, 1, 0.5, 2)
        );

        assertThat(COLLECTOR.stats()).isEqualTo(expected);
    }

    @Test
    void oneNumberInArrayTest() throws InterruptedException {
        String metricName = "metric";
        double[] numbers = {0.5};
        COLLECTOR.push(metricName, numbers);
        List<Metric> expected = List.of(
            new Metric(metricName, 0.5, 0.5, 0.5, 0.5)
        );

        assertThat(COLLECTOR.stats()).isEqualTo(expected);
    }

    @Test
    void mainTest() {
        String metricName1 = "metric1";
        String metricName2 = "metric2";
        String metricName3 = "metric3";
        String metricName4 = "metric4";
        String metricName5 = "metric5";

        double[] numbers1 = {1, 2, 3, 4, 5};
        double[] numbers2 = {1, 0.1, 11, 0.33};
        double[] numbers3 = {0.1, 0.05, 1.4, 5.1, 0.3};
        double[] numbers4 = {0, 0, 0, 0, 3};
        double[] numbers5 = {1, 2, 1, 24, 1};

        Metric metric1 = new Metric(metricName1, 15, 3, 1, 5);
        Metric metric2 = new Metric(metricName2, 12.43, 3.1075, 0.1, 11);
        Metric metric3 = new Metric(metricName3, 6.95, 1.39, 0.05, 5.1);
        Metric metric4 = new Metric(metricName4, 3, 0.6, 0, 3);
        Metric metric5 = new Metric(metricName5, 29, 5.8, 1, 24);

        COLLECTOR.push(metricName1, numbers1);
        COLLECTOR.push(metricName2, numbers2);
        COLLECTOR.push(metricName3, numbers3);
        COLLECTOR.push(metricName4, numbers4);
        COLLECTOR.push(metricName5, numbers5);

        assertAll(
            () -> assertThat(COLLECTOR.getByMetricName(metricName1)).isEqualToComparingFieldByField(metric1),
            () -> assertThat(COLLECTOR.getByMetricName(metricName2)).isEqualToComparingFieldByField(metric2),
            () -> assertThat(COLLECTOR.getByMetricName(metricName3)).isEqualToComparingFieldByField(metric3),
            () -> assertThat(COLLECTOR.getByMetricName(metricName4)).isEqualToComparingFieldByField(metric4),
            () -> assertThat(COLLECTOR.getByMetricName(metricName5)).isEqualToComparingFieldByField(metric5)
        );
    }
}
