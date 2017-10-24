/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.TipoUsuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marcu
 */
public class TipoUsuarioDAO extends SQLiteJDBC {

    public TipoUsuarioDAO() {
        super();
    }

    public List<TipoUsuario> obterTiposUsuario() throws SQLException {
        ResultSet rs = execQuery("SELECT TUS_ID,TUS_DESCRICAO,TUS_NIVEL_ACESSO FROM TIPO_USUARIO;");
        List<TipoUsuario> lst = new ArrayList<>();

        while (rs.next()) {
            TipoUsuario t = new TipoUsuario();
            t.setId(rs.getInt("TUS_ID"));
            t.setDescricao(rs.getString("TUS_DESCRICAO"));
            t.setNivelAcesso(rs.getInt("TUS_NIVEL_ACESSO"));
            lst.add(t);
        }

        rs.close();
        super.closeConnection();
        return lst;

    }

    public TipoUsuario getById(int id) throws SQLException {
        ResultSet rs = execQuery("SELECT * FROM TIPO_USUARIO WHERE TUS_ID = " + id);

        TipoUsuario t = new TipoUsuario();

        while (rs.next()) {
            t.setDescricao(rs.getString("TUS_DESCRICAO"));
            t.setId(rs.getInt("TUS_ID"));
            t.setNivelAcesso(rs.getInt("TUS_NIVEL_ACESSO"));
        }

        return t;
    }

}
