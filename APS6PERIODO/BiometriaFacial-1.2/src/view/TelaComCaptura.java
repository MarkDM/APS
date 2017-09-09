/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
import utils.PGMConverter;
import utils.Utils;

/**
 *
 * @author marcu
 */
public abstract class TelaComCaptura extends javax.swing.JFrame {

    private BufferedImage frameAtual;
    private List<String> listPgmTrain = new ArrayList<String>();
    private String mensagem;
    private String cascadeFile;

    String localPath;

    public void launch() {
        localPath = new File("").getAbsolutePath();
        System.load(localPath + "\\src\\nativeLibrary\\opencv_java300.dll");
    }

    public BufferedImage getFrameAtual() {
        return frameAtual;
    }

    public String getMensagem() {
        return mensagem;
    }

    private void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    private void setFrameAtual(BufferedImage frameAtual) {
        this.frameAtual = frameAtual;
    }

    public List<String> getListPgmTrain() {
        return listPgmTrain;
    }

    public void mostraVideo(JPanel containerVideo) {

        Utils ut = new Utils();
        Graphics g = containerVideo.getGraphics();
        //Matriz que contem os dados da imagem
        Mat frame = new Mat();
        VideoCapture captura = new VideoCapture(0);
        String cascadeFile = localPath + "\\src\\resources\\cascades\\haarcascade_frontalface_alt.xml";
        //String cascadePath = cascadeFile;
        CascadeClassifier classificador = new CascadeClassifier(cascadeFile);
        MatOfRect facesDetectadas = new MatOfRect();
        double scaleFactor = 1.1;
        int minNeighbors = 2;
        int flags = 0;
        Size minSize = new Size(30, 30);
        Size maxSize = new Size(500, 500);
        Scalar cor = new Scalar(0, 255, 0);
        int larguraPanel = containerVideo.getWidth();
        int alturaPanel = containerVideo.getHeight();

        while (true) {
            if (captura.isOpened()) {
                //Captura um frame
                captura.read(frame);
                if (!frame.empty()) {
                    setSize(frame.width(), frame.height());
                    Mat imagemColorida = frame;
                    Mat imagemCinza = new Mat();
                    Imgproc.cvtColor(imagemColorida, imagemCinza, Imgproc.COLOR_BGR2GRAY);
                    //Detecta faces
                    classificador.detectMultiScale(imagemCinza, facesDetectadas, scaleFactor, minNeighbors, flags, minSize, maxSize);
                    Rect[] faces = facesDetectadas.toArray();
                    for (int i = 0; i < faces.length; i++) {
                        Imgproc.rectangle(frame, faces[i].tl(), faces[i].br(), cor, 3);
                    }
                    //BufferedImage imagem = new Utils().convertMatToImage(frame);
                    BufferedImage imagem = ut.matToBufferedImage(frame);
                    this.setFrameAtual(imagem);
                    g.drawImage(imagem, 0, 0, larguraPanel, alturaPanel, null);
                    imagemCinza = null;
                }
            }
        }
    }

    public String salvarPgm(String path) {
        PGMConverter converter = new PGMConverter();

        try {
            if (converter.write2pgm(this.getFrameAtual(), path)) {

                listPgmTrain.add(path);

                //gridModel.addRow(new Object[]{new ImageIcon(frameAtual)});
                return path;
            } else {
                return "";
            }
        } catch (Exception e) {
            setMensagem(converter.getMensagem() == null ? e.getMessage() : converter.getMensagem());
        }
        return "";

    }

    public BufferedImage salvarPgmGetImage(String path) {
        PGMConverter converter = new PGMConverter();
        try {
            if (converter.write2pgm(this.getFrameAtual(), path)) {

                listPgmTrain.add(path);

                //gridModel.addRow(new Object[]{new ImageIcon(frameAtual)});
                return this.getFrameAtual();
            } else {
                return null;
            }
        } catch (Exception e) {
            setMensagem(converter.getMensagem() == null ? e.getMessage() : converter.getMensagem());
        }

        return null;

    }
}
