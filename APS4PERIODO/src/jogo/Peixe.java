/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import jplay.Scene;
import jplay.Sprite;
import jplay.TileInfo;
import jplay.URL;

/**
 *
 * @author Marcos
 */
public class Peixe extends ObjetoJogo {

    public boolean morto = false;

    public Peixe(String fileName, int numFrames, double x, double y) {
        super(URL.sprite(fileName), numFrames);
        this.x = x;
        this.y = y;
        this.setTotalDuration(2000);
    }

    public void morrer() {
        this.x = 100000;
        this.morto = true;

    }

}
