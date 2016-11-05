package jogo;

import javax.swing.JOptionPane;
import jplay.GameImage;
import jplay.Keyboard;
import jplay.URL;
import jplay.Window;

/**
 *
 * @author Marcos
 */
public class Main {

    public static void main(String[] args) {
        //Som.play("Enter_Sandman.mid");
       
        Window janela = new Window(800, 600);
        GameImage plano = new GameImage(URL.sprite("menu.png"));
        Keyboard teclado = janela.getKeyboard();

        while (true) {
            plano.draw();
            janela.update();
            //Evita processamento desnecessário
            janela.delay(20);
            
            
            if (teclado.keyDown(Keyboard.ENTER_KEY)) {
               // Som.play("Mars.wav");
                new Cenario1(janela);
            }

        }
    }
    

}
