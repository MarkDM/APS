/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.util.List;

/**
 *
 * @author marcu
 */
public class Trainer extends LaplacianFaces {

    private String tResult = "";
    private String localPath;

    /**
     *
     * @param pgmst
     * @param pgmsTrain
     */
    public boolean treinar(List<FileTrain> pgmsTrain) {

        localPath = new File("").getAbsolutePath();
        // String pathFiles = localPath + "\\src\\resources\\pgmTrainer";

        //set system parameters
        SizeX = 120;
        SizeY = 120;
        xDiv = 20;
        yDiv = 20;
        BlockWidth = SizeX / xDiv;
        BlockHeight = SizeY / yDiv;

        FaceTemplate = new int[xDiv][yDiv];
        Faces = new int[xDiv][yDiv][MaxFaces];
        LaplacianFaces = new int[xDiv][yDiv][MaxFaces];
        FaceFileNames = new String[MaxFaces];
        //NumFaces = pgmsTrain.size();
        NumFaces = 0;
        MaxFaceIndex = pgmsTrain.size();

        for (FileTrain ft : pgmsTrain) {

            PGM pgm1 = new PGM();
            NumFaces++;
            pgm1.setFilePath(ft.getPgmImagePath());
            pgm1.readImage();

            for (xBase = 0; xBase <= xDiv - 1; xBase++) {
                for (yBase = 0; yBase <= yDiv - 1; yBase++) {
                    StartX = xBase * BlockWidth;
                    StartY = yBase * BlockHeight;
                    xLow = StartX;
                    xHigh = StartX + BlockWidth - 1;
                    yLow = StartY;
                    yHigh = StartY + BlockHeight - 1;

                    CellSum = 0;
                    for (xSub = xLow; xSub <= xHigh; xSub++) {
                        for (ySub = yLow; ySub <= yHigh; ySub++) {
                            GrayLevel = pgm1.getPixel(xSub, ySub);
                            CellSum += GrayLevel;
                        }
                    }
                    CellAvg = CellSum / (BlockWidth * BlockHeight);
                    Faces[xBase][yBase][NumFaces] = CellAvg;

                }

            }
        }

        for (xBase = 0; xBase <= xDiv - 1; xBase++) {
            for (yBase = 0; yBase <= yDiv - 1; yBase++) {
                CellSum = 0;
                for (i = 1; i <= NumFaces; i++) {
                    CellSum += Faces[xBase][yBase][i];
                }
                CellAvg = CellSum / NumFaces;
                FaceTemplate[xBase][yBase] = CellAvg;
            }
        }

        for (xBase = 0; xBase <= xDiv - 1; xBase++) {
            for (yBase = 0; yBase <= yDiv - 1; yBase++) {
                for (i = 1; i <= NumFaces; i++) {

                    LaplacianFaces[xBase][yBase][i] = Faces[xBase][yBase][i] - FaceTemplate[xBase][yBase];

                }
            }
        }

        System.out.println("Treinamento concluido com sucesso");

        return true;
    }

}
