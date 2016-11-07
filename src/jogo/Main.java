package jogo;

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
       Som.play("Nothing_Else_Matters.mid");

        Window janela = new Window(800, 600);
     
        Game.rodar(janela);
    }
}
