package edu.project4.Renderers;

import edu.project4.BaseClasses.FractalImage;
import edu.project4.BaseClasses.Pixel;

public class GammaCorrection {
    private static final double GAMMA = 0.6;

    private GammaCorrection() {
    }

    public static void correct(FractalImage canvas) {
        for (int y = 0; y < canvas.height(); ++y) {
            for (int x = 0; x < canvas.width(); ++x) {
                Pixel pixel = canvas.pixel(x, y);
                Pixel repaintedPixel = repaintPixel(pixel);
                canvas.setPixel(repaintedPixel, x, y);
            }
        }
    }

    private static Pixel repaintPixel(Pixel pixel) {
        double coefficient = Math.pow(pixel.getNormal(), (1 / GAMMA));

        int newR = (int) (pixel.r() * coefficient);
        int newG = (int) (pixel.g() * coefficient);
        int newB = (int) (pixel.b() * coefficient);

        return new Pixel(newR, newG, newB, pixel.hitCount());
    }
}
