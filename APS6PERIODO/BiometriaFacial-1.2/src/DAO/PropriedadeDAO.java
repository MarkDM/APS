/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Propriedade;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author marcu
 */
public class PropriedadeDAO extends SQLiteJDBC {

    public PropriedadeDAO() {
        super();
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public Propriedade getByOffset(int offset) throws SQLException {
        String sql = "SELECT * FROM PROPRIEDADE\n"
                + "LIMIT 1 OFFSET " + offset;

        ResultSet rs = execQuery(sql);
        Propriedade p = null;
        while (rs.next()) {
            p = new Propriedade();
            p.setId(rs.getInt("PRP_ID"));
            p.setDescricao(rs.getString("PRP_DESCRICAO"));
            p.setProprietario(rs.getString("PRP_PROPRIETARIO"));
            p.setAgrotoxicos(rs.getString("PRP_AGROTOXICOS"));

        }

        return p;

    }

    public void inserir(Propriedade p) throws SQLException {
        String sql = "INSERT INTO PROPRIEDADE (PRP_DESCRICAO,PRP_PROPRIETARIO,PRP_AGROTOXICOS) \n"
                + "VALUES ('" + p.getDescricao() + "','" + p.getProprietario() + "','" + p.getAgrotoxicos() + "')";

        execUpdateSQL(sql);

    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM PROPRIEDADE WHERE PRP_ID = " + id;

        execUpdateSQL(sql);
    }

    public void alterar(Propriedade p) throws SQLException {
        String sql = "UPDATE PROPRIEDADE\n"
                + "SET PRP_DESCRICAO = '" + p.getDescricao() + "',\n"
                + "PRP_PROPRIETARIO = '" + p.getProprietario() + "',\n"
                + "PRP_AGROTOXICOS = '" + p.getAgrotoxicos() + "'\n"
                + "WHERE PRP_ID = " + p.getId();
        execUpdateSQL(sql);
    }

    public int getLastId() throws SQLException {
        String sql = "SELECT MAX(PRP_ID) AS lastId FROM PROPRIEDADE";

        ResultSet rs = execQuery(sql);
        int lastId = 0;

        while (rs.next()) {
            lastId = rs.getInt("lastId");
        }

        return lastId;
    }

    public int getCount() throws SQLException {
        String sql = "SELECT COUNT(1) AS QTD FROM PROPRIEDADE";

        int qtd = 0;

        ResultSet rs = execQuery(sql);

        while (rs.next()) {
            qtd = rs.getInt("QTD");
        }

        return qtd;
    }

}
