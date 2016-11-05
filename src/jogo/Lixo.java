/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.util.ArrayList;
import jplay.Scene;
import jplay.Sprite;
import jplay.URL;
import jplay.Window;

/**
 *
 * @author Marcos
 */
public class Lixo extends ObjetoJogo {

    public Lixo(int x, int y, String sprite) {
        super(URL.sprite(sprite), 1);
        this.x = x;
        this.y = y;
        this.velocidade = 0.3;
        this.setTotalDuration(2000);

        ArrayList<Integer> tilesBloqueio = new ArrayList<>();
        tilesBloqueio.add(1);
        tilesBloqueio.add(2);
        tilesBloqueio.add(17);

        this.controle.setTiles(tilesBloqueio);

    }

    public void flutuar(int x, int y) {
        moveTo(x, y, velocidade);
    }
    
    public void morrer(){
        
    }

    public void sumir() {
        this.x = 10_000;
        this.velocidade = 0;

    }

}
