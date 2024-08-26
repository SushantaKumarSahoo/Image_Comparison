import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {
    public static void main(String[] args) {
        // Load the two images
        //image2 and image3 are same images. and image1 is different
        BufferedImage image1 = loadImage("image2.jpg");
        BufferedImage image2 = loadImage("image3.jpg");

        // Get the dimensions of the images
        int width = image1.getWidth();
        int height = image1.getHeight();

        // Create a 2D array to store the pixel values of each image
        int[][][] pixels1 = new int[width][height][3];
        int[][][] pixels2 = new int[width][height][3];

        // Get the pixel values of each image
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int rgb1 = image1.getRGB(x, y);
                int rgb2 = image2.getRGB(x, y);

                // Extract the red, green, and blue components of each pixel
                pixels1[x][y][0] = (rgb1 >> 16) & 0xFF;
                pixels1[x][y][1] = (rgb1 >> 8) & 0xFF;
                pixels1[x][y][2] = rgb1 & 0xFF;

                pixels2[x][y][0] = (rgb2 >> 16) & 0xFF;
                pixels2[x][y][1] = (rgb2 >> 8) & 0xFF;
                pixels2[x][y][2] = rgb2 & 0xFF;
            }
        }

        // Compare the pixel values of the two images
        boolean identical = true;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (pixels1[x][y][0] != pixels2[x][y][0] ||
                    pixels1[x][y][1] != pixels2[x][y][1] ||
                    pixels1[x][y][2] != pixels2[x][y][2]) {
                    identical = false;
                    break;
                }
            }
            if (!identical) break;
        }

        // Print the result
        if (identical) {
            System.out.println("The two images are identical.");
        } else {
            System.out.println("The two images are not identical.");
        }
    }

    private static BufferedImage loadImage(String filename) {
        try {
            return ImageIO.read(new File(filename));
        } catch (IOException e) {
            System.err.println("Error loading image: " + e.getMessage());
            return null;
        }
    }
}
