package pixelpro.mutation.filter;

import java.awt.Color;
import java.awt.image.BufferedImage;

import pixelpro.mutation.Mutation;

public class ComicMutation implements Mutation {

    @Override
    public BufferedImage apply(BufferedImage image, int width, int height) {
        BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(image.getRGB(x, y), true);
                int a = color.getAlpha();
                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();
                double grayScale = r * 0.3 + g * 0.59 + b * 0.11;
                int val = 0;

                if (grayScale > 120) {
                    val = 255;
                }
                Color finalColor = new Color(val, val, val, a);

                out.setRGB(x, y, finalColor.getRGB());
            }

        }
        return out;
    }
}
