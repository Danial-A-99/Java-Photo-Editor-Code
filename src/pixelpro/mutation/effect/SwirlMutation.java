package pixelpro.mutation.effect;

import java.awt.image.BufferedImage;

import pixelpro.mutation.Mutation;

public class SwirlMutation implements Mutation {

    @Override
    public BufferedImage apply(BufferedImage image, int width, int height) {
        BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        double centerX = width / 2.0;
        double centerY = height / 2.0;
        int maxSwirlRadius = (centerX > centerY) ? (int) centerY : (int) centerX;
        double TWIST_STRENGTH = 0.008;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double dx = x - centerX;
                double dy = y - centerY;
                double straightDistance = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

                if (straightDistance < maxSwirlRadius) {
                    double angle = Math.atan2(dy, dx);
                    angle += TWIST_STRENGTH * (straightDistance - maxSwirlRadius);
                    int srcX = (int) (centerX + straightDistance * Math.cos(angle));
                    int srcY = (int) (centerY + straightDistance * Math.sin(angle));
                    out.setRGB(x, y, image.getRGB(srcX, srcY));
                } else {
                    out.setRGB(x, y, image.getRGB(x, y));
                }

            }
        }
        return out;
    }

}
