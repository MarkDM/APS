package utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Text2ImageConverter {

    public void writeImage(String text, BufferedImage img, int x, int y) {
        // BufferedImage bf = new BufferedImage(272, 480, BufferedImage.TYPE_INT_RGB);  
        Graphics graph = img.getGraphics();
        //graph.setColor(new Color(153, 204, 00));
        //graph.fillRect(0, 0, 272, 480);
        graph.setColor(new Color(255, 255, 255));
        Font font = new Font("Trebuchet MS", Font.PLAIN, 18);
        graph.setFont(font);

        //for (int c1 = 0; c1 < text.length; c1++) {  
        graph.drawString(text, x, y);
        //}   
    }

}
