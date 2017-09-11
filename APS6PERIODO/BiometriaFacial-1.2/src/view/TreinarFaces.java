package view;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
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
import main.ReconhecedorDeFaces;
import main.TreinadorDeFaces;
import utils.Utils;

/**
 *
 * @author marcu
 */
public class TreinarFaces extends TelaComCaptura {

    private TreinadorDeFaces trainer;
    private ReconhecedorDeFaces recognizer;
    private List<TreinadorDeFaces> listaDeTreinadores = new ArrayList<>();

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

//        Runnable capturar = new Runnable() {
//            @Override
//            public void run() {
        mostraVideo(videoCapture);
//            }
//        };

    }


    private void btnSalvarPGMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarPGMActionPerformed
        if (txtIdentificador.getText().equals("")) {
            msg("É necessário preencher o campo identificador", "Preencher identificador", JOptionPane.WARNING_MESSAGE);
            return;
        }

        txtIdentificador.setEnabled(false);
        Utils ut = new Utils();
        JTableRenderer renderer = new JTableRenderer();
        setSalvandoPGM(true);
        //StopCapture();
        try {

            // List<BufferedImage> facesRecortadas = getFacesRecortadas();
            for (BufferedImage bi : getFacesRecortadas()) {
                String path = localPath + "\\src\\resources\\pgmTrainer\\" + txtIdentificador.getText() + trainer.getListPgmTrain().size() + ".pgm";
                trainer.add2ListaPgmTrain(salvarPgm(path, bi));

                // ut.mostraImagem(bi);
                TableColumnModel columnModel = gridImgs.getColumnModel();

                columnModel.getColumn(0).setCellRenderer(renderer);

                DefaultTableModel modelo = (DefaultTableModel) gridImgs.getModel();

                // if (bi != null) {
                Image resized = ut.resize(bi, 150, 150);

                gridImgs.setRowHeight(150);

                modelo.addRow(new Object[]{new ImageIcon(resized)});
//                } else {
//                    System.out.println("Imagem não foi salva como PGM");
//                }
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

            for (TreinadorDeFaces t : listaDeTreinadores) {
                if (t.getListPgmTrain().size() < 2) {
                    msg("Numero de imagens para treinamento precisa ser pelo menos 2", "Imagens insuficientes", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            trainer.setIdentificador(txtIdentificador.getText());
            setSalvandoPGM(false);
            trainer.Treinar(listaDeTreinadores);
            msg(trainer.getMensagem(), "Resultado", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }//GEN-LAST:event_btnTreinarActionPerformed

    private void btnReconhecerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReconhecerActionPerformed
        ReconhecedorDeFaces r = new ReconhecedorDeFaces();

//        if (txtIdentificador.getText().equals("")) {
//            JOptionPane.showMessageDialog(rootPane, "É necessário preencher o campo identificador", "Preencher identificador", HEIGHT);
//            return;
//        }
        List<BufferedImage> facesRecortadas = getFacesRecortadas();

        if (facesRecortadas.size() == 0) {
            System.out.println("Nehuma face detectada");
            return;
        }

        for (BufferedImage bi : facesRecortadas) {
            String pgmPath = localPath + "\\src\\resources\\pgmTrainer\\Reconhecer" + getListPgmRecognize().size() + ".pgm";
            try {
                if (salvarPgm(pgmPath, bi) != null) {
                    add2ListaPgmRecognize(pgmPath);

                } else {
                    System.out.println("Imagem não foi salva como PGM");
                    return;
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }

        }

        setPessoasReconhecidas(r.reconhecer(listaDeTreinadores, getListPgmRecognize()));

        if (getPessoasReconhecidas().isEmpty()) {
            msg("Não foi reconhecida nenhuma face", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        //System.out.println("Pessoas reconhecidas");
        String facesReconhecidas = "Pessoas reconhecidas\n";

        for (String id : getPessoasReconhecidas()) {

            facesReconhecidas += id + "\n";
        }

        msg(facesReconhecidas, "Faces Reconhecidas", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnReconhecerActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        txtIdentificador.setEnabled(true);
        // setSalvandoPGM(true);
        txtIdentificador.setText("");
        trainer = new TreinadorDeFaces();
        //trainer.
        listaDeTreinadores.add(trainer);
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
