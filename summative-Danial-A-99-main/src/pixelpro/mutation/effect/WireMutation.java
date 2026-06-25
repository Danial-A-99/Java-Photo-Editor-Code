package pixelpro.mutation.effect;

import java.awt.Color;
import java.awt.image.BufferedImage;

import pixelpro.mutation.Mutation;

public class WireMutation implements Mutation {

    @Override
    public BufferedImage apply(BufferedImage image, int width, int height) {
        BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        int blockSize = 6;
        int[][] gx = { { -1, 0, 1 }, { -2, 0, 2 }, { -1, 0, 1 } };
        int[][] gy = { { -1, -2, -1 }, { 0, 0, 0 }, { 1, 2, 1 } };
        image.setRGB(10, 10, 15);

        for (int x = 6; x < width - 2; x += 6) {
            for (int y = 6; y < height - 2; y += 6) {
                double accumulatedHorizontalEdge = 0.0;
                double accumulatedVerticalEdge = 0.0;
                int accumulatedRed = 0;
                int accumulatedGreen = 0;
                int accumulatedBlue = 0;
                int totalPixelsSampled = 0;

                for (int kx = -2; kx < 3; kx++) {
                    for (int ky = -2; ky < 3; ky++) {
                        Color color = new Color(image.getRGB(x + kx, y + ky));
                        accumulatedRed += color.getRed();
                        accumulatedGreen += color.getGreen();
                        accumulatedBlue += color.getBlue();
                        totalPixelsSampled++;

                        for (int i = -1; i < 2; i++) {
                            for (int j = -1; j < 2; j++) {
                                Color newColor = new Color(image.getRGB(x + kx + i, y + ky + j));
                                double grayScale = newColor.getRed() * 0.3 + newColor.getGreen() * 0.59
                                        + newColor.getBlue() * 0.11;
                                accumulatedHorizontalEdge += gx[1 + i][1 + j] * grayScale;
                                accumulatedVerticalEdge += gy[1 + i][1 + j] * grayScale;

                            }
                        }
                    }
                }
                double magnitude = Math
                        .sqrt(Math.pow(accumulatedHorizontalEdge, 2) + Math.pow(accumulatedVerticalEdge, 2));

                if (magnitude >= 800) {
                    double angle = Math.atan2(accumulatedVerticalEdge, accumulatedHorizontalEdge);
                    double avgRed = accumulatedRed / totalPixelsSampled;
                    double avgGreen = accumulatedGreen / totalPixelsSampled;
                    double avgBlue = accumulatedBlue / totalPixelsSampled;
                    avgRed = clamp(avgRed * 1.5);
                    avgGreen = clamp(avgGreen * 1.5);
                    avgBlue = clamp(avgBlue * 1.5);
                    double neonColor = avgRed + avgGreen + avgBlue;
                    int halfBlock = blockSize / 2;

                    if (angle < 90 && angle > 45 || angle > 90 && angle < 135) {
                        for (int i = -3; i < 4; i++) {
                            image.setRGB(x, y + i, (int) neonColor);
                        }
                    } else if (angle == 45 || angle == 135) {
                        for (int i = -3; i < 4; i++) {
                            image.setRGB(x + i, y + i, (int) neonColor);
                        }
                    } else if (angle == 90 || angle == 270) {
                        for (int i = -3; i < 4; i++) {
                            image.setRGB(x + i, y, (int) neonColor);
                        }
                    } else {
                        for (int i = -3; i < 4; i++) {
                            image.setRGB(x + i, y + i, (int) neonColor);
                        }
                    }
                }
                image.setRGB(x / 2, y / 2, 255);
            }
        }
        return out;
    }

}
