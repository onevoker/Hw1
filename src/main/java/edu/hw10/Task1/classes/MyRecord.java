package edu.hw10.Task1.classes;

import edu.hw10.Task1.annotations.Max;
import edu.hw10.Task1.annotations.Min;
import edu.hw10.Task1.annotations.NotNull;

public record MyRecord(@NotNull String str, @Min(0) @Max(10) int x, @Max(50) int y) {
}
