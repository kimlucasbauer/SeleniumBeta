package utils.dsl;

import org.junit.Assert;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

public class Assertions extends Functions {

    public void fail(String... message) {
        Assert.fail(Arrays.toString(message));
    }

    public void compare(Object expected, Object actual, String... message) {
        String msg = "The expected(" + expected + ") is not the same as the current(" + actual + ")\n";
        for (String s : message) msg = s;
        Assert.assertEquals(msg, expected, actual);
    }

    public void assertImage(Object locatorEnum, String fileName) {
        ajaxInject();
        assertImageLoaded(locatorEnum, fileName);
    }

    public void assertImageLoaded(Object locatorEnum, String fileName) {
        boolean isDifferent;
        ajaxInject();
        BufferedImage expectedImage = getImageInFolder(fileName);
        Screenshot loadedImage = printElement(locatorEnum);
        BufferedImage actualImage = loadedImage.getImage();

        ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(expectedImage, actualImage);

        isDifferent = diff.hasDiff();

        if (isDifferent) {
            System.out.println("Validate image with 90% -> " + locatorEnum);
            imgDiff = new ImageDiffer();
            int pixels = actualImage.getWidth() * actualImage.getHeight();
            pixels = pixels / 100 * 10;
            diff = imgDiff.makeDiff(expectedImage, actualImage).withDiffSizeTrigger(pixels);
            isDifferent = diff.hasDiff();
        }

        if (isDifferent) {
            boolean ignored = new File("errors").mkdir();
            saveImageFile(diff.getMarkedImage(), "errors/" + fileName + "ERROdiff.png");
        }

        Assert.assertFalse(isDifferent);
    }
}
