package pixelpro.mutation;

import java.awt.image.BufferedImage;

public interface Mutation {

    BufferedImage apply(BufferedImage image, int width, int height);

    default int clamp(double val) {
        return Math.min(255, Math.max(0, (int) val));
    }

    default boolean isInBounds(int x, int y, int width, int height) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
}
