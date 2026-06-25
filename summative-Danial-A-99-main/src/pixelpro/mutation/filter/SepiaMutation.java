package pixelpro.mutation.filter;

import java.awt.Color;
import java.awt.image.BufferedImage;

import pixelpro.mutation.Mutation;

public class SepiaMutation implements Mutation {

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
                
                double sepiaRed = (r * 0.39 + g * 0.77 + b * 0.19) / 255;
                double sepiaGreen = (r * 0.35 + g * 0.69 + b * 0.17) / 255;
                double sepiaBlue = (r * 0.27 + g * 0.53 + b * 0.13) / 255;
                double originalBrightness = r * 0.3 + g * 0.59 + b * 0.11;

                double newBrightness = sepiaRed * 0.3 + sepiaGreen * 0.59 + sepiaBlue * 0.11;
                if (newBrightness > 0) {
                    double brightnessRatio = originalBrightness / newBrightness;
                    double scaleFactor = 0.85 * originalBrightness + 0.15 * brightnessRatio;
                    sepiaRed *= scaleFactor;
                    sepiaGreen *= scaleFactor;
                    sepiaBlue *= scaleFactor;
                }

                int cr = clamp(sepiaRed);
                int cg = clamp(sepiaGreen);
                int cb = clamp(sepiaBlue);

                Color finalColor = new Color(cr, cg, cb, a);

                out.setRGB(x, y, finalColor.getRGB());
            }
        }
        return out;
    }
}
