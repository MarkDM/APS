/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marcu
 */
public class main {

    public static void main(String[] args) {
        List<String> pgmTrain = new ArrayList();

        pgmTrain.add("src\\pgm\\Marcos0.pgm");
        pgmTrain.add("src\\pgm\\Marcos1.pgm");
        pgmTrain.add("src\\pgm\\Marcos2.pgm");

        Trainer t = new Trainer();

        t.train(pgmTrain);

        Recognizer r = new Recognizer();
        
        r.recogize("src\\pgm\\MarcosD1.pgm");
    }
}
