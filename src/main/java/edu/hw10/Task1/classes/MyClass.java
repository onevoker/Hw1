package edu.hw10.Task1.classes;

import edu.hw10.Task1.annotations.Max;
import edu.hw10.Task1.annotations.Min;

@SuppressWarnings("MagicNumber")
public class MyClass {
    private int x = 5;
    private int y = 20;
    private int z = 25;

    public MyClass() {
    }

    public MyClass(@Min(-5) @Max(10) int x) {
        this.x = x;
    }

    public MyClass(@Min(-5) @Max(10) int x, @Min(15) @Max(100) int y) {
        this.x = x;
        this.y = y;
    }

    public MyClass(@Min(-5) @Max(10) int x, @Min(15) @Max(100) int y, @Max(100) int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @SuppressWarnings("MagicNumber")
    public MyClass createDefault() {
        return new MyClass(this.x, this.y, this.z);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getZ() {
        return this.z;
    }
}
