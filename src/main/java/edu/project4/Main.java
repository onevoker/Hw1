package edu.project4;

import edu.project4.BaseClasses.FractalImage;
import edu.project4.BaseClasses.ImageFormat;
import edu.project4.BaseClasses.ImageUtil;
import edu.project4.BaseClasses.Rect;
import edu.project4.Renderers.MultiThreadRenderer;
import edu.project4.Transformations.Affine;
import edu.project4.Transformations.AnotherMyTransformation;
import edu.project4.Transformations.Disk;
import edu.project4.Transformations.Heart;
import edu.project4.Transformations.MyTransformation;
import edu.project4.Transformations.Polar;
import edu.project4.Transformations.Sinusoidal;
import edu.project4.Transformations.Spherical;
import edu.project4.Transformations.Transformation;
import java.nio.file.Path;
import java.util.List;

@SuppressWarnings("MagicNumber")
public class Main {
    private static final int IMAGE_WIDTH = 1920;
    private static final int IMAGE_HEIGHT = 1080;
    private static final String IMAGE_DIRECTORY = "./src/main/java/edu/project4/images/";
    private static final Rect WORLD = new Rect(-1.777, -1, 3.544, 2);

    private Main() {
    }

    public static void main(String[] args) {
        FractalImage image = FractalImage.create(IMAGE_WIDTH, IMAGE_HEIGHT);
        Path path = Path.of(IMAGE_DIRECTORY);

        List<Transformation> variations = List.of(
            new MyTransformation(),
            new AnotherMyTransformation(),
            new Affine(0.5, 0.12, 0.0, 0.6, 0.9, 0.5),
            new Sinusoidal(),
            new Spherical(),
            new Polar(),
            new Heart(),
            new Disk()
        );

        var renderer = new MultiThreadRenderer(12);
        renderer.render(image, WORLD, variations, 5000000, (short) 10, 9);
        ImageUtil.save(image, path, "image", ImageFormat.PNG);
    }
}
