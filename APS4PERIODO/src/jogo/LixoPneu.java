/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.util.ArrayList;

/**
 *
 * @author Marcos
 */
public class LixoPneu extends Lixo {

    public LixoPneu(int x, int y, String sprite) {
        super(x, y, sprite);
        this.velocidade = 0.3;
        this.energia = 800;

  
    }

}
