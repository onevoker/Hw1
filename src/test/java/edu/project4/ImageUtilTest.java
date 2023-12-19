package edu.project4;

import edu.project4.BaseClasses.FractalImage;
import edu.project4.BaseClasses.ImageFormat;
import edu.project4.BaseClasses.ImageUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.nio.file.Path;
import static org.assertj.core.api.Assertions.assertThat;

public class ImageUtilTest {
    private static final String PATH = "./src/test/java/edu/project4/";
    private static final FractalImage IMAGE = FractalImage.create(60, 100);

    @Test
    void testSave() {
        Path directory = Path.of(PATH);
        ImageUtil.save(IMAGE, directory, "test", ImageFormat.JPEG);
        String directoryWithImage = PATH + "test.jpg";
        Path newPath = Path.of(directoryWithImage);
        String fileName = newPath.getFileName().toString();

        assertThat(fileName).isEqualTo("test.jpg");
    }

    @BeforeEach
    void deleteImage() {
        File file = new File(PATH + "test.jpg");
        file.deleteOnExit();
    }
}
