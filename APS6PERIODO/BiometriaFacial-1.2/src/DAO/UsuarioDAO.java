/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Usuario;
import java.sql.Connection;
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

    public int inserirUsuario(Usuario u) {
        String sql = "INSERT INTO USUARIO (USR_NOME,USR_SENHA,USR_LOGIN,USR_TIPO_USUARIO_ID)\n"
                + "VALUES ('" + u.getNome() + "','" + new Utils().getMd5(u.getSenha()) + "','" + u.getLogin() + "'," + u.getTipoUsuario().getId() + ")";

        try {
            return execUpdateSQL(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

}
