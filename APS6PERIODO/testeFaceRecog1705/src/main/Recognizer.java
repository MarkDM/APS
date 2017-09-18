/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javax.swing.JOptionPane;

/**
 *
 * @author marcu
 */
public class Recognizer extends LaplacianFaces {

    public void recogize(String pgmRecognize) {
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
        SizeX = 80;
        SizeY = 80;
        xDiv = 20;
        yDiv = 20;
        BlockWidth = SizeX / xDiv;
        BlockHeight = SizeY / yDiv;

        int TestFace[][] = new int[xDiv][yDiv];
        int TestLaplacianFace[][] = new int[xDiv][yDiv];
        int LaplacianDiff;
        int MinLaplacianIndex;
        double TotalLaplacianDiff, MinLaplacianDiff;

        //addResultText("\nTesting...");
        PGM pgm1 = new PGM();
        pgm1.setFilePath(pgmRecognize);
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

        PGM pgm2 = new PGM();
        pgm2.setFilePath("diff.pgm");
        pgm2.setType("P5");
        pgm2.setComment("");
        pgm2.setDimension(SizeX, SizeY);
        pgm2.setMaxGray(255);

        for (xBase = 0; xBase <= xDiv - 1; xBase++) {
            for (yBase = 0; yBase <= yDiv - 1; yBase++) {
                StartX = xBase * BlockWidth;
                StartY = yBase * BlockHeight;
                xLow = StartX;
                xHigh = StartX + BlockWidth - 1;
                yLow = StartY;
                yHigh = StartY + BlockHeight - 1;

                for (xSub = xLow; xSub <= xHigh; xSub++) {
                    for (ySub = yLow; ySub <= yHigh; ySub++) {
                        GrayLevel = TestFace[xBase][yBase];
                        pgm2.setPixel(xSub, ySub, GrayLevel);
                    }
                }
            }
        }

        for (xBase = 0; xBase <= xDiv - 1; xBase++) {
            for (yBase = 0; yBase <= yDiv - 1; yBase++) {

//                try {
                TestLaplacianFace[xBase][yBase] = TestFace[xBase][yBase] - FaceTemplate[xBase][yBase];
//                } catch (Exception e) {
//                    System.out.println(e.getMessage() + "\nxBase: " + xBase + "\nyBase:" + yBase);
//                }
            }
        }

        MinLaplacianDiff = 2147483647; //2^32
        MinLaplacianIndex = -1;
        for (i = 1; i <= NumFaces; i++) {
            TotalLaplacianDiff = 0;
            for (xBase = 0; xBase <= xDiv - 1; xBase++) {
                for (yBase = 0; yBase <= yDiv - 1; yBase++) {
                    TotalLaplacianDiff = TotalLaplacianDiff + java.lang.Math.abs(TestLaplacianFace[xBase][yBase] - LaplacianFaces[xBase][yBase][i]);
                }
            }
            if (MinLaplacianDiff > TotalLaplacianDiff) {
                MinLaplacianDiff = TotalLaplacianDiff;
                MinLaplacianIndex = i;
            }
        }

        pgm2.writeImage();

        if (MinLaplacianDiff > DifferenceThreshold) {
            //frmImage2.getGraphics().clearRect(0, 0, 200, 150);
            //addResultText("done.");
            //addResultText("\n\nNot Matched.");
            JOptionPane.showMessageDialog(null, "Not Matched.");

        } else {
            //PGM pgmMatched = new PGM();
            //pgmMatched.setFilePath(FaceFileNames[MinLaplacianIndex]);
            //pgmMatched.readImage();
            // pgmMatched.setFilePath("matched.pgm");
            // pgmMatched.writeImage();
            // drawImage(frmImage2.getGraphics(), "matched.pgm");
            //addResultText("done.");
            //addResultText("\n\nMatched: " + FaceFileNames[MinLaplacianIndex]);
            JOptionPane.showMessageDialog(null, "Matched: " + FaceFileNames[MinLaplacianIndex]);
        }
    }

}
