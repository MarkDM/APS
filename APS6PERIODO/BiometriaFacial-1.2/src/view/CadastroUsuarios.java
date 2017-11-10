/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DAO.ImagensTreinamentoDAO;
import DAO.TipoUsuarioDAO;
import DAO.UsuarioDAO;
import Model.ImagemTreinamento;
import Model.Item;
import Model.TipoUsuario;
import Model.Usuario;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.MaskFormatter;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import utils.PreProcessing;
import utils.Text2ImageConverter;
import utils.Utils;

/**
 *
 * @author marcu
 */
public class CadastroUsuarios extends TelaComCaptura {
    
    private int usuarioOffset = 0;
    private int qtdUsuariosCadastros = 0;
    private Thread tVideo;
    private DefaultTableModel modelo;
    
    private enum ModoFormulario {
        INSERCAO, ALTERACAO, LEITURA
    };
    
    private ModoFormulario modo;
    
    public CadastroUsuarios() {
        
        try {
            initComponents();
            modelo = (DefaultTableModel) gridImgsTreinamento.getModel();
            loadByOffset();
            updateOffset();
            modo = modo.LEITURA;
            testarModo();
            preencherCmbTipoUsuario();
        } catch (SQLException ex) {
            Utils.msg("Erro ao preencher tipos de usuario", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        btnPausarCaptura.setEnabled(false);
        
        JTableRenderer renderer1 = new JTableRenderer();
        TableColumnModel columnModel = gridImgsTreinamento.getColumnModel();
        columnModel.getColumn(0).setCellRenderer(renderer1);
        //columnModel.addColumn(new TableColumn());

        gridImgsTreinamento.setRowHeight(130);
    }
    
    public Boolean isDigit(Character c) {
        return Character.isDigit(c);
    }
    
    JComboBox<Item<TipoUsuario>> comboBox = new JComboBox<>();
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        txtId = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        txtLogin = new javax.swing.JTextField();
        txtSenha = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnLimparCampos = new javax.swing.JButton();
        cBoxAlterarSenha = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        videoCapturePane = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        gridImgsTreinamento = new javax.swing.JTable();
        btnSalvarImgTreinamento = new javax.swing.JButton();
        btnPausarCaptura = new javax.swing.JButton();
        btnIniciarCaptura = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnGravar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnAnterior = new javax.swing.JButton();
        btnProximo = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastro de Usuários");

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        txtId.setEditable(false);

        txtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNomeKeyTyped(evt);
            }
        });

        jLabel1.setText("Identificador");

        jLabel2.setText("Nome *");

        jLabel3.setText("Login *");

        jLabel4.setText("Senha *");

        jLabel5.setText("Tipo Usuário *");

        btnLimparCampos.setText("Limpar Campos");
        btnLimparCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparCamposActionPerformed(evt);
            }
        });

        cBoxAlterarSenha.setText("Alterar Senha");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(btnLimparCampos)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNome, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                            .addComponent(txtLogin)
                            .addComponent(txtSenha))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cBoxAlterarSenha))
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addContainerGap(195, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cBoxAlterarSenha))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(44, 44, 44)
                .addComponent(btnLimparCampos)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cadastro", jPanel1);

        videoCapturePane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout videoCapturePaneLayout = new javax.swing.GroupLayout(videoCapturePane);
        videoCapturePane.setLayout(videoCapturePaneLayout);
        videoCapturePaneLayout.setHorizontalGroup(
            videoCapturePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 484, Short.MAX_VALUE)
        );
        videoCapturePaneLayout.setVerticalGroup(
            videoCapturePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );

        gridImgsTreinamento.setModel(new javax.swing.table.DefaultTableModel(
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
        gridImgsTreinamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gridImgsTreinamentoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(gridImgsTreinamento);

        btnSalvarImgTreinamento.setText("Salvar");
        btnSalvarImgTreinamento.setEnabled(false);
        btnSalvarImgTreinamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarImgTreinamentoActionPerformed(evt);
            }
        });

        btnPausarCaptura.setText("Pausar");
        btnPausarCaptura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPausarCapturaActionPerformed(evt);
            }
        });

        btnIniciarCaptura.setText("Iniciar");
        btnIniciarCaptura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarCapturaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(videoCapturePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnIniciarCaptura)
                        .addGap(12, 12, 12)
                        .addComponent(btnPausarCaptura)
                        .addGap(65, 65, 65)
                        .addComponent(btnSalvarImgTreinamento)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(videoCapturePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvarImgTreinamento)
                    .addComponent(btnPausarCaptura)
                    .addComponent(btnIniciarCaptura))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Biometria Facial", jPanel2);

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnGravar.setText("Gravar");
        btnGravar.setEnabled(false);
        btnGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGravarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnAnterior.setText("Anterior");
        btnAnterior.setEnabled(false);
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });

        btnProximo.setText("Próximo");
        btnProximo.setEnabled(false);
        btnProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProximoActionPerformed(evt);
            }
        });

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.setEnabled(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(btnNovo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGravar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAlterar)
                .addGap(9, 9, 9)
                .addComponent(btnExcluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAnterior)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnProximo)
                .addGap(21, 21, 21))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGravar)
                    .addComponent(btnExcluir)
                    .addComponent(btnAnterior)
                    .addComponent(btnProximo)
                    .addComponent(btnNovo)
                    .addComponent(btnCancelar)
                    .addComponent(btnAlterar))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Cadastro");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravarActionPerformed
        
        if (!validarDados()) {
            Utils.msg("Existem campos obrigatórios não preenchidos", "Erro", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (modo == modo.INSERCAO) {
            if (new UsuarioDAO().getUsuarioByLogin(txtLogin.getText()) != null) {
                Utils.msg("Login já existe, favor escolher outro", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        Usuario u = new Usuario();
        u.setLogin(txtLogin.getText());
        u.setNome(txtNome.getText());
        u.setSenha(String.valueOf(txtSenha.getPassword()));
        Item item = (Item) comboBox.getSelectedItem();
        TipoUsuario tipoUsuario = (TipoUsuario) item.getValue();
        //int idTipoUsuario = tipoUsuario.getId();

        u.setTipoUsuario(tipoUsuario);
        
        UsuarioDAO usrDao = new UsuarioDAO();
        
        try {
            
            if (modo == modo.INSERCAO) {
                usrDao.inserirUsuario(u);
                txtId.setText(String.valueOf(new UsuarioDAO().getLastId()));
            } else if (modo == modo.ALTERACAO) {
                u.setId(Integer.parseInt(txtId.getText()));
                usrDao.alterarUsuario(u, cBoxAlterarSenha.isSelected());
            }
            
            Utils.msg("Registro gravado com sucesso", "sucesso", JOptionPane.INFORMATION_MESSAGE);
            modo = modo.LEITURA;
            testarModo();
            updateOffset();
            
        } catch (SQLException ex) {
            Utils.msg("Erro ao gravar registro: " + usrDao.getMensagemErro(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        

    }//GEN-LAST:event_btnGravarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if (txtId.getText().isEmpty()) {
            return;
        }
        UsuarioDAO usrDAO = new UsuarioDAO();
        
        try {
            
            usrDAO.deletarUsuario(Integer.parseInt(txtId.getText()));
            Utils.msg("Registro excluido com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparCampos();
            updateOffset();
            
        } catch (SQLException ex) {
            Utils.msg("Erro ao excluir registro: " + usrDAO.getMensagemErro(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        modo = modo.INSERCAO;
        
        testarModo();
        
        limparCampos();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        
        modo = modo.LEITURA;
        
        testarModo();
    }//GEN-LAST:event_btnCancelarActionPerformed
    
    private void limparCampos() {
        txtId.setText("");
        txtLogin.setText("");
        txtNome.setText("");
        txtSenha.setText("");
    }
    
    public void loadByOfsset() {
        
    }
    
    private void testarModo() {
        if (modo == modo.INSERCAO || modo == modo.ALTERACAO) {
            txtLogin.setEditable(true);
            txtNome.setEditable(true);
            txtSenha.setEditable(true);
            btnCancelar.setEnabled(true);
            btnGravar.setEnabled(true);
            btnNovo.setEnabled(false);
            btnExcluir.setEnabled(false);
            btnAlterar.setEnabled(false);
            //btnAnterior.setEnabled(false);
            //btnProximo.setEnabled(false);
        } else if (modo == modo.LEITURA) {
            txtLogin.setEditable(false);
            txtNome.setEditable(false);
            txtSenha.setEditable(false);
            btnCancelar.setEnabled(true);
            btnGravar.setEnabled(false);
            btnNovo.setEnabled(true);
            btnExcluir.setEnabled(true);
            btnAlterar.setEnabled(true);
            // btnAnterior.setEnabled(true);
            // btnProximo.setEnabled(true);
        }
    }
    
    public void addImgToTable(BufferedImage img) {
        new Utils().Img2GrayScale(img);
        //modelo.addRow(new Object[]{new ImageIcon(img.getImgBitMap())});
        modelo.addRow(
                new Object[]{new ImageIcon(img)}
        );
        gridImgsTreinamento.scrollRectToVisible(gridImgsTreinamento.getCellRect(gridImgsTreinamento.getRowCount() - 1, 0, true));
    }
    

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        
        if (usuarioOffset == 1) {
            btnAnterior.setEnabled(false);
        }
        btnProximo.setEnabled(true);
        
        if (usuarioOffset >= 0) {
            usuarioOffset--;
        }
        
        try {
            loadByOffset();
            
        } catch (SQLException ex) {
            Logger.getLogger(CadastroUsuarios.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void btnProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProximoActionPerformed
        
        if (usuarioOffset == qtdUsuariosCadastros - 2) {
            btnProximo.setEnabled(false);
        }
        
        btnAnterior.setEnabled(true);
        
        if (usuarioOffset < qtdUsuariosCadastros - 1) {
            usuarioOffset++;
        } else {
            return;
        }
        
        try {
            loadByOffset();
            
        } catch (SQLException ex) {
            Logger.getLogger(CadastroUsuarios.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        

    }//GEN-LAST:event_btnProximoActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        if (txtId.getText().equals("")) {
            return;
        }
        
        modo = modo.ALTERACAO;
        testarModo();
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnLimparCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparCamposActionPerformed
        limparCampos();
    }//GEN-LAST:event_btnLimparCamposActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        

    }//GEN-LAST:event_jTabbedPane1StateChanged
    
    private void loadByOffset() throws SQLException {
        Usuario u = new UsuarioDAO().getByOffset(usuarioOffset);
        
        if (u == null) {
            return;
        }
        
        txtId.setText(String.valueOf(u.getId()));
        txtLogin.setText(u.getLogin());
        txtNome.setText(u.getNome());
        txtSenha.setText(u.getSenha());
        TipoUsuario t = u.getTipoUsuario();
        carregarImagensUsuario(u.getId());

        //comboBox.setSelectedItem(new Item<TipoUsuario>(t, t.toString()));
        comboBox.getModel().setSelectedItem(new Item<TipoUsuario>(t, t.toString()));
        //comboBox.setSelectedItem();
    }

    private void btnIniciarCapturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarCapturaActionPerformed
        btnSalvarImgTreinamento.setEnabled(true);
        
        tVideo = new Thread(new Runnable() {
            @Override
            public void run() {
                mostraVideo(videoCapturePane, tVideo);
            }
        });
        
        tVideo.start();
        btnIniciarCaptura.setEnabled(false);
        btnPausarCaptura.setEnabled(true);
    }//GEN-LAST:event_btnIniciarCapturaActionPerformed

    private void btnPausarCapturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPausarCapturaActionPerformed
        
        btnSalvarImgTreinamento.setEnabled(false);
        tVideo.interrupt();
        tVideo = null;
        //videoCapturePane.repaint();
        btnIniciarCaptura.setEnabled(true);
        btnPausarCaptura.setEnabled(false);
    }//GEN-LAST:event_btnPausarCapturaActionPerformed

    private void btnSalvarImgTreinamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarImgTreinamentoActionPerformed
        
        if (txtId.getText().equals("")) {
            Utils.msg("Deve se primeiro gravar o usuário", "Erro", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Utils ut = new Utils();
        ImagensTreinamentoDAO imgsTrDAO = new ImagensTreinamentoDAO();
        //List<ImagemTreinamento> imgsTreinamento = new ArrayList<>();

        try {
            
            getFacesRecortadas().forEach((m) -> {
                //Adiciona na lista de treinamento
                String path = "resources\\pgmTrainer\\" + txtId.getText() + "_" + System.currentTimeMillis() + ".pgm";
                BufferedImage resized = ut.resize(ut.matToBufferedImage(m), 120, 120);
                //Pré processa a imagem
                PreProcessing p = new PreProcessing();
                BufferedImage imgPreProcessed = p.enhance(resized);
                if (!(salvarPgm2(path, ut.bufferedImageToMat(imgPreProcessed)).equals(""))) {
                    ImagemTreinamento img = new ImagemTreinamento();

                    //Text2ImageConverter txtImg = new Text2ImageConverter();
                    //txtImg.writeImage(txtLogin.getText(), imgPreProcessed, 5, imgPreProcessed.getHeight() - 5);
                    img.setCaminho(path);
                    img.setUsuario(new UsuarioDAO().getUsuarioById(Integer.parseInt(txtId.getText())));
                    img.setImgBitMap(imgPreProcessed);
                    try {
                        imgsTrDAO.inserirImagem(img);
                        addImgToTable(imgPreProcessed);
                    } catch (SQLException ex) {
                        Utils.msg("Erro ao gravar caminho da imagem no banco: " + imgsTrDAO.getMensagemErro(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    Utils.msg("Erro ao gravar imagem em disco", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            });
            
            setSalvandoPGM(false);
            
        } catch (Exception e) {
            System.out.println(getMensagem() + " // " + e.toString());
            setSalvandoPGM(false);
        }

    }//GEN-LAST:event_btnSalvarImgTreinamentoActionPerformed

    private void gridImgsTreinamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gridImgsTreinamentoMouseClicked
        
        Object img = gridImgsTreinamento.getModel().getValueAt(gridImgsTreinamento.getSelectedRow(), 0);
        //Object img =  gridImgsTreinamento.getV
    }//GEN-LAST:event_gridImgsTreinamentoMouseClicked

    private void txtNomeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyTyped
    }//GEN-LAST:event_txtNomeKeyTyped
    
    private void preencherCmbTipoUsuario() throws SQLException {
        List<TipoUsuario> lstTipoUsuario = new TipoUsuarioDAO().obterTiposUsuario();
        
        comboBox.setLocation(10, 266);
        comboBox.setSize(349, 20);
        
        for (TipoUsuario tu : lstTipoUsuario) {
            comboBox.addItem(
                    new Item<TipoUsuario>(tu, tu.toString())
            );
        }
        
        this.jPanel1.add(comboBox);
    }
    
    private void updateOffset() {
        qtdUsuariosCadastros = new UsuarioDAO().getCount();
        if (qtdUsuariosCadastros > 1) {
            btnProximo.setEnabled(true);
        }
        
        if (usuarioOffset > 0) {
            btnAnterior.setEnabled(true);
        }
    }
    
    private Boolean validarDados() {
        if (txtLogin.getText().equals("")) {
            return false;
        }
        
        if (txtNome.getText().equals("")) {
            return false;
        }
        
        if (txtSenha.getPassword().equals("")) {
            return false;
        }
        
        return true;
    }
    
    public void carregarImagensUsuario(int id) throws SQLException {
        
        List<ImagemTreinamento> imagens = new ImagensTreinamentoDAO().getImagensByUsuario(id);
        
        limparImagensTreinamento();
        
        for (ImagemTreinamento pgm : imagens) {
            Utils ut = new Utils();
            Mat mat = ut.carregarImgMat(pgm.getCaminho(), CvType.CV_8UC1);
            
            if (mat == null) {
                return;
            }
            
            BufferedImage img = ut.matToBufferedImage(mat);
            addImgToTable(img);
        }
        
    }
    
    public void limparImagensTreinamento() {
        DefaultTableModel modelo = (DefaultTableModel) gridImgsTreinamento.getModel();
        modelo.setNumRows(0);
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
            java.util.logging.Logger.getLogger(CadastroUsuarios.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroUsuarios.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroUsuarios.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroUsuarios.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        new Thread(new Runnable() {
            @Override
            public void run() {
                CadastroUsuarios tela = new CadastroUsuarios();
                tela.setVisible(true);
            }
        }).start();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnGravar;
    private javax.swing.JButton btnIniciarCaptura;
    private javax.swing.JButton btnLimparCampos;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPausarCaptura;
    private javax.swing.JButton btnProximo;
    private javax.swing.JButton btnSalvarImgTreinamento;
    private javax.swing.JCheckBox cBoxAlterarSenha;
    private javax.swing.JTable gridImgsTreinamento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JTextField txtNome;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JPanel videoCapturePane;
    // End of variables declaration//GEN-END:variables
}
