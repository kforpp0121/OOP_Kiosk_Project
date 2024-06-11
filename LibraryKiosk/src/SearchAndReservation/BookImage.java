package SearchAndReservation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BookImage extends JPanel {
    private BufferedImage image;

    public BookImage(String imagePath) {
        if (imagePath != null && !imagePath.isEmpty()) {
            try {
                imagePath = imagePath.replace("\"", "");
                image = ImageIO.read(new File("LibraryKiosk/bookcover/" + imagePath));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            // 기본 이미지 설정 (기본 이미지를 사용할 경우)
            try {
                image = ImageIO.read(new File("LibraryKiosk/bookcover/symbol_Silver.jpeg"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            int panelWidth = getWidth();
            int panelHeight = getHeight();
            Image scaledImage = image.getScaledInstance(panelWidth, panelHeight, Image.SCALE_SMOOTH);
            g.drawImage(scaledImage, 0, 0, this);
        }
    }
}
