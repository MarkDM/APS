/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.github.wihoho.Trainer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Utils;

/**
 *
 * @author marcu
 */
public class ReconhecedorDeFaces {

    //    public String reconhecer(Trainer t, String pgm) {
    //
    //        Utils ut = new Utils();
    //
    //        return t.recognize(ut.convertToMatrix(pgm));
    //
    //    }
    public List<String> reconhecer(List<TreinadorDeFaces> listaTreinadores, List<String> listaPgmsReconhecer) {

        Utils ut = new Utils();
        List<String> idsReconhecidos = new ArrayList<>();

        for (String pgmReconhecer : listaPgmsReconhecer) {
            for (TreinadorDeFaces t : listaTreinadores) {
                String idReconhecido = t.getTrainer().recognize(ut.convertToMatrix(pgmReconhecer));
                idsReconhecidos.add(idReconhecido);
            }
        }

        return idsReconhecidos;
    }
}
