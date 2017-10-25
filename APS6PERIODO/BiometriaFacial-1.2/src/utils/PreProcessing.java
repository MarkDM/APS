/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.image.BufferedImage;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author marcu
 */
public class PreProcessing {

    public BufferedImage enhance(BufferedImage img) {
        Utils ut = new Utils();

        ut.Img2GrayScale(img);
        Mat source = ut.bufferedImageToMat(img);

        Mat destination = new Mat(source.rows(), source.cols(), source.type());

        //Melhora constraste
        //Imgproc.equalizeHist(source, destination);
        //Melhora brilho
        source.convertTo(destination, -1, 2, 60);

        //Suavização
       // Imgproc.GaussianBlur(source, destination, new Size(11, 11), 0);

        //Melhora a definição
        Imgproc.GaussianBlur(source, destination, new Size(0, 0), 10);
        Core.addWeighted(source, 1.8, destination, -0.8, 0, destination);
        //return ut.convertMatToImage(destination);
        BufferedImage imgRetorno = ut.matToBufferedImage(destination);
        return imgRetorno;
    }

}
