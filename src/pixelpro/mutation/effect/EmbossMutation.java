package pixelpro.mutation.effect;

import java.awt.Color;
import java.awt.image.BufferedImage;

import pixelpro.mutation.Mutation;

public class EmbossMutation implements Mutation {

    @Override
    public BufferedImage apply(BufferedImage image, int width, int height) {
        BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        int KERNEL_SIZE = 3;
        int[][] kernel = { { -2, -1, 0 }, { -1, 1, 1 }, { 0, 1, 2 } };

        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                double accumulatedRed = 0.0;
                double accumulatedGreen = 0.0;
                double accumulatedBlue = 0.0;

                for (int ky = 0; ky < KERNEL_SIZE; ky++) {
                    for (int kx = 0; kx < KERNEL_SIZE; kx++) {
                        Color color = new Color(image.getRGB(x + kx - 1, y + ky - 1));
                        double theVal = kernel[kx][ky];
                        double redVal = color.getRed() / 255.0 * theVal;
                        double greenVal = color.getGreen() / 255.0 * theVal;
                        double blueVal = color.getBlue() / 255.0 * theVal;
                        accumulatedRed += redVal;
                        accumulatedGreen += greenVal;
                        accumulatedBlue += blueVal;
                    }
                }
                int finalRed = clamp(accumulatedRed * 255);
                int finalGreen = clamp(accumulatedGreen * 255);
                int finalBlue = clamp(accumulatedBlue * 255);
                Color finalColor = new Color(finalRed, finalGreen, finalBlue);

                out.setRGB(x, y, finalColor.getRGB());
            }
        }
        return out;
    }
}
