package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.StringContent;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by user on 15/10/28.
 */
public class ImageComponent extends JComponent {

    private BufferedImage image;

    public ImageComponent(String path) {
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException ex) {
            // handle exception...
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }
}
