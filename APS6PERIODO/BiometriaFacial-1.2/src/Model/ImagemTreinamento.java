/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.image.BufferedImage;

/**
 *
 * @author marcu
 */
public class ImagemTreinamento {

    private int id;
    private String caminho;
    private Usuario usuario;
    private String tipo;
    private BufferedImage imgBitMap;
    private double diffLaplacian;

    public double getDiffLaplacian() {
        return diffLaplacian;
    }

    public void setDiffLaplacian(double diffLaplacian) {
        this.diffLaplacian = diffLaplacian;
    }

    public BufferedImage getImgBitMap() {
        return imgBitMap;
    }

    public void setImgBitMap(BufferedImage img) {
        this.imgBitMap = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
