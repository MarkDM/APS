package view;

import DAO.ImagensTreinamentoDAO;
import DAO.UsuarioDAO;
import Model.FileTrain;
import Model.ImagemTreinamento;
import Model.Usuario;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import main.ClassificadorFacial;
import main.LaplacianFaces;
import main.Recognizer;
import main.Trainer;
import org.opencv.core.Mat;
import org.opencv.objdetect.CascadeClassifier;
import utils.PreProcessing;
import utils.Text2ImageConverter;
import utils.Utils;

public class Login extends TelaComCaptura {

    private List<String> listaReconhecer = new ArrayList<>();
    private Recognizer reconhecedor = new Recognizer();
    List<ImagemTreinamento> listaTreinamento;
    Thread t;
    Thread tReconhecer;
    private static Usuario usuarioAutenticado;

    public Usuario getUsuarioAutenticado() {
        return Login.usuarioAutenticado;
    }

    public void setUsuarioAutenticado(Usuario usuarioAutenticado) {
        Login.usuarioAutenticado = usuarioAutenticado;
    }

    public Login() {
        initComponents();
        //lookAndFeel();

        launch();

        t = new Thread(new Runnable() {
            @Override
            public void run() {
                mostraVideo(videoPanel, t);
            }
        });

        t.start();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        videoPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnAcessar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtLogin = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        btnAutenticar = new javax.swing.JButton();
        lblResultadoRegog = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setMaximumSize(new java.awt.Dimension(347, 486));
        setMinimumSize(new java.awt.Dimension(347, 486));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        videoPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout videoPanelLayout = new javax.swing.GroupLayout(videoPanel);
        videoPanel.setLayout(videoPanelLayout);
        videoPanelLayout.setHorizontalGroup(
            videoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 326, Short.MAX_VALUE)
        );
        videoPanelLayout.setVerticalGroup(
            videoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 225, Short.MAX_VALUE)
        );

        btnAcessar.setText("Acessar");
        btnAcessar.setEnabled(false);
        btnAcessar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcessarActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel1.setText("Login");

        jLabel2.setText("Senha");

        txtSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSenhaKeyTyped(evt);
            }
        });

        btnAutenticar.setText("Autenticar");
        btnAutenticar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAutenticarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(btnAutenticar)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtSenha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                        .addComponent(txtLogin)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAutenticar)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        lblResultadoRegog.setText("Pronto");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblResultadoRegog)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAcessar))
                    .addComponent(jLabel3)
                    .addComponent(videoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(videoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAcessar)
                    .addComponent(lblResultadoRegog))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAcessarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcessarActionPerformed
        new CadastroPropriedade(getUsuarioAutenticado()).setVisible(true);
        t.interrupt();
        t = null;
        this.dispose();
        //System.exit(0);
    }//GEN-LAST:event_btnAcessarActionPerformed

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped

    }//GEN-LAST:event_formKeyTyped

    private void btnAutenticarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAutenticarActionPerformed
        if (validarDados()) {
            aunteticar();
        } else {
            Utils.msg("É necessário preencher login e senha", "Erro", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnAutenticarActionPerformed

    private void txtSenhaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSenhaKeyTyped
        if (evt.getKeyChar() == '\n') {
            aunteticar();
        }
    }//GEN-LAST:event_txtSenhaKeyTyped

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
//
//        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Login tela = new Login();
                //tela.launch();
                tela.setVisible(true);
                //tela.mostraVideo(tela.videoPanel, new Thread());
            }
        });
    }

    private Boolean validarDados() {
        if (txtLogin.getText().equals("")) {
            return false;
        }

        if (txtSenha.getPassword().equals("")) {
            return false;
        }

        return true;
    }

    private void aunteticar() {

        if (tReconhecer != null) {
            tReconhecer.interrupt();
        }

        UsuarioDAO usrDAO = new UsuarioDAO();
        Usuario user = usrDAO.getUsuarioByLogin(txtLogin.getText());

        if (user != null) {
            if (user.getSenha().equals(new Utils().getMd5(Arrays.toString(txtSenha.getPassword())))) {
                //Administrador
                if (user.getTipoUsuario().getId() == 4) {
                    setUsuarioAutenticado(user);
                    int firstNameLength = (user.getNome().indexOf(" ") == -1) ? user.getNome().length() : user.getNome().indexOf(" ");
                    setLblResultText("Bem vindo " + user.getNome().subSequence(0, firstNameLength) + " - " + user.getTipoUsuario().getDescricao(), Color.GREEN);
                    btnAcessar.setEnabled(true);
                } else {

                    if (!treinar(user.getId())) {
                        return;
                    }

                    tReconhecer = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Boolean reconhecido = false;

                            while (!tReconhecer.isInterrupted()) {

                                if (reconhecer(user)) {
                                    reconhecido = true;
                                    break;
                                } else {
                                    reconhecido = false;
                                }
                                btnAcessar.setEnabled(false);
                            }

                            if (reconhecido) {
                                setUsuarioAutenticado(user);
                                btnAcessar.setEnabled(true);
                                setLblResultText("Bem vindo " + user.getNome() + " - " + user.getTipoUsuario().getDescricao(), Color.GREEN);
                            }

                        }
                    });
                    tReconhecer.start();

                }

            } else {
                Utils.msg("Senha digitada inválida", "Erro", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } else {
            Utils.msg("Usuário informado não existe", "Erro", JOptionPane.WARNING_MESSAGE);
        }
    }

    public Boolean treinar(int idUsuario) {
        try {
            listaTreinamento = new ImagensTreinamentoDAO().getImagensByUsuario(idUsuario);
            if (listaTreinamento.isEmpty()) {
                Utils.msg("Não foram cadastradas imagens de treinamento para o usuário", "Erro", JOptionPane.ERROR_MESSAGE);
                t.interrupt();
                return false;
            }

            new Trainer().treinar(listaTreinamento);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private void setLblResultText(String text, Color c) {
        lblResultadoRegog.setBorder(new EmptyBorder(5, 5, 5, 5));
        lblResultadoRegog.setText(text);
        lblResultadoRegog.setOpaque(true);
        lblResultadoRegog.setBackground(c);
    }

    private Boolean reconhecer(Usuario user) {
        if (!getUsuariosReconhecidos().isEmpty()) {

            for (Usuario u : getUsuariosReconhecidos()) {
                if (u.getId() == user.getId()) {
                    return true;
                }
            }

        }
        return false;
    }

    private List<Usuario> getUsuariosReconhecidos() {

        List<Usuario> retorno = new ArrayList<>();
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
            //System.out.println("Nehuma face detectada");
            return retorno;
        }

        for (Mat m : facesRecortadas) {
            int numFoto = 0;
            String pgmPath = localPath + "\\resources\\pgmTrainer\\Reconhecer" + numFoto + ".pgm";
            Utils ut = new Utils();
            // ut.mostraImagem(bi);
            BufferedImage resized = ut.resize(ut.matToBufferedImage(m), 120, 120);
            //Pré processa a imagem
            PreProcessing p = new PreProcessing();
            BufferedImage imgPreProcessed = p.enhance(resized);

            try {
                if (salvarPgm2(pgmPath, ut.bufferedImageToMat(imgPreProcessed)) != "") {
                    listaReconhecer.add(pgmPath);
                } else {
                    System.out.println("Imagem não foi salva como PGM");
                    continue;
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }

        }

        List<ImagemTreinamento> usuariosReconhecidos = new ArrayList<>();

        for (String pgm : listaReconhecer) {
            ImagemTreinamento ftMatched = reconhecedor.reconhecer(pgm, listaTreinamento);

            if (ftMatched != null) {

                if (!usuariosReconhecidos.contains(ftMatched)) {
                    usuariosReconhecidos.add(ftMatched);
                }

            }

        }

        if (usuariosReconhecidos.isEmpty()) {
            //Utils.msg("Não foi reconhecida nenhuma face", "Aviso", JOptionPane.WARNING_MESSAGE);
            setLblResultText("Não foi reconhecida nenhuma face", Color.red);
            return retorno;
        }

        String facesReconhecidas = "";

        for (ImagemTreinamento ft : usuariosReconhecidos) {
            Double precisao = 100 * (LaplacianFaces.DifferenceThreshold - ft.getDiffLaplacian()) / LaplacianFaces.DifferenceThreshold;
            DecimalFormat df = new DecimalFormat("0.00");
            String strPrecisao = df.format(precisao);
            facesReconhecidas += ft.getUsuario().getNome() + " - Precisão: " + strPrecisao + "%  ";
            retorno.add(ft.getUsuario());
        }

        //Utils.msg(facesReconhecidas, "Faces Reconhecidas", JOptionPane.INFORMATION_MESSAGE);
        // lblResultRecog.setText(facesReconhecidas);
        setLblResultText(facesReconhecidas, Color.LIGHT_GRAY);

        return retorno;

        //System.out.println("Pessoas reconhecidas");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAcessar;
    private javax.swing.JButton btnAutenticar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblResultadoRegog;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JPanel videoPanel;
    // End of variables declaration//GEN-END:variables
}
