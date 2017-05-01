/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import jplay.Sound;
import jplay.URL;

/**
 *Classe para Reprodução de Sons no jogo
 * @author Marcos
 */
public class Som {

    private static Sound musica;

    /**
     * 
     * @param audio 
     */
    public static void play(String audio) {
        stop();
        //musica = new Sound(URL.audio(audio));
        musica = new Sound("C:/Users/Marcos/Documents/GitHub/APS4PERIODO/APS/src/recursos/audio/" + audio);
        Som.musica.play();
        Som.musica.setRepeat(true);
    }
    
    public static void stop(){
        if (Som.musica != null) {
            musica.stop();
        }
    }

}
