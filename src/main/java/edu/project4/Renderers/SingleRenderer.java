package edu.project4.Renderers;

import edu.project4.BaseClasses.FractalImage;
import edu.project4.BaseClasses.Pixel;
import edu.project4.BaseClasses.Point;
import edu.project4.BaseClasses.Rect;
import edu.project4.Transformations.Transformation;
import java.util.List;
import static edu.project4.Renderers.RendererUtil.mapCoordinate;
import static edu.project4.Renderers.RendererUtil.mapRange;
import static edu.project4.Renderers.RendererUtil.randomPoint;
import static edu.project4.Renderers.RendererUtil.randomVariation;
import static edu.project4.Renderers.RendererUtil.rotate;
import static edu.project4.Renderers.RendererUtil.updatePixelColor;

public class SingleRenderer implements Renderer {
    @Override
    public void render(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        int samples,
        short iterPerSample,
        int symmetry
    ) {
        for (int num = 0; num < samples; ++num) {
            Point pw = randomPoint(world);

            for (short step = 0; step < iterPerSample; ++step) {
                Transformation variation = randomVariation(variations);
                pw = variation.apply(pw);

                double theta2 = 0.0;

                for (int s = 0; s < symmetry; theta2 += Math.PI * 2 / symmetry, ++s) {
                    var pwr = rotate(pw, theta2);

                    if (!world.contains(pwr)) {
                        continue;
                    }

                    Pixel pixel = mapRange(world, pwr, canvas);
                    if (pixel == null) {
                        continue;
                    }

                    int x = mapCoordinate(pwr.x(), world.x(), world.width(), canvas.width());
                    int y = mapCoordinate(pwr.y(), world.y(), world.height(), canvas.height());

                    updatePixelColor(canvas, x, y, pixel);
                }
            }
        }

        GammaCorrection.correct(canvas);
    }
}
