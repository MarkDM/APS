/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.util.ArrayList;
import java.util.Random;
import jplay.Scene;
import jplay.Sprite;
import jplay.TileInfo;
import jplay.URL;
import jplay.Window;

/**
 *
 * @author Marcos
 */
public class Lixo extends ObjetoJogo {
    
    public boolean sumiu = false;

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

    public void flutuar(Scene cena) {
        Random r = new Random();
        TileInfo tile = cena.getTile(30, r.nextInt(25));
        moveTo(tile.x, tile.y, velocidade);
    }

    public void sumir() {
        this.x = 10_000;
        this.velocidade = 0;
        
    }

    void flutuar(int x, int y) {
        moveTo(x, y, velocidade);
    }

}
