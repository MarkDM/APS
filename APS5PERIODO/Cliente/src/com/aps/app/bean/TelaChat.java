/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aps.app.bean;

import com.aps.app.service.ClienteService;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author marcos
 */
public abstract class TelaChat extends javax.swing.JFrame {


    public String montarInfoMensagem(ChatMessage message, String sender) {
        Date data = new Date();
        SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");
        return "(" + (sender == null ? message.getName() : sender) + " - " + f.format(data) + ")\n" + message.getText() + "\n\n";
    }
    
    public abstract void receive(ChatMessage message);

    public void alertaErro(String msg, String title) {
        JOptionPane.showMessageDialog(this, msg, title, JOptionPane.ERROR_MESSAGE);
    }

    public void alerta(String msg, String title) {
        JOptionPane.showMessageDialog(this, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

}
