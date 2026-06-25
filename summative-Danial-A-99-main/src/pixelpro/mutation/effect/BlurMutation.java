package pixelpro.mutation.effect;

import java.awt.Color;
import java.awt.image.BufferedImage;

import pixelpro.mutation.Mutation;

public class BlurMutation implements Mutation {

    @Override
    public BufferedImage apply(BufferedImage image, int width, int height) {
        BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        int radius = 3;
        int size = radius * 2 + 1;
        double[][] filter = new double[size][size];
        double sigma = 2.0;
        double sum = 0.0;

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                double xDiff = x - radius;
                double yDiff = y - radius;

                filter[x][y] = Math.exp(-(xDiff * xDiff + yDiff * yDiff) / (2 * sigma * sigma));
                sum += filter[x][y];
            }
        }

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                filter[x][y] /= sum;
            }
        }

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double r = 0, g = 0, b = 0;

                for (int fx = 0; fx < size; fx++) {
                    for (int fy = 0; fy < size; fy++) {
                        int pixelX = Math.min(Math.max(x - radius + fx, 0), width - 1);
                        int pixelY = Math.min(Math.max(y - radius + fy, 0), height - 1);
                        Color color = new Color(image.getRGB(pixelX, pixelY), true);
                        double weight = filter[fx][fy];

                        r += color.getRed() * weight;
                        g += color.getGreen() * weight;
                        b += color.getBlue() * weight;
                    }
                }

                Color finalColor = new Color(clamp(r), clamp(g), clamp(b));

                out.setRGB(x, y, finalColor.getRGB());
            }
        }
        return out;
    }
}
