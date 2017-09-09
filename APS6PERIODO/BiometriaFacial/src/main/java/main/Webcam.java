package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.scene.image.Image;
import javax.swing.JFrame;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

/**
 *
 * @author marcu
 */
public class Webcam extends javax.swing.JFrame {

    // a timer for acquiring the video stream
    private ScheduledExecutorService timer;

    private BufferedImage frameAtual;

    public BufferedImage getFrameAtual() {
        return frameAtual;
    }

    public void setFrameAtual(BufferedImage frameAtual) {
        this.frameAtual = frameAtual;
    }

    public Webcam() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        webCamView = new javax.swing.JPanel();
        btnSalvarPGM = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        webCamView.setName("webcamView"); // NOI18N

        javax.swing.GroupLayout webCamViewLayout = new javax.swing.GroupLayout(webCamView);
        webCamView.setLayout(webCamViewLayout);
        webCamViewLayout.setHorizontalGroup(
            webCamViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 429, Short.MAX_VALUE)
        );
        webCamViewLayout.setVerticalGroup(
            webCamViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        btnSalvarPGM.setText("Salvar");
        btnSalvarPGM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarPGMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(webCamView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvarPGM))
                .addContainerGap(165, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(webCamView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSalvarPGM)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarPGMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarPGMActionPerformed
        PGMConverter converter = new PGMConverter();

        if (converter.write2pgm(this.getFrameAtual(), "C:\\testepgm\\marcos" + System.currentTimeMillis() + ".pgm")) {
            System.out.println("Imagem salva em C:\\testepgm");
        } else {
            System.out.println("Imagem não foi salvas");
        }


    }//GEN-LAST:event_btnSalvarPGMActionPerformed

    public static void main(String args[]) {
        //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        System.load("C:\\OpenCV\\java\\x64\\opencv_java300.dll");

        Webcam janela = new Webcam();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(600, 500);
        janela.setVisible(true);
        janela.mostraVideo();
    }

    public void mostraVideo() {

        Utils ut = new Utils();

        Graphics g = webCamView.getGraphics();
        //Matriz que contem os dados da imagem
        Mat frame = new Mat();
        VideoCapture captura = new VideoCapture(0);
        CascadeClassifier classificador = new CascadeClassifier("src\\cascades\\haarcascade_frontalface_alt.xml");

        MatOfRect facesDetectadas = new MatOfRect();

        double scaleFactor = 1.1;
        int minNeighbors = 2;
        int flags = 0;
        Size minSize = new Size(30, 30);
        Size maxSize = new Size(500, 500);
        Scalar cor = new Scalar(0, 255, 0);

        int larguraPanel = webCamView.getWidth();
        int alturaPanel = webCamView.getHeight();

        while (true) {
//            try {
//                Thread.sleep(66);
//            } catch (Exception e) {
//            }

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
                    //Desenha um retangulo em cada face detectada
//                        for (Rect rect : facesDetectadas.toArray()) {
//                            Imgproc.rectangle(frame, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 255), 1);
//                        }

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

        // grab a frame every 33 ms (30 frames/sec)
//        Runnable frameGrabber = new Runnable() {
//
//            @Override
//            public void run() {
//                if (captura.isOpened()) {
//                    //Captura um frame
//                    captura.read(frame);
//                    if (!frame.empty()) {
//                        setSize(frame.width(), frame.height());
//
//                        Mat imagemColorida = frame;
//                        Mat imagemCinza = new Mat();
//                        Imgproc.cvtColor(imagemColorida, imagemCinza, Imgproc.COLOR_BGR2GRAY);
//
//                        //Detecta faces
//                        classificador.detectMultiScale(imagemCinza, facesDetectadas, scaleFactor, minNeighbors, flags, minSize, maxSize);
//                        //Desenha um retangulo em cada face detectada
////                        for (Rect rect : facesDetectadas.toArray()) {
////                            Imgproc.rectangle(frame, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 255), 1);
////                        }
//
//                        Rect[] faces = facesDetectadas.toArray();
//                        for (int i = 0; i < faces.length; i++) {
//                            Imgproc.rectangle(frame, faces[i].tl(), faces[i].br(), cor, 3);
//                        }
//                        //BufferedImage imagem = new Utils().convertMatToImage(frame);
//                        BufferedImage imagem = ut.matToBufferedImage(frame);
//
//                        g.drawImage(imagem, 0, 0, larguraPanel, alturaPanel, null);
//                        imagemCinza = null;
//                    }
//                }
//            }
//        };
//        this.timer = Executors.newSingleThreadScheduledExecutor();
//
//        this.timer.scheduleAtFixedRate(frameGrabber, 0, 33, TimeUnit.MILLISECONDS);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalvarPGM;
    private javax.swing.JPanel webCamView;
    // End of variables declaration//GEN-END:variables
}
