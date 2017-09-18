package main;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.github.wihoho.Trainer;
import com.github.wihoho.constant.FeatureType;
import com.github.wihoho.jama.Matrix;
import com.github.wihoho.training.CosineDissimilarity;
import com.github.wihoho.training.EuclideanDistance;
import com.github.wihoho.training.FileManager;
import com.github.wihoho.training.L1Distance;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class App {

    ClassLoader classLoader = getClass().getClassLoader();
    public String mensagemErro;

    public void testTraining() throws Exception {

        String pessoaReconhecida = "";
        List<String> pgmsTrain = new ArrayList<String>();
//        pgmsTrain.add("faces/marcos/s1/1.pgm");
//        pgmsTrain.add("faces/marcos/s1/2.pgm");
//        pgmsTrain.add("faces/marcos/s1/3.pgm");
//        pgmsTrain.add("faces/marcos/s1/4.pgm");
//        pgmsTrain.add("faces/marcos/s1/5.pgm");
//        pgmsTrain.add("faces/marcos/s1/6.pgm");
//        pgmsTrain.add("faces/marcos/s1/7.pgm");

        pgmsTrain.add("faces/marcos/s2/Jolie0.pgm");
        pgmsTrain.add("faces/marcos/s2/Jolie1.pgm");
        pgmsTrain.add("faces/marcos/s2/Jolie2.pgm");

        for (int i = 1; i < pgmsTrain.size(); i++) {

            for (int j = 1; j <= pgmsTrain.size(); j++) {

                System.out.println("numberOfComponents:" + i + " K:" + j);

                // Build a trainer
                Trainer trainer = Trainer.builder().metric(new EuclideanDistance()).featureType(FeatureType.LDA)
                        .numberOfComponents(i).k(j).build();

                String marcosRec = "faces/marcos/s2/Marcos1.pgm";
                //String marcosRec = "faces/marcos/s1/1.pgm";

                for (String pgm : pgmsTrain) {
                    trainer.add(convertToMatrix(pgm), "Jolie");
                }

                // train
                trainer.train();

                // recognize
                pessoaReconhecida = trainer.recognize(convertToMatrix(marcosRec));
                
                assertEquals("Marcos", pessoaReconhecida);

                if (!pessoaReconhecida.equals("")) {
                    System.out.println(pessoaReconhecida);
                    //break;
                }
            }

        }

    }

    private Matrix convertToMatrix(String fileAddress) {
        //File file = new File(classLoader.getResource(fileAddress).getFile());
        //File file = new File(classLoader.getResource("/" + fileAddress).getFile());
        String relativeAdress = "src/main/java/main/" + fileAddress;

        File file = new File(relativeAdress);

        if (!file.exists()) {
            System.out.println("Arquivo: " + relativeAdress + " não encontrado");
            return null;
        }

        try {
            return vectorize(convertPGMtoMatrix(file.getAbsolutePath()));
        } catch (Exception e) {
            mensagemErro = "Erro ao converter PGM para matrix";
        }

        return null;
    }

    // Convert a m by n matrix into a m*n by 1 matrix
    static Matrix vectorize(Matrix input) {
        int m = input.getRowDimension();
        int n = input.getColumnDimension();

        Matrix result = new Matrix(m * n, 1);
        for (int p = 0; p < n; p++) {
            for (int q = 0; q < m; q++) {
                result.set(p * m + q, 0, input.get(q, p));
            }
        }
        return result;
    }

//    //Apenas para teste, retirar depois
    public Matrix convertPGMtoMatrix(String address) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(address);
        Scanner scan = new Scanner(fileInputStream);

        // Discard the magic number
        scan.nextLine();
        scan.nextLine();
        // Read pic width, height and max value
        int picWidth = scan.nextInt();
        int picHeight = scan.nextInt();

        fileInputStream.close();

        // Now parse the file as binary data
        fileInputStream = new FileInputStream(address);
        DataInputStream dis = new DataInputStream(fileInputStream);

        // look for 4 lines (i.e.: the header) and discard them
        int numnewlines = 3;
        while (numnewlines > 0) {
            char c;
            do {
                c = (char) (dis.readUnsignedByte());
            } while (c != '\n');
            numnewlines--;
        }

        // read the image data
        double[][] data2D = new double[picHeight][picWidth];
        for (int row = 0; row < picHeight; row++) {
            for (int col = 0; col < picWidth; col++) {

                try {
                    data2D[row][col] = dis.readUnsignedByte();
                } catch (Exception e) {
                    mensagemErro = "Erro ao ler byte na posição: linha: " + row + " coluna: " + col;
                }
            }
        }

        return new Matrix(data2D);
    }
}
