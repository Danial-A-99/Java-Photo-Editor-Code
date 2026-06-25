package pixelpro.mutation.transform;

import java.awt.image.BufferedImage;

import pixelpro.mutation.Mutation;

public class ZoomOutMutation implements Mutation {

    @Override
    public BufferedImage apply(BufferedImage image, int width, int height) {
        BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        double SCALE_FACTOR = 0.6;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double halfWayX = width / 2;
                double halfWayY = height / 2;
                int sourceX = (int) ((x - halfWayX) / SCALE_FACTOR + halfWayX);
                int sourceY = (int) ((y - halfWayY) / SCALE_FACTOR + halfWayY);
                if (isInBounds(sourceX, sourceY, width, height)) {
                    out.setRGB(x, y, image.getRGB(sourceX, sourceY));
                }
            }
        }
        return out;
    }
}
