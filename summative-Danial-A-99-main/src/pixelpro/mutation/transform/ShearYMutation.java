package pixelpro.mutation.transform;

import java.awt.image.BufferedImage;

import pixelpro.mutation.Mutation;

public class ShearYMutation implements Mutation {

    @Override
    public BufferedImage apply(BufferedImage image, int width, int height) {
        double SHEAR_FACTOR = 0.3;
        int newHeight = (int) (height + (SHEAR_FACTOR * width));
        BufferedImage out = new BufferedImage(width, newHeight, BufferedImage.TYPE_INT_ARGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int newY = (int) (y + SHEAR_FACTOR * x);
                if (newY >= 0 && newY < newHeight) {
                    out.setRGB(x, newY, image.getRGB(x, y));
                }
            }
        }
        return out;
    }
}
