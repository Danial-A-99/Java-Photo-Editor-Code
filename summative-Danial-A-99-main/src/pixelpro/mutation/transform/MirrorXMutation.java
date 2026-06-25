package pixelpro.mutation.transform;

import java.awt.image.BufferedImage;

import pixelpro.mutation.Mutation;

public class MirrorXMutation implements Mutation {

    @Override
    public BufferedImage apply(BufferedImage image, int width, int height) {
        BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                out.setRGB(width - x - 1, y, image.getRGB(x, y));
            }
        }
        return out;
    }
}
