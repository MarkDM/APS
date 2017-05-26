/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aps.app.view;

import com.aps.app.bean.ChatMessage;
import com.aps.app.bean.ChatMessage.Action;
import com.aps.app.bean.TelaChat;
import com.aps.app.service.ClienteService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Marcos
 */
public class ClienteFrame extends TelaChat {

    private Socket socket;
    private ChatMessage message;
    private ClienteService service;
    private Map<String, ChatPrivado> conversasPrivadas = new HashMap<String, ChatPrivado>();
    private long tamanhoPermitidoKB = 5120; //Igual a 5MB
    private boolean temArquivo = false;

    private String nomeArquivo;
    private byte[] conteudoArquivo;

    /**
     * Creates new form ClienteFrame
     */
    public ClienteFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private class ListenerSocket implements Runnable {

        private ObjectInputStream input;

        public ListenerSocket(Socket socket) {
            try {
                input = new ObjectInputStream(socket.getInputStream());
            } catch (IOException ex) {
                Logger.getLogger(ClienteFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public void run() {
            ChatMessage message = null;

            try {
                while ((message = (ChatMessage) input.readObject()) != null) {
                    Action action = message.getAction();

                    if (action.equals(action.CONNECT)) {
                        connected(message);
                    } else if (action.equals(action.DISCONECT)) {
                        socket.close();
                    } else if (action.equals(action.SEND_ONE)) {
                        receive(message);

                    } else if (action.equals(action.USERS_ONLINE)) {
                        refreshOnlines(message);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(ListenerSocket.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClienteFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void refreshOnlines(ChatMessage message) {
        Set<String> names = message.getSetOnline();
        names.remove((String) message.getName());

        String[] array = (String[]) names.toArray(new String[names.size()]);

        this.listOnlines.setListData(array);
        this.listOnlines.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.listOnlines.setLayoutOrientation(JList.VERTICAL);
    }

    public void receive(ChatMessage message) {
        if (message.getConteudoArquivo() != null) {
            try {
                receiveArquivo(message);
            } catch (IOException ex) {
                Logger.getLogger(ClienteFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (message.getAction().equals(Action.SEND_ONE)) {

            /*
             Verifica se já existe uma conversa em aberto com o sender da mensagem
             caso houver apenas chama o receive() do chat privado
             */
            for (Map.Entry<String, ChatPrivado> kv : conversasPrivadas.entrySet()) {
                if (kv.getKey().equals(message.getName())) {
                    kv.getValue().receive(message);
                    return;
                }
            }

            //Se não houver inicia um novo chat privado
            ChatPrivado chatPrivado = new ChatPrivado(message, this.service, this.conversasPrivadas);
            chatPrivado.setVisible(true);
            chatPrivado.receive(message);

            conversasPrivadas.put(message.getName(), chatPrivado);

        } else {
            this.txtAreaReceive.append(montarInfoMensagem(message, null));
        }

    }

    public void receiveArquivo(ChatMessage message) throws FileNotFoundException, IOException {
        FileOutputStream fos;

        File criarPasta = new File("arquivos");
        String caminho = criarPasta.getAbsolutePath() + "/";

        if (criarPasta.exists()) {

            fos = new FileOutputStream(caminho + message.getNomeArquivo());
            fos.write(message.getConteudoArquivo());
            fos.close();
            Runtime.getRuntime().exec("explorer " + criarPasta.getAbsolutePath());
        } else {
            criarPasta.mkdir();
            fos = new FileOutputStream(caminho + message.getNomeArquivo());
            fos.write(message.getConteudoArquivo());
            fos.close();
            Runtime.getRuntime().exec("explorer " + criarPasta.getAbsolutePath());
        }
    }

    private void connected(ChatMessage message) {

        if (message.getText().equals("NO")) {
            this.txtName.setText("");
            alertaErro("Não foi possível conectar, nome de usuário já existe", "Erro ao conectar");
            return;
        }

        alerta("Você está conectado ao chat", "Conectado com sucesso");
        this.message = message;
        this.btnConectar.setEnabled(false);
        this.txtName.setEnabled(false);
        this.btnSair.setEnabled(true);
        this.txtAreaSend.setEnabled(true);
        this.btnEnviar.setEnabled(true);
        this.btnLimpar.setEnabled(true);
        this.btnSelecionarArquivo.setEnabled(true);
    }

    private void disconnected() throws IOException {

        if (this.message == null) {
            return;
        }

        ChatMessage message = new ChatMessage();
        message.setName(this.message.getName());
        message.setAction(Action.DISCONECT);
        this.service.send(message);
        //socket.close();
        this.btnConectar.setEnabled(true);
        this.txtName.setEnabled(true);
        this.btnSair.setEnabled(false);
        this.txtAreaSend.setEnabled(false);
        this.btnEnviar.setEnabled(false);
        this.btnLimpar.setEnabled(false);
        alerta("Você saiu do chat", "Desconectado");
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtName = new javax.swing.JTextField();
        btnConectar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listOnlines = new javax.swing.JList();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaReceive = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAreaSend = new javax.swing.JTextArea();
        btnLimpar = new javax.swing.JButton();
        btnEnviar = new javax.swing.JButton();
        btnSelecionarArquivo = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblNomeArquivo = new javax.swing.JLabel();
        lblTamanhoArquivo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Conectar"));

        txtName.setName("txtName"); // NOI18N

        btnConectar.setText("Conectar");
        btnConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConectarActionPerformed(evt);
            }
        });

        btnSair.setText("Sair");
        btnSair.setEnabled(false);
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnConectar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConectar)
                    .addComponent(btnSair))
                .addGap(0, 20, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Onlines"));

        listOnlines.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        listOnlines.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listOnlinesMouseClicked(evt);
            }
        });
        listOnlines.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listOnlinesValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(listOnlines);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtAreaReceive.setEditable(false);
        txtAreaReceive.setColumns(20);
        txtAreaReceive.setRows(5);
        jScrollPane1.setViewportView(txtAreaReceive);

        txtAreaSend.setColumns(20);
        txtAreaSend.setFont(new java.awt.Font("Arial Unicode MS", 0, 12)); // NOI18N
        txtAreaSend.setRows(5);
        jScrollPane2.setViewportView(txtAreaSend);

        btnLimpar.setText("Limpar");
        btnLimpar.setEnabled(false);
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        btnEnviar.setText("Enviar");
        btnEnviar.setEnabled(false);
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        btnSelecionarArquivo.setText("Selecionar Arquivo");
        btnSelecionarArquivo.setEnabled(false);
        btnSelecionarArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionarArquivoActionPerformed(evt);
            }
        });

        jLabel1.setText("Tamanho:");

        jLabel2.setText("Nome:");

        lblNomeArquivo.setText("         ");

        lblTamanhoArquivo.setText("      ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnSelecionarArquivo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLimpar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblTamanhoArquivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(29, 29, 29)
                                .addComponent(lblNomeArquivo)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSelecionarArquivo)
                    .addComponent(btnLimpar)
                    .addComponent(btnEnviar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblNomeArquivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblTamanhoArquivo))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConectarActionPerformed
        String name = this.txtName.getText();

        if (!name.isEmpty()) {
            try {
                //Cria mensagem do tipo CONNECT
                this.message = new ChatMessage();
                this.message.setAction(Action.CONNECT);
                this.message.setName(name);

                this.service = new ClienteService();
                this.socket = service.connect();

                new Thread(new ListenerSocket(this.socket)).start();

                this.service.send(this.message);
            } catch (Exception e) {
                alertaErro(e.getMessage(), "Erro ao conectar!");
            }
        }
    }//GEN-LAST:event_btnConectarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        try {
            disconnected();
        } catch (IOException ex) {
            Logger.getLogger(ClienteFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        this.txtAreaSend.setText("");
        this.conteudoArquivo = null;
    }//GEN-LAST:event_btnLimparActionPerformed

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        String text = this.txtAreaSend.getText();

        if (text.isEmpty() && !temArquivo) {
            return;
        }

        String name = this.message.getName();
        this.message = new ChatMessage();

        int selected = this.listOnlines.getSelectedIndex();

        if (selected > -1) {
            this.message.setNameReserved((String) this.listOnlines.getSelectedValue());
            this.message.setAction(Action.SEND_ONE);
            this.listOnlines.clearSelection();
        } else {
            this.message.setAction(Action.SEND_ALL);
        }

        if (temArquivo) {
            this.message.setNomeArquivo(nomeArquivo);
            this.message.setConteudoArquivo(conteudoArquivo);
        }

        this.message.setName(name);
        this.message.setText(text);

        this.txtAreaReceive.append(montarInfoMensagem(message, "Você"));

        this.service.send(message);
        this.txtAreaSend.setText("");
        this.temArquivo = false;
        this.lblNomeArquivo.setText("");
        this.lblTamanhoArquivo.setText("");
    }//GEN-LAST:event_btnEnviarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            disconnected();
        } catch (Exception ex) {
            Logger.getLogger(ClienteFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosing

    private void listOnlinesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listOnlinesValueChanged

    }//GEN-LAST:event_listOnlinesValueChanged

    private void btnSelecionarArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionarArquivoActionPerformed
        FileInputStream fis;
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.setDialogTitle("Escolha o arquivo");

            if (chooser.showOpenDialog(this) == JFileChooser.OPEN_DIALOG) {
                File fileSelected = chooser.getSelectedFile();

                long tamanhoArquivo = fileSelected.length() / 1024;

                if (tamanhoArquivo > tamanhoPermitidoKB) {
                    JOptionPane.showMessageDialog(this, "Arquivo com tamanho maior que o permitido!", "Aviso",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                byte[] bFile = new byte[(int) fileSelected.length()];
                fis = new FileInputStream(fileSelected);
                fis.read(bFile);
                fis.close();

                this.conteudoArquivo = bFile;
                this.nomeArquivo = fileSelected.getName();
                lblNomeArquivo.setText(this.nomeArquivo);
                lblTamanhoArquivo.setText(tamanhoArquivo + " KB");
                txtAreaSend.append("\n" + nomeArquivo + "\n");
                temArquivo = true;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSelecionarArquivoActionPerformed

    private void listOnlinesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listOnlinesMouseClicked
        JList list = (JList) evt.getSource();
        if (evt.getClickCount() == 2) {
            //int index = list.locationToIndex(evt.getPoint());
            // System.out.println("index: " + index);
            int selected = list.getSelectedIndex();

            if (selected > -1) {
                ChatMessage message = new ChatMessage();
                String nameReserved = (String) this.listOnlines.getSelectedValue();
                message.setNameReserved(nameReserved);
                message.setAction(Action.SEND_ONE);
                message.setName(txtName.getText());

                /*
                 Verifica se já existe uma conversa em aberto com o sender da mensagem
                 caso houver apenas chama o receive() do chat privado
                 */
                for (Map.Entry<String, ChatPrivado> kv : conversasPrivadas.entrySet()) {
                    if (kv.getKey().equals(nameReserved)) {
                        //kv.getValue().receive(message);
                        return;
                    }
                }

                ChatPrivado chat = new ChatPrivado(message, this.service, this.conversasPrivadas);
                chat.setVisible(true);
                conversasPrivadas.put(nameReserved, chat);
            }
        }


    }//GEN-LAST:event_listOnlinesMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConectar;
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSelecionarArquivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblNomeArquivo;
    private javax.swing.JLabel lblTamanhoArquivo;
    private javax.swing.JList listOnlines;
    private javax.swing.JTextArea txtAreaReceive;
    private javax.swing.JTextArea txtAreaSend;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
