/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import jplay.GameObject;
import jplay.TileInfo;

/**
 * Verifica colisão entre um objeto e um tile
 *
 * @author marcos
 */
public class Controle {

    /**
     * Numero do tile do arquivo .scn
     */
    private int tileId = 7;

    public boolean colisao(GameObject obj, TileInfo tile) {

        if ((tile.id >= tileId) && obj.collided(tile)) {
            return true;
        }

        return false;
    }

    public int getTileId() {
        return tileId;
    }

    public void setTileId(int tileId) {
        this.tileId = tileId;
    }

}
