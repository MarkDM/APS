/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.util.LinkedList;
import jplay.Scene;
import jplay.Sound;
import jplay.URL;

/**
 *
 * @author Marcos
 */
public class ControleTiros {
    
    LinkedList<Tiro> tiros = new LinkedList<>();
    
    public void adicionaTiro(double x, double y, int caminho, Scene cena) {
        Tiro tiro = new Tiro(x, y, caminho);
        tiros.addFirst(tiro);
        cena.addOverlay(tiro);
        somDisparo();
    }
    
    public void run() {
        for (int i = 0; i < tiros.size(); i++) {
            Tiro tiro = tiros.removeFirst();
            tiro.mover();
            tiros.addLast(tiro);
        }
    }
    
    private void somDisparo() {
        new Sound(URL.audio("pistol.wav")).play();
    }
    
}
