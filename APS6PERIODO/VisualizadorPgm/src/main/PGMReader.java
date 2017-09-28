/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

/**
 *
 * @author marcu
 */
public class PGMReader {

    public Mat readImage(String path) {
        FileInputStream fin;
        int cols = 0;
        int rows = 0;
        int maxgray = 0;
        int pixels[][];

        try {
            fin = new FileInputStream(path);

            int tr, tc, c;
            String tstr;

            //read first line of ImageHeader
            tstr = "";
            c = fin.read();
            tstr += (char) c;
            c = fin.read();
            tstr += (char) c;
            //type = tstr;

            //read second line of ImageHeader
            c = fin.read(); //read Lf (linefeed)

            while (c == 10 || c == 13) {
                c = fin.read();
            }

            //c = fin.read(); //read '#'
            tstr = "";
            boolean iscomment = false;
            while ((char) c == '#') //read comment
            {
                iscomment = true;
                tstr += (char) c;
                while (c != 10 && c != 13) {
                    c = fin.read();
                    tstr += (char) c;
                }
                c = fin.read(); //read next '#'
            }
            //comment = tstr;

            //read third line of ImageHeader
            //read cols
            if (iscomment == true) {
                c = fin.read();
                tstr = "";
                //tstr += (char) c;
            }

            tstr += (char) c;

            while (c != 32 && c != 10 && c != 13) {
                c = fin.read();
                tstr += (char) c;
            }
            tstr = tstr.substring(0, tstr.length() - 1);
            cols = Integer.parseInt(tstr);

            //read rows
            c = fin.read();

            tstr = "";
            tstr += (char) c;
            while (c != 32 && c != 10 && c != 13) {
                c = fin.read();
                tstr += (char) c;
            }
            tstr = tstr.substring(0, tstr.length() - 1);
            rows = Integer.parseInt(tstr);

            //read maxgray
            c = fin.read();

            while (c == 10 || c == 13) {
                c = fin.read();
            }

            tstr = "";
            tstr += (char) c;
            while (c != 32 && c != 10 && c != 13) {
                c = fin.read();
                tstr += (char) c;
            }
            tstr = tstr.substring(0, tstr.length() - 1);
            maxgray = Integer.parseInt(tstr);

            //read pixels from ImageData
            pixels = new int[rows][cols];
            for (tr = 0; tr < rows; tr++) {
                for (tc = 0; tc < cols; tc++) {
                    c = (int) fin.read();
                    //setPixel(tr, tc, c);
                    pixels[tr][tc] = c;
                }
            }

            Mat mat = new Mat(rows, cols, CvType.CV_8UC1);
            //byte[] data = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
            for (int row = 0; row < rows; row++) {

                for (int col = 0; col < cols; col++) {
                    mat.put(row, col, pixels[row][col]);
                }

            }

            fin.close();

            //mat.put(0, 0, data);
            return mat;

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return null;
    }

    public BufferedImage matToBufferedImage(Mat original) {
        // init
        BufferedImage image = null;
        int width = original.width();
        int height = original.height();
        int channels = original.channels();

        byte[] sourcePixels = new byte[width * height * channels];
        original.get(0, 0, sourcePixels);

        if (original.channels() > 1) {
            image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        } else {
            image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        }
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(sourcePixels, 0, targetPixels, 0, sourcePixels.length);

        return image;
    }

}
