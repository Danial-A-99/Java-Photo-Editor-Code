package pixelpro.mutation.effect;

import java.awt.image.BufferedImage;

import pixelpro.mutation.Mutation;

public class BulgeMutation implements Mutation {

    @Override
    public BufferedImage apply(BufferedImage image, int width, int height) {
        BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        int centerX = width / 2;
        int centerY = height / 2;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int xCartesian = i - centerX;
                int yCartesian = j - centerY;

                double radiusVar = Math.sqrt(Math.pow(xCartesian, 2) + Math.pow(yCartesian, 2));
                double angleTheta = Math.atan2(yCartesian, xCartesian);
                radiusVar = Math.pow(radiusVar, 1.6) / 30;

                int xPrime = (int) (radiusVar * Math.cos(angleTheta)) + centerX;
                int yPrime = (int) (radiusVar * Math.sin(angleTheta)) + centerY;

                if (isInBounds(xPrime, yPrime, width, height)) {
                    out.setRGB(i, j, image.getRGB(xPrime, yPrime));
                }
            }
        }
        return out;
    }
}
