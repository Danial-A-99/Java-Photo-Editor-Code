package pixelpro.mutation.transform;

import java.awt.image.BufferedImage;

import pixelpro.mutation.Mutation;

public class RotateYMutation implements Mutation {

    @Override
    public BufferedImage apply(BufferedImage image, int width, int height) {
        BufferedImage out = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                out.setRGB(height - y - 1, x, image.getRGB(x, y));
            }
        }
        return out;
    }
}
