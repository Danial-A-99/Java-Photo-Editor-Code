package pixelpro.mutation.effect;

import java.awt.Color;
import java.awt.image.BufferedImage;

import pixelpro.mutation.Mutation;

public class OilMutation implements Mutation {
    // Credit to fixes - Matthew.B (This code was changed after the summative was seen and graded)
    @Override
    public BufferedImage apply(BufferedImage image, int width, int height) {
        BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        int scanRadius = 4;
        int totalIntensityBins = 20;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int[] intensityCounts = new int[totalIntensityBins];
                int[] totalRed = new int[totalIntensityBins];
                int[] totalGreen = new int[totalIntensityBins];
                int[] totalBlue = new int[totalIntensityBins];

                for (int mi = -scanRadius; mi <= scanRadius; mi++) {
                    for (int mj = -scanRadius; mj <= scanRadius; mj++) {
                        int xcoord = x + mi;
                        int ycoord = y + mj;

                        if (xcoord<0){
                            xcoord = 0;
                        } else if (xcoord >= width){
                            xcoord = width - 1;
                        }

                        if (ycoord<0){
                            ycoord = 0;
                        } else if (ycoord >= height){
                            ycoord = height - 1;
                        }

                        Color color = new Color(image.getRGB(xcoord, ycoord),true);
                        int averageBrightness = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
                        int intensityBin = (averageBrightness * totalIntensityBins)/256;
                        intensityCounts[intensityBin]++;
                        totalRed[intensityBin] += color.getRed();
                        totalGreen[intensityBin] += color.getGreen();
                        totalBlue[intensityBin] += color.getBlue();
                    }
                }
                int dominantBin = 0;
                int highestPixelCount = 0;

                for (int binIndex = 0; binIndex < totalIntensityBins; binIndex++) {
                    if (intensityCounts[binIndex] > highestPixelCount) {
                        highestPixelCount = intensityCounts[binIndex];
                        dominantBin = binIndex;
                    }
                }
                int finalRed = totalRed[dominantBin] / highestPixelCount;
                int finalGreen = totalGreen[dominantBin] / highestPixelCount;
                int finalBlue = totalBlue[dominantBin] / highestPixelCount;
                Color finalColor = new Color(finalRed, finalGreen, finalBlue);
                out.setRGB(x, y, finalColor.getRGB());
            }
        }
        return out;
    }
}
