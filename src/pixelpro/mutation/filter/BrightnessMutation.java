package pixelpro.mutation.filter;

import java.awt.Color;
import java.awt.image.BufferedImage;

import pixelpro.mutation.Mutation;

public class BrightnessMutation implements Mutation {

    @Override
    public BufferedImage apply(BufferedImage image, int width, int height) {
        BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        double scale = 1.2;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(image.getRGB(x, y), true);
                int a = color.getAlpha();
                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();
                int cr = clamp(r * scale);
                int cg = clamp(g * scale);
                int cb = clamp(b * scale);
                Color finalColor = new Color(cr, cg, cb, a);

                out.setRGB(x, y, finalColor.getRGB());
            }
        }
        return out;
    }
}
