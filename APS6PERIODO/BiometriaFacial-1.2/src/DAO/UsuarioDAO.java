/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Utils;

/**
 *
 * @author marcu
 */
public class UsuarioDAO extends SQLiteJDBC {

    public UsuarioDAO() {
        super();
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public void inserirUsuario(Usuario u) throws SQLException {
        String sql = "INSERT INTO USUARIO (USR_NOME,USR_SENHA,USR_LOGIN,USR_TIPO_USUARIO_ID)\n"
                + "VALUES ('" + u.getNome() + "','" + new Utils().getMd5(u.getSenha()) + "','" + u.getLogin() + "'," + u.getTipoUsuario().getId() + ")";

        try {
            execUpdateSQL(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.closeConnection();
    }

    public void alterarUsuario(Usuario u, Boolean updatePass) throws SQLException {
        String sql = "UPDATE USUARIO SET USR_NOME = '" + u.getNome()
                + "'\n,USR_LOGIN = '" + u.getLogin();

        if (updatePass) {
            sql += "'\n,USR_SENHA = '" + new Utils().getMd5(u.getSenha());
        }

        sql += "'\n,USR_TIPO_USUARIO_ID = " + u.getTipoUsuario().getId()
                + "\nWHERE USR_ID = " + u.getId();

        execUpdateSQL(sql);
    }

    public void deletarUsuario(int idUsuario) throws SQLException {

        try {
            String sql = "DELETE FROM USUARIO WHERE USR_ID = " + idUsuario;
            execUpdateSQL(sql);
            sql = "DELETE FROM IMAGENS_TREINAMENTO WHERE USR_ID = " + idUsuario;
            execUpdateSQL(sql);
            super.closeConnection();

        } catch (Exception e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public Usuario getUsuarioById(int idUsuario) {
        try {
            ResultSet rs = execQuery("SELECT * FROM USUARIO WHERE USR_ID = " + idUsuario);

            Usuario u = new Usuario();

            while (rs.next()) {
                u.setId(rs.getInt("USR_ID"));
                u.setLogin(rs.getString("USR_LOGIN"));
                u.setNome(rs.getString("USR_NOME"));
                u.setSenha(rs.getString("USR_SENHA"));
                u.setTipoUsuario(new TipoUsuarioDAO().getById(rs.getInt("USR_TIPO_USUARIO_ID")));
            }
            rs.close();
            super.closeConnection();

            return u;

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public Usuario getUsuarioByLogin(String login) {
        try {
            ResultSet rs = execQuery("SELECT * FROM USUARIO WHERE USR_LOGIN = '" + login + "'");

            Usuario u = null;

            while (rs.next()) {
                u = new Usuario();
                u.setId(rs.getInt("USR_ID"));
                u.setLogin(rs.getString("USR_LOGIN"));
                u.setNome(rs.getString("USR_NOME"));
                u.setSenha(rs.getString("USR_SENHA"));
                u.setTipoUsuario(new TipoUsuarioDAO().getById(rs.getInt("USR_TIPO_USUARIO_ID")));
            }
            rs.close();
            super.closeConnection();

            return u;

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public Usuario getByOffset(int offset) throws SQLException {
        ResultSet rs = execQuery("SELECT * FROM USUARIO LIMIT 1 OFFSET " + offset);

        Usuario u = null;

        while (rs.next()) {
            u = new Usuario();
            u.setId(rs.getInt("USR_ID"));
            u.setLogin(rs.getString("USR_LOGIN"));
            u.setNome(rs.getString("USR_NOME"));
            u.setSenha(rs.getString("USR_SENHA"));
            u.setTipoUsuario(new TipoUsuarioDAO().getById(rs.getInt("USR_TIPO_USUARIO_ID")));
        }

        return u;
    }

    public int getCount() {
        try {
            ResultSet rs = execQuery("SELECT COUNT(1) as qtd FROM USUARIO");

            int qtd = 0;

            while (rs.next()) {
                qtd = rs.getInt("qtd");
            }

            super.closeConnection();
            return qtd;

        } catch (Exception e) {
        }

        return 0;
    }

    public int getLastId() throws SQLException {
        ResultSet rs = execQuery("SELECT MAX(USR_ID) as id from USUARIO");
        int id = 0;
        while (rs.next()) {
            id = rs.getInt("id");
        }
        return id;
    }

}
