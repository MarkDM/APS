/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import javafx.scene.input.DataFormat;
import jplay.URL;

/**
 *
 * @author Marcos
 */
public class Npc extends Personagem {

    public Npc(int x, int y) {
        super(URL.sprite("zumbi.png"), 16);
        this.x = x;
        this.y = y;
        this.velocidade = 0.3;
    }

    public void perseguir(double x, double y) {
        if (this.x > x && this.y <= y + 50 && this.y >= y - 50) {
            moveTo(x, y, velocidade);

            if (direcao != 1) {
                setSequence(5, 8);
                direcao = 1;
            }

            movendo = true;
        } else if (this.x < x && this.y <= y + 50 && this.y >= -50) {
            moveTo(x, y, velocidade);
            if (direcao != 2) {
                setSequence(9, 12);
                direcao = 2;
            }
            movendo = true;
        } else if (this.y > y) {
            moveTo(x, y, velocidade);
            if (direcao != 4) {
                setSequence(13, 16);
                direcao = 4;
            }
            movendo = true;
        } else if (this.y < y) {
            moveTo(x, y, velocidade);

            if (direcao != 5) {
                setSequence(1, 4);
                direcao = 5;
            }
            movendo = true;
        }
        
        if (movendo) {
            update();
            movendo = false;
        }
    }
}
