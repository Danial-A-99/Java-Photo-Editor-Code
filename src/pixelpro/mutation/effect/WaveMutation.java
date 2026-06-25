package pixelpro.mutation.effect;

import java.awt.Color;
import java.awt.image.BufferedImage;

import pixelpro.mutation.Mutation;

public class WaveMutation implements Mutation {

    @Override
    public BufferedImage apply(BufferedImage image, int width, int height) {
        BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            double fastSine = Math.sin(y * 0.14);
            double slowCos = Math.cos(y * 0.06);
            double veryFastSine = Math.sin(y * 0.34);
            double totalWave = 0.5 * fastSine + 0.4 * slowCos + 0.1 * veryFastSine;
            double shiftX = 20.0 * totalWave;

            if ((y > height * 0.3 && y < height * 0.34) || (y > height * 0.75 && y < height * 0.78)) {
                shiftX += 35;
            }

            for (int x = 0; x < width; x++) {
                double srcX = ((x + shiftX) % width);

                if (srcX < 0) {
                    srcX += width;
                }

                Color color = new Color(image.getRGB((int) srcX, y));
                int a = color.getAlpha();
                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();

                if (shiftX > 15 || shiftX < 15) {
                    r *= 1.2;
                    b *= 1.1;
                    r = clamp(r);
                    b = clamp(b);
                }

                Color finalColor = new Color(r, g, b, a);

                out.setRGB(x, y, finalColor.getRGB());
            }
        }
        return out;
    }
}
