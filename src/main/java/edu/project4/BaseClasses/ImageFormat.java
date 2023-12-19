package edu.project4.BaseClasses;

public enum ImageFormat {
    JPEG("jpg"),
    BMP("bmp"),
    PNG("png");

    private final String extension;

    ImageFormat(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }
}

