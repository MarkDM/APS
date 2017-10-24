/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marcu
 */
public class FileTrain {

    private String identificador;
    private String pgmImagePath;
    private BufferedImage image;
    private double diffLaplacian;

    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public double getDiffLaplacian() {
        return diffLaplacian;
    }

    public void setDiffLaplacian(double diffLaplacian) {
        this.diffLaplacian = diffLaplacian;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getPgmImagePath() {
        return pgmImagePath;
    }

    public void setPgmImagePath(String pgmImagePath) {
        this.pgmImagePath = pgmImagePath;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
}
