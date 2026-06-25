package pixelpro.mutation.filter;

import java.awt.Color;
import java.awt.image.BufferedImage;

import pixelpro.mutation.Mutation;

public class HeatMutation implements Mutation {

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
                
                if (grayScaleBrightness < 51) {
                    b = (int) (grayScaleBrightness * 5);
                } else if (grayScaleBrightness > 51 && grayScaleBrightness < 101) {
                    b = 255;
                    r = 128;
                } else if (grayScaleBrightness > 102 && grayScaleBrightness < 152) {
                    r = 255;
                    g = 128;
                    b = 64;
                } else if (grayScaleBrightness > 153 && grayScaleBrightness < 203) {
                    r = 255;
                    g = 145;
                } else {
                    r = 255;
                    g = 250;
                    b = 128;
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
