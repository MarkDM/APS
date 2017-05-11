/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aps.app;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Marcos & Igor
 */
public class Log {

    //Informação
    public static void i(String msg) {

        Date data = new Date();
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println("Info: " + f.format(data) + " - " + msg);
    }

    public static String getI(String msg) {

        Date data = new Date();
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return ("Info: " + f.format(data) + " - " + msg + "\n");
    }

    //Erro
    public static void e(String msg) {

        Date data = new Date();
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        System.out.println("Erro: " + f.format(data) + " - " + msg);
    }

    public static String getE(String msg) {

        Date data = new Date();
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        return ("Erro: " + f.format(data) + " - " + msg + "\n");
    }

}
