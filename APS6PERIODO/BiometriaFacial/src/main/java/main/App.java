package main;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.github.wihoho.Trainer;
import com.github.wihoho.constant.FeatureType;
import com.github.wihoho.jama.Matrix;
import com.github.wihoho.training.CosineDissimilarity;
import com.github.wihoho.training.FileManager;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class App {

    ClassLoader classLoader = getClass().getClassLoader();
    public String mensagemErro;

    public void testTraining() throws Exception {
        // Build a trainer
        Trainer trainer = Trainer.builder().metric(new CosineDissimilarity()).featureType(FeatureType.PCA)
                .numberOfComponents(1).k(1).build();

        //Set de imagens, usa duas imagens para treinar e outra para reconhecer
        String marcos1 = "faces/marcos/marcos2.pgm";
        String marcos2 = "faces/marcos/marcos0.pgm";

        String marcos3 = "faces/marcos/marcos1.pgm";

        trainer.add(convertToMatrix(marcos1), "Marcos");
        trainer.add(convertToMatrix(marcos2), "Marcos");

        // train
        trainer.train();

        // recognize
        System.out.println("Pessoa encontrada: " + trainer.recognize(convertToMatrix(marcos3)));
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
            return vectorize(FileManager.convertPGMtoMatrix(file.getAbsolutePath()));
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
//    public Matrix convertPGMtoMatrix(String address) throws IOException {
//        FileInputStream fileInputStream = new FileInputStream(address);
//        Scanner scan = new Scanner(fileInputStream);
//
//        // Discard the magic number
//        scan.nextLine();
//        // Read pic width, height and max value
//        int picWidth = scan.nextInt();
//        int picHeight = scan.nextInt();
//
//        fileInputStream.close();
//
//        // Now parse the file as binary data
//        fileInputStream = new FileInputStream(address);
//        DataInputStream dis = new DataInputStream(fileInputStream);
//
//        // look for 4 lines (i.e.: the header) and discard them
//        int numnewlines = 3;
//        while (numnewlines > 0) {
//            char c;
//            do {
//                c = (char) (dis.readUnsignedByte());
//            } while (c != '\n');
//            numnewlines--;
//        }
//
//        // read the image data
//        double[][] data2D = new double[picHeight][picWidth];
//        for (int row = 0; row < picHeight; row++) {
//            for (int col = 0; col < picWidth; col++) {
//
//                try {
//                    data2D[row][col] = dis.readUnsignedByte();
//                } catch (Exception e) {
//                    mensagemErro = "Erro ao ler byte na posição: linha: " + row + " coluna: " + col; 
//                }
//            }
//        }
//
//        return new Matrix(data2D);
//    }
}
