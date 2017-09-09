/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.github.wihoho.Trainer;
import utils.Utils;

/**
 *
 * @author marcu
 */
public class ReconhecedorDeFaces {

    public String reconhecer(Trainer t, String pgm) {

        Utils ut = new Utils();

        return t.recognize(ut.convertToMatrix(pgm));
    }
}
