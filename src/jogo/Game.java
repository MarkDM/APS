/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import javax.swing.JOptionPane;
import jplay.GameImage;
import jplay.Keyboard;
import jplay.URL;
import jplay.Window;

/**
 *
 * @author marcos
 */
public class Game {

    public static void rodar(Window janela) {
        GameImage plano = new GameImage(URL.sprite("fundo.jpg"));
        Keyboard teclado = janela.getKeyboard();

        while (true) {
            plano.draw();
            janela.update();
            //Evita processamento desnecessário
            janela.delay(20);

            if (teclado.keyDown(Keyboard.ENTER_KEY)) {
                Som.play("Mars.wav");
                new Cenario1(janela);
            } else if (teclado.keyDown(Keyboard.ESCAPE_KEY)) {

                int option = JOptionPane.showConfirmDialog(null, "Deseja sair do Jogo", "Sair?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (option == 0) {
                    
                    System.exit(0);
                }

            }
        }
    }
}
