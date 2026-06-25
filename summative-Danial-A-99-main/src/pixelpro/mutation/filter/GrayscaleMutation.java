package pixelpro.mutation.filter;

import java.awt.Color;
import java.awt.image.BufferedImage;

import pixelpro.mutation.Mutation;

public class GrayscaleMutation implements Mutation {

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
                
                double grayValue = r * 0.21 + g * 0.71 + b * 0.07;
                int newgrayValue = clamp(grayValue);
                Color finalColor = new Color(newgrayValue, newgrayValue, newgrayValue, a);

                out.setRGB(x, y, finalColor.getRGB());
            }
        }
        return out;
    }
}
