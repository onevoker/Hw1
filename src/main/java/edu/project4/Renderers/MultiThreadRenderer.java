package edu.project4.Renderers;

import edu.project4.BaseClasses.FractalImage;
import edu.project4.BaseClasses.Pixel;
import edu.project4.BaseClasses.Point;
import edu.project4.BaseClasses.Rect;
import edu.project4.Transformations.Transformation;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import static edu.project4.Renderers.RendererUtil.mapCoordinate;
import static edu.project4.Renderers.RendererUtil.mapRange;
import static edu.project4.Renderers.RendererUtil.randomPoint;
import static edu.project4.Renderers.RendererUtil.randomVariation;
import static edu.project4.Renderers.RendererUtil.rotate;
import static edu.project4.Renderers.RendererUtil.updatePixelColor;

public class MultiThreadRenderer implements Renderer {
    private static ExecutorService service;

    public MultiThreadRenderer(int numOfThreads) {
        service = Executors.newFixedThreadPool(numOfThreads);
    }

    @Override
    public void render(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        int samples,
        short iterPerSample,
        int symmetry
    ) {
        for (int number = 0; number < samples; ++number) {
            service.submit(() -> {
                    renderTask(canvas, world, variations, iterPerSample, symmetry);
                }
            );
        }

        service.shutdown();

        try {
            service.awaitTermination(1L, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        GammaCorrection.correct(canvas);
    }

    private void renderTask(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        short iterPerSample,
        int symmetry
    ) {
        Point pw = randomPoint(world);

        for (short step = 0; step < iterPerSample; ++step) {
            Transformation variation = randomVariation(variations);
            pw = variation.apply(pw);
            double theta = 0.0;

            for (int s = 0; s < symmetry; theta += Math.PI * 2 / symmetry, ++s) {
                var pwr = rotate(pw, theta);
                if (!world.contains(pwr)) {
                    continue;
                }

                Pixel pixel = mapRange(world, pwr, canvas);
                if (pixel == null) {
                    continue;
                }

                int x = mapCoordinate(pwr.x(), world.x(), world.width(), canvas.width());
                int y = mapCoordinate(pwr.y(), world.y(), world.height(), canvas.height());

                synchronized (this) {
                    updatePixelColor(canvas, x, y, pixel);
                }
            }
        }
    }
}
