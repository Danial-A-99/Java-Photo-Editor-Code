package pixelpro.mutation.filter;

import java.awt.Color;
import java.awt.image.BufferedImage;

import pixelpro.mutation.Mutation;

public class NightMutation implements Mutation {

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
                double grayScaleBrightness = r * 0.3 + g * 0.59 + b * 0.11;
                r = (int) (grayScaleBrightness * 0.2);
                g = (int) (grayScaleBrightness * 1.2 + 20);
                b = (int) (grayScaleBrightness * 0.3);
                
                if (y % 2 == 0) {
                    r = (int) (r * 0.2);
                    g = (int) (r * 0.2);
                    b = (int) (r * 0.2);
                }

                int cr = clamp(r);
                int cg = clamp(g);
                int cb = clamp(b);

                Color finalColor = new Color(cr, cg, cb, a);

                out.setRGB(x, y, finalColor.getRGB());
            }
        }
        return out;
    }
}
