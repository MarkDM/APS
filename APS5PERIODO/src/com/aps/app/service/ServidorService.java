/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aps.app.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.aps.app.bean.ChatMessage;
import com.aps.app.bean.ChatMessage.Action;
import java.net.Inet4Address;

/**
 *
 * @author Marcos
 */
public class ServidorService {

    private ServerSocket serverSocket;
    private Socket socket;
    private Map<String, ObjectOutputStream> mapOnlines = new HashMap<String, ObjectOutputStream>();

    public ServidorService() {
        try {

            serverSocket = new ServerSocket(5555);
            System.out.println("Servidor on!");

            while (true) {
                socket = serverSocket.accept();

                new Thread(new ListenerSocket(socket)).start();
            }

        } catch (IOException ex) {
            Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private boolean connect(ChatMessage message, ObjectOutputStream output) {
        if (mapOnlines.size() == 0) {
            message.setText("YES");
            sendOne(message, output);
            return true;
        }

        for (Map.Entry<String, ObjectOutputStream> kv : mapOnlines.entrySet()) {
            if (kv.getKey().equals(message.getName())) {
                message.setText("NO");
                sendOne(message, output);
                return false;
            } else if (true) {
                message.setText("YES");
                sendOne(message, output);
                return true;
            }

        }

        return false;
    }

    private void sendOne(ChatMessage message, ObjectOutputStream output) {
        try {
            output.writeObject(message);
        } catch (IOException ex) {
            Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sendAll(ChatMessage message) {
        for (Map.Entry<String, ObjectOutputStream> kv : mapOnlines.entrySet()) {
            if (!kv.getKey().equals(message.getName())) {
                message.setAction(Action.SEND_ONE);

                try {

                    System.out.println("Enviando mensagem de broadcast para: " + kv.getKey());
                    kv.getValue().writeObject(message);
                } catch (IOException ex) {
                    Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        System.out.println("Broadcast finalizado");
    }

    public void disconnect(ChatMessage message, ObjectOutputStream output) {
        //Remove da lista de usuários o usuário com a chave igual ao seu nome
        mapOnlines.remove(message.getName());
        message.setText("deixou o chat");
        message.setAction(Action.SEND_ONE);
        sendAll(message);

        System.out.println("Usuário: " + message.getName() + " saiu do chat");
    }

    private class ListenerSocket implements Runnable {

        private ObjectOutputStream output;
        private ObjectInputStream input;

        public ListenerSocket(Socket socket) {
            try {
                this.output = new ObjectOutputStream(socket.getOutputStream());
                this.input = new ObjectInputStream(socket.getInputStream());
            } catch (IOException ex) {
                Logger.getLogger(ListenerSocket.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        @Override
        public void run() {
            ChatMessage message = null;

            try {
                while ((message = (ChatMessage) input.readObject()) != null) {
                    Action action = message.getAction();

                    if (action.equals(action.CONNECT)) {

                        if (connect(message, output)) {
                            mapOnlines.put(message.getName(), output);
                        }

                    } else if (action.equals(action.DISCONECT)) {
                        disconnect(message, output);
                        return;
                    } else if (action.equals(action.SEND_ONE)) {

                        sendOne(message, output);

                    } else if (action.equals(action.SEND_ALL)) {

                        sendAll(message);

                    } else if (action.equals(action.USERS_ONLINE)) {

                    }
                }
            } catch (IOException ex) {
                disconnect(message, output);
                Logger.getLogger(ListenerSocket.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
