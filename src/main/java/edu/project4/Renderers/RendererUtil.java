package edu.project4.Renderers;

import edu.project4.BaseClasses.FractalImage;
import edu.project4.BaseClasses.Pixel;
import edu.project4.BaseClasses.Point;
import edu.project4.BaseClasses.Rect;
import edu.project4.Transformations.Transformation;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RendererUtil {
    private static final int RED_CONSTANT = 200;
    private static final int GREEN_CONSTANT = 60;
    private static final int BLUE_CONSTANT = 50;
    private static final int RGB_MAX_VALUE = 256;
    private static final int COEFFICIENT_FOR_MEDIUM = 3;

    private RendererUtil() {
    }

    public static Point randomPoint(Rect world) {
        double x = world.x() + ThreadLocalRandom.current().nextDouble() * world.width();
        double y = world.y() + ThreadLocalRandom.current().nextDouble() * world.height();

        return new Point(x, y);
    }

    public static Transformation randomVariation(List<Transformation> variations) {
        int randomIndex = ThreadLocalRandom.current().nextInt(variations.size());
        return variations.get(randomIndex);
    }

    public static Point rotate(Point point, double theta) {
        double x = point.x() * Math.cos(theta) - point.y() * Math.sin(theta);
        double y = point.x() * Math.sin(theta) + point.y() * Math.cos(theta);

        return new Point(x, y);
    }

    public static int mapCoordinate(double current, double min, double range, int size) {
        return (int) (((current - min) / range) * size);
    }

    public static Pixel mapRange(Rect world, Point point, FractalImage canvas) {
        int x = mapCoordinate(point.x(), world.x(), world.width(), canvas.width());
        int y = mapCoordinate(point.y(), world.y(), world.height(), canvas.height());

        return canvas.contains(x, y) ? canvas.pixel(x, y) : null;
    }

    public static void updatePixelColor(FractalImage canvas, int x, int y, Pixel pixel) {
        Pixel currentPixel = canvas.pixel(x, y);
        int newR = ((currentPixel.r() + pixel.r() + RED_CONSTANT) / COEFFICIENT_FOR_MEDIUM) % RGB_MAX_VALUE;
        int newG = ((currentPixel.g() + pixel.g() + GREEN_CONSTANT) / COEFFICIENT_FOR_MEDIUM) % RGB_MAX_VALUE;
        int newB = ((currentPixel.b() + pixel.b() + BLUE_CONSTANT) / COEFFICIENT_FOR_MEDIUM) % RGB_MAX_VALUE;

        Pixel updatedPixel = new Pixel(newR, newG, newB, currentPixel.hitCount() + 1);
        canvas.setPixel(updatedPixel, x, y);
    }
}
