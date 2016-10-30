/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.awt.Point;
import java.util.Vector;
import jplay.GameObject;
import jplay.Keyboard;
import jplay.Scene;
import jplay.Sprite;
import jplay.TileInfo;
import jplay.URL;
import jplay.Window;

/**
 *Classe que representa qualquer personagem do jogo
 * @author Marcos
 */
public class Personagem extends Sprite {

    protected int direcao = 3;
    public boolean movendo = false;
    private Controle controle = new Controle();
    public double velocidade = 1;
    public double energia = 1000;

    public Personagem(String fileName, int numFrames) {
        super(fileName, numFrames);
    }

    /**
     * Controle de caminho percorrível pelo personagem
     *
     * @param cena
     */
    public void caminho(Scene cena) {
        Point min = new Point((int) this.x, (int) this.y);
        Point max = new Point((int) this.x + this.width, (int) this.y + this.height);

        Vector<?> tiles = cena.getTilesFromPosition(min, max);

        for (int i = 0; i < tiles.size(); i++) {

            TileInfo tile = (TileInfo) tiles.elementAt(i);

            //Se estiver colidindo com um tile fora dos tiles percorriveis, reposiciona o personagem
            if (controle.colisao(this, tile)) {

                if (colisaoVertical(this, tile)) {
                    //Testa colisão Vertical com tiles acima do personagem
                    if (tile.y + tile.height - 2 < this.y) {
                        this.y = tile.y + tile.height;
                        //Testa colisão Vertical com tiles abaixo do personagem
                    } else if (tile.y > this.y + this.height - 2) {
                        this.y = tile.y - this.height;

                    }

                }

                if (colisaoHorizontal(this, tile)) {

                    //Testa colisão Horizontal com tiles a esquerda do personagem
                    if (tile.x > this.x + this.width - 2) {
                        this.x = tile.x - this.width;
                        //Colisão Horizontal com tiles a Direita do personagem
                    } else {
                        this.x = tile.x + this.width - 8;
                    }
                }

            }

        }
    }

    private boolean colisaoVertical(GameObject obj, GameObject obj2) {

        if (obj2.x + obj2.width <= obj.x) {
            return false;
        }
        if (obj.x + obj.width <= obj2.x) {
            return false;
        }

        return true;

    }

    private boolean colisaoHorizontal(GameObject obj, GameObject obj2) {

        if (obj2.y + obj2.height <= obj.y) {
            return false;
        }

        if (obj.y + obj.height <= obj2.y) {
            return false;
        }

        return true;

    }

}
