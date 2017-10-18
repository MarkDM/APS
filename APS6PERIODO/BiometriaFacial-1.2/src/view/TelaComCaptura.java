/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static java.awt.image.ImageObserver.HEIGHT;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.opencv.core.CvType;
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
    private boolean salvandoPGM = false;
    private String mensagem;
    //private List<BufferedImage> lstFacesRecortadas = new ArrayList<>();
    private List<Mat> lstFacesRecortadas = new ArrayList<>();
    private List<String> pessoasReconhecidas = new ArrayList<>();
    private VideoCapture captura;

    String localPath;

    public void launch() {
        localPath = new File("").getAbsolutePath();
        System.load(localPath + "\\src\\nativeLibrary\\opencv_java300.dll");
    }

    public BufferedImage getFrameAtual() {
        return frameAtual;
    }

    public boolean isSalvandoPGM() {
        return salvandoPGM;
    }

    public void OpenCapture() {
        captura.open(0);
    }

    public void StopCapture() {
        captura.release();
    }

    public void setSalvandoPGM(boolean salvandoPGM) {
        this.salvandoPGM = salvandoPGM;
    }

    public String getMensagem() {
        return mensagem;
    }

    public List<String> getPessoasReconhecidas() {
        return pessoasReconhecidas;
    }

    public void setPessoasReconhecidas(List<String> pessoasReconhecidas) {
        this.pessoasReconhecidas = pessoasReconhecidas;
    }

    public void add2PessoasReconhecidas(String id) {
        this.pessoasReconhecidas.add(id);
    }

//    public List<BufferedImage> getFacesRecortadas() {
//
//        List<BufferedImage> listaRetorno = new ArrayList<>();
//
//        for (BufferedImage bi : lstFacesRecortadas) {
//            listaRetorno.add(bi);
//        }
//
//        return listaRetorno;
//    }
    public List<Mat> getFacesRecortadas() {

        List<Mat> listaRetorno = new ArrayList<>();

        for (Mat bi : lstFacesRecortadas) {
            listaRetorno.add(bi);
        }

        return listaRetorno;
    }

    private void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    private void setFrameAtual(BufferedImage frameAtual) {
        this.frameAtual = frameAtual;
    }

    public void mostraVideo(JPanel containerVideo) {

        Utils ut = new Utils();
        Graphics g = containerVideo.getGraphics();
        //Matriz que contem os dados da imagem
        Mat frame = new Mat();
        Mat frameSemRetangulo = new Mat();
        captura = new VideoCapture(0);
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
        int thickness = 3;

        while (true) {

//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(TelaComCaptura.class.getName()).log(Level.SEVERE, null, ex);
//            }
            if (captura.isOpened()) {
                //Captura um frame
                captura.read(frame);
                if (!frame.empty()) {
                    // setSize(frame.width(), frame.height());
                    Mat imagemColorida = frame;
                    Mat imagemCinza = new Mat();
                    Imgproc.cvtColor(imagemColorida, imagemCinza, Imgproc.COLOR_BGR2GRAY);

                    //Detecta faces
                    classificador.detectMultiScale(imagemCinza, facesDetectadas, scaleFactor, minNeighbors, flags, minSize, maxSize);
                    Rect[] faces = facesDetectadas.toArray();
                    frame.copyTo(frameSemRetangulo);
                    Rect faceRecortada = null;

                    if (lstFacesRecortadas.size() > faces.length) {
                        lstFacesRecortadas.clear();
                    }

                    for (int i = 0; i < faces.length; i++) {
                        Imgproc.rectangle(frame, faces[i].tl(), faces[i].br(), cor, thickness);

                        int larguraFace = (int) (faces[i].width * 0.8f);
                        int alturaFace = (int) (faces[i].height * 0.8f);

                        faceRecortada = new Rect(faces[i].x + 20, faces[i].y + 20, larguraFace, alturaFace);
                        // faceRecortada = new Rect(faces[i].x, faces[i].y, 150, 160);
                        if (faceRecortada != null) {
                            // if (lstFacesRecortadas.size() < faces.length) {
                            //System.out.println("Pessoa entrou da captura");

                            try {
                                //lstFacesRecortadas.add(ut.matToBufferedImage(new Mat(frameSemRetangulo, faceRecortada)));
                                lstFacesRecortadas.add(new Mat(frameSemRetangulo, faceRecortada));
                            } catch (Exception e) {
                            }

//                            } else if (lstFacesRecortadas.size() > faces.length) {
//                                //System.out.println("Pessoa saiu da captura");
//                                lstFacesRecortadas.clear();
//                                lstFacesRecortadas.add(ut.matToBufferedImage(new Mat(frameSemRetangulo, faceRecortada)));
//                            }
                        }

                    }

                    BufferedImage frameLimpo = ut.matToBufferedImage(frameSemRetangulo);
                    BufferedImage frameComRetangulos = ut.matToBufferedImage(frame);

                    this.setFrameAtual(frameLimpo);

                    g.drawImage(frameComRetangulos, 0, 0, larguraPanel, alturaPanel, null);

                    //Limpa variáveis
                    imagemCinza = null;
                    faceRecortada = null;
                    frameLimpo = null;
                    if (!isSalvandoPGM()) {
                        //lstFacesRecortadas.clear();
                    }

                    frameComRetangulos = null;
                }
            }
        }
    }

    /**
     * *
     * Salva a imagem como .pgm no caminho informado e e retorna uma imagem
     * redimensionada
     *
     * @param path
     * @param img
     * @return BufferedImage
     */
    public String salvarPgm(String path, BufferedImage img) {
        PGMConverter converter = new PGMConverter();

        setSalvandoPGM(true);

        try {
            // BufferedImage resized = new Utils().resize(img, 120, 120);
            //String path = localPath + "\\src\\resources\\pgmTrainer\\" + identificador + getListPgmTrain().size() + ".pgm";
            if (converter.write2pgm(img, path)) {
                //getListPgmTrain().add(path);

                return path;
            } else {

                return null;
            }
        } catch (Exception e) {

            setMensagem(converter.getMensagem() == null ? e.getMessage() : converter.getMensagem());
        }

        return null;

//        if (this.getFaceRecortadaAtual() == null) {
//            return null;
//        }
    }

    public String salvarPgm2(String path, Mat mat) {
        FileOutputStream fout;

        int pixels[][] = new int[mat.rows()][mat.cols()];

        try {
            fout = new FileOutputStream(path);

            //write image header
            //write PGM magic value 'P5'
            String tstr;
            tstr = "P5" + "\n";
            fout.write(tstr.getBytes());

            //write comment
            // comment = comment + "\n";
            //fout.write(comment.getBytes());
            //write cols
            tstr = Integer.toString(mat.cols());
            fout.write(tstr.getBytes());
            fout.write(32); //write blank space

            //write rows
            tstr = Integer.toString(mat.rows());
            fout.write(tstr.getBytes());
            fout.write(32); //write blank space

            //write maxgray
            tstr = Integer.toString(255);
            tstr = tstr + "\n";
            fout.write(tstr.getBytes());

            for (int r = 0; r < mat.rows(); r++) {
                for (int c = 0; c < mat.cols(); c++) {

                    double[] pixelsRgb = mat.get(r, c);

                    int red = (int) (pixelsRgb[0] * 0.299);
                    int green = (int) (pixelsRgb[1] * 0.587);
                    int blue = (int) (pixelsRgb[1] * 0.114);

                    Color newColor = new Color(red + green + blue,
                            red + green + blue, red + green + blue);

                    //int pixel = (int) (pixelsRgb[0] + pixelsRgb[1] + pixelsRgb[1]);
                    //pixel = pixel / 3;
                    fout.write(newColor.getRGB());

                    // fout.write((int) mat.get(r, c)[0]);
                }
            }

            fout.close();
            return path;
        } catch (Exception e) {
            System.out.println("Erro ao salvar PGM: " + e);
        }

        return "";
    }

    public BufferedImage salvarPgmGetImage(String path, BufferedImage img) {
        PGMConverter converter = new PGMConverter();

        //System.out.println(getFaceRecortadaAtual().toString());
        try {
            if (img == null) {
                return null;
            }

            //BufferedImage resized = new Utils().resize(img, 120, 120);
            if (converter.write2pgm(img, path)) {

                //listAddPath.add(path);
                //return this.getFaceRecortadaAtual();
                return img;
            } else {
                return null;
            }
        } catch (Exception e) {
            setMensagem(converter.getMensagem() == null ? e.getMessage() : converter.getMensagem());
        }

        return null;

    }

    public void msg(String msg, String title, int messageType) {
        JOptionPane.showMessageDialog(rootPane, msg, title, messageType);
    }
}
