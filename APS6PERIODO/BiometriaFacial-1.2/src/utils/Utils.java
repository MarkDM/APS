package utils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.github.wihoho.jama.Matrix;
import com.github.wihoho.training.FileManager;
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
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import static org.opencv.imgcodecs.Imgcodecs.imread;

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
        Mat imagemColorida = imread(path, type);
        return imagemColorida;
    }

    public Matrix convertToMatrix(String fileAddress) {
        //File file = new File(classLoader.getResource(fileAddress).getFile());
        //File file = new File(classLoader.getResource("/" + fileAddress).getFile());
        //String relativeAdress = "src/main/java/main/" + fileAddress;

        File file = new File(fileAddress);

        if (!file.exists()) {
            System.out.println("Arquivo: " + fileAddress + " não encontrado");
            return null;
        }

        try {
            //return vectorize(FileManager.convertPGMtoMatrix(file.getAbsolutePath()));
            return vectorize(convertPGMtoMatrix(file.getAbsolutePath()));
        } catch (Exception e) {
            setMensagemErro("Erro ao converter PGM para matrix");
        }

        return null;
    }

    // Convert a m by n matrix into a m*n by 1 matrix
    private Matrix vectorize(Matrix input) {
        int m = input.getRowDimension();
        int n = input.getColumnDimension();

        Matrix result = new Matrix(m * n, 1);
        for (int p = 0; p < n; p++) {
            for (int q = 0; q < m; q++) {
                result.set(p * m + q, 0, input.get(q, p));
            }
        }
        return result;
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

    public Matrix convertPGMtoMatrix(String address) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(address);
        Scanner scan = new Scanner(fileInputStream);

        // Discard the magic number
        scan.nextLine();
        //Descarta comentário criado pela Biblioteca de geração do arquivo PGM
        scan.nextLine();
        // Read pic width, height and max value
        int picWidth = scan.nextInt();

        //String line = scan.nextLine();
        //String StrPicWidth = scan.next();
        //int picWidth = Integer.parseInt(StrPicWidth);
        int picHeight = scan.nextInt();

        fileInputStream.close();

        // Now parse the file as binary data
        fileInputStream = new FileInputStream(address);
        DataInputStream dis = new DataInputStream(fileInputStream);

        // look for 4 lines (i.e.: the header) and discard them
        int numnewlines = 3;
        while (numnewlines > 0) {
            char c;
            do {
                c = (char) (dis.readUnsignedByte());
            } while (c != '\n');
            numnewlines--;
        }

        // read the image data
        double[][] data2D = new double[picHeight][picWidth];
        for (int row = 0; row < picHeight; row++) {
            for (int col = 0; col < picWidth; col++) {

                try {
                    data2D[row][col] = dis.readUnsignedByte();
                } catch (Exception e) {
                    mensagemErro = "Erro ao ler byte na posição: linha: " + row + " coluna: " + col;
                }
            }
        }

        return new Matrix(data2D);
    }

}
