package utils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author marcu
 *
 */
public class PGMConverter {

    private String mensagem;

    public String getMensagem() {
        return mensagem;
    }

    public Boolean write2pgm(String inputPath, String outputPath) {
        File img = new File(inputPath);

        if (img.isDirectory()) {
            mensagem = "Caminho informado é um diretório, arquivo esperado";
            return false;
        }

        if (!img.exists()) {
            mensagem = "Arquivo de entrada n�o encontrado";
            return false;
        }

        try {
            BufferedImage bi = ImageIO.read(img);
            return ImageIO.write(bi, "pnm", new File(outputPath));
        } catch (Exception e) {
            mensagem = "Erro ao salvar imagem em disco: " + e.getMessage();
        }

        return false;
    }

    public Boolean write2pgm(BufferedImage bi, String outputPath) {
        try {
            return ImageIO.write(bi, "pnm", new File(outputPath));

        } catch (Exception e) {
            mensagem = "Erro ao salvar imagem em disco: " + e.getMessage();
        }

        return false;
    }

}
