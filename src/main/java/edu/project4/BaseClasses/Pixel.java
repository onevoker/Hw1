package edu.project4.BaseClasses;

public record Pixel(int r, int g, int b, int hitCount) {
    public double getNormal() {
        if (this.hitCount != 0) {
            return Math.log10(this.hitCount);
        }
        return 0;
    }
}
