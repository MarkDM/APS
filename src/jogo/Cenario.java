/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.awt.Point;
import java.util.Vector;
import jplay.GameObject;
import jplay.Scene;
import jplay.TileInfo;

/**
 *
 * @author Marcos
 */
public abstract class Cenario {

    protected boolean tileCollision(int value, Jogador jogador, Scene cena) {

        Point min = new Point((int) jogador.x, (int) jogador.y);
        Point max = new Point((int) (jogador.x + jogador.width), (int) (jogador.y + jogador.height));

        Vector<?> tiles = cena.getTilesFromPosition(min, max);

        for (int i = 0; i < tiles.size(); i++) {
            TileInfo tile = (TileInfo) tiles.elementAt(i);

            if (tileCollision(jogador, tile, value)) {
                return true;
            }

        }
        return false;
    }

    private boolean tileCollision(GameObject object, TileInfo tile, int value) {
        if ((tile.id == value) && object.collided(tile)) {
            return true;
        }
        return false;
    }

}
