/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aps.app.bean;

/**
 *
 * @author Marcos
 */
public class Configuracoes {

    private static String ip = "127.0.0.1";
    private static int porta = 5555;

    public static String getIp() {
        return ip;
    }

    public static void setIp(String ip) {
        Configuracoes.ip = ip;
    }

    public static int getPorta() {
        return porta;
    }

    public static void setPorta(int porta) {
        Configuracoes.porta = porta;
    }

}
