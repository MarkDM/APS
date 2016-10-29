/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import jplay.Keyboard;
import jplay.Scene;
import jplay.URL;
import jplay.Window;

/**
 *
 * @author Marcos
 */
public class Cenario1 {

    private Window janela;
    private Scene cena;
    private Jogador jogador;
    private Keyboard teclado;

    public Cenario1(Window janela) {
        this.janela = janela;
        cena = new Scene();
        cena.loadFromFile(URL.scenario("Cenario1.scn"));
        teclado = janela.getKeyboard();
        jogador = new Jogador(640,350);
        run();
    }

    private void run() {
        while (true) {
            cena.draw();
            jogador.draw();
            jogador.mover(janela);
            janela.update();
            
        }
    }

}
