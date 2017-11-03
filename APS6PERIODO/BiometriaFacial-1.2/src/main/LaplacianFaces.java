/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author marcu
 */
public abstract class LaplacianFaces {

    public static int NumFaces;

    public static int FaceTemplate[][], Faces[][][], LaplacianFaces[][][];
    public static String FaceFileNames[];
    public static double MinLaplacianDiff;

    public static double DifferenceThreshold = 7500;
    public static int MaxFaceIndex;
    public static int MaxFaces = 100, xBase, yBase, xSub, ySub, xLow, xHigh,
            yLow, yHigh, GrayLevel, CellSum, CellAvg, i, j, xDiv, yDiv, BlockWidth, BlockHeight, StartX, StartY, SizeX, SizeY;

}
