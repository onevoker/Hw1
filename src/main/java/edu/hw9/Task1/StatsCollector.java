package edu.hw9.Task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class StatsCollector {
    private final Queue<Metric> metrics = new ConcurrentLinkedQueue<>();
    private static final int NUM_OF_THREADS = Runtime.getRuntime().availableProcessors();
    private final ExecutorService executorService = Executors.newFixedThreadPool(NUM_OF_THREADS);

    public void push(String metricName, double[] numbers) {
        executorService.execute(() -> {
                double sum = Arrays.stream(numbers).sum();
                double average = Arrays.stream(numbers).average().orElse(0);
                double min = Arrays.stream(numbers).min().orElse(Double.MIN_VALUE);
                double max = Arrays.stream(numbers).max().orElse(Double.MAX_VALUE);

                Metric metric = new Metric(metricName, sum, average, min, max);
                metrics.add(metric);
            }
        );
    }

    public List<Metric> stats() throws InterruptedException {
        executorService.shutdown();
        executorService.awaitTermination(1L, TimeUnit.MINUTES);
        return new ArrayList<>(metrics);
    }

    public Metric getByMetricName(String metricName) {
        for (var metric : metrics) {
            if (metric.metricName().equals(metricName)) {
                return metric;
            }
        }

        return null;
    }
}
