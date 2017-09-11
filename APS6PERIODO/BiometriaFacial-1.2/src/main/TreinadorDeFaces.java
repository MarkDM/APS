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
import java.util.ArrayList;
import java.util.List;
import utils.Utils;

/**
 *
 * @author marcu
 */
public class TreinadorDeFaces {

    ClassLoader classLoader = getClass().getClassLoader();
    public String mensagemErro;
    private Trainer trainer;
    private String identificador;
    private List<String> listPgmTrain = new ArrayList<String>();

    public Trainer getTrainer() {
        return trainer;

    }

    private void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public String getIdentificador() {
        return identificador;
    }

    public List<String> getListPgmTrain() {
        return listPgmTrain;
    }

    public void add2ListaPgmTrain(String pathPgm) {
        listPgmTrain.add(pathPgm);
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

//    public void Treinar(List<String> pgms, String identificador) throws Exception {
//        // Build a trainer
//        trainer = Trainer.builder().metric(new CosineDissimilarity()).featureType(FeatureType.PCA)
//                .numberOfComponents(1).k(1).build();
//        Utils ut = new Utils();
//
//        for (String pgm : pgms) {
//            trainer.add(ut.convertToMatrix(pgm), identificador);
//        }
//
//        try {
//            // train
//            trainer.train();
//            System.out.println("Treinamento concluido com sucesso");
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//    }
    public void Treinar(List<TreinadorDeFaces> listaDeTreinadores) throws Exception {

        for (TreinadorDeFaces t : listaDeTreinadores) {
            // Build a trainer
            trainer = Trainer.builder().metric(new CosineDissimilarity()).featureType(FeatureType.PCA)
                    .numberOfComponents(1).k(1).build();
            Utils ut = new Utils();

            for (String pgm : t.getListPgmTrain()) {
                trainer.add(ut.convertToMatrix(pgm), t.getIdentificador());
            }

            try {
                // train
                trainer.train();
                System.out.println("Treinamento concluido com sucesso");
            } catch (Exception e) {
                mensagemErro = "Erro ao realizar treinamento";
            }
        }

    }

}
