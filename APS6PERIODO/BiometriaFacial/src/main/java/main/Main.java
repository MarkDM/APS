package main;

import main.App;

public class Main {

    public static void main(String[] args) {
        App app = new App();

        try {
            app.testTraining();
        } catch (Exception e) {
            System.out.println("Erro:" + e.getMessage() + " - outros detalhes: " + app.mensagemErro);
        }

    }

}
