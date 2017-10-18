/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author marcu
 */
public class Recognizer extends LaplacianFaces {

    public FileTrain reconhecer(String pgm, List<FileTrain> listaDeTreinamento) {
        //set system parameters
        SizeX = 120;
        SizeY = 120;
        xDiv = 20;
        yDiv = 20;
        BlockWidth = SizeX / xDiv;
        BlockHeight = SizeY / yDiv;

        int TestFace[][] = new int[xDiv][yDiv];
        int TestLaplacianFace[][] = new int[xDiv][yDiv];
        int LaplacianDiff;
        int MinLaplacianIndex;
        double TotalLaplacianDiff;

        PGM pgm1 = new PGM();
        pgm1.setFilePath(pgm);
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
                TestFace[xBase][yBase] = CellAvg;
            }
        }

        for (xBase = 0; xBase <= xDiv - 1; xBase++) {
            for (yBase = 0; yBase <= yDiv - 1; yBase++) {
                TestLaplacianFace[xBase][yBase] = TestFace[xBase][yBase] - FaceTemplate[xBase][yBase];
            }
        }

        MinLaplacianDiff = 2147483647; //2^32
        MinLaplacianIndex = -1;
        FileTrain ftMatch = null;

        i = 1;
        for (FileTrain ft : listaDeTreinamento) {
            TotalLaplacianDiff = 0;
            for (xBase = 0; xBase <= xDiv - 1; xBase++) {
                for (yBase = 0; yBase <= yDiv - 1; yBase++) {
                    TotalLaplacianDiff += java.lang.Math.abs(TestLaplacianFace[xBase][yBase] - LaplacianFaces[xBase][yBase][i]);
                }
            }

            //ft.setDiffLaplacian(TotalLaplacianDiff);
            if (MinLaplacianDiff > TotalLaplacianDiff) {
                MinLaplacianDiff = TotalLaplacianDiff;
                ftMatch = ft;
                //MinLaplacianIndex = i;
            }

            i++;
        }

        if (MinLaplacianDiff < DifferenceThreshold) {

            System.out.println("Reconhecido");
            //System.out.println("Arquivo de Match:" + FaceFileNames[MinLaplacianIndex]);
            System.out.println("Arquivo de Match:" + ftMatch.getPgmImagePath());
            ftMatch.setDiffLaplacian(MinLaplacianDiff);
            System.out.println("Arquivo informado:" + pgm);
            System.out.println("Diferença:" + MinLaplacianDiff);
            //return FaceFileNames[MinLaplacianIndex];
            return ftMatch;

        } else {
            System.out.println("Não reconhecido");
            System.out.println("Arquivo informado:" + pgm);
            System.out.println("Diferença:" + MinLaplacianDiff);
        }

        return null;

    }

}
