package pixelpro.mutation.effect;

import java.awt.Color;
import java.awt.image.BufferedImage;

import pixelpro.mutation.Mutation;

public class NeonMutation implements Mutation {

    @Override
    public BufferedImage apply(BufferedImage image, int width, int height) {
        BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        int[][] gx = { { -1, 0, 1 }, { -2, 0, 2 }, { -1, 0, 1 } };
        int[][] gy = { { -1, -2, -1 }, { 0, 0, 0 }, { 1, 2, 1 } };

        for (int x = 1; x < width - 2; x++) {
            for (int y = 1; y < height - 2; y++) {
                double accumulatedHorizontalStrength = 0.0;
                double accumulatedVerticalStrength = 0.0;

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        Color color = new Color(image.getRGB(x + i, y + j));
                        double grayScale = color.getRed() * 0.3 + color.getGreen() * 0.59 + color.getBlue() * 0.11;
                        accumulatedHorizontalStrength += grayScale * gx[i][j];
                        accumulatedVerticalStrength += grayScale * gy[i][j];
                    }
                }
                double magnitude = Math.sqrt(Math.pow(accumulatedHorizontalStrength, 2) + Math.pow(accumulatedVerticalStrength, 2));
                magnitude /= 255;
                double newRed = ((Math.sin(x * 0.005 + y * 0.002) + 1) / 2) * 255;
                double newGreen = ((Math.cos(y * 0.006 - x * 0.003) + 1) / 2) * 200;
                double newBlue = ((Math.sin(x * 0.002) + 1) / 2) * 255;
                newRed = clamp(newRed);
                newGreen = clamp(newGreen);
                newBlue = clamp(newBlue);

                if (magnitude > -0.15) {
                    newRed *= magnitude * 1.3;
                    newGreen *= magnitude * 1.3;
                    newBlue *= magnitude * 1.3;
                    newRed = clamp(newRed);
                    newGreen = clamp(newGreen);
                    newBlue = clamp(newBlue);
                } else {
                    newRed /= 8;
                    newGreen /= 8;
                    newBlue /= 8;
                }

                Color finalColor = new Color((int) newRed, (int) newGreen, (int) newBlue);
                out.setRGB(x, y, finalColor.getRGB());

            }
        }
        return out;
    }
}
