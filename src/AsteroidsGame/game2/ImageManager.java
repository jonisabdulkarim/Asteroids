package AsteroidsGame.game2;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

class ImageManager {

    private final static String path = "images/";
    private final static String ext = ".png";

    private static Map<String, Image> images = new HashMap<>();

    static Image loadImage(String fname) throws IOException {
        BufferedImage img;
        img = ImageIO.read(new File(path + fname + ext));
        images.put(fname, img);
        return img;
    }

}
