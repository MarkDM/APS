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
 *
 * @author Marcos
 */
public class Jogador extends Sprite {

    private double velocidade = 0.3;
    private int direcao = 4;
    private Keyboard teclado;
    private boolean movendo = false;
    Controle controle = new Controle();

    public Jogador(int x, int y) {
        super(URL.sprite("jogador.png"), 20);
        this.x = x;
        this.y = y;
        this.setTotalDuration(2000);
    }

    public void mover(Window janela) {

        if (teclado == null) {
            teclado = janela.getKeyboard();
        }

        if (teclado.keyDown(Keyboard.LEFT_KEY)) {

            if (this.x > 0) {
                this.x -= velocidade;
            }
            if (direcao != 1) {
                setSequence(4, 8);
                direcao = 1;
            }
            movendo = true;

        } else if (teclado.keyDown(Keyboard.RIGHT_KEY)) {

            if (this.x < janela.getWidth() - 50) {
                this.x += velocidade;
            }
            if (direcao != 2) {
                setSequence(8, 12);
                direcao = 2;
            }
            movendo = true;

        } else if (teclado.keyDown(Keyboard.UP_KEY)) {

            if (this.y > 0) {
                this.y -= velocidade;
            }
            if (direcao != 4) {
                setSequence(12, 16);
                direcao = 4;
            }
            movendo = true;

        } else if (teclado.keyDown(Keyboard.DOWN_KEY)) {

            if (this.y < janela.getHeight() - 60) {
                this.y += velocidade;
            }
            if (direcao != 5) {
                setSequence(0, 4);
                direcao = 5;
            }
            movendo = true;

        }

        if (movendo) {
            update();
            movendo = false;
        }

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
            
            if (controle.colisao) {
                
            }
            
        }
    }

    private boolean colisaoVertical(GameObject obj, GameObject obj2) {

        if (obj.x + obj2.width <= obj.x) {

            return false;

        }
        if (obj.x + obj.width <= obj2.x) {
            return true;
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
