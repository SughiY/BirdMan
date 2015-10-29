package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.StringContent;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by user on 15/10/28.
 */
public class ImageComponent extends JComponent {

    private ImageIcon image;

    public ImageComponent(URL path) {
            image = new ImageIcon(path);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(),null);
    }
}
