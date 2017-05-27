/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aps.app.service;

import com.aps.app.bean.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcos
 */
public class ClienteService {

    private Socket socket;
    private ObjectOutputStream output;

    public Socket connect() {
        try {
            String ip = Configuracoes.getIp();
            int porta = Configuracoes.getPorta();

            this.socket = new Socket(ip, porta);
            this.output = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            throw new RuntimeException("Não foi possível conectar ao servidor. \n Servidor está fora do ar ou as informações de configuração estão incorretas. \n Detalhes do erro: " + ex.getMessage());
        }
        return socket;
    }

    public void send(ChatMessage message) {
        try {
            this.output.writeObject(message);
        } catch (IOException ex) {
            throw new RuntimeException("Servidor fora do ar! " + ex.getMessage());
        }
    }
}
