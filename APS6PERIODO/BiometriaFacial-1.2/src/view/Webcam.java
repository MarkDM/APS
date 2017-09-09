package view;

import utils.PGMConverter;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
import javafx.scene.image.Image;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import utils.Utils;
//import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
//import org.opencv.core.Point;
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

    private static String localPath;
    private List<String> listPgmTrain = new ArrayList<String>();
    // TableColumnModel columnModel;
    DefaultTableModel gridModel;

    // a timer for acquiring the video stream
    //private ScheduledExecutorService timer;
    private BufferedImage frameAtual;

    public BufferedImage getFrameAtual() {
        return frameAtual;
    }

    public void setFrameAtual(BufferedImage frameAtual) {
        this.frameAtual = frameAtual;
    }

    public Webcam() {
        initComponents();
        // columnModel = gridImgs.getColumnModel();
        gridModel = (DefaultTableModel) gridImgs.getModel();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        webCamView = new javax.swing.JPanel();
        btnSalvarPGM = new javax.swing.JButton();
        btnTreinar = new javax.swing.JButton();
        btnReconhecer = new javax.swing.JButton();
        txtIdentificador = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        gridImgs = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        webCamView.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        webCamView.setName("webcamView"); // NOI18N

        javax.swing.GroupLayout webCamViewLayout = new javax.swing.GroupLayout(webCamView);
        webCamView.setLayout(webCamViewLayout);
        webCamViewLayout.setHorizontalGroup(
            webCamViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 521, Short.MAX_VALUE)
        );
        webCamViewLayout.setVerticalGroup(
            webCamViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        btnSalvarPGM.setText("Salvar");
        btnSalvarPGM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarPGMActionPerformed(evt);
            }
        });

        btnTreinar.setText("Treinar");

        btnReconhecer.setText("Reconhecer");

        jLabel1.setText("Identificador");

        gridImgs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(gridImgs);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(webCamView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSalvarPGM)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTreinar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReconhecer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 244, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdentificador, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(webCamView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvarPGM)
                    .addComponent(btnTreinar)
                    .addComponent(btnReconhecer)
                    .addComponent(jLabel1)
                    .addComponent(txtIdentificador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //DefaultListModel listSavedFilesModel = new DefaultListModel();

    private void btnSalvarPGMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarPGMActionPerformed
        PGMConverter converter = new PGMConverter();

        if (txtIdentificador.getText().equals("")) {
            return;
        }

        String path = localPath + "\\src\\resources\\pgmTrainer\\" + txtIdentificador.getText() + listPgmTrain.size() + ".pgm";

        if (converter.write2pgm(this.getFrameAtual(), path)) {

//            listSavedFilesModel.addElement(path);
//            listPgmTrain.add(path);
//            listSavedFiles.setModel(listSavedFilesModel);
            // listSavedFiles.add(path, this);
            gridModel.addRow(new Object[]{new ImageIcon(frameAtual)});

        } else {
            System.out.println("Imagem não foi salvas");
        }


    }//GEN-LAST:event_btnSalvarPGMActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (InstantiationException ex) {
            Logger.getLogger(Webcam.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Webcam.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Webcam.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Webcam.class.getName()).log(Level.SEVERE, null, ex);
        }

        //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        localPath = new File("").getAbsolutePath();
        System.load(localPath + "\\src\\nativeLibrary\\opencv_java300.dll");

        Webcam janela = new Webcam();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //janela.setSize(1280, 800);
        janela.setVisible(true);
        janela.mostraVideo();
    }

    public void mostraVideo() {

        Utils ut = new Utils();

        Graphics g = webCamView.getGraphics();
        //Matriz que contem os dados da imagem
        Mat frame = new Mat();
        VideoCapture captura = new VideoCapture(0);

        String cascadePath = localPath + "\\src\\resources\\cascades\\haarcascade_frontalface_alt.xml";

        CascadeClassifier classificador = new CascadeClassifier(cascadePath);

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
    private javax.swing.JButton btnReconhecer;
    private javax.swing.JButton btnSalvarPGM;
    private javax.swing.JButton btnTreinar;
    private javax.swing.JTable gridImgs;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtIdentificador;
    private javax.swing.JPanel webCamView;
    // End of variables declaration//GEN-END:variables
}
