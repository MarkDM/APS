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

    public static int FaceTemplate[][];
    public static int Faces[][][];
    public static int LaplacianFaces[][][];
    public static String FaceFileNames[];
    public static double MinLaplacianDiff;

    public static double DifferenceThreshold = 12100.0;
    public static int MaxFaceIndex;
    public static int MaxFaces = 100;

}
