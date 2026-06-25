package pixelpro.mutation.effect;

import java.awt.Color;
import java.awt.image.BufferedImage;

import pixelpro.mutation.Mutation;

public class PixelationMutation implements Mutation {

    @Override
    public BufferedImage apply(BufferedImage image, int width, int height) {
        BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        int blockSize = 10;

        for (int x = 0; x < width; x += 10) {
            for (int y = 0; y < height; y += 10) {
                int xcoord = x + blockSize / 2;
                int ycoord = y + blockSize / 2;

                if (!isInBounds(xcoord, ycoord, width, height)) {
                    xcoord = clamp(xcoord);
                    ycoord = clamp(ycoord);
                }
                Color color = new Color(image.getRGB(xcoord, ycoord));

                for (int bx = 0; bx < 10; bx++) {
                    for (int by = 0; by < 10; by++) {
                        if (isInBounds(x + bx, y + by, width, height)) {
                            out.setRGB(x + bx, y + by, color.getRGB());
                        }
                    }
                }
            }
        }
        return out;
    }

}
