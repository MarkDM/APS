package view;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import main.FileTrain;
import main.LaplacianFaces;
import main.Recognizer;
import main.Trainer;
import org.opencv.core.Mat;
import utils.PreProcessing;
import utils.Text2ImageConverter;
import utils.Utils;

/**
 *
 * @author marcu
 */
public class TreinarFaces extends TelaComCaptura {

    private Trainer treinador = new Trainer();
    private Recognizer reconhecedor = new Recognizer();
    FileTrain fileTrain;
    private List<FileTrain> listaTreinamento = new ArrayList<>();
    private List<String> listaReconhecer = new ArrayList<>();

    public TreinarFaces() {
        initComponents();
        txtIdentificador.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        videoCapture = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        gridImgs = new javax.swing.JTable();
        btnSalvarPGM = new javax.swing.JButton();
        btnTreinar = new javax.swing.JButton();
        btnReconhecer = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtIdentificador = new javax.swing.JTextField();
        btnNovo = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Treinador de Faces");
        setMaximumSize(new java.awt.Dimension(0, 0));

        videoCapture.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        gridImgs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(gridImgs);

        btnSalvarPGM.setText("Salvar");
        btnSalvarPGM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarPGMActionPerformed(evt);
            }
        });

        btnTreinar.setText("Treinar");
        btnTreinar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTreinarActionPerformed(evt);
            }
        });

        btnReconhecer.setText("Reconhecer");
        btnReconhecer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReconhecerActionPerformed(evt);
            }
        });

        jLabel1.setText("Identificador");

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(videoCapture, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSalvarPGM)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTreinar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReconhecer)
                        .addGap(157, 157, 157)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnNovo)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtIdentificador))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(videoCapture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIdentificador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvarPGM)
                    .addComponent(btnTreinar)
                    .addComponent(btnReconhecer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNovo)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void iniciarCaptura() {

        mostraVideo(videoCapture);

    }

    public void addImgToTable(BufferedImage bi) {
        JTableRenderer renderer = new JTableRenderer();
        TableColumnModel columnModel = gridImgs.getColumnModel();
        columnModel.getColumn(0).setCellRenderer(renderer);
        DefaultTableModel modelo = (DefaultTableModel) gridImgs.getModel();
        gridImgs.setRowHeight(150);
        new Utils().Img2GrayScale(bi);
        modelo.addRow(new Object[]{new ImageIcon(bi)});
        gridImgs.scrollRectToVisible(gridImgs.getCellRect(gridImgs.getRowCount() - 1, 0, true));
    }


    private void btnSalvarPGMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarPGMActionPerformed
        if (txtIdentificador.getText().equals("")) {
            msg("É necessário preencher o campo identificador", "Preencher identificador", JOptionPane.WARNING_MESSAGE);
            return;
        }

        //trainer.setIdentificador(txtIdentificador.getText());
        txtIdentificador.setEnabled(false);
        fileTrain.setIdentificador(txtIdentificador.getText());
        Utils ut = new Utils();

        setSalvandoPGM(true);
        //StopCapture();
        try {

            for (Mat m : getFacesRecortadas()) {
                //Adiciona na lista de treinamento
                String path = localPath + "\\src\\resources\\pgmTrainer\\" + txtIdentificador.getText() + listaTreinamento.size() + ".pgm";

                // ut.mostraImagem(bi);
                BufferedImage resized = ut.resize(ut.matToBufferedImage(m), 120, 120);
                //Pré processa a imagem
                PreProcessing p = new PreProcessing();
                BufferedImage imgPreProcessed = p.enhance(resized);

                //ut.Img2GrayScale(imgPreProcessed);
                //salvarPgm2(path, m);
                if (salvarPgm2(path, ut.bufferedImageToMat(imgPreProcessed)) == "") {
                    continue;
                }

                addImgToTable(imgPreProcessed);
                Text2ImageConverter txtImg = new Text2ImageConverter();
                txtImg.writeImage(txtIdentificador.getText() + " " + listaTreinamento.size(), imgPreProcessed, 5, imgPreProcessed.getHeight() - 5);
                fileTrain.setPgmImagePath(path);
                fileTrain.setImage(imgPreProcessed);

                listaTreinamento.add(fileTrain);

            }
            setSalvandoPGM(false);
            //OpenCapture();

        } catch (Exception e) {
            System.out.println(getMensagem() + " // " + e.toString());
            setSalvandoPGM(false);
        }


    }//GEN-LAST:event_btnSalvarPGMActionPerformed

    private void btnTreinarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTreinarActionPerformed

        if (txtIdentificador.getText().equals("")) {
            msg("É necessário preencher o campo identificador", "Preencher identificador", JOptionPane.WARNING_MESSAGE);

            return;
        }

        try {

            Boolean trainSuccess = false;

            if (listaTreinamento.isEmpty()) {
                msg("Lista de treinamento esta vazia", "Lista vazia", JOptionPane.WARNING_MESSAGE);
                return;

            }

            trainSuccess = treinador.treinar(listaTreinamento);

            if (trainSuccess) {
                msg("Treinamento concluído com sucesso", "Treinamento concluido", JOptionPane.INFORMATION_MESSAGE);

                setSalvandoPGM(false);
            } else {
                msg("Treinamento falhou", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }//GEN-LAST:event_btnTreinarActionPerformed

    private void btnReconhecerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReconhecerActionPerformed
        //Limpa a lista de reconhecimento para montar uma nova
        listaReconhecer.clear();
        //Limpa as faces recortadas e espera 200ms para obter novas
        getFacesRecortadas().clear();
        // getPessoasReconhecidas().clear();

        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {

        }

        List<Mat> facesRecortadas = getFacesRecortadas();

        if (facesRecortadas.size() == 0) {
            System.out.println("Nehuma face detectada");
            return;
        }

        for (Mat m : facesRecortadas) {
            int numFoto = listaReconhecer.size();

            String pgmPath = localPath + "\\src\\resources\\pgmTrainer\\Reconhecer" + numFoto + ".pgm";

            Utils ut = new Utils();

            // ut.mostraImagem(bi);
            BufferedImage resized = ut.resize(ut.matToBufferedImage(m), 120, 120);
            //Pré processa a imagem
            PreProcessing p = new PreProcessing();
            BufferedImage imgPreProcessed = p.enhance(resized);

            try {
                if (salvarPgm2(pgmPath, ut.bufferedImageToMat(imgPreProcessed)) != "") {

                    listaReconhecer.add(pgmPath);
                    Text2ImageConverter txtImg = new Text2ImageConverter();
                    txtImg.writeImage("Reconhecer" + " " + numFoto, imgPreProcessed, 5, imgPreProcessed.getHeight() - 5);
                    //ut.Img2GrayScale(imgPreProcessed);
                    addImgToTable(imgPreProcessed);
                } else {
                    System.out.println("Imagem não foi salva como PGM");
                    continue;
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }

        }

        List<FileTrain> idsReconhecidos = new ArrayList<>();

        for (String pgm : listaReconhecer) {
            FileTrain ftMatched = reconhecedor.reconhecer(pgm, listaTreinamento);

            if (ftMatched != null) {

                if (!idsReconhecidos.contains(ftMatched)) {
                    idsReconhecidos.add(ftMatched);
                }

            }

        }

        if (idsReconhecidos.isEmpty()) {
            msg("Não foi reconhecida nenhuma face", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String facesReconhecidas = "Pessoas reconhecidas\n";

        for (FileTrain ft : idsReconhecidos) {
            Double precisao = 100 * (LaplacianFaces.DifferenceThreshold - ft.getDiffLaplacian()) / LaplacianFaces.DifferenceThreshold;
            //precisao = 

            DecimalFormat df = new DecimalFormat("0.00");

            String strPrecisao = df.format(precisao);

            facesReconhecidas += ft.getIdentificador() + " - Precisão: " + strPrecisao + "%\n";
        }

        msg(facesReconhecidas, "Faces Reconhecidas", JOptionPane.INFORMATION_MESSAGE);

        //System.out.println("Pessoas reconhecidas");

    }//GEN-LAST:event_btnReconhecerActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        txtIdentificador.setEnabled(true);
        setSalvandoPGM(true);
        txtIdentificador.setText("");

        fileTrain = new FileTrain();

    }//GEN-LAST:event_btnNovoActionPerformed

    public static void main(String args[]) {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TreinarFaces.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TreinarFaces.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TreinarFaces.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TreinarFaces.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        TreinarFaces tela = new TreinarFaces();
        tela.launch();
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setVisible(true);
        tela.iniciarCaptura();

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//
//                TreinarFaces tela = new TreinarFaces();
//                tela.launch();
//                tela.setVisible(true);
//                tela.iniciarCaptura();
//            }
//        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnReconhecer;
    private javax.swing.JButton btnSalvarPGM;
    private javax.swing.JButton btnTreinar;
    private javax.swing.JTable gridImgs;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtIdentificador;
    private javax.swing.JPanel videoCapture;
    // End of variables declaration//GEN-END:variables
}
