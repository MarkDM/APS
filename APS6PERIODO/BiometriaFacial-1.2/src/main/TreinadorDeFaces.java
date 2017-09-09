/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.github.wihoho.Trainer;
import com.github.wihoho.constant.FeatureType;
import com.github.wihoho.jama.Matrix;
import com.github.wihoho.training.CosineDissimilarity;
import com.github.wihoho.training.FileManager;
import java.io.File;
import java.util.List;
import utils.Utils;

/**
 *
 * @author marcu
 */
public class TreinadorDeFaces {

    ClassLoader classLoader = getClass().getClassLoader();
    public String mensagemErro;

    public void Treinar(List<String> pgms, String identificador) throws Exception {
        // Build a trainer
        Trainer trainer = Trainer.builder().metric(new CosineDissimilarity()).featureType(FeatureType.PCA)
                .numberOfComponents(1).k(1).build();
//
//        //Set de imagens, usa duas imagens para treinar e outra para reconhecer
//        String marcos1 = "faces/marcos/marcos2.pgm";
//        String marcos2 = "faces/marcos/marcos0.pgm";
//
//        String marcos3 = "faces/marcos/marcos1.pgm";
//
//        trainer.add(convertToMatrix(marcos1), "Marcos");
//        trainer.add(convertToMatrix(marcos2), "Marcos");

        Utils ut = new Utils();

        for (String pgm : pgms) {
            trainer.add(ut.convertToMatrix(pgm), identificador);
        }

        // train
        trainer.train();

    }

}
