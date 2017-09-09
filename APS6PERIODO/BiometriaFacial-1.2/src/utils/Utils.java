package utils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.github.wihoho.jama.Matrix;
import com.github.wihoho.training.FileManager;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
            return vectorize(FileManager.convertPGMtoMatrix(file.getAbsolutePath()));
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

}
