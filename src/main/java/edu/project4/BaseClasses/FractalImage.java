package edu.project4.BaseClasses;

public record FractalImage(Pixel[][] data, int width, int height) {
    public static FractalImage create(int width, int height) {
        Pixel[][] pixels = new Pixel[height][width];
        fillData(pixels);
        return new FractalImage(pixels, width, height);
    }

    private static void fillData(Pixel[][] pixels) {
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                pixels[i][j] = new Pixel(0, 0, 0, 0);
            }
        }
    }

    public boolean contains(int x, int y) {
        return (x >= 0 && x < width) && (y >= 0 && y < height);
    }

    public Pixel pixel(int x, int y) {
        return data[y][x];
    }

    public void setPixel(Pixel pixel, int x, int y) {
        data[y][x] = pixel;
    }
}
