package edu.project4.Renderers;

import edu.project4.BaseClasses.FractalImage;
import edu.project4.BaseClasses.Rect;
import edu.project4.Transformations.Transformation;
import java.util.List;

@FunctionalInterface
public interface Renderer {
    void render(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        int samples,
        short iterPerSample,
        int symmetry
    );
}
