package pixelpro.mutation.transform;

import java.awt.image.BufferedImage;

import pixelpro.mutation.Mutation;

public class ShearXMutation implements Mutation {

    @Override
    public BufferedImage apply(BufferedImage image, int width, int height) {
        double SHEAR_FACTOR = 0.3;
        int newWidth = (int) (width + (SHEAR_FACTOR * height));
        BufferedImage out = new BufferedImage(newWidth, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int newX = (int) (x + SHEAR_FACTOR * y);
                if (newX >= 0 && newX < newWidth) {
                    out.setRGB(newX, y, image.getRGB(x, y));
                }
            }
        }
        return out;
    }
}
