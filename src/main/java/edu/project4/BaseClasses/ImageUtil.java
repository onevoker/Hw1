package edu.project4.BaseClasses;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;

public final class ImageUtil {
    private ImageUtil() {}

    @SuppressWarnings("MagicNumber")
    public static void save(FractalImage image, Path directory, String filename, ImageFormat format) {
        BufferedImage bufferedImage = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < image.height(); ++y) {
            for (int x = 0; x < image.width(); ++x) {
                Pixel pixel = image.pixel(x, y);
                int rgb = (pixel.r() << 16) | (pixel.g() << 8) | pixel.b();
                bufferedImage.setRGB(x, y, rgb);
            }
        }

        try {
            Path filePath = directory.resolve(filename + "." + format.getExtension());

            switch (format) {
                case JPEG:
                    ImageIO.write(bufferedImage, "jpg", filePath.toFile());
                    break;
                case BMP:
                    ImageIO.write(bufferedImage, "bmp", filePath.toFile());
                    break;
                case PNG:
                    ImageIO.write(bufferedImage, "png", filePath.toFile());
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported image format: " + format);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

