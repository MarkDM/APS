package view;

import DAO.PropriedadeDAO;
import Model.Propriedade;
import Model.Usuario;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import utils.Utils;

public class CadastroPropriedade extends javax.swing.JFrame {

    private int propriedadeOffset;
    private int qtdPropriedadesCad;
    private Usuario usuarioLogado;

    private CadastroPropriedade() {
        initComponents();
        modo = modo.LEITURA;
        testarModo();

        try {
            updateOffset();

        } catch (SQLException ex) {
            Logger.getLogger(CadastroPropriedade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private enum ModoFormulario {
        INSERCAO, ALTERACAO, LEITURA
    };

    private ModoFormulario modo;

    public CadastroPropriedade(Usuario usuarioAutenticado) {
        setUsuarioLogado(usuarioAutenticado);
        initComponents();
        modo = modo.LEITURA;
        testarModo();

        try {
            updateOffset();
            loadByOffset(new PropriedadeDAO());

        } catch (SQLException ex) {
            Logger.getLogger(CadastroPropriedade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel4 = new javax.swing.JPanel();
               jPanel5 = new javax.swing.JPanel();
        btnPGravar = new javax.swing.JButton();
        btnPExcluir = new javax.swing.JButton();
        btnPAnterior = new javax.swing.JButton();
        btnPProximo = new javax.swing.JButton();
        btnPNovo = new javax.swing.JButton();
        btnPCancelar = new javax.swing.JButton();
        btnPAlterar = new javax.swing.JButton();
        txtId = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        txtProprietario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAgrotoxicos = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuCadUsuarios = new javax.swing.JMenu();
        menuLogin = new javax.swing.JMenu();

        jMenuItem1.setText("jMenuItem1");

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

       
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastro de Propriedade");

        jPanel5.setBackground(new java.awt.Color(102, 102, 102));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnPGravar.setText("Gravar");
        btnPGravar.setEnabled(false);
        btnPGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPGravarActionPerformed(evt);
            }
        });

        btnPExcluir.setText("Excluir");
        btnPExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPExcluirActionPerformed(evt);
            }
        });

        btnPAnterior.setText("Anterior");
        btnPAnterior.setEnabled(false);
        btnPAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPAnteriorActionPerformed(evt);
            }
        });

        btnPProximo.setText("Próximo");
        btnPProximo.setEnabled(false);
        btnPProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPProximoActionPerformed(evt);
            }
        });

        btnPNovo.setText("Novo");
        btnPNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPNovoActionPerformed(evt);
            }
        });

        btnPCancelar.setText("Cancelar");
        btnPCancelar.setEnabled(false);
        btnPCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPCancelarActionPerformed(evt);
            }
        });

        btnPAlterar.setText("Alterar");
        btnPAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPAlterarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(btnPNovo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPGravar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPAlterar)
                .addGap(9, 9, 9)
                .addComponent(btnPExcluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPAnterior)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPProximo)
                .addGap(21, 21, 21))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPGravar)
                    .addComponent(btnPExcluir)
                    .addComponent(btnPAnterior)
                    .addComponent(btnPProximo)
                    .addComponent(btnPNovo)
                    .addComponent(btnPCancelar)
                    .addComponent(btnPAlterar))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        txtId.setEnabled(false);

        jLabel1.setText("Identificador");

        jLabel2.setText("Descrição *");

        jLabel3.setText("Proprietário *");

        txtAgrotoxicos.setColumns(20);
        txtAgrotoxicos.setRows(5);
        jScrollPane1.setViewportView(txtAgrotoxicos);

        jLabel4.setText("Agrotóxicos utilizados");

        menuCadUsuarios.setText("Cadastro de Usuarios");
        menuCadUsuarios.setAutoscrolls(true);
        menuCadUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuCadUsuariosMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuCadUsuarios);

        menuLogin.setText("Sair");
        menuLogin.setAutoscrolls(true);
        menuLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuLoginMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuLogin);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)
                                .addComponent(txtDescricao)
                                .addComponent(txtProprietario)
                                .addComponent(jLabel3)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 203, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtProprietario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuCadUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuCadUsuariosMouseClicked
        if (menuCadUsuarios.isEnabled()) {
            try {
                new CadastroUsuarios().setVisible(true);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

    }//GEN-LAST:event_menuCadUsuariosMouseClicked

    private void menuLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuLoginMouseClicked
        new Login().setVisible(true);
        this.dispose();

    }//GEN-LAST:event_menuLoginMouseClicked


    private void btnPGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPGravarActionPerformed
        if (!validarDados()) {
            Utils.msg("Existem campos obrigatórios não preenchidos", "Erro", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Propriedade p = new Propriedade();
        p.setDescricao(txtDescricao.getText());
        p.setProprietario(txtProprietario.getText());
        p.setAgrotoxicos(txtAgrotoxicos.getText());

        PropriedadeDAO pDao = new PropriedadeDAO();

        try {

            if (modo == modo.INSERCAO) {
                pDao.inserir(p);
                txtId.setText(String.valueOf(pDao.getLastId()));
            } else if (modo == modo.ALTERACAO) {
                p.setId(Integer.parseInt(txtId.getText()));
                pDao.alterar(p);
            }

            Utils.msg("Registro gravado com sucesso", "sucesso", JOptionPane.INFORMATION_MESSAGE);
            modo = modo.LEITURA;
            testarModo();
            updateOffset();

        } catch (SQLException ex) {
            Utils.msg(pDao.getMensagemErro(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnPGravarActionPerformed

    private void btnPExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPExcluirActionPerformed
        if (txtId.getText().equals("")) {
            return;
        }

        PropriedadeDAO prDao = new PropriedadeDAO();

        try {
            prDao.deletar(Integer.parseInt(txtId.getText()));
            Utils.msg("Registro excluido com sucesso", "Sucesso", JOptionPane.ERROR_MESSAGE);
            updateOffset();
            limparCampos();
        } catch (SQLException ex) {
            Utils.msg("Erro ao excluir registro:" + prDao.getMensagemErro(), "Erro", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnPExcluirActionPerformed

    private void btnPAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPAnteriorActionPerformed
        if (propriedadeOffset == 1) {
            btnPAnterior.setEnabled(false);
        }
        btnPProximo.setEnabled(true);

        if (propriedadeOffset >= 0) {
            propriedadeOffset--;
        }

        PropriedadeDAO pDao = new PropriedadeDAO();

        try {
            loadByOffset(pDao);
        } catch (SQLException ex) {
            Utils.msg(pDao.getMensagemErro(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnPAnteriorActionPerformed

    private void btnPProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPProximoActionPerformed
        if (propriedadeOffset == qtdPropriedadesCad - 2) {
            btnPProximo.setEnabled(false);
        }

        btnPAnterior.setEnabled(true);

        if (propriedadeOffset < qtdPropriedadesCad - 1) {
            propriedadeOffset++;
        } else {
            return;
        }
        PropriedadeDAO pDao = new PropriedadeDAO();

        try {
            loadByOffset(pDao);
        } catch (SQLException ex) {
            Utils.msg(pDao.getMensagemErro(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnPProximoActionPerformed

    private void btnPNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPNovoActionPerformed
        modo = modo.INSERCAO;
        limparCampos();
        testarModo();
    }//GEN-LAST:event_btnPNovoActionPerformed

    public void loadByOffset(PropriedadeDAO pDao) throws SQLException {

        Propriedade p = pDao.getByOffset(propriedadeOffset);
        if (p == null) {
            return;
        }

        txtId.setText(String.valueOf(p.getId()));
        txtProprietario.setText(p.getProprietario());
        txtDescricao.setText(p.getDescricao());
        txtAgrotoxicos.setText(p.getAgrotoxicos());
        testaPermissaoUsuario();
    }

    private void btnPCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPCancelarActionPerformed

        modo = modo.LEITURA;

        testarModo();
    }//GEN-LAST:event_btnPCancelarActionPerformed

    private void btnPAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPAlterarActionPerformed
        if (txtId.getText().equals("")) {
            return;
        }

        modo = modo.ALTERACAO;
        testarModo();
    }//GEN-LAST:event_btnPAlterarActionPerformed

    private void testarModo() {
        if (modo == modo.INSERCAO || modo == modo.ALTERACAO) {
            btnPCancelar.setEnabled(true);
            btnPGravar.setEnabled(true);
            btnPNovo.setEnabled(false);
            btnPExcluir.setEnabled(false);
            btnPAlterar.setEnabled(false);
            btnPAnterior.setEnabled(false);
            btnPProximo.setEnabled(false);
            txtAgrotoxicos.setEditable(true);
            txtDescricao.setEditable(true);
            txtProprietario.setEditable(true);
        } else if (modo == modo.LEITURA) {
            btnPCancelar.setEnabled(true);
            btnPGravar.setEnabled(false);
            btnPNovo.setEnabled(true);
            btnPExcluir.setEnabled(true);
            btnPAlterar.setEnabled(true);
            btnPAnterior.setEnabled(true);
            btnPProximo.setEnabled(true);
            txtAgrotoxicos.setEditable(false);
            txtDescricao.setEditable(false);
            txtProprietario.setEditable(false);
        }
    }

    private void updateOffset() throws SQLException {
        qtdPropriedadesCad = new PropriedadeDAO().getCount();
        if (qtdPropriedadesCad > 1) {
            btnPProximo.setEnabled(true);
        }
    }

    private boolean validarDados() {

        if (txtDescricao.getText().isEmpty()) {
            return false;
        }

        if (txtProprietario.getText().isEmpty()) {
            return false;
        }

        return true;
    }

    private void limparCampos() {
        txtAgrotoxicos.setText("");
        txtDescricao.setText("");
        txtProprietario.setText("");
        txtId.setText("");
    }

    private void testaPermissaoUsuario() {
        switch (getUsuarioLogado().getTipoUsuario().getNivelAcesso()) {
            case 1:
                txtProprietario.setText("APENAS NÍVEL 2 ACIMA");
                txtProprietario.setEnabled(false);
                txtAgrotoxicos.setText("APENAS NÍVEL 3 ACIMA");
                txtAgrotoxicos.setEnabled(false);
                btnPNovo.setEnabled(false);
                btnPAlterar.setEnabled(false);
                btnPExcluir.setEnabled(false);
                btnPCancelar.setEnabled(false);
                menuCadUsuarios.setEnabled(false);
                menuCadUsuarios.setText("Cadastro de Usuários (Apenas nível 4)");
                break;
            case 2:
                txtProprietario.setEnabled(true);
                txtAgrotoxicos.setText("APENAS NÍVEL 3 ACIMA");
                txtAgrotoxicos.setEnabled(false);
                btnPNovo.setEnabled(false);
                btnPAlterar.setEnabled(false);
                btnPExcluir.setEnabled(false);
                btnPCancelar.setEnabled(false);
                menuCadUsuarios.setEnabled(false);
                menuCadUsuarios.setText("Cadastro de Usuários (Apenas nível 4)");
                break;
            case 3:
                txtProprietario.setEnabled(true);
                txtAgrotoxicos.setEnabled(true);
                btnPNovo.setEnabled(true);
                btnPAlterar.setEnabled(true);
                btnPExcluir.setEnabled(true);
                btnPCancelar.setEnabled(true);
                menuCadUsuarios.setEnabled(false);
                menuCadUsuarios.setText("Cadastro de Usuários (Apenas nível 4)");
                break;
            case 4:
                txtProprietario.setEnabled(true);
                txtAgrotoxicos.setEnabled(true);
                btnPNovo.setEnabled(true);
                btnPAlterar.setEnabled(true);
                btnPExcluir.setEnabled(true);
                btnPCancelar.setEnabled(true);
                menuCadUsuarios.setEnabled(true);
                menuCadUsuarios.setText("Cadastro de Usuários");
                break;
        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadastroPropriedade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroPropriedade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroPropriedade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroPropriedade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroPropriedade().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnGravar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPAlterar;
    private javax.swing.JButton btnPAnterior;
    private javax.swing.JButton btnPCancelar;
    private javax.swing.JButton btnPExcluir;
    private javax.swing.JButton btnPGravar;
    private javax.swing.JButton btnPNovo;
    private javax.swing.JButton btnPProximo;
    private javax.swing.JButton btnProximo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu menuCadUsuarios;
    private javax.swing.JMenu menuLogin;
    private javax.swing.JTextArea txtAgrotoxicos;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtProprietario;
    // End of variables declaration//GEN-END:variables
}
