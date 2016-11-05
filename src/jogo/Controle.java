/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.util.ArrayList;
import jplay.GameObject;
import jplay.TileInfo;

/**
 * Verifica colisão com os tiles não percorríveis
 *
 * @author marcos
 */
public class Controle {

    /**
     * Numero do tile do arquivo .scn
     */
    private ArrayList<Integer> tiles = new ArrayList<>();

    public boolean colisao(GameObject obj, TileInfo tile) {

        if (tiles.contains(tile.id) && obj.collided(tile)) {
            return true;
        }

        return false;
    }

    public ArrayList<Integer> getTiles() {
        return tiles;
    }

    public void setTiles(ArrayList<Integer> tiles) {
        this.tiles = tiles;
    }

}
