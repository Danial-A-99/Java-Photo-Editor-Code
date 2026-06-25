package pixelpro.mutation.effect;

import java.awt.Color;
import java.awt.image.BufferedImage;

import pixelpro.mutation.Mutation;

public class EdgeMutation implements Mutation {

    @Override
    public BufferedImage apply(BufferedImage image, int width, int height) {
        BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        double SENSITIVITY_MULTIPLIER = 4.0;
        int[][] kernel = { { -1, -1, -1 }, { -1, 8, -1 }, { -1, -1, -1 } };

        for (int x = 1; x < width - 1; x++) {
            for (int y = 1; y < height - 1; y++) {
                double accumulatedRed = 0.0;
                double accumulatedGreen = 0.0;
                double accumulatedBlue = 0.0;

                for (int kx = 0; kx < 3; kx++) {
                    for (int ky = 0; ky < 3; ky++) {
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
                int finalRed = clamp(Math.abs(accumulatedRed) * SENSITIVITY_MULTIPLIER * 255.0);
                int finalGreen = clamp(Math.abs(accumulatedGreen) * SENSITIVITY_MULTIPLIER * 255.0);
                int finalBlue = clamp(Math.abs(accumulatedBlue) * SENSITIVITY_MULTIPLIER * 255.0);
                Color finalColor = new Color(finalRed, finalGreen, finalBlue);

                out.setRGB(x, y, finalColor.getRGB());

            }
        }
        return out;
    }
}
