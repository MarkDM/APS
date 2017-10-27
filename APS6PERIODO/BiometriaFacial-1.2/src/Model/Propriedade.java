/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author marcu
 */
public class Propriedade {

    private int id;
    private String descricao;
    private String proprietario;
    private String agrotoxicos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public String getAgrotoxicos() {
        return agrotoxicos;
    }

    public void setAgrotoxicos(String agrotoxicos) {
        this.agrotoxicos = agrotoxicos;
    }

}
