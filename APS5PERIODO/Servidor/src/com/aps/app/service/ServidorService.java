/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aps.app.service;

import com.aps.app.Log;
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
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Marcos
 */
public class ServidorService {

    private ServerSocket serverSocket;
    private Socket socket;
    private Map<String, ObjectOutputStream> mapOnlines = new HashMap<String, ObjectOutputStream>();

    public ServidorService(int porta) {
        try {

            serverSocket = new ServerSocket(porta);
            Log.i("Servidor on!");
            while (true) {
                socket = serverSocket.accept();

                new Thread(new ListenerSocket(socket)).start();
            }

        } catch (IOException ex) {
            Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ServidorService() {
        try {

            serverSocket = new ServerSocket(5555);
            Log.i("Servidor on!");
            while (true) {
                socket = serverSocket.accept();
                Log.i("Conexão de socket detectada");

                new Thread(new ListenerSocket(socket)).start();
            }

        } catch (IOException ex) {
            Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean connect(ChatMessage message, ObjectOutputStream output) {
        if (mapOnlines.size() == 0) {
            message.setText("YES");
            Log.i("Usuário: " + message.getName() + " se conectou ao chat");
            send(message, output);
            return true;
        }

        if (mapOnlines.containsKey(message.getName())) {
            message.setText("NO");
            send(message, output);
            return false;
        } else {
            message.setText("YES");
            send(message, output);
            return true;
        }
    }

    private void send(ChatMessage message, ObjectOutputStream output) {
        try {
            output.writeObject(message);
        } catch (IOException ex) {
            Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sendOne(ChatMessage message) {

        //Envia apenas para o usuario selecionado na lista
        for (Map.Entry<String, ObjectOutputStream> kv : mapOnlines.entrySet()) {
            if (kv.getKey().equals(message.getNameReserved())) {
                try {
                    kv.getValue().writeObject(message);
                } catch (IOException ex) {
                    Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    private void sendAll(ChatMessage message) {
        for (Map.Entry<String, ObjectOutputStream> kv : mapOnlines.entrySet()) {
            if (!kv.getKey().equals(message.getName())) {
                message.setAction(Action.SEND_ONE);

                try {

                    Log.i("Enviando mensagem de broadcast para: " + kv.getKey());
                    kv.getValue().writeObject(message);
                } catch (IOException ex) {
                    Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        Log.i("Broadcast finalizado");
    }

    public void disconnect(ChatMessage message, ObjectOutputStream output) {
        //Remove da lista de usuários o usuário com a chave igual ao seu nome
        mapOnlines.remove(message.getName());
        message.setText("Deixou o chat");
        message.setAction(Action.SEND_ONE);
        sendAll(message);

        Log.i("Usuário: " + message.getName() + " se desconectou do chat");
    }

    private void sendOnlines() {

        Set<String> setNames = new HashSet<String>();

        //Prenche o set com os nomes dos usuários
        for (Map.Entry<String, ObjectOutputStream> kv : mapOnlines.entrySet()) {
            setNames.add(kv.getKey());
        }

        ChatMessage message = new ChatMessage();
        message.setAction(Action.USERS_ONLINE);
        message.setSetOnline(setNames);

        //Envia o set para cada um dos usuários online
        for (Map.Entry<String, ObjectOutputStream> kv : mapOnlines.entrySet()) {
            message.setName(kv.getKey());

            try {
                kv.getValue().writeObject(message);
            } catch (IOException ex) {
                Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private class ListenerSocket implements Runnable {

        private ObjectOutputStream output;
        private ObjectInputStream input;
        private Socket socket;

        public ListenerSocket(Socket socket) {
            this.socket = socket;
            
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
                            sendOnlines();
                        }

                    } else if (action.equals(action.DISCONECT)) {
                        disconnect(message, output);
                        sendOnlines();
                        return;
                    } else if (action.equals(action.SEND_ONE)) {

                        sendOne(message);

                    } else if (action.equals(action.SEND_ALL)) {

                        sendAll(message);

                    }
                }
                
                Log.i("Conexão de socket encerrada");
            } catch (IOException ex) {
                disconnect(message, output);
                sendOnlines();
                //Logger.getLogger(ListenerSocket.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
