package pixelpro.mutation.filter;

import java.awt.Color;
import java.awt.image.BufferedImage;

import pixelpro.mutation.Mutation;

public class CinematicMutation implements Mutation {

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
                double luminance = (r * 0.21 + g * 0.71 + b * 0.07) / 255;
                
                double newr = r + (35 * (luminance - 0.35));
                double newg = g + (10 * (luminance - 0.5));
                double newb = b + (40 * (luminance - 0.65));

                if (newr < 128) {
                    newr *= newr / 128;
                } else {
                    newr = Math.sqrt(newr * 128);
                }
                if (newg < 128) {
                    newg = newg * newg / 128;
                } else {
                    newg = Math.sqrt(newg * 128);
                }
                if (newb < 128) {
                    newb = newb * newb / 128;
                } else {
                    newb = Math.sqrt(newb * 128);
                }

                double finalr = newr * 0.85 + r * 0.15;
                double finalg = newg * 0.85 + g * 0.15;
                double finalb = newb * 0.85 + b * 0.15;

                int cr = clamp(finalr);
                int cg = clamp(finalg);
                int cb = clamp(finalb);
                Color finalColor = new Color(cr, cg, cb, a);

                out.setRGB(x, y, finalColor.getRGB());

            }
        }
        return out;
    }
}
