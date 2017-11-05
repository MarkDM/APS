package utils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//import com.github.wihoho.jama.Matrix;
//import com.github.wihoho.training.FileManager;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import net.coobird.thumbnailator.Thumbnails;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import static org.opencv.imgcodecs.Imgcodecs.imread;
import java.security.*;
import java.math.*;
import javax.swing.JOptionPane;
import view.TelaComCaptura;

/**
 *
 * @author marcu
 */
public class Utils {

    private String mensagemErro;

    public String getMensagemErro() {
        return mensagemErro;
    }

    private void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }

    public BufferedImage convertMatToImage(Mat mat) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (mat.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }

        int bufferSize = mat.channels() * mat.cols() * mat.rows();
        byte[] bytes = new byte[bufferSize];
        mat.get(0, 0, bytes);
        BufferedImage imagem = new BufferedImage(mat.cols(), mat.rows(), type);
        byte[] targetPixels = ((DataBufferByte) imagem.getRaster().getDataBuffer()).getData();
        System.arraycopy(bytes, 0, targetPixels, 0, bytes.length);
        return imagem;
    }

    public BufferedImage matToBufferedImage(Mat original) {
        // init
        BufferedImage image = null;
        int width = original.width(), height = original.height(), channels = original.channels();
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

    public void mostraImagem(BufferedImage imagem) {
        ImageIcon icon = new ImageIcon(imagem);
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(imagem.getWidth(), imagem.getHeight());
        JLabel lbl = new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void loadOpenCV() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public Mat carregarImgMat(String path, int type) {

        if (!new File(path).exists()) {
            System.out.println("carregarImgMat(): Arquivo informado não existe");
            return null;
        }

        try {
            Mat imagemColorida = imread(path, type);
            return imagemColorida;
        } catch (Exception e) {
            System.out.println("Erro ao carregar imagem: " + e.getMessage());
            return null;
        }

    }

//    public BufferedImage resize(BufferedImage img, int newW, int newH) {
//        try {
//            Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
//            BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
//
//            Graphics2D g2d = dimg.createGraphics();
//            g2d.drawImage(tmp, 0, 0, null);
//            g2d.dispose();
//
//            return dimg;
//        } catch (Exception e) {
//            System.out.println("Erro ao redimensionar imagem " + e.getMessage());
//        }
//        return null;
//    }
    public BufferedImage resize(BufferedImage img, int newW, int newH) {
        try {
            BufferedImage thumbnail = Thumbnails.of(img)
                    .size(newW, newH)
                    .asBufferedImage();

            return thumbnail;

        } catch (IOException ex) {
            System.out.println("Erro ao redimensionar imagem " + ex.getMessage());
        }

        return null;
    }

    public Mat bufferedImageToMat(BufferedImage bi) {
        Mat mat = new Mat(bi.getHeight(), bi.getWidth(), CvType.CV_8UC3);
        byte[] data = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
        mat.put(0, 0, data);
        return mat;
    }

    public void Img2GrayScale(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        for (int i = 0; i < height; i++) {

            for (int j = 0; j < width; j++) {

                Color c = new Color(image.getRGB(j, i));
                int red = (int) (c.getRed() * 0.299);
                int green = (int) (c.getGreen() * 0.587);
                int blue = (int) (c.getBlue() * 0.114);
                Color newColor = new Color(red + green + blue,
                        red + green + blue, red + green + blue);

                image.setRGB(j, i, newColor.getRGB());
            }
        }

    }

    public String getMd5(String plainText) {

        MessageDigest m;
        try {
            m = MessageDigest.getInstance("MD5");
            m.update(plainText.getBytes(), 0, plainText.length());
            return new BigInteger(1, m.digest()).toString(16);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "";

    }

    public static void msg(String msg, String title, int messageType) {
        JOptionPane.showMessageDialog(null, msg, title, messageType);
    }

    public static void msgErro(String msg, String title) {
        msg(msg, title, JOptionPane.ERROR_MESSAGE);
    }

}
