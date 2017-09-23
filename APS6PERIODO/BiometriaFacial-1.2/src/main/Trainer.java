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
        String pathFiles = localPath + "\\src\\resources\\pgmTrainer";

        int xBase, yBase, xSub, ySub;
        int xLow, xHigh, yLow, yHigh;
        int GrayLevel;
        int CellSum, CellAvg;
        int i, j;
        int xDiv, yDiv;
        int BlockWidth, BlockHeight;
        int StartX, StartY;
        int SizeX, SizeY;

        //set system parameters
        SizeX = 120;
        SizeY = 120;
        xDiv = 30;
        yDiv = 30;
        BlockWidth = SizeX / xDiv;
        BlockHeight = SizeY / yDiv;

        FaceTemplate = new int[xDiv][yDiv];
        Faces = new int[xDiv][yDiv][MaxFaces];
        LaplacianFaces = new int[xDiv][yDiv][MaxFaces];
        FaceFileNames = new String[MaxFaces];
        NumFaces = 0;
        MaxFaceIndex = pgmsTrain.size();

        for (FileTrain ft : pgmsTrain) {

            PGM pgm1 = new PGM();
            NumFaces++;

            //FaceFileNames[NumFaces] = "src\\images\\train\\Marcos" + i + (char) j + ".pgm";
            //FaceFileNames[NumFaces] = ft.getPgmImagePath();
            //PGM_ImageFilter imgFilter = new PGM_ImageFilter();
            // imgFilter.set_inFilePath(FaceFileNames[NumFaces]);
            //imgFilter.set_inFilePath(ft.getPgmImagePath());
            //imgFilter.set_outFilePath(pathFiles + "\\temp" + NumFaces + ".pgm");
            //imgFilter.resize(SizeX, SizeY);
            //pgm1.setFilePath(pathFiles + "\\temp" + NumFaces + ".pgm");
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
                            CellSum = CellSum + GrayLevel;
                        }
                    }
                    CellAvg = CellSum / (BlockWidth * BlockHeight);
                    Faces[xBase][yBase][NumFaces] = CellAvg;
                }
            }

            for (xBase = 0; xBase <= xDiv - 1; xBase++) {
                for (yBase = 0; yBase <= yDiv - 1; yBase++) {
                    CellSum = 0;
                    for (i = 1; i <= NumFaces; i++) {
                        CellSum = CellSum + Faces[xBase][yBase][i];
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

//            PGM pgm2 = new PGM();
//            pgm2.setFilePath(pathFiles + "\\template" + NumFaces + ".pgm");
//            pgm2.setType("P5");
//            pgm2.setComment("");
//            pgm2.setDimension(SizeX, SizeY);
//            pgm2.setMaxGray(255);
//
//            for (xBase = 0; xBase <= xDiv - 1; xBase++) {
//                for (yBase = 0; yBase <= yDiv - 1; yBase++) {
//                    StartX = xBase * BlockWidth;
//                    StartY = yBase * BlockHeight;
//                    xLow = StartX;
//                    xHigh = StartX + BlockWidth - 1;
//                    yLow = StartY;
//                    yHigh = StartY + BlockHeight - 1;
//
//                    for (xSub = xLow; xSub <= xHigh; xSub++) {
//                        for (ySub = yLow; ySub <= yHigh; ySub++) {
//                            GrayLevel = FaceTemplate[xBase][yBase];
//                            pgm2.setPixel(xSub, ySub, GrayLevel);
//                        }
//                    }
//                }
//            }
//            pgm2.writeImage();
        }

        System.out.println("Treinamento concluido com sucesso");

        return true;
    }

}
